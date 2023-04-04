package com.jrm.stw;

import java.util.Random;

public class Map {
    public final int width;
    public final int length;

    private Chunk[][] chunks;
    private long seed;

    public Map() {
        width = 10;
        length = 10;
        //chunks = generateMap(0);
        chunks = new Chunk[width][length];
        seed = 12345;
    }

    public Map(int zero, int one) {
        width = 5;
        length = 5;
        chunks = new Chunk[width][length];
        //chunks = generateMap(seed,zero,one);
        seed = 12345;
    }

    public Map(int width, int length, Chunk[][] chunks, long seed) {
        this.width = width;
        this.length = length;
        this.chunks = chunks;
        this.seed = seed;
    }

    public Map(Chunk[][] chunks) {
        this.chunks = chunks;
        width = chunks.length;
        length = chunks[0].length;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public Chunk[][] getChunks() {
        return chunks;
    }

    public void setChunks(Chunk[][] chunks) {
        this.chunks = chunks;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public void fillChunksWithAir() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                chunks[i][j] = new Chunk();
            }
        }
    }

    public void fillChunksRandom() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                chunks[i][j] = new Chunk();
                chunks[i][j].createLogicalBlockDistribution();
            }
        }
    }

    public static int[][] generateLandRandom(long seed, int width, int length) {
        //uses seed to create random
        Random random = new Random(seed);
        //initialize land as new array of this.width and this.length size
        int[][] land = new int[width][length];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                int rand = random.nextInt(10);
                //1 in 10 chance of being land
                if (rand >= 1) {
                    land[i][j] = 0;
                } else {
                    land[i][j] = 1;
                }
            }
        }
        //return the populated array
        return land;
    }


    private static void print(int[][] arr) {
        String out = "";
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[0].length; c++) {
                out += arr[c][r] + " ";
            }
            out += "\n";
        }
        System.out.println(out);

    }


    public String toString() {
        String map = "";
        for (int r = 0; r < width; r++) {
            for (int c = 0; c < length; c++) {
                map += chunks[c][r] + " ";
            }
            map += "\n";
        }
        return map;
    }
}