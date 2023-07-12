package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class AddHomeButton extends Button {
    public static final float WIDTH = 160;
    public static final float HEIGHT = 160;

    MyGdxGame mgg;
    int x,y;
    Texture texture;
    boolean isBuilded;

    public AddHomeButton(MyGdxGame game){
        mgg = game;
        x = MyGdxGame.SRC_WIDTH-160;
        y = MyGdxGame.SRC_HEIGHT - 160;
        texture = new Texture("home.png");
        isBuilded = false;
    }
    public void drawButton(){
        mgg.batch.draw(texture,x,y,WIDTH,HEIGHT);
    }
    public boolean isPressed(int x,int y){
        if (x>this.x&&x<this.x+WIDTH&&y<HEIGHT&&y>0){
            return true;
        }
        return false;
    }
}
