package com.jrm.stw;

public class Block {
    public int x;
    public int y;
    public int z;
    public Chunk chunk;
    public BlockType type;

    public enum BlockType {
        SAND, DIRT, AIR, STONE, GRASS, WATER
    }

    public Block(int x, int y, int z, Chunk chunk) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.chunk = chunk;
        this.type = BlockType.AIR;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public BlockType getType() {
        return type;
    }

    public Chunk getChunk() {
        return chunk;
    }
}
