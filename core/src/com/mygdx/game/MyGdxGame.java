package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.Collections;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.sun.tools.javac.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture blue,red,background,background2, home;
	Button goToBuyHome;
	Sprite sprite;
	int x,y;
	boolean touchHome,touchSquare,pressedHome,draggedHome;

	Square[][] mas;
	boolean [][] isEmpty;
	Map<String,String> map = new HashMap<>();
	Preferences prefs;
	ArrayList<Home> list;

	public static int SRC_WIDTH, SRC_HEIGHT;

	@Override
	public void create() {
		batch = new SpriteBatch();
		blue = new Texture("blue.png");
		background = new Texture("background.png");
		background2 = new Texture("background2.png");
		home = new Texture("home.png");
		red = new Texture("red.png");

		goToBuyHome = new Button();

		touchHome = false;
		touchSquare = false;
		pressedHome = false;
		draggedHome = true;

		SRC_WIDTH = Gdx.graphics.getWidth();
		SRC_HEIGHT = Gdx.graphics.getHeight();

		x = 0;
		y = 0;

		mas = new Square[18][32];
		isEmpty = new boolean[18][32];
		list = new ArrayList<>();

		prefs = Gdx.app.getPreferences("My Preferences");

		for (int i = 0; i<18;i++){
			for(int j = 0;j<32;j++){
				mas[i][j] = new Square(i,j,(SRC_WIDTH-1920)/2+j*60,(17-i)*60);
				isEmpty[i][j] = false;
				if (i==7||i==8||i>=13){
					mas[i][j].isEmpty = true;
					isEmpty[i][j] = true;
				}
				if (j==10||j==11||j==22||j==23){
					mas[i][j].isEmpty = true;
					isEmpty[i][j] = true;
				}
				prefs.putString(i+" "+j,mas[i][j].toString());
				prefs.flush();
			}
		}
	}

	@Override
	public void render() {
		batch.begin();
		for (int i = 17; i >=0; i--) {
			for (int j = 0;j<32;j++){
				if (fromString(4,prefs.getString(i+" "+j))==1){
					mas[i][j].isEmpty=true;
					isEmpty[i][j] = true;
				}
			}
		}
		for (int i = 0; i<18;i++) {
			for (int j = 0; j < 32; j++) {
				if (mas[i][j].isEmpty && isEmpty[i][j]){
					isEmpty[i][j] = false;
				}
			}
		}


		batch.draw(blue,0,0,SRC_WIDTH,SRC_HEIGHT);
		batch.draw(background,(SRC_WIDTH-1920)/2 , (SRC_HEIGHT-1080)/2, 1920, 1080);

		Gdx.input.setInputProcessor(new InputAdapter(){
			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				if(pressedHome) {
					if (screenX > (SRC_WIDTH - 1920) / 2 && screenX < (SRC_WIDTH - 1920) / 2 + 1920) {
						x = screenX;
						y = SRC_HEIGHT - screenY;
						pressedHome = false;
						draggedHome = true;
						if (!isEmpty[(y - (SRC_HEIGHT - 1080) / 2) / 60][(x - (SRC_WIDTH - 1920) / 2) / 60]) {
							x = (x - (SRC_WIDTH - 1920) / 2) / 60;
							y = (y - (SRC_HEIGHT - 1080) / 2) / 60;
							fillArray(1,y,x);
							fillArray(2,y,x);

							list.add(new Home(home,mas[y][x]));

							if (y <= 5 || y == 9 || y == 10 || x == 10 || x == 11 || x == 22 || x == 23||mas[17-y][x].isEmpty) {
								x = 0;
								y = 0;
							} else {
								x = (SRC_WIDTH - 1920) / 2 + x * 60;
								y = (SRC_HEIGHT - 1080) / 2 + y * 60;

							}
						}
					}
				}
					return true;
			}
			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				if (screenX>x && screenX<x+240 &&screenY>SRC_HEIGHT-y-240 && screenY<SRC_HEIGHT-y){
					pressedHome = true;
					draggedHome = true;
					x=screenX;
					y=SRC_WIDTH-screenY;

				}
				return true;
			}
		});
		batch.draw(home,x,y);

		batch.end();
	}

		@Override
		public void dispose () {
			batch.dispose();
			background.dispose();
			home.dispose();
		}

		public void fillArray(int flag, int i, int j){
			if (flag==1){
				mas[i][j].isEmpty = true;
				mas[i-1][j].isEmpty = true;
				mas[i][j+1].isEmpty = true;
				mas[i-1][j+1].isEmpty = true;

				prefs.putString(i+" "+j,mas[i][j].toString());
				prefs.putString((i-1)+" "+j,mas[i-1][j].toString());
				prefs.putString(i+" "+(j+1),mas[i][j+1].toString());
				prefs.putString((i-1)+" "+(j+1),mas[i-1][j+1].toString());
				prefs.flush();

			}
			if (flag == 2){
				isEmpty[i][j] = true;
				isEmpty[i-1][j] = true;
				isEmpty[i][j+1] = true;
				isEmpty[i-1][j+1] = true;
			}
		}

		public int fromString(int flag,String str){
		String[] arr = str.split("/");
			if (flag==1){
				return Integer.parseInt(arr[0]);
			}
			if (flag==2){
				return Integer.parseInt(arr[1]);
			}
			if (flag==3){
				return Integer.parseInt(arr[2]);
			}
			if (flag==4){
				return Integer.parseInt(arr[3]);
			}
			if (flag==5){
				if (Boolean.parseBoolean(arr[4])==false){
					return 0;
				}
				if (Boolean.parseBoolean(arr[4])==true){
					return 1;
				}

			}
			return -1;
		}
}
