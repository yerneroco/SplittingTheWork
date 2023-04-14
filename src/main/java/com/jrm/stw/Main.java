package com.jrm.stw;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Map test = timeMapGeneration();

        timeWriteToFile(test);

        //Add Deep Ocean
    }
    public static Map timeMapGeneration() {
        long startTime = System.nanoTime();

        MapGenerator mapGen = new MapGenerator();
        Map test = mapGen.getMap();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; // convert to milliseconds
        System.out.println("Time taken: " + duration + "ms");
        return test;
    }
    public static void timeWriteToFile(Map test){
        Chunk[][] chunks = test.getChunks();
        String fileName;
        long startTime = System.nanoTime();
        try {
            for (int x = 0; x < chunks.length; x++) {
                for (int y = 0; y < chunks[0].length; y++) {
                    fileName = "chunk" + x + "_" + y + ".json";
                    MapGenerator.writeJsonToFile(chunks[x][y], fileName);
                }
            }
            System.out.println("JSON file written successfully");
        } catch (IOException e) {
            System.err.println("Error writing JSON file: " + e.getMessage());
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; // convert to milliseconds
        System.out.println("Time taken: " + duration + "ms");

    }
}