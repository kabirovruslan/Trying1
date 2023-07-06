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

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background, home;
	Button goToBuyHome;
	Sprite sprite;
	int x,y;
	boolean touchHome,touchSquare,pressedHome;



	public static int SRC_WIDTH, SRC_HEIGHT;

	@Override
	public void create() {
		batch = new SpriteBatch();
		background = new Texture("background.jpg");
		home = new Texture("home.png");

		goToBuyHome = new Button();

		touchHome = false;
		touchSquare = false;
		pressedHome = false;

		SRC_WIDTH = Gdx.graphics.getWidth();
		SRC_HEIGHT = Gdx.graphics.getHeight();

		x = SRC_WIDTH - 240;
		y = 0;
	}

	@Override
	public void render() {
		batch.begin();
		batch.draw(background, 0, 0, SRC_WIDTH, SRC_HEIGHT);
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
