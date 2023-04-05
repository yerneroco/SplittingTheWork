package com.jrm.stw;


import java.io.Serializable;
import java.util.Random;

public class Chunk implements Serializable {
    public static final int WIDTH = 32;
    public static final int LENGTH = 32;
    public static final int HEIGHT = 100;

    private int x, y;
    private Biome biome;
    private boolean land;
    private long seed;
    private Block[][][] blocks;

    /**
     * Create a Void chunk with location 0,0 in the world.
     * Assumes this chunk is land and the seed is 12345L.
     * Calls the Forest fill method for the chunk.
     */
    public Chunk() {
        blocks = new Block[WIDTH][LENGTH][HEIGHT];
        setX(0);
        setY(0);
        setBiome(Biome.Void);
        this.land = true;
        seed = 12345L;
        fillChunk();

    }

    /**
     * Creates a Void chunk with location x,y in the world.
     * Assumes this chunk is land and the seed is 12345L.
     * Calls the Forest fill method for the chunk.
     *
     * @param x Chunk's x in the map
     * @param y Chunk's y in the map
     */
    public Chunk(int x, int y) {
        blocks = new Block[WIDTH][LENGTH][HEIGHT];
        setX(x);
        setY(y);
        setBiome(Biome.Void);
        this.land = false;
        seed = 12345L;
        fillChunk();
    }

    /**
     * Creates a chunk of Biome biome with location x,y in the world.
     * Sets land status and seed to passed in values
     * Calls the proper fill method for the chunk's biome.
     * @param x
     * @param y
     * @param biome
     * @param land
     * @param seed
     */
    public Chunk(int x, int y, Biome biome, boolean land, long seed) {
        blocks = new Block[WIDTH][LENGTH][HEIGHT];
        setX(x);
        setY(y);
        setBiome(biome);
        this.land = land;
        this.seed = seed;
        fillChunk();

    }

    /**
     * Directs to the correct Fill method for the chunk's biome
     */
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
                createDesert();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + biome);
        }
    }

    /**
     * Fills chunk with a dirt and grass top
     */
    private void createForest() {
        for (int w = 0; w < WIDTH; w++) {
            for (int l = 0; l < LENGTH; l++) {
                for (int h = 0; h < HEIGHT; h++) {
                    BlockType type = BlockType.AIR;
                    if (land) {
                        if (h >= 70) {
                            type = BlockType.AIR;
                        } else if (h == 69) {
                            Random rand = new Random(seed);
                            if (rand.nextInt(10) > 5) {
                                type = BlockType.DIRT;
                            } else {
                                type = BlockType.GRASS;
                            }
                        } else if (h >= 60) {
                            type = BlockType.DIRT;
                        } else if (h >= 40) {
                            type = BlockType.STONE;
                        } else if (h >= 20) {
                            type = BlockType.STONE;
                        } else {
                            type = BlockType.STONE;
                        }
                    } else {
                        if (h >= 68) {
                            type = BlockType.AIR;
                        } else if (h >= 30) {
                            type = BlockType.WATER;
                        } else if (h >= 20) {
                            type = BlockType.DIRT;
                        } else if (h >= 10) {
                            type = BlockType.STONE;
                        }

                    }
                    blocks[w][l][h] = new Block(w, l, h, this.x, this.y, type);


                }
            }
        }
    }

    /**
     * Fills the chunk with a majority of grass on top with dirt mixed in.
     */
    private void createPlains() {
        for (int w = 0; w < WIDTH; w++) {
            for (int l = 0; l < LENGTH; l++) {
                for (int h = 0; h < HEIGHT; h++) {
                    BlockType type = BlockType.AIR;
                    if (land) {
                        if (h >= 70) {
                            type = BlockType.AIR;
                        } else if (h == 69) {
                            Random rand = new Random(seed);
                            if (rand.nextInt(10) > 3) {
                                type = BlockType.DIRT;
                            } else {
                                type = BlockType.GRASS;
                            }
                        } else if (h >= 60) {
                            type = BlockType.DIRT;
                        } else if (h >= 40) {
                            type = BlockType.STONE;
                        } else if (h >= 20) {
                            type = BlockType.STONE;
                        } else {
                            type = BlockType.STONE;
                        }

                    } else {
                        if (h >= 68) {
                            type = BlockType.AIR;
                        } else if (h >= 30) {
                            type = BlockType.WATER;
                        } else if (h >= 20) {
                            type = BlockType.DIRT;
                        } else if (h >= 10) {
                            type = BlockType.STONE;
                        }

                    }
                    blocks[w][l][h] = new Block(w, l, h, this.x, this.y, type);
                }
            }
        }
    }

    /**
     * Fills the chunk with sand on top
     */
    private void createDesert() {
        for (int w = 0; w < WIDTH; w++) {
            for (int l = 0; l < LENGTH; l++) {
                for (int h = 0; h < HEIGHT; h++) {
                    BlockType type = BlockType.AIR;
                    if (land) {
                        if (h >= 70) {
                            type = BlockType.AIR;
                        } else if (h >= 60) {
                            type = BlockType.SAND;
                        } else if (h >= 40) {
                            type = BlockType.STONE;
                        } else if (h >= 20) {
                            type = BlockType.STONE;
                        } else {
                            type = BlockType.STONE;
                        }
                    } else {
                        if (h >= 68) {
                            type = BlockType.AIR;
                        } else if (h >= 30) {
                            type = BlockType.WATER;
                        } else if (h >= 20) {
                            type = BlockType.DIRT;
                        } else if (h >= 10) {
                            type = BlockType.STONE;
                        }
                    }
                    blocks[w][l][h] = new Block(w, l, h, this.x, this.y, type);

                }
            }
        }
    }

    private void createVoid() {
        for (int w = 0; w < blocks.length; w++) {
            for (int l = 0; l < blocks[0].length; l++) {
                for (int h = 0; h < blocks[0][0].length; h++) {
                    blocks[w][l][h] = new Block(w, h, l, x, y, BlockType.AIR);
                }
            }
        }
    }
    //Unimplemented Biomes below

    private void createRainForest() {
        //Not implemented
    }

    private void createSavanna() {
        //Not implemented
    }

    private void createSeasonalForest() {
        //Not implemented
    }

    private void createShrubland() {
        //Not implemented
    }

    private void createSwamp() {
        //Not implemented
    }

    private void createTaiga() {
        //Not implemented
    }

    private void createTundra() {
        //Not implemented
    }

    /**
     * Fills the chunk with a random but semi logical block distribution
     */
    public void createLogicalBlockDistribution() {
        for (int w = 0; w < WIDTH; w++) {
            for (int l = 0; l < LENGTH; l++) {
                for (int h = 0; h < HEIGHT; h++) {
                    BlockType type = BlockType.AIR;
                    if (h >= 70) {
                        type = BlockType.AIR;
                    } else if (h >= 60) {
                        type = BlockType.WATER;
                    } else if (h >= 40) {
                        type = BlockType.STONE;
                    } else if (h >= 20) {
                        type = BlockType.DIRT;
                    } else {
                        type = BlockType.SAND;
                    }
                    blocks[w][l][h] = new Block(w, l, h, this.x, this.y, type);

                }
            }
        }

    }

    //Unimplemented ore methods
    public void addOreVein() {
    /*
    solid chunk of dirt
    ore veins
        random spot start
        random walk away from start
    */
    }

    public static Block[][][] addOreVein(Block[][][] blocks) {
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

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Prints the blocks inside a chunk by slicing into the chunk vertically.
     * Displays a vertical slices left to right on the screen
     * @param blocks
     */
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

    /**
     * Outputs the blocks inside a chunk by slicing into the chunk vertically.
     * Displays a vertical slices left to right on the screen
     * @return
     */
    public String toString() {
        String out = "";
        for (int h = blocks[0][0].length - 1; h >= 0; h--) {
            for (int l = 0; l < blocks.length; l++) {
                if (h == blocks[0][0].length - 1) {
                    out += (l + ":");
                } else {
                    out += ("  ");
                    if (l > 9) {
                        out += (" ");
                    }
                }
                for (int w = 0; w < blocks[0].length; w++) {
                    out += blocks[w][l][h].getType().ordinal();

                }
                out += (" ");
            }
            out += "\n";

        }
        return out;
    }
}
