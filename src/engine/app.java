package engine;

import java.util.ArrayList;
import java.math.*;

import buildings.ArcheryRange;
import buildings.MilitaryBuilding;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyFireException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import units.Archer;
import units.Army;

public class app {

    public static void main(String[] args) throws FriendlyFireException, BuildingInCoolDownException, MaxRecruitedException, NotEnoughGoldException, MaxLevelException {
      double y = Math.random()*3;
    	System.out.println((int)y);

    }
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
