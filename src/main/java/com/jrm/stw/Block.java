package com.jrm.stw;

import java.io.Serializable;

public class Block implements Serializable {
    private int x,y,z;
    private int chunkX, chunkY;
    private BlockType type;

    public enum BlockType {
        SAND, DIRT, AIR, STONE, GRASS, WATER
    }
    public Block(){
        x = 0;
        y = 0;
        z = 0;
        chunkX = 0;
        chunkY = 0;
        type = BlockType.AIR;

    }
    public Block(int x, int y, int z, int chunkX,int chunkY,BlockType type) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.chunkX = chunkX;
        this.chunkY = chunkY;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getChunkX() {
        return chunkX;
    }

    public void setChunkX(int chunkX) {
        this.chunkX = chunkX;
    }

    public int getChunkY() {
        return chunkY;
    }

    public void setChunkY(int chunkY) {
        this.chunkY = chunkY;
    }

    public BlockType getType() {
        return type;
    }

    public void setType(BlockType type) {
        this.type = type;
    }
}
