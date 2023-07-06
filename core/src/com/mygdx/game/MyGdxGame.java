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
	boolean touchHome,touchSquare;



	public static int SRC_WIDTH, SRC_HEIGHT;

	@Override
	public void create() {
		batch = new SpriteBatch();
		background = new Texture("background.jpg");
		home = new Texture("home.png");

		goToBuyHome = new Button();

		touchHome = false;
		touchSquare = false;

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
				if (touchHome) {
					touchHome = false;
					touchSquare = true;
				}
					return true;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				if (screenX>x && screenX<x+240 &&screenY>SRC_HEIGHT-y-240 && screenY<SRC_HEIGHT-y){
					touchHome = true;
				}
				return true;
			}
		});
		if (Gdx.input.justTouched()&& touchSquare){
			x = Gdx.input.getX();
			y = SRC_HEIGHT - Gdx.input.getY();
			touchSquare = false;
		}
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
