package com.jrm.stw;

public class Main {
    public static void main(String arg[]){
        Map test = new Map(10,1);
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

        //Add Deep Ocean
    }
}