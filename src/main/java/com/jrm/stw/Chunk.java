package com.jrm.stw;

import com.jrm.stw.Block.BlockType;

public class Chunk {
    public final int WIDTH = 32;
    public final int LENGTH = 32;
    public final int HEIGHT = 100;

    public enum Biome {
        Tundra, Taiga, Plains, Swamp, Forest, Shrubland, RainForest, SeasonalForest, Savanna, Desert
    }

    private Biome biome;
    private int x, y;
    private Block[][][] blocks;


    public Chunk() {
        blocks = new Block[WIDTH][LENGTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < LENGTH; y++) {
                for (int z = 0; z < HEIGHT; z++) {
                    //blocks[x][y][z] = new Block();
                    blocks[x][y][z] = new Block(x, y, z, this.x, this.y, BlockType.AIR);
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
                        blocks[x][y][z] = new Block(x, y, z, this.x, this.y, BlockType.SAND);
                    } else if (z < 40) {
                        blocks[x][y][z] = new Block(x, y, z, this.x, this.y, BlockType.DIRT);
                    } else if (z < 60) {
                        blocks[x][y][z] = new Block(x, y, z, this.x, this.y, BlockType.STONE);
                    } else if (z < 70) {
                        blocks[x][y][z] = new Block(x, y, z, this.x, this.y, BlockType.WATER);
                    } else {
                        blocks[x][y][z] = new Block(x, y, z, this.x, this.y, BlockType.AIR);
                    }
                }
            }
        }
    }


    public Block[][][] getBlocks() {
        return blocks;
    }

    public void setBlocks(Block[][][] blocks) {
        this.blocks = blocks;
    }

    public Biome getBiome() {
        return biome;
    }

    public void setBiome(Biome biome) {
        this.biome = biome;
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
}
