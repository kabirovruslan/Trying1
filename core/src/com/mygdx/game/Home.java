package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Home { // класс дома(сейчас), в котором необходимо указать клетки, которые занимает дом
    Texture texture;
    Square square;
    Square square1;
    Square square2;
    Square square3;

    public Home(Texture texture, Square square) {
        this.texture = texture;
        this.square = square;
        square1 = new Square(square.i+1,square.j,square.x,square.y+60);
        square2 = new Square(square.i,square.j+1,square.x+60,square.y);
        square3 = new Square(square.i+1,square.j+1,square.x+60,square.y+60);
        square1.isEmpty =true;
        square2.isEmpty = true;
        square3.isEmpty = true;
    }


    @Override
    public String toString() {
        return "";
    }
}
