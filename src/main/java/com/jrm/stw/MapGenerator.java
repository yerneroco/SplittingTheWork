package com.jrm.stw;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MapGenerator {
    private double[][] temperatures;
    private double[][] rainFall;

    private Biome[][] biomes;
    private boolean[][] landMass;
    private Map map;
    private long seed;
    private int mapWidth;
    private int mapLength;

    public MapGenerator(){
        seed = 12345L;
        mapWidth = 10;
        mapLength = 10;

        temperatures = generateNoiseMap(mapWidth,mapLength);
        rainFall = generateNoiseMap(mapWidth,mapLength);


    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Biome[][] generateBiomes(int width, int length){

        return biomes;
    }
    public static double[][] generateNoiseMap(int width, int length) {
        double[][] noiseMap = new double[width][length];
        Random random = new Random();

        // Generate random values for the noise map
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                noiseMap[i][j] = random.nextDouble() * 10; // Generate random value between 0 and 10
            }
        }

        return noiseMap;
    }
    public static boolean[][] generateLandRandom(long seed, int width, int length) {
        //uses seed to create random
        Random random = new Random(seed);
        //initialize landMass as new array of this.width and this.length size
        boolean[][] land = new boolean[width][length];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                int rand = random.nextInt(10);
                //1 in 10 chance of being land
                if (rand >= 1) {
                    land[i][j] = false;
                } else {
                    land[i][j] = true;
                }
            }
        }
        //return the populated array
        return land;
    }

    public void generateLandRandom() {

        //uses this.seed to create random
        Random random = new Random(map.getSeed());
        //initialize landMass as new array of this.width and this.length size
        //int[][] landMass = new int[width][length];
        //place a random value, 1 or 0, in every part of the array
        for (int i = 0; i < map.width; i++) {
            for (int j = 0; j < map.length; j++) {
                int rand = random.nextInt(10);
                //1 in 10 chance of being land
                if (rand >= 1) {
                    landMass[i][j] = false;
                } else {
                    landMass[i][j] = true;
                }
            }
        }
    }


    /**
     * creates a landmass map
     *
     * @return 2d array of whether or not a chunk is ocean or land
     */
    public static boolean[][] generateLandMass(long seed, int width, int length) {
        //uses seed to create random
        Random rand = new Random(seed);

        boolean[][] land = new boolean[width][length];
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
        printLandToFile();
    }

    private static void print(boolean[][] land) {
        String out = "";
        for (int r = 0; r < land.length; r++) {
            for (int c = 0; c < land[0].length; c++) {
                out += land[c][r] + " ";
            }
            out += "\n";
        }
        System.out.println(out);

    }

    private void printLand() {
        String out = "";
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map.width; c++) {
                if (landMass[c][r]) {
                    out += 1 + " ";

                } else {
                    out += 0 + " ";

                }
            }
            out += "\n";
        }
        System.out.println(out);

    }

    private void printLandToFile() {
        try {
            // create a new directory called "landmass"
            File directory = new File("landMass");
            if (!directory.exists()) {
                directory.mkdir();
            }

            // create a new file called "landMass.txt" in the "landmass" directory
            File file = new File(directory, "landMass.txt");
            FileWriter fw = new FileWriter(file);

            // write the contents of the landMass array to the file
            for (int r = 0; r < map.length; r++) {
                for (int c = 0; c < map.width; c++) {
                    if (landMass[c][r]) {
                        fw.write(1 + " ");

                    } else {
                        fw.write(0 + " ");

                    }
                }
                fw.write("\n");
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    private void printLandToFile(String fileName) {
        try {
            // create a new directory called "landmass"
            File directory = new File("landMass");
            if (!directory.exists()) {
                directory.mkdir();
            }

            // create a new file called "landMass.txt" in the "landmass" directory
            File file = new File(directory, fileName);
            FileWriter fw = new FileWriter(file);

            // write the contents of the landMass array to the file
            for (int r = 0; r < map.length; r++) {
                for (int c = 0; c < map.width; c++) {
                    if (landMass[c][r]) {
                        fw.write(1 + " ");

                    } else {
                        fw.write(0 + " ");

                    }
                }
                fw.write("\n");
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    private void printLandToFile(boolean[][] land, String fileName) {
        try {
            // create a new directory called "landmass"
            File directory = new File("landMass");
            if (!directory.exists()) {
                directory.mkdir();
            }

            // create a new file called "landMass.txt" in the "landmass" directory
            File file = new File(directory, fileName);
            FileWriter fw = new FileWriter(file);

            // write the contents of the landMass array to the file
            for (int r = 0; r < landMass.length; r++) {
                for (int c = 0; c < land[0].length; c++) {
                    if (landMass[c][r]) {
                        fw.write(1 + " ");

                    } else {
                        fw.write(0 + " ");

                    }
                }
                fw.write("\n");
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }


    private void removeTooMuchOcean() {
        landMass = landMass;
    }

    private static boolean
            [][] removeTooMuchOcean(boolean[][] landMass) {

        return landMass;
    }


    private void addIsland() {
        int width = landMass.length;
        int length = landMass[0].length;
        boolean[][] newLand = new boolean[landMass.length][landMass[0].length];
        Random rand = new Random(seed);

        int order = 0;
        for (int c = 0; c < width; c++) {
            for (int r = 0; r < length; r++) {
                //check number of water tiles surrounding current block
                int count = 0;
                if (!landMass[c][r]) {
                    //make sure square is not an edge
                    if (c != 0 && r != 0) {
                        if (c != width - 1 && r != length - 1) {

                            //checks amount of water surrounding square
                            for (int i = -1; i <= 1; i++) {
                                for (int j = -1; j <= 1; j++) {
                                    if (!landMass[c + j][r + i]) {
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
                            newLand[c][r] = false;
                            //50% chance of turning into land
                        } else {
                            newLand[c][r] = true;
                        }
                    } else {
                        //if less than 7 out of 9 tiles are water
                        //keep this tile water
                        newLand[c][r] = false;
                    }
                    //if the tile is already land set new array to the same
                } else {
                    newLand[c][r] = true;
                }
                order++;
            }
        }
        landMass = newLand;
    }

    private static boolean[][] addIsland(boolean[][] landMass, long seed) {
        int width = landMass.length;
        int length = landMass[0].length;
        boolean[][] newLand = new boolean[width][length];
        Random rand = new Random(seed);

        int order = 0;
        for (int c = 0; c < width; c++) {
            for (int r = 0; r < length; r++) {

                //check number of water tiles surrounding current block
                int count = 0;
                if (!landMass[c][r]) {
                    //make sure square is not an edge
                    if (c != 0 && r != 0) {
                        if (c != width - 1 && r != length - 1) {

                            //checks amount of water surrounding square
                            for (int i = -1; i <= 1; i++) {
                                for (int j = -1; j <= 1; j++) {
                                    if (!landMass[c + i][r + j]) {
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
                            newLand[c][r] = false;
                            //50% chance of turning into land
                        } else {
                            newLand[c][r] = true;
                        }
                    } else {
                        //if less than 7 out of 9 tiles are water
                        //keep this tile water
                        newLand[c][r] = false;
                    }
                    //if the tile is already land set new array to the same
                } else {
                    newLand[c][r] = true;
                }
                order++;
            }
        }
        //return new Map(map.width,length, newLand);
        return newLand;
    }


    private static boolean[][] zoom(boolean[][] landMass) {
        //create a new array 2x the size of the old one
        int newWidth = landMass.length * 2;
        int newLength = landMass[0].length * 2;
        boolean[][] newLand = new boolean[newWidth][newLength];
        //set every 4 tiles equal to one from the old array
        for (int r = 0; r < landMass.length; r++) {
            for (int c = 0; c < landMass[0].length; c++) {
                newLand[2 * c][2 * r] = landMass[c][r];
                newLand[(2 * c) + 1][2 * r] = landMass[c][r];
                newLand[2 * c][(2 * r) + 1] = landMass[c][r];
                newLand[(2 * c) + 1][(2 * r) + 1] = landMass[c][r];

            }
        }
        return newLand;
    }

    private void zoom() {
        //copy landmass into land
        boolean[][] land = new boolean[map.width][map.length];
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map.width; c++) {
                land[c][r] = landMass[c][r];
            }
        }
        //create new array 2x the size of old one
        int width = map.width * 2;
        int length = map.length * 2;
        landMass = new boolean[width][length];
        //set every 4 tiles equal to one from the old array
        for (int r = 0; r < length / 2; r++) {
            for (int c = 0; c < width / 2; c++) {
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
        Chunk[][] newLand = new Chunk[newWidth][newlength];
        for(int r = 0; r < length; r++){
            for(int c = 0; c < width; c++){
                newLand[2*c][2*r] = chunks[c][r];
                newLand[(2*c)+1][2*r] = chunks[c][r];
                newLand[2*c][(2*r)+1] = chunks[c][r];
                newLand[(2*c)+1][(2*r)+1] = chunks[c][r];

            }
        }
        return new Map(newWidth,newlength,newLand);
//       width = newWidth;
//       length = newWidth;
//       chunks = newLand;
//
//       return this;
    }
    */

    /*
    public Map addIsland(){
        Chunk[][] newLand = new Chunk[width][length];
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
                            newLand[c][r] = new Chunk(c,r, 0, order);
                        }else{
                            newLand[c][r] = new Chunk(c,r, chunks[c][r].value, order);
                        }
                    }else{
                        newLand[c][r] = new Chunk(c,r, 0, order);
                    }

                }else{
                    newLand[c][r] = new Chunk(c,r,chunks[c][r].value,order);
                }
                order++;
            }
        }
        return new Map(width,length, newLand);
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
}
