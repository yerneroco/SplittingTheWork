package com.jrm.stw;

import com.jrm.stw.Block.BlockType;

import java.io.Serializable;

public class Chunk implements Serializable {
    public static final int WIDTH = 32;
    public static final int LENGTH = 32;
    public static final int HEIGHT = 100;

    private Biome biome;
    private int x, z;
    private Block[][][] blocks;


    public Chunk() {
        biome = Biome.Forest;
        x = 0;
        z = 0;
        blocks = new Block[WIDTH][LENGTH][HEIGHT];
        for (int bX = 0; bX < WIDTH; bX++) {
            for (int bY = 0; bY < LENGTH; bY++) {
                for (int bZ = 0; bZ < HEIGHT; bZ++) {
                    //blocks[x][y][z] = new Block();
                    blocks[bX][bY][bZ] = new Block(bX, bY, bZ, this.x, this.z, BlockType.AIR);
                }
            }
        }
    }

    public Chunk(int x, int z) {
        this();
        this.x = x;
        this.z = z;
    }

    public void createLogicalBlockDistribution() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < LENGTH; y++) {
                for (int z = 0; z < HEIGHT; z++) {
                    if (z < 20) {
                        blocks[x][y][z] = new Block(x, y, z, this.x, this.z, BlockType.SAND);
                    } else if (z < 40) {
                        blocks[x][y][z] = new Block(x, y, z, this.x, this.z, BlockType.DIRT);
                    } else if (z < 60) {
                        blocks[x][y][z] = new Block(x, y, z, this.x, this.z, BlockType.STONE);
                    } else if (z < 70) {
                        blocks[x][y][z] = new Block(x, y, z, this.x, this.z, BlockType.WATER);
                    } else {
                        blocks[x][y][z] = new Block(x, y, z, this.x, this.z, BlockType.AIR);
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

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
