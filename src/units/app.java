package units;

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
    
    Archer arch = new Archer(1,50,0.5,0.6,0.7);
    Infantry inf = new Infantry(1,50,0.5,0.6,0.7);
    arch.attack(inf);
    }

}
