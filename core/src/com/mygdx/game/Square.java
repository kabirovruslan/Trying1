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

    @Override
    public String toString() {
        return x+"/"+y+"/"+"/"+i+"/"+j+"/"+isEmpty;
    }
}
