package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture blue,red,background, home;
	Button goToBuyHome;
	Sprite sprite;
	int x,y;
	boolean touchHome,touchSquare,pressedHome;
	Square[][] mas;
	boolean [][] isEmpty;
	int time;

	public static int SRC_WIDTH, SRC_HEIGHT;

	@Override
	public void create() {
		batch = new SpriteBatch();
		blue = new Texture("blue.png");
		background = new Texture("background.png");
		home = new Texture("home.png");
		red = new Texture("red.png");

		goToBuyHome = new Button();

		touchHome = false;
		touchSquare = false;
		pressedHome = false;

		SRC_WIDTH = Gdx.graphics.getWidth();
		SRC_HEIGHT = Gdx.graphics.getHeight();

		x = SRC_WIDTH - 240;
		y = 0;

		mas = new Square[18][32];
		isEmpty = new boolean[18][32];
		for (int i = 0; i<18;i++){
			for(int j = 0;j<32;j++){
				mas[i][j] = new Square((SRC_WIDTH-1920)/2+j*60,(17-i)*60);
				isEmpty[i][j] = false;
			}
		}



	}

	@Override
	public void render() {
		batch.begin();
		for (int i = 0; i<18;i++) {
			for (int j = 0; j < 32; j++) {
				if (mas[i][j].isEmpty == isEmpty[i][j]){
					mas[i][j].isEmpty = false;
				}
			}
		}
		batch.draw(blue,0,0,SRC_WIDTH,SRC_HEIGHT);
		batch.draw(background,(SRC_WIDTH-1920)/2 , (SRC_HEIGHT-1080)/2, 1920, 1080);
		for (int i = 17; i >=0; i--) {
			for (int j = 0; j < 32; j++) {
				if ( !mas[i][j].isEmpty && (j%4==0) && (i+3)%4==0 ){
					batch.draw(home,(SRC_WIDTH-1920)/2+j*60,(17-i)*60);
					mas[i][j].isEmpty = true;
					mas[17-i][j].isEmpty = true;
					mas[i][j+1].isEmpty = true;
					mas[17-i][j+1].isEmpty = true;

					isEmpty[i][j] = true;
					isEmpty[17-i][j] = true;
					isEmpty[i][j+1] = true;
					isEmpty[17-i][j+1] = true;
				}
			}
			/*if (i==16) break;*/
		}


		Gdx.input.setInputProcessor(new InputAdapter(){
			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				if(pressedHome){
					x=screenX;
					y = SRC_HEIGHT - screenY;
					pressedHome = false;
				}

					return true;
			}
			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				if (screenX>x && screenX<x+240 &&screenY>SRC_HEIGHT-y-240 && screenY<SRC_HEIGHT-y){
					pressedHome = true;
				}
				return true;
			}
		});

		batch.draw(home, x, y);
		batch.end();
	}

		@Override
		public void dispose () {
			batch.dispose();
			background.dispose();
			home.dispose();
		}

}
