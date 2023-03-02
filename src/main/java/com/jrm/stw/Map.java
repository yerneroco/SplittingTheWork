package com.jrm.stw;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Map {
    private int width;
    private int length;
    private double[][] temperatures;
    private double[][] rainFall;
    private double[][] biomes;
    private int[][] landMass;
    private Chunk[][] chunks;
    private long seed;

    public Map() {
        width = 10;
        length = 10;
        //chunks = generateMap(0);
        landMass = new int[width][length];
        chunks = new Chunk[width][length];
        seed = 12345;
    }

    public Map(int zero, int one) {
        width = 5;
        length = 5;
        landMass = new int[width][length];
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

    public Chunk[][] getMap() {

        return chunks;
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
        //initialize landMass as new array of this.width and this.length size
        int[][] land = new int[width][length];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                land[i][j] = random.nextInt(2); // generates 0 or 1
            }
        }
        //return the populated array
        return land;
    }

    public void generateLandRandom() {
        //uses this.seed to create random
        Random random = new Random(seed);
        //initialize landMass as new array of this.width and this.length size
        //int[][] landMass = new int[width][length];
        //place a random value, 1 or 0, in every part of the array
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                landMass[i][j] = random.nextInt(2); // generates 0 or 1
            }
        }
    }


    /**
     * creates a landmass map
     *
     * @return 2d array of whether or not a chunk is ocean or land
     */
    public static int[][] generateLandMass(long seed, int width, int length) {
        //uses seed to create random
        Random rand = new Random(seed);

        int[][] land = new int[width][length];
        land = generateLandRandom(seed, width, length);
        print(land);
        land = zoom(land);
        land = addIsland(land, seed);
        land = zoom(land);
        land = addIsland(land, seed);
        land = addIsland(land, seed);
        land = addIsland(land, seed);
        land = removeTooMuchOcean(land);
        land = addIsland(land, seed);
        land = zoom(land);
        land = zoom(land);
        land = addIsland(land, seed);

        return land;

    }

    public void generateLandMass() {
        //Random rand = new Random(seed);
        generateLandRandom();
        printLand();
        zoom();
        addIsland();
        zoom();
        addIsland();
        addIsland();
        addIsland();
        landMass = removeTooMuchOcean(landMass);
        addIsland();
        zoom();
        zoom();
        addIsland();

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

    private void printLand() {
        String out = "";
        for (int r = 0; r < length; r++) {
            for (int c = 0; c < width; c++) {
                out += landMass[c][r] + " ";
            }
            out += "\n";
        }
        System.out.println(out);

    }

    private void removeTooMuchOcean() {
        landMass = landMass;
    }

    private static int[][] removeTooMuchOcean(int[][] landMass) {

        return landMass;
    }


    private void addIsland() {
        int[][] newChunks = new int[width][length];
        Random rand = new Random(seed);

        int order = 0;
        for (int c = 0; c < width; c++) {
            for (int r = 0; r < length; r++) {
                //check number of water tiles surrounding current block
                int count = 0;
                if (landMass[c][r] == 0) {
                    //make sure square is not an edge
                    if (c != 0 && r != 0) {
                        if (c != width - 1 && r != length - 1) {

                            //checks amount of water surrounding square
                            for (int i = -1; i <= 1; i++) {
                                for (int j = -1; j <= 1; j++) {
                                    if (landMass[c + j][r + i] == 0) {
                                        count++;
                                    }
                                }
                            }
                        }
                    }
                    //Change chances of an island forming
                    if (count > 7) {
                        //if more than 7 out of 9 tiles are water
                        //50% chance of turning into water
                        if (rand.nextInt() % 2 == 0) {
                            newChunks[c][r] = 0;
                            //50% chance of turning into land
                        } else {
                            newChunks[c][r] = 1;
                        }
                    } else {
                        //if less than 7 out of 9 tiles are water
                        //keep this tile water
                        newChunks[c][r] = 0;
                    }
                    //if the tile is already land set new array to the same
                } else {
                    newChunks[c][r] = 1;
                }
                order++;
            }
        }
        landMass = newChunks;
    }

    private static int[][] addIsland(int[][] landMass, long seed) {
        int width = landMass.length;
        int length = landMass[0].length;
        int[][] newChunks = new int[width][length];
        Random rand = new Random(seed);

        int order = 0;
        for (int c = 0; c < width; c++) {
            for (int r = 0; r < width; r++) {

                //check number of water tiles surrounding current block
                int count = 0;
                if (landMass[c][r] == 0) {
                    //make sure square is not an edge
                    if (c != 0 && r != 0) {
                        if (c != width - 1 && r != length - 1) {

                            //checks amount of water surrounding square
                            for (int i = -1; i <= 1; i++) {
                                for (int j = -1; j <= 1; j++) {
                                    if (landMass[c + i][r + j] == 0) {
                                        count++;
                                    }
                                }
                            }
                        }
                    }
                    //Change chances of an island forming
                    if (count > 7) {
                        //if more than 7 out of 9 tiles are water
                        //50% chance of turning into water
                        if (rand.nextInt() % 2 == 0) {
                            newChunks[c][r] = 0;
                            //50% chance of turning into land
                        } else {
                            newChunks[c][r] = 1;
                        }
                    } else {
                        //if less than 7 out of 9 tiles are water
                        //keep this tile water
                        newChunks[c][r] = 0;
                    }
                    //if the tile is already land set new array to the same
                } else {
                    newChunks[c][r] = 1;
                }
                order++;
            }
        }
        //return new Map(width,length, newChunks);
        return newChunks;
    }


    private static int[][] zoom(int[][] landMass) {
        //create a new array 2x the size of the old one
        int newWidth = landMass.length * 2;
        int newLength = landMass[0].length * 2;
        int[][] newLandMass = new int[newWidth][newLength];
        //set every 4 tiles equal to one from the old array
        for (int r = 0; r < landMass.length; r++) {
            for (int c = 0; c < landMass[0].length; c++) {
                newLandMass[2 * c][2 * r] = landMass[c][r];
                newLandMass[(2 * c) + 1][2 * r] = landMass[c][r];
                newLandMass[2 * c][(2 * r) + 1] = landMass[c][r];
                newLandMass[(2 * c) + 1][(2 * r) + 1] = landMass[c][r];

            }
        }
        return newLandMass;
    }

    private void zoom() {
        //copy landmass into land
        int[][] land = new int[width][length];
        for (int r = 0; r < length; r++) {
            for (int c = 0; c < width; c++) {
                land[c][r] = landMass[c][r];
            }
        }
        //create new array 2x the size of old one
        int newWidth = width * 2;
        int newLength = length * 2;
        landMass = new int[newWidth][newLength];
        //set every 4 tiles equal to one from the old array
        for (int r = 0; r < length; r++) {
            for (int c = 0; c < width; c++) {
                landMass[2 * c][2 * r] = land[c][r];
                landMass[(2 * c) + 1][2 * r] = land[c][r];
                landMass[2 * c][(2 * r) + 1] = land[c][r];
                landMass[(2 * c) + 1][(2 * r) + 1] = land[c][r];

            }
        }

    }

    /*
    public Chunk[][] generateMap(){
        //A is 65
        int order = 1;
        Chunk[][] map = new Chunk[width][length];
        Random rand = new Random(seed);
        System.out.println(rand.nextDouble());
        for(int r = 0; r < length; r++){
            for(int c = 0; c < width; c++){

                double value = (Math.abs(rand.nextInt()) % 2);
                map[c][r] = new Chunk(c,r,value,order);
                order++;
            }
        }
        return map;
    }
    */

    /*
    public Chunk[][] generateMap(long seed, int zero, int one){
        //A is 65
        int order = 1;
        Chunk[][] map = new Chunk[width][length];
        Random rand = new Random(seed);
        //System.out.println(rand.nextDouble());
        for(int r = 0; r < length; r++){
            for(int c = 0; c < width; c++){

                double value = ( ((int)(rand.nextDouble()*10))/10.0);
                System.out.println(value);

                map[c][r] = new Chunk(c,r,value,order);
                order++;
            }
        }
        return map;
    }*/
/*
    public Map zoom(){
        //starting zoom is pixels = 4096 final blocks or 2^12 so 12 zooms
        int newWidth = width *2;
        int newlength = length *2;
        Chunk[][] newChunks = new Chunk[newWidth][newlength];
        for(int r = 0; r < length; r++){
            for(int c = 0; c < width; c++){
                newChunks[2*c][2*r] = chunks[c][r];
                newChunks[(2*c)+1][2*r] = chunks[c][r];
                newChunks[2*c][(2*r)+1] = chunks[c][r];
                newChunks[(2*c)+1][(2*r)+1] = chunks[c][r];

            }
        }
        return new Map(newWidth,newlength,newChunks);
//       width = newWidth;
//       length = newWidth;
//       chunks = newChunks;
//
//       return this;
    }
    */

    /*
    public Map addIsland(){
        Chunk[][] newChunks = new Chunk[width][length];
        Random rand = new Random(seed);
        int order = 0;
        for(int r = 0; r < length; r++){
            for(int c = 0; c < width; c++){
                int count = 0;
                if(chunks[c][r].value < 0.25){
                    //make sure square is not an edge
                    if(c != 0 && r != 0){
                        if(c != width-1 && r != length-1){
                            for(int i = -1; i <= 1; i++){
                                for(int j = -1; j <=1; j++){
                                    if(chunks[c+j][r+i].value == 0){
                                        count++;

                                    }
                                }
                            }
                        }
                    }
                    //Change chances of an island forming
                    if(count > 7){
                        if(rand.nextInt() % 2 == 0){
                            newChunks[c][r] = new Chunk(c,r, 0, order);
                        }else{
                            newChunks[c][r] = new Chunk(c,r, chunks[c][r].value, order);
                        }
                    }else{
                        newChunks[c][r] = new Chunk(c,r, 0, order);
                    }

                }else{
                    newChunks[c][r] = new Chunk(c,r,chunks[c][r].value,order);
                }
                order++;
            }
        }
        return new Map(width,length, newChunks);
    }*/
    public static void writeJsonToFile(Chunk chunk, String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(chunk);

        File directory = new File("chunks");
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(directory, fileName);
        objectMapper.writeValue(file, json);
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