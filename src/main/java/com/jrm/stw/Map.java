package com.jrm.stw;

import java.util.Random;

public class Map {
    public final int width;
    public final int length;
    private long seed;
    private Random rand;

    private Chunk[][] chunks;

    public Map() {
        width = 10;
        length = 10;
        //chunks = generateMap(0);
        chunks = new Chunk[width][length];
        seed = 12345;
        //Biome[][] biomes = MapGenerator.generateNoiseMap(width,length,seed);
        //this(10,10, )
    }

    public Map(int width, int length, Biome[][] biomes, int[][] land, long seed) {
        this.width = width;
        this.length = length;
        chunks = new Chunk[width][length];

        this.chunks = chunks;
        this.seed = seed;
    }

    public Map(int width, int length, Long seed, Chunk[][] chunks){
        this.width = width;
        this.length = length;
        this.seed = seed;
        rand = new Random(seed);

        setChunks(chunks);
    }

    private void fillMap(Biome[][] biomes, boolean[][] land) {
        for (int c = 0; c < width; c++) {
            for (int r = 0; r < length; r++) {
                chunks[c][r] = new Chunk(c,r,biomes[c][r],land[c][r],seed);
            }
        }
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
        this.chunks = new Chunk[chunks.length][chunks[0].length];
        for(int x = 0; x< width;x++) {
            for (int y = 0; y < length; y++) {
                this.chunks[x][y] = new Chunk(chunks[x][y]);
            }
        }
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public void fillMapVoid() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                chunks[i][j] = new Chunk();
            }
        }
    }

    public void fillMapRandom() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                chunks[i][j] = new Chunk();
                chunks[i][j].createLogicalBlockDistribution();
            }
        }
    }
    public Chunk getChunk(int x, int y){
        return chunks[x][y];
    }
    public void setChunk(int x, int y, Chunk chunk){
        chunks[x][y] = chunk;
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