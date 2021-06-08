package units;

import java.util.ArrayList;
import java.math.*;

import buildings.ArcheryRange;
import buildings.MilitaryBuilding;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyFireException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;

public class app {

    public static void main(String[] args) throws FriendlyFireException {
    
    Archer arch = new Archer(1,50,0.5,0.6,0.7);
    Infantry inf = new Infantry(1,50,0.5,0.6,0.7);
    Army a = new Army("Cairo") ;
    Army b = new Army("Alex") ;
    arch.setParentArmy(a);
    inf.setParentArmy(b);
    
    arch.attack(inf);
    }

}
