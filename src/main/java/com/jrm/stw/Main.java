package com.jrm.stw;

public class Main {
    public static void main(String arg[]){
        Map test = new Map(0,1);
        System.out.println(test);
        test = test.zoom();
        System.out.println(test);
        test = test.addIsland();
        System.out.println(test);
        test = test.zoom();
        System.out.println(test);
        test = test.addIsland();
        System.out.println(test);
        test = test.addIsland();
        System.out.println(test);
        test = test.addIsland();
        System.out.println(test);
        //test = test.removeTooMuchOcean();
        //System.out.println(test);
        //Add temps
        test = test.addIsland();
        System.out.println(test);
        //balance temps
        test = test.zoom();
        System.out.println(test);
        test = test.zoom();
        System.out.println(test);
        test = test.addIsland();
        System.out.println(test);
        String fileName = "finals.json";
        test.writeChunkArrayToJsonFile(fileName);
        Map test2 = new Map(Map.readChunkArrayFromJsonFile(fileName));
        System.out.println(test2);

        //Add Deep Ocean
    }
}