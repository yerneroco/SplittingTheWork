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
        setX(0);
        setZ(0);
        setBiome(Biome.Void);
        fillChunk();
        
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
     *
     * @param x Chunk's x in the map
     * @param z Chunk's y in the map
     */
    public Chunk(int x, int z) {
        blocks = new Block[WIDTH][LENGTH][HEIGHT];
        setX(x);
        setZ(z);
        setBiome(Biome.Void);
        fillChunk();
    }

    public Chunk(int x, int z, Biome biome) {
        blocks = new Block[WIDTH][LENGTH][HEIGHT];
        setX(x);
        setZ(z);
        setBiome(biome);
        fillChunk();

    }

    private void fillChunk() {
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
        for (int w = 0; w < blocks.length; w++) {
            for (int l = 0; l < blocks[0].length; l++) {
                for (int h = 0; h < blocks[0][0].length; h++) {
                    blocks[w][l][h] = new Block(w, h, l, x, z, BlockType.AIR);
                }
            }
        }
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
            for (int bY = 0; bY < HEIGHT; bY++) {
                for (int bZ = 0; bZ < LENGTH; bZ++) {
                    if (bY < 20) {
                        blocks[bX][bZ][bY] = new Block(bX, bY, bZ, this.x, this.z, BlockType.SAND);
                    } else if (bY < 40) {
                        blocks[bX][bZ][bY] = new Block(bX, bY, bZ, this.x, this.z, BlockType.DIRT);
                    } else if (bY < 60) {
                        blocks[bX][bZ][bY] = new Block(bX, bY, bZ, this.x, this.z, BlockType.STONE);
                    } else if (bY < 70) {
                        blocks[bX][bZ][bY] = new Block(bX, bY, bZ, this.x, this.z, BlockType.WATER);
                    } else {
                        blocks[bX][bZ][bY] = new Block(bX, bY, bZ, this.x, this.z, BlockType.AIR);
                    }
                }
            }
        }
    }

    public void addOreVein() {
    /*
    solid chunk of dirt
    ore veins
        random spot start
        random walk away from start
    */
    }

    public static Block[][][] addOreVein(Block[][][] blks) {
        Block[][][] newBlocks = new Block[WIDTH][LENGTH][HEIGHT];
        return newBlocks;
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

    public static void printBlocks(Block[][][] blocks) {
        //System.out.println(blocks.length);//width
        //System.out.println(blocks[0].length);//length
        //System.out.println(blocks[0][0].length);//height
        for (int h = blocks[0][0].length - 1; h >= 0; h--) {
            for (int w = 0; w < blocks.length; w++) {
                if (h == blocks[0][0].length - 1) {
                    System.out.print(w + ":");
                } else {
                    System.out.print("  ");
                    if (w > 9) {
                        System.out.print(" ");
                    }
                }
                for (int l = 0; l < blocks[0].length; l++) {
                    System.out.print(blocks[w][l][h].getType().ordinal());

                }
                System.out.print(" ");
            }
            System.out.println();

        }
    }
}
