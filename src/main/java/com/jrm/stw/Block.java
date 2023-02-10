package com.jrm.stw;

public class Block {
    private int x;
    private int y;
    private int z;
    private BlockType type;
    private Chunk chunk;

    public Block(int x, int y, int z, BlockType type, Chunk chunk) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;
        this.chunk = chunk;
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
