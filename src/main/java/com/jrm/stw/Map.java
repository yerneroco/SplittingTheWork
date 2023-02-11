package com.jrm.stw;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Map{
    private int width;
    private int height;
    private Chunk[][] chunks;
    private int seed;
    public Map(){
        width = 30;
        height = 30;
        chunks = generateMap(0);
        seed = 12345;
    }
    public Map(int zero, int one){
        width = 5;
        height = 5;
        chunks = generateMap(seed,zero,one);
        seed  = 12345;
    }
    public Map(int width,int height,Chunk[][] chunks){
        this.width = width;
        this.height = height;
        this.chunks = chunks;
    }
    public Map(Chunk[][] chunks){
        this.chunks = chunks;
        width = chunks.length;
        height = chunks[0].length;
    }
    public Chunk[][] getMap(){
        return chunks;
    }

    public Chunk[][] generateMap(int seed){
        //A is 65
        int order = 1;
        Chunk[][] map = new Chunk[width][height];
        Random rand = new Random(seed);
        System.out.println(rand.nextDouble());
        for(int r = 0; r < height; r++){
            for(int c = 0; c < width; c++){

                double value = (Math.abs(rand.nextInt()) % 2);
                map[c][r] = new Chunk(c,r,value,order);
                order++;
            }
        }
        return map;
    }
    public Chunk[][] generateMap(int seed, int zero, int one){
        //A is 65
        int order = 1;
        Chunk[][] map = new Chunk[width][height];
        Random rand = new Random(seed);
        //System.out.println(rand.nextDouble());
        for(int r = 0; r < height; r++){
            for(int c = 0; c < width; c++){

                double value = ( ((int)(rand.nextDouble()*10))/10.0);
                System.out.println(value);

                map[c][r] = new Chunk(c,r,value,order);
                order++;
            }
        }
        return map;
    }

    public Map zoom(){
        //starting zoom is pixels = 4096 final blocks or 2^12 so 12 zooms
        int newWidth = width *2;
        int newHeight = height *2;
        Chunk[][] newChunks = new Chunk[newWidth][newHeight];
        for(int r = 0; r < height; r++){
            for(int c = 0; c < width; c++){
                newChunks[2*c][2*r] = chunks[c][r];
                newChunks[(2*c)+1][2*r] = chunks[c][r];
                newChunks[2*c][(2*r)+1] = chunks[c][r];
                newChunks[(2*c)+1][(2*r)+1] = chunks[c][r];

            }
        }
        return new Map(newWidth,newHeight,newChunks);
//       width = newWidth;
//       height = newWidth;
//       chunks = newChunks;
//
//       return this;
    }

    public Map addIsland(){
        Chunk[][] newChunks = new Chunk[width][height];
        Random rand = new Random(seed);
        int order = 0;
        for(int r = 0; r < height; r++){
            for(int c = 0; c < width; c++){
                int count = 0;
                if(chunks[c][r].value < 0.25){
                    //make sure square is not an edge
                    if(c != 0 && r != 0){
                        if(c != width-1 && r != height-1){
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
        return new Map(width,height, newChunks);
    }
    public static void writeChunkArrayToJsonFile(Chunk[][] chunkArray, String fileName) {
        JSONArray jsonArray = new JSONArray();
        for (Chunk[] chunks : chunkArray) {
            JSONArray innerJsonArray = new JSONArray();
            for (Chunk chunk : chunks) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("element", chunk);
                innerJsonArray.add(jsonObject);
            }
            jsonArray.add(innerJsonArray);
        }
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public void writeChunkArrayToJsonFile(String fileName) {
//        JSONArray chunkArray = new JSONArray();
//        for (Chunk[] chunkRow : chunks) {
//            JSONArray rowArray = new JSONArray();
//            for (Chunk chunk : chunkRow) {
//                JSONObject chunkObject = new JSONObject();
//                chunkObject.put("x", chunk.x);
//                chunkObject.put("y", chunk.y);
//                chunkObject.put("value", chunk.value);
//                chunkObject.put("order", chunk.order);
//                rowArray.add(chunkObject);
//            }
//            chunkArray.add(rowArray);
//        }
//
//        try (FileWriter file = new FileWriter(fileName)) {
//            file.write(chunkArray.toJSONString());
//            file.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public static Chunk[][] readChunkArrayFromJsonFile(String fileName) {
//        Chunk[][] chunks = null;
//        try (FileReader reader = new FileReader(fileName)) {
//            JSONParser parser = new JSONParser();
//            JSONArray jsonArray = (JSONArray) parser.parse(reader);
//
//            chunks = new Chunk[jsonArray.size()][];
//            for (int i = 0; i < jsonArray.size(); i++) {
//                JSONArray innerArray = (JSONArray) jsonArray.get(i);
//                chunks[i] = new Chunk[innerArray.size()];
//                for (int j = 0; j < innerArray.size(); j++) {
//                    JSONObject jsonObject = (JSONObject) innerArray.get(j);
//                    int x = ((Long) jsonObject.get("x")).intValue();
//                    int y = ((Long) jsonObject.get("y")).intValue();
//                    double value = (Double) jsonObject.get("value");
//                    int order = ((Long) jsonObject.get("order")).intValue();
//                    chunks[i][j] = new Chunk(x, y, value, order);
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return chunks;
//    }
//

    public String toString(){
        String map = "";
        for(int r = 0; r < height; r++){
            for(int c = 0; c < width; c++){
                map += chunks[c][r].value + " ";
            }
            map += "\n";
        }
        return map;
    }
}