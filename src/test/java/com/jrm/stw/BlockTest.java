package com.jrm.stw;

import org.junit.Test;

import static org.junit.Assert.*;

public class BlockTest {

    @Test
    public void getX() {
        Block tester = new Block();
        assertEquals(tester.getX(), 0);
    }

    @Test
    public void setX() {
        Block tester = new Block();
        tester.setX(10);
        assertEquals(tester.getX(), 10);
        assertEquals(tester.getUniversalX(),10);

        tester.setX(-1);
        assertEquals(tester.getX(), 10);
        assertEquals(tester.getUniversalX(),10);

        tester.setX(32);
        assertEquals(tester.getX(), 10);
        assertEquals(tester.getUniversalX(),10);



    }

    @Test
    public void getY() {
        Block tester = new Block();
        assertEquals(tester.getY(), 0);
    }

    @Test
    public void setY() {
        Block tester = new Block();
        tester.setY(10);
        assertEquals(tester.getY(), 10);
    }

    @Test
    public void getZ() {
        Block tester = new Block();
        assertEquals(tester.getZ(), 0);
    }

    @Test
    public void setZ() {
        Block tester = new Block();
        tester.setZ(10);
        assertEquals(tester.getZ(), 10);
    }

    @Test
    public void getChunkX() {
        Block tester = new Block();
        assertEquals(tester.getChunkX(), 0);
    }

    @Test
    public void setChunkX() {
    }

    @Test
    public void getChunkZ() {
        Block tester = new Block();
        assertEquals(tester.getChunkZ(), 0);
    }

    @Test
    public void setChunkZ() {
    }

    @Test
    public void getType() {
        Block tester = new Block();
        assertEquals(tester.getType(), BlockType.AIR);
    }

    @Test
    public void setType() {
    }

    @Test
    public void calculateUniversalLocation() {

    }

    @Test
    public void getUniversalX() {
        //test 1
        Block tester = new Block();
        assertEquals(tester.getUniversalX(), 0);
        //test 2
        tester.setChunkX(5);
        tester.setChunkZ(3);
        assertEquals(tester.getUniversalX(), 160);
        tester.setX(5);
        assertEquals(tester.getUniversalX(), 165);

    }

    @Test
    public void getUniversalY() {
        //test 1
        Block tester = new Block();
        assertEquals(tester.getUniversalY(), 0);
        //test 2
        tester.setChunkZ(2);
        assertEquals(tester.getUniversalY(), 0);

        tester.setChunkX(3);
        assertEquals(tester.getUniversalY(), 0);

        tester.setY(5);
        assertEquals(tester.getUniversalY(), 5);
    }

    @Test
    public void getUniversalZ() {
        //test 1
        Block tester = new Block();
        assertEquals(0,tester.getUniversalZ());
        //test 2
        tester.setChunkX(5);
        tester.setChunkZ(3);
        assertEquals(96,tester.getUniversalZ());
        tester.setZ(5);
        assertEquals(101,tester.getUniversalZ());
    }

    @Test
    public void destroy() {
        Block tester = new Block();
        tester.destroy();
        assertEquals(tester,null);
    }
}