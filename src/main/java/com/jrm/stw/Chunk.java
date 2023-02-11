package com.jrm.stw;

import com.jrm.stw.Block.BlockType;

public class Chunk{
    public final int WIDTH = 32;
    public final int LENGTH = 32;
    public final int HEIGHT = 100;

    private Block[][][] blocks;

    public Chunk(){
        blocks = new Block[WIDTH][LENGTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < LENGTH; y++) {
                for (int z = 0; z < HEIGHT; z++) {
                    blocks[x][y][z] = new Block(x, y, z, BlockType.Air, this);
                }
            }
        }
    }

    public Chunk(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    public void createLogicalBlockDistribution() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < LENGTH; y++) {
                for (int z = 0; z < HEIGHT; z++) {
                    if (z < 20) {
                        blocks[x][y][z] = new Block(x, y, z, BlockType.Sand, this);
                    } else if (z < 40) {
                        blocks[x][y][z] = new Block(x, y, z, BlockType.Dirt, this);
                    } else if (z < 60) {
                        blocks[x][y][z] = new Block(x, y, z, BlockType.Stone, this);
                    } else if (z < 70) {
                        blocks[x][y][z] = new Block(x, y, z, BlockType.Water, this);
                    } else {
                        blocks[x][y][z] = new Block(x, y, z, BlockType.Air, this);
                    }
                }
            }
        }
    }
}
