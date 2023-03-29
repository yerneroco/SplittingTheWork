package com.jrm.stw;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChunkTest {

    @Test
    public void getZ() {
        Chunk tester = new Chunk();
        assertEquals(0, tester.getZ());
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
                    assertNotNull(blocks[X][Z][Y]);
                }
            }
        }    }

    @Test
    public void createLogicalBlockDistribution() {
        Chunk tester = new Chunk();
        tester.createLogicalBlockDistribution();
        Block[][][] blocks = tester.getBlocks();
        for (int X = 0; X < Chunk.WIDTH; X++) {
            for (int Y = 0; Y < Chunk.HEIGHT; Y++) {
                for (int Z = 0; Z < Chunk.LENGTH; Z++) {
                    assertNotNull(blocks[X][Z][Y]);
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
        tester = new Chunk(1,2);
        assertEquals(Biome.Void, tester.getBiome());
        tester = new Chunk(1,2,Biome.Forest);
        assertEquals(Biome.Forest, tester.getBiome());

    }

    @Test
    public void setX() {
        Chunk tester = new Chunk();
        tester.setX(-5);
        assertEquals(-5, tester.getX());
        tester = new Chunk(1,2);
        tester.setX(-5);
        assertEquals(-5, tester.getX());
        tester = new Chunk(1,2,Biome.Forest);
        tester.setX(-5);
        assertEquals(-5, tester.getX());
    }
    @Test
    public void setZ() {
        Chunk tester = new Chunk();
        tester.setZ(-5);
        assertEquals(-5, tester.getZ());
        tester = new Chunk(1,2);
        tester.setZ(-5);
        assertEquals(-5, tester.getZ());
        tester = new Chunk(1,2,Biome.Forest);
        tester.setZ(-5);
        assertEquals(-5, tester.getZ());
    }
}