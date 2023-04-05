package com.jrm.stw;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChunkTest {

    @Test
    public void getY() {
        Chunk tester = new Chunk();
        assertEquals(0, tester.getY());
    }

    @Test
    public void getX() {
        Chunk tester = new Chunk();
        assertEquals(0, tester.getX());
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
            for (int Y = 0; Y < Chunk.HEIGHT; Y++) {
                for (int Z = 0; Z < Chunk.LENGTH; Z++) {
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
            for (int Y = 0; Y < Chunk.HEIGHT; Y++) {
                for (int Z = 0; Z < Chunk.LENGTH; Z++) {
                    assertNotNull(blocks[X][Y][Z]);
                }
            }
        }
        Chunk.printBlocks(tester.getBlocks());
    }

    @Test
    public void addOreVein() {

    }

    @Test
    public void testAddOreVein() {

    }


    @Test
    public void setBlocks() {
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

        Chunk tester1 = new Chunk(0,0,Biome.Forest,true,12345L);
        System.out.println(tester1);
        Chunk tester2 = new Chunk(0,0,Biome.Plains,true,12345L);
        System.out.println(tester2);
        Chunk tester3 = new Chunk(0,0,Biome.Desert,true,12345L);
        System.out.println(tester3);
    }
}