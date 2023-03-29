package com.jrm.stw;

import org.junit.Test;

import static org.junit.Assert.*;

public class BlockTest {

    @Test
    public void getX() {
        Block tester = new Block();
        assertEquals(0, tester.getX());
    }

    @Test
    public void getY() {
        Block tester = new Block();
        assertEquals(0, tester.getY());
    }

    @Test
    public void getZ() {
        Block tester = new Block();
        assertEquals(0, tester.getZ());
    }

    @Test
    public void getChunkX() {
        Block tester = new Block();
        assertEquals(0, tester.getChunkX());
    }

    @Test
    public void getChunkZ() {
        Block tester = new Block();
        assertEquals(0, tester.getChunkZ());
    }

    @Test
    public void getUniversalX() {
        //test 1
        Block tester = new Block();
        assertEquals(0, tester.getUniversalX());
        //test 2
        tester.setChunkX(5);
        tester.setChunkZ(3);
        assertEquals(160, tester.getUniversalX());
        tester.setX(5);
        assertEquals(165, tester.getUniversalX());

    }

    @Test
    public void getUniversalY() {
        //test 1
        Block tester = new Block();
        assertEquals(0, tester.getUniversalY());
        //test 2
        tester.setChunkZ(2);
        assertEquals(0, tester.getUniversalY());

        tester.setChunkX(3);
        assertEquals(0, tester.getUniversalY());

        tester.setY(5);
        assertEquals(5, tester.getUniversalY());
    }

    @Test
    public void getUniversalZ() {
        //test 1
        Block tester = new Block();
        assertEquals(0, tester.getUniversalZ());
        //test 2
        tester.setChunkX(5);
        tester.setChunkZ(3);
        assertEquals(96, tester.getUniversalZ());
        tester.setZ(5);
        assertEquals(101, tester.getUniversalZ());
    }

    @Test
    public void getType() {
        Block tester = new Block();
        assertEquals(BlockType.AIR, tester.getType());
    }


    @Test
    public void setX() {
        Block tester = new Block();
        //valid value
        tester.setX(10);
        assertEquals(10, tester.getX());
        //assertEquals(tester.getUniversalX(), 10);
        //negative value
        tester.setX(-1);
        assertEquals(10, tester.getX());
        //assertEquals(tester.getUniversalX(), 10);
        //Too high value
        tester.setX(32);
        assertEquals(10, tester.getX());
        //assertEquals(tester.getUniversalX(), 10);


    }

    @Test
    public void setZ() {
        Block tester = new Block();
        //valid value
        tester.setZ(10);
        assertEquals(10, tester.getZ());
        //assertEquals(tester.getUniversalZ(), 10);
        //negative value
        tester.setZ(-1);
        assertEquals(10, tester.getZ());
        // assertEquals(tester.getUniversalZ(), 10);
        //too high value
        tester.setZ(32);
        assertEquals(10, tester.getZ());
        //assertEquals(tester.getUniversalZ(), 10);
    }

    @Test
    public void setY() {
        Block tester = new Block();
        //valid value
        tester.setY(10);
        assertEquals(10, tester.getY());
        //negative value
        tester.setY(-1);
        assertEquals(10, tester.getY());
        //too high value
        tester.setY(100);
        assertEquals(10, tester.getY());
    }


    @Test
    public void setChunkX() {
        Block tester = new Block();
        //valid value
        tester.setChunkX(10);
        assertEquals(10, tester.getChunkX());
        //assertEquals(tester.getUniversalX(), 10);
        //negative value
        tester.setChunkX(-1);
        assertEquals(10, tester.getChunkX());
        tester.setChunkX(0);
        assertEquals(0, tester.getChunkX());
        //assertEquals(tester.getUniversalX(), 10);
        //Too high value
        //tester.setChunkX(32);
        //assertEquals(10,tester.getChunkX());
        //assertEquals(tester.getUniversalX(), 10);

    }


    @Test
    public void setChunkZ() {
        Block tester = new Block();
        //valid value
        tester.setChunkZ(10);
        assertEquals(10, tester.getChunkZ());
        //assertEquals(tester.getUniversalZ(), 10);
        //negative value
        tester.setChunkZ(-1);
        assertEquals(10, tester.getChunkZ());
        tester.setChunkZ(0);
        assertEquals(0, tester.getChunkZ());
        //assertEquals(tester.getUniversalZ(), 10);
        //Too high value
        //tester.setChunkZ(32);
        //assertEquals(10,tester.getChunkZ());
        //assertEquals(tester.getUniversalZ(), 10);
    }


    @Test
    public void setType() {
        Block tester = new Block();
        tester.setType(BlockType.WATER);
        assertEquals(BlockType.WATER,tester.getType());

        tester.setType(BlockType.GRASS);
        assertEquals(BlockType.GRASS,tester.getType());

        tester.setType(BlockType.STONE);
        assertEquals(BlockType.STONE,tester.getType());

        tester.setType(BlockType.AIR);
        assertEquals(BlockType.AIR,tester.getType());

        tester.setType(BlockType.DIRT);
        assertEquals(BlockType.DIRT,tester.getType());

        tester.setType(BlockType.SAND);
        assertEquals(BlockType.SAND,tester.getType());
    }

    @Test
    public void calculateUniversalLocation() {
        //default constructor
        Block tester = new Block();
        assertEquals(0,tester.getUniversalX());
        assertEquals(0,tester.getUniversalY());
        assertEquals(0,tester.getUniversalZ());
        //full constructor
        tester = new Block(0,0,0,0,0,BlockType.AIR);
        assertEquals(0,tester.getUniversalX());
        assertEquals(0,tester.getUniversalY());
        assertEquals(0,tester.getUniversalZ());

        //setters
        tester.setX(5);
        tester.setChunkX(5);
        assertEquals(165,tester.getUniversalX());
        tester.setY(5);
        assertEquals(5,tester.getUniversalY());
        tester.setZ(5);
        tester.setChunkZ(5);
        assertEquals(165,tester.getUniversalZ());



    }

}