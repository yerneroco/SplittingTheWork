package com.jrm.stw;

import java.io.Serializable;


public class Block implements Serializable {
    private int x, y, z;
    private int chunkX, chunkY;
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
        chunkY = 0;
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
     * @param chunkY y location of Chunk relative to Map
     * @param type   type of Block
     */
    public Block(int x, int y, int z, int chunkX, int chunkY, BlockType type) {
        setX(x);
        setX(x);
        setY(y);
        setZ(z);
        setChunkX(chunkX);
        setChunkY(chunkY);
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
            this.universalX = (Chunk.WIDTH * chunkX) + x;
        } else {
            System.out.println("Invalid X Block Location at: " + x + "," + y + "," + z + " Chunk:" + chunkX + "," + chunkY);
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
        if (y >= 0 && y < Chunk.LENGTH) {
            this.y = y;
            this.universalY = (Chunk.LENGTH * chunkY) + y;
        } else {
            System.out.println("Invalid Y Block Location at: " + x + "," + y + "," + z + " Chunk:" + chunkX + "," + chunkY);
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
        if (z >= 0 && z < Chunk.HEIGHT) {
            this.z = z;
            this.universalZ = z;
        } else {
            System.out.println("Invalid Z Block Location " + z + " at: " + x + "," + y + "," + z + " Chunk:" + chunkX + "," + chunkY);
        }
    }

    /**
     * Gets the Chunk's X location in the map of this block
     *
     * @return chunkX
     */
    public int getChunkX() {
        return chunkX;
    }

    /**
     * Sets the Chunk's X location in the map of this block
     *
     * @param chunkX
     */
    public void setChunkX(int chunkX) {
        if (chunkX >= 0) {
            this.chunkX = chunkX;
            calculateUniversalLocation();
        } else {
            System.out.println("Invalid X Chunk Location at: " + x + "," + y + "," + z + " Chunk: " + chunkX + "," + chunkY);
        }
    }

    /**
     * Gets the Chunk's Y location in the map of this block
     *
     * @return chunkZ
     */
    public int getChunkY() {
        return chunkY;
    }

    /**
     * Sets the Chunk's Y location in the map of this block
     *
     * @param chunkY
     */
    public void setChunkY(int chunkY) {
        if (chunkY >= 0) {
            this.chunkY = chunkY;
            calculateUniversalLocation();
        } else {
            System.out.println("Invalid Y Chunk Location at: " + x + "," + y + "," + z + " Chunk:" + chunkX + "," + chunkY);
        }
    }

    /**
     * Gets the type of block
     *
     * @return type;
     */
    public BlockType getType() {
        return type;
    }

    /**
     * Sets the type for the Block
     *
     * @param type
     */
    public void setType(BlockType type) {
        this.type = type;
    }

    /**
     * Calculates the Universal location of this block in the map
     */
    private void calculateUniversalLocation() {
        universalX = x + (Chunk.WIDTH * chunkX);
        universalY = y + (Chunk.LENGTH * chunkY);
        universalZ = z;
    }

    /**
     * Get UniversalX, recalculate if necessary.
     *
     * @return universalX
     */
    public int getUniversalX() {
        if (universalX < 0 || universalX > 32) {
            calculateUniversalLocation();
        }
        return universalX;
    }

    /**
     * Get UniversalY, recalculate if necessary.
     *
     * @return universalY
     */
    public int getUniversalY() {
        if (universalY < 0 || universalY > 32) {
            calculateUniversalLocation();
        }
        return universalY;
    }

    /**
     * Get UniversalZ, recalculate if necessary.
     *
     * @return universalZ
     */
    public int getUniversalZ() {
        if (universalZ < 0 || universalZ > 100) {
            calculateUniversalLocation();
        }
        return universalZ;
    }

}
