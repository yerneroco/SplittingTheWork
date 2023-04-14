package com.jrm.stw;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MapGenerator {
    private int mapWidth;
    private int mapLength;
    private long seed;
    private Random rand;

    private double[][] temperatures;
    private double[][] rainFall;

    private Biome[][] biomes;

    private boolean[][] landMass;

    private Map map;
    private int newMapLength;


    public MapGenerator() {
        this(2, 2, 12345L);
    }


    public MapGenerator(int startWidth, int startLength, long seed) {
        this.mapWidth = startWidth;
        this.mapLength = startLength;
        this.seed = seed;
        rand = new Random(seed);

        generateLandMass();//creates final size

        temperatures = generateNoiseMap();
        rainFall = generateNoiseMap();
        generateBiomes();
        generateMap();

    }

    /**
     * Creates an array of booleans indicating whether a chunk of the map will be ocean or land.
     */
    public void generateLandMass() {
        generateStartLandRandom();
        int count = 0;
        printLand(count++, "init");
        zoom();
        printLand(count++, "zoom");
        addIsland();
        printLand(count++, "addIsland");
        zoom();
        printLand(count++, "zoom");
        addIsland();
        printLand(count++, "addIsland");
        addIsland();
        printLand(count++, "addIsland");
        addIsland();
        printLand(count++, "addIsland");
        landMass = removeTooMuchOcean(landMass);
        addIsland();
        printLand(count++, "addIsland");
        printLandToFile();
    }

    /**
     * Creates an initial landMass randomly
     */
    private void generateStartLandRandom() {
        //initialize landMass with starting map width and length
        landMass = new boolean[mapWidth][mapLength];
        //give each boolean in the array a value using a 1/10 chance of being land
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapLength; y++) {
                int isLand = rand.nextInt(10);
                landMass[x][y] = isLand < 1;
            }
        }

    }

    private void printLand(int num, String operation) {
        String directoryName = "generateLandMassSteps";
        String fileName = String.format("%02d_%s.txt", num, operation);
        String filePath = directoryName + "/" + fileName;
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            // loop through the 2d array
            for (int x = 0; x < mapWidth; x++) {
                for (int y = 0; y < mapLength; y++) {
                    if (landMass[x][y]) {
                        fileWriter.write("1 ");
                    } else {
                        fileWriter.write("0 ");
                    }
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void zoom() {
        //copy landmass into land
        boolean[][] land = new boolean[mapWidth][mapLength];
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapLength; y++) {
                land[x][y] = landMass[x][y];
            }
        }
        //create new array 2x the size of old one
        int newMapWidth = mapWidth * 2;
        int newMapLength = mapLength * 2;
        landMass = new boolean[newMapWidth][newMapLength];
        //set every 4 tiles equal to one from the old array
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapLength; y++) {
                boolean isLand = land[x][y];
                //adding randomness
                landMass[2 * x][2 * y] = isLand && rand.nextBoolean();
                landMass[(2 * x) + 1][2 * y] = isLand && rand.nextBoolean();
                landMass[2 * x][(2 * y) + 1] = isLand && rand.nextBoolean();
                landMass[(2 * x) + 1][(2 * y) + 1] = isLand && rand.nextBoolean();

            }
        }
        mapWidth = newMapWidth;
        mapLength = newMapLength;

    }

    private void addIsland() {
        //create new land array to hold new map with more islands
        boolean[][] newLand = new boolean[mapWidth][mapLength];
        //loop through all current chunks except edges
        for (int x = 1; x < mapWidth - 1; x++) {
            for (int y = 1; y < mapLength - 1; y++) {
                //only adding land so need to check ocean(false)
                if (!landMass[x][y]) {
                    //check number of water tiles surrounding current block
                    int count = 0;
                    //counts amount of water surrounding square
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if (!landMass[x + j][y + i]) {
                                count++;
                            }}}
                    //Change chances of an island forming
                    if (count > 7) {
                        //if the surrounding 8 tiles are water
                        //50% chance of turning into water
                        if (rand.nextInt() % 2 == 0) {
                            newLand[x][y] = false;
                            //50% chance of turning into land
                        } else {
                            newLand[x][y] = true;
                        }
                    } else {
                        //if less than 7 out of 9 tiles are water keep this tile water
                        newLand[x][y] = false;
                    }
                    //if the tile is already land set new array to the same
                } else {
                    newLand[x][y] = true;
                }}}
        landMass = newLand;
    }

    private static boolean[][] removeTooMuchOcean(boolean[][] landMass) {

        return landMass;
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
            for (int y = 0; y < mapLength; y++) {
                for (int x = 0; x < mapWidth; x++) {
                    if (landMass[x][y]) {
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

    public double[][] generateNoiseMap() {
        double[][] noiseMap = new double[mapWidth][mapLength];

        // Generate random values for the noise map
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapLength; y++) {
                noiseMap[x][y] = rand.nextDouble(); // Generate random value between 0 and 10
            }
        }

        return noiseMap;
    }

    public void generateBiomes() {
        printNoiseMap(temperatures, "Temperatures");
        printNoiseMap(rainFall, "RainFall");
        biomes = new Biome[mapWidth][mapLength];
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapLength; y++) {
                double temp = temperatures[x][y];
                double rain = rainFall[x][y];
                biomes[x][y] = Biome.Plains;
                if (rain > 0.50) {
                    biomes[x][y] = Biome.Forest;
                } else {
                    if (temp > 0.5) {
                        biomes[x][y] = Biome.Desert;
                    }
                }
            }
        }
    }

    private void printNoiseMap(double[][] noiseMap, String name) {
        String directoryName = "NoiseMaps";
        String fileName = String.format("%s.txt", name);
        String filePath = directoryName + "/" + fileName;
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            // loop through the 2d array
            for (int x = 0; x < noiseMap.length; x++) {
                for (int y = 0; y < noiseMap[x].length; y++) {
                    fileWriter.write(String.format("%f ", noiseMap[x][y]));
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateMap() {
        Chunk[][] chunks = new Chunk[mapWidth][mapLength];
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapLength; y++) {
                chunks[x][y] = new Chunk(x, y, biomes[x][y], landMass[x][y], seed);
            }
        }
        map = new Map(mapWidth, mapLength, seed, chunks);
    }

    private static Chunk[][] generateMap(int width, int length, Biome[][] biomes, boolean[][] landMass, long seed) {
        Chunk[][] chunks = new Chunk[width][length];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                chunks[x][y] = new Chunk(x, y, biomes[x][y], landMass[x][y], seed);
            }
        }
        return chunks;
    }

    public static Biome[][] generateBiomes(double[][] temperatures, double[][] rainFall, int mapWidth, int mapLength) {
        //printNoiseMap(temperatures, "Temperatures");
        //printNoiseMap(rainFall, "RainFall");
        Biome[][] biomes = new Biome[mapWidth][mapLength];
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapLength; y++) {
                double temp = temperatures[x][y];
                double rain = rainFall[x][y];
                biomes[x][y] = Biome.Plains;
                if (rain > 0.50) {
                    biomes[x][y] = Biome.Forest;
                } else {
                    if (temp > 0.5) {
                        biomes[x][y] = Biome.Desert;
                    }
                }
            }
        }


        return biomes;
    }

    public static double[][] generateNoiseMap(int width, int length, long seed) {
        double[][] noiseMap = new double[width][length];
        Random random = new Random(seed);

        // Generate random values for the noise map
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                noiseMap[x][y] = random.nextDouble() * 10; // Generate random value between 0 and 10
            }
        }

        return noiseMap;
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
        land = generateStartLandRandom(seed, width, length);
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

    public static boolean[][] generateStartLandRandom(long seed, int width, int length) {
        //uses seed to create random
        Random random = new Random(seed);
        //initialize landMass as new array of this.width and this.length size
        boolean[][] land = new boolean[width][length];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                int rand = random.nextInt(10);
                //1 in 10 chance of being land
                if (rand >= 1) {
                    land[x][y] = false;
                } else {
                    land[x][y] = true;
                }
            }
        }
        //return the populated array
        return land;
    }

    private static void print(boolean[][] land) {
        String out = "";
        for (int y = 0; y < land.length; y++) {
            for (int x = 0; x < land[0].length; x++) {
                out += land[x][y] + " ";
            }
            out += "\n";
        }
        System.out.println(out);

    }

    private static boolean[][] zoom(boolean[][] landMass) {
        //create a new array 2x the size of the old one
        int newWidth = landMass.length * 2;
        int newLength = landMass[0].length * 2;
        boolean[][] newLand = new boolean[newWidth][newLength];
        //set every 4 tiles equal to one from the old array
        for (int y = 0; y < landMass.length; y++) {
            for (int x = 0; x < landMass[0].length; x++) {
                newLand[2 * x][2 * y] = landMass[x][y];
                newLand[(2 * x) + 1][2 * y] = landMass[x][y];
                newLand[2 * x][(2 * y) + 1] = landMass[x][y];
                newLand[(2 * x) + 1][(2 * y) + 1] = landMass[x][y];

            }
        }
        return newLand;
    }

    private static boolean[][] addIsland(boolean[][] landMass, long seed) {
        int width = landMass.length;
        int length = landMass[0].length;
        boolean[][] newLand = new boolean[width][length];
        Random rand = new Random(seed);

        int order = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {

                //check number of water tiles surrounding current block
                int count = 0;
                if (!landMass[x][y]) {
                    //make sure square is not an edge
                    if (x != 0 && y != 0) {
                        if (x != width - 1 && y != length - 1) {

                            //checks amount of water surrounding square
                            for (int i = -1; i <= 1; i++) {
                                for (int j = -1; j <= 1; j++) {
                                    if (!landMass[x + i][y + j]) {
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
                            newLand[x][y] = false;
                            //50% chance of turning into land
                        } else {
                            newLand[x][y] = true;
                        }
                    } else {
                        //if less than 7 out of 9 tiles are water
                        //keep this tile water
                        newLand[x][y] = false;
                    }
                    //if the tile is already land set new array to the same
                } else {
                    newLand[x][y] = true;
                }
                order++;
            }
        }
        //return new Map(map.width,length, newLand);
        return newLand;
    }

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
        Block[][][] blocks = chunk.getBlocks();
        int[][][] blks = new int[32][32][100];

        // Loop through the array and add each element to the JSON array
        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {
                for (int z = 0; z < blocks[x][y].length; z++) {
                    blks[x][y][z] = blocks[x][y][z].getType().ordinal();
                }
            }
        }

        // Create directory if it doesn't exist
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        ObjectMapper mapper = new ObjectMapper();
        // Write the JSON string to a file
        File file = new File(directory, fileName);
        mapper.writeValue(file,blks);
    }


    private void printLand() {
        String out = "";
        //loop through the 2d array
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapLength; y++) {
                if (landMass[x][y]) {
                    out += 1 + " ";

                } else {
                    out += 0 + " ";

                }
            }
            out += "\n";
        }
        System.out.println(out);

    }

    /**
     * Return the map object generated
     *
     * @return map generated map
     */
    public Map getMap() {
        return map;
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
            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map.width; x++) {
                    if (landMass[x][y]) {
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
            for (int y = 0; y < landMass.length; y++) {
                for (int x = 0; x < land[0].length; x++) {
                    if (landMass[x][y]) {
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

    private void removeTooMuchOcean() {
        landMass = landMass;
    }
}
