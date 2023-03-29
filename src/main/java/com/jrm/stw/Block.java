package com.jrm.stw;

import java.io.Serializable;


public class Block implements Serializable {
    private int x, y, z;
    private int chunkX, chunkZ;
    private int universalX, universalY, universalZ;
    private BlockType type;

    /**
     * A Block with x,y,z location of 0,0,0 relative to the parent Chunk.
     * The parent Chunk is chunk at 0,0 in the Map.
     * The type is Air.
     */
    public Block() {
        universalX = 0;
        universalY = 0;
        universalZ = 0;
        x = 0;
        y = 0;
        z = 0;
        chunkX = 0;
        chunkZ = 0;
        calculateUniversalLocation();
        type = BlockType.AIR;

    }

    /**
     * A Block with a given x,y,z location relative to the parent Chunk.
     * The parent Chunk is tracked using the Chunk's x and y location in the global map.
     * The type is given at creation.
     *
     * @param x      x location relative to Chunk
     * @param y      y location relative to Chunk
     * @param z      z location relative to Chunk
     * @param chunkX x location of Chunk relative to Map
     * @param chunkZ y location of Chunk relative to Map
     * @param type   type of Block
     */
    public Block(int x, int y, int z, int chunkX, int chunkZ, BlockType type) {
        setX(x);
        setY(y);
        setZ(z);
        setChunkX(chunkX);
        setChunkZ(chunkZ);
        calculateUniversalLocation();
        setType(type);
    }

    /**
     * x is the Block's index in the parent's Chunk Array
     *
     * @return the x location of this block in its chunk
     */
    public int getX() {
        return x;
    }

    /**
     * x is the Block's index in the parent's chunk Array
     *
     * @param x must be greater than zero and less than Chunk.WIDTH.
     */
    public void setX(int x) {
        if (x >= 0 && x < Chunk.WIDTH) {
            this.x = x;
            calculateUniversalLocation();
        } else {
            System.out.println("Invalid X Block Location at: " + x + "," + y + "," + z + " Chunk:" + chunkX + "," + chunkZ);
        }
    }

    /**
     * y is the Block's index in the parent's Chunk Array
     *
     * @return the y location of this block in its chunk
     */
    public int getY() {
        return y;
    }

    /**
     * y is the Block's index in the parent's chunk Array
     *
     * @param y must be greater than zero and less than Chunk.HEIGHT.
     */
    public void setY(int y) {
        if (y >= 0 && y < Chunk.HEIGHT) {
            this.y = y;
            this.universalY = y;
        } else {
            System.out.println("Invalid Y Block Location at: " + x + "," + y + "," + z + " Chunk:" + chunkX + "," + chunkZ);
        }
    }

    /**
     * z is the Block's index in the parent's Chunk Array
     *
     * @return the z location of this block in its chunk
     */
    public int getZ() {
        return z;
    }

    /**
     * z is the Block's index in the parent's chunk Array
     *
     * @param z must be greater than zero and less than Chunk.LENGTH.
     */
    public void setZ(int z) {
        if (z >= 0 && z < Chunk.LENGTH) {
            this.z = z;
            calculateUniversalLocation();
        } else {
            System.out.println("Invalid Z Block Location " + z +" at: " + x + "," + y + "," + z + " Chunk:" + chunkX + "," + chunkZ);
        }
    }

    public int getChunkX() {
        return chunkX;
    }

    public void setChunkX(int chunkX) {
        if (chunkX >= 0) {
            this.chunkX = chunkX;
            calculateUniversalLocation();
        } else {
            System.out.println("Invalid X Chunk Location at: " + x + "," + y + "," + z + " Chunk: " + chunkX + "," + chunkZ);
        }
    }

    public int getChunkZ() {
        return chunkZ;
    }

    public void setChunkZ(int chunkZ) {
        if (chunkZ >= 0) {
            this.chunkZ = chunkZ;
            calculateUniversalLocation();
        } else {
            System.out.println("Invalid Z Chunk Location at: " + x + "," + y + "," + z + " Chunk:" + chunkX + "," + chunkZ);
        }
    }

    public BlockType getType() {
        return type;
    }

    public void setType(BlockType type) {
        this.type = type;
    }

    private void calculateUniversalLocation() {
        universalX = x + (Chunk.WIDTH * chunkX);
        universalY = y;
        universalZ = z + (Chunk.LENGTH * chunkZ);
    }

    public int getUniversalX() {
        if (universalX < 0 || universalX > 32) {
            calculateUniversalLocation();
        }
        return universalX;
    }

    public int getUniversalY() {
        if (universalY < 0 || universalY > 100) {
            calculateUniversalLocation();
        }
        return universalY;
    }

    public int getUniversalZ() {
        if (universalZ < 0 || universalZ > 32) {
            calculateUniversalLocation();
        }
        return universalZ;
    }

}
