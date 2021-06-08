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

        while (i < 100000) {
            switch (getRandomNumber(1, 5)) {
                case 1:
                    num1++;
                case 2:
                    num2++;
                case 3:
                    num3++;
                case 4:
                    num4++;
                case 5:
                    num5++;
                default:
                    break;
            }
            i++;
        }
        System.out.println("Hello, my name is kokouPy and I will help  you today:");
        System.out.println(num1);
        System.out.println(num2);
        System.out.println(num3);
        System.out.println(num4);
        System.out.println(num5);
        System.out.println("Hi, I tested:" + num1 + num2 + num3 + num4 + num5);
        System.out.println("Hi, I tested without 5:" + num1 + num2 + num3 + num4);

    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
