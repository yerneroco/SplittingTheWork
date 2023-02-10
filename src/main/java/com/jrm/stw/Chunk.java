package com.jrm.stw;

public class Chunk{
    public int x;
    public int y;
    public double value;
    public int order;

    public Chunk(){
        x = -1;
        y = -1;
        value = 'z';
    }
    public Chunk(int x,int y, double value, int order){
        this.x = x;
        this.y = y;
        this.value = value;
        this.order = order;
    }
    public void setType(int value){
        this.value = value;
    }

}
