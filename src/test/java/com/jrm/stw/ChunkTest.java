package com.jrm.stw;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class ChunkTest {

    @Test
    public void getX() {
        Chunk tester = new Chunk();
        assertEquals(0, tester.getX());
    }

    @Test
    public void getY() {
        Chunk tester = new Chunk();
        assertEquals(0, tester.getY());
    }

    @Test
    public void getBiome() {
        Chunk tester = new Chunk();
        assertEquals(Biome.Void, tester.getBiome());
    }

    @Test
    public void getBlocks() {
        Chunk tester = new Chunk();
        Block[][][] blocks = tester.getBlocks();
        for (int X = 0; X < Chunk.WIDTH; X++) {
            for (int Y = 0; Y < Chunk.LENGTH; Y++) {
                for (int Z = 0; Z < Chunk.HEIGHT; Z++) {
                    assertNotNull(blocks[X][Y][Z]);
                }
            }
        }
    }

    @Test
    public void createLogicalBlockDistribution() {
        Chunk tester = new Chunk();
        tester.createLogicalBlockDistribution();
        Block[][][] blocks = tester.getBlocks();
        for (int X = 0; X < Chunk.WIDTH; X++) {
            for (int Y = 0; Y < Chunk.LENGTH; Y++) {
                for (int Z = 0; Z < Chunk.HEIGHT; Z++) {
                    assertNotNull(blocks[X][Y][Z]);
                }
            }
        }
        //Chunk.printBlocks(tester.getBlocks());
    }

    @Test
    public void fillChunk() {
        //IDK how to test this
    }

    @Test
    public void createForest() {
        Chunk tester = new Chunk();
        tester.setBiome(Biome.Forest);
        Block[][][] blocks = tester.getBlocks();
        for (int X = 0; X < Chunk.WIDTH; X++) {
            for (int Y = 0; Y < Chunk.LENGTH; Y++) {
                for (int Z = 0; Z < Chunk.HEIGHT; Z++) {
                    assertNotNull(blocks[X][Y][Z]);
                }
            }
        }
    }

    public void createPlains() {
        Chunk tester = new Chunk();
        tester.setBiome(Biome.Plains);
        Block[][][] blocks = tester.getBlocks();
        for (int X = 0; X < Chunk.WIDTH; X++) {
            for (int Y = 0; Y < Chunk.LENGTH; Y++) {
                for (int Z = 0; Z < Chunk.HEIGHT; Z++) {
                    assertNotNull(blocks[X][Y][Z]);
                }
            }
        }
    }

    public void createDesert() {
        Chunk tester = new Chunk();
        tester.setBiome(Biome.Desert);
        Block[][][] blocks = tester.getBlocks();
        for (int X = 0; X < Chunk.WIDTH; X++) {
            for (int Y = 0; Y < Chunk.LENGTH; Y++) {
                for (int Z = 0; Z < Chunk.HEIGHT; Z++) {
                    assertNotNull(blocks[X][Y][Z]);
                }
            }
        }
    }

    public void createVoid() {
        Chunk tester = new Chunk();
        tester.setBiome(Biome.Void);
        Block[][][] blocks = tester.getBlocks();
        for (int X = 0; X < Chunk.WIDTH; X++) {
            for (int Y = 0; Y < Chunk.LENGTH; Y++) {
                for (int Z = 0; Z < Chunk.HEIGHT; Z++) {
                    assertNotNull(blocks[X][Y][Z]);
                }
            }
        }
    }

    @Test
    public void setBlocks() {
        Chunk tester = new Chunk();
        Block[][][] testBlocks = new Block[Chunk.WIDTH][Chunk.LENGTH][Chunk.HEIGHT];
        for (int w = 0; w < Chunk.WIDTH; w++) {
            for (int l = 0; l < Chunk.LENGTH; l++) {
                for (int h = 0; h < Chunk.HEIGHT; h++) {
                    Random rand = new Random();
                    if (rand.nextBoolean()) {
                        testBlocks[w][l][h] = new Block(w, l, h, 0, 0, BlockType.AIR);

                    } else {
                        testBlocks[w][l][h] = new Block(w, l, h, 0, 0, BlockType.STONE);

                    }
                }
            }
        }
        Block[][][] testerBlocks = tester.getBlocks();
        tester.setBlocks(testBlocks);
        for (int w = 0; w < Chunk.WIDTH; w++) {
            for (int l = 0; l < Chunk.LENGTH; l++) {
                for (int h = 0; h < Chunk.HEIGHT; h++) {
                    assertEquals(testBlocks[w][l][h], testerBlocks[w][l][h]);
                }
            }
        }
    }

    @Test
    public void setBiome() {
        Chunk tester = new Chunk();
        assertEquals(Biome.Void, tester.getBiome());
        tester.setBiome(Biome.Forest);
        assertEquals(Biome.Forest, tester.getBiome());
        tester = new Chunk(1, 2);
        assertEquals(Biome.Void, tester.getBiome());
        tester = new Chunk(1, 2, Biome.Forest, true, 12345L);
        assertEquals(Biome.Forest, tester.getBiome());

    }

    @Test
    public void setX() {
        Chunk tester = new Chunk();
        tester.setX(-5);
        assertEquals(-5, tester.getX());
        tester = new Chunk(1, 2);
        tester.setX(-5);
        assertEquals(-5, tester.getX());
        tester = new Chunk(1, 2, Biome.Forest, true, 12345L);
        tester.setX(-5);
        assertEquals(-5, tester.getX());
    }

    @Test
    public void setY() {
        Chunk tester = new Chunk();
        tester.setY(-5);
        assertEquals(-5, tester.getY());
        tester = new Chunk(1, 2);
        tester.setY(-5);
        assertEquals(-5, tester.getY());
        tester = new Chunk(1, 2, Biome.Forest, true, 12345L);
        tester.setY(-5);
        assertEquals(-5, tester.getY());
    }

    @Test
    public void printBlocks() {
        /*MapGenerator gen = new MapGenerator();
        Map map = gen.getMap();
        Chunk tester = map.getChunks()[0][0];
        System.out.println(tester);*/

        Chunk tester1 = new Chunk(0, 0, Biome.Forest, true, 12345L);
        System.out.println(tester1);
        Chunk tester2 = new Chunk(0, 0, Biome.Plains, true, 12345L);
        System.out.println(tester2);
        Chunk tester3 = new Chunk(0, 0, Biome.Desert, true, 12345L);
        System.out.println(tester3);
    }
}