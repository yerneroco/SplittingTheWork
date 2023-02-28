package com.jrm.stw;


import java.io.Serializable;

public class Chunk implements Serializable {
    public static final int WIDTH = 32;
    public static final int LENGTH = 32;
    public static final int HEIGHT = 100;

    private int x, z;
    private Biome biome;
    private Block[][][] blocks;

    /**
     * Create a base forest Void chunk with location 0,0 in the world. Sets every block's x,y,z equal its location
     * in the chunk, the chunk it is located in, and the block tpe of air.
     */
    public Chunk() {
        blocks = new Block[WIDTH][LENGTH][HEIGHT];
        x = 0;
        z = 0;
        biome = Biome.Void;

        
/*
        for (int bX = 0; bX < WIDTH; bX++) {
            for (int bY = 0; bY < LENGTH; bY++) {
                for (int bZ = 0; bZ < HEIGHT; bZ++) {
                    //blocks[x][bY][z] = new Block();
                    blocks[bX][bY][bZ] = new Block(bX, bY, bZ, this.x, this.z, BlockType.AIR);
                }
            }
        }
*/
    }

    /**
     * Create a base forest Void chunk with location 0,0 in the world. Sets every block's x,y,z equal its location
     * in the chunk, the chunk it is located in, and the block tpe of air.
     * @param x
     * @param z
     */
    public Chunk(int x, int z) {
        this.x = x;
        this.z = z;
        this.biome =
    }
    public Chunk(int x, int z,Biome biome) {
        this.x = x;
        this.z = z;
        this.biome = biome;
        switch (biome) {
            case Tundra:
                createTundra();
                break;
            case Taiga:
                createTaiga();
                break;
            case Plains:
                createPlains();
                break;
            case Swamp:
                createSwamp();
                break;
            case Forest:
                createForest();
                break;
            case Shrubland:
                createShrubland();
                break;
            case RainForest:
                createRainForest();
                break;
            case SeasonalForest:
                createSeasonalForest();
                break;
            case Savanna:
                createSavanna();
                break;
            case Void:
                createVoid();
                break;
            case Desert:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + biome);
        }
    }
    
    private void createTundra() {
    }
    private void createTaiga() {
    }
    private void createPlains() {
    }
    private void createVoid() {
    }
    private void createSwamp() {
    }

    private void createForest() {
    }

    private void createShrubland() {
    }

    private void createRainForest() {
    }

    private void createSeasonalForest() {
    }

    private void createSavanna() {
    }


    public void createLogicalBlockDistribution() {
        for (int bX = 0; bX < WIDTH; bX++) {
            for (int bY = 0; bY < LENGTH; bY++) {
                for (int bZ = 0; bZ < HEIGHT; bZ++) {
                    if (bZ < 20) {
                        blocks[bX][bY][bZ] = new Block(bX, bY, bZ, this.x, this.z, BlockType.SAND);
                    } else if (bZ < 40) {
                        blocks[bX][bY][bZ] = new Block(bX, bY, bZ, this.x, this.z, BlockType.DIRT);
                    } else if (bZ < 60) {
                        blocks[bX][bY][bZ] = new Block(bX, bY, bZ, this.x, this.z, BlockType.STONE);
                    } else if (bZ < 70) {
                        blocks[bX][bY][bZ] = new Block(bX, bY, bZ, this.x, this.z, BlockType.WATER);
                    } else {
                        blocks[bX][bY][bZ] = new Block(bX, bY, bZ, this.x, this.z, BlockType.AIR);
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
