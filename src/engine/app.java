package engine;

import java.util.ArrayList;
import java.math.*;

import buildings.ArcheryRange;
import buildings.MilitaryBuilding;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;

public class app {

    public static void main(String[] args) {
        int i = 0;
        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        int num4 = 0;
        int num5 = 0;

        while (i < 100000000) {
            int x = getRandomNumber(1, 5);
            // System.out.println(x);
            if(x == 1)
                num1++;
            if(x == 2)
                num2++;
            if(x == 3)
                num3++;
            if(x == 4)
                num4++;
            if(x == 5)
                num5++;
            i++;
        }
        System.out.println("Hello, my name is kokouPy and I will help  you today:");
        System.out.println(num1);
        System.out.println(num2);
        System.out.println(num3);
        System.out.println(num4);
        System.out.println(num5);
        int sum = num1 + num2 + num3 + num4;
        System.out.println("Hi, I tested:" + sum);
        System.out.println("Hi, I without 5:" + sum);
        
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public static int flipCoin(int Number){
        return (int) Math.random() * Number; 
    }

}
