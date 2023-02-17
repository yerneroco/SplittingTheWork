package com.jrm.stw;

import java.io.IOException;

public class Main {
    public static void main(String arg[]) {
        Map test = new Map();
        test.fillChunksWithAir();
        Chunk[][] chunks = test.getMap();
        String fileName;

        try {
            for (int i = 0; i < chunks.length; i++) {
                for (int j = 0; j < chunks[0].length; j++) {
                    fileName = "chunk" + i + "_" + j + ".json";
                    Map.writeJsonToFile(chunks[i][j], fileName);
                }
            }
            System.out.println("JSON file written successfully");
        } catch (IOException e) {
            System.err.println("Error writing JSON file: " + e.getMessage());
        }

        //Add Deep Ocean
    }
}