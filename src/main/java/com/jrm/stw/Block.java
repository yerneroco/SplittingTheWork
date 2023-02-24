package com.jrm.stw;

import java.io.Serializable;

public class Block implements Serializable {
    private int x, y, z;
    private int chunkX, chunkZ;
    private int universalX, universalY, universalZ;
    private BlockType type;

    public enum BlockType {
        SAND, DIRT, AIR, STONE, GRASS, WATER
    }

    public Block() {
        universalX = 0;
        universalY = 0;
        universalZ = 0;
        x = 0;
        y = 0;
        z = 0;
        chunkX = 0;
        chunkZ = 0;
        type = BlockType.AIR;

    }

    public Block(int x, int y, int z, int chunkX, int chunkZ, BlockType type) {

        if (x > 0 && y > 0 && z > 0 && chunkX > 0 && chunkZ > 0) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.chunkX = chunkX;
            this.chunkZ = chunkZ;
            calculateUniversalLocation();
        } else {
            System.out.println("Invalid Block Location at Constructor: " + x + "," + y + "," + z + " " + chunkX + "," + chunkZ);
        }

        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x > 0) {
            this.x = x;
            calculateUniversalLocation();
        } else {
            System.out.println("Invalid X Block Location at: " + x + "," + y + "," + z + " " + chunkX + "," + chunkZ);
        }
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y > 0) {
            this.y = y;
            calculateUniversalLocation();
        } else {
            System.out.println("Invalid Y Block Location at: " + x + "," + y + "," + z + " " + chunkX + "," + chunkZ);
        }
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        if (z > 0) {
            this.z = z;
            this.universalZ = z;
        } else {
            System.out.println("Invalid Z Block Location at: " + x + "," + y + "," + z + " " + chunkX + "," + chunkZ);
        }
    }

    public int getChunkX() {
        return chunkX;
    }

    public void setChunkX(int chunkX) {
        if (chunkX > 0) {
            this.chunkX = chunkX;
            calculateUniversalLocation();
        } else {
            System.out.println("Invalid X Chunk Location at: " + x + "," + y + "," + z + " " + this.chunkX + "," + chunkZ);
        }
    }

    public int getChunkZ() {
        return chunkZ;
    }

    public void setChunkZ(int chunkZ) {
        if (chunkZ > 0) {
            this.chunkZ = chunkZ;
            calculateUniversalLocation();
        } else {
            System.out.println("Invalid Z Chunk Location at: " + x + "," + y + "," + z + " " + chunkX + "," + this.chunkZ);
        }
    }

    public BlockType getType() {
        return type;
    }

    public void setType(BlockType type) {
        this.type = type;
    }

    public void calculateUniversalLocation() {
        universalX = x + (32 * Chunk.WIDTH);
        universalY = y;
        universalZ = z + (32 * Chunk.HEIGHT);
    }

    public int getUniversalX() {
        if (universalX < 0) {
            calculateUniversalLocation();
        }
        return universalX;
    }

    public int getUniversalY() {
        if (universalY < 0) {
            calculateUniversalLocation();
        }
        return universalY;
    }

    public int getUniversalZ() {
        if (universalZ < 0) {
            calculateUniversalLocation();
        }
        return universalZ;
    }


    public void destroy() {

    }
}
