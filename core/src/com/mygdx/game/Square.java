package com.mygdx.game;

public class Square {
    int width,height;
    int x,y;
    int i,j;
    boolean isEmpty;

    public Square(int i,int j,int x,int y) {
        this.x = x;
        this.y = y;
        this.j = j;
        this.x = x;
        isEmpty = false;
    }
    public Square(int x,int y){
        this.x = x;
        this.y = y;
        i = (x-(MyGdxGame.SRC_WIDTH-1920)/2)/60;
        j = (y-(MyGdxGame.SRC_HEIGHT-1080)/2)/60;
    }

    @Override
    public String toString() {
        return x+"/"+y+"/"+"/"+i+"/"+j+"/"+isEmpty;
    }
}
