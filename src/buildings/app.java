package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class app {

    public static void main(String[] args) throws BuildingInCoolDownException, MaxLevelException {
        // Farm f = new Farm();
        // System.out.println(f.getLevel() + " " + f.getUpgradeCost());
        // f.upgrade();
        // System.out.println(f.getLevel() + " " + f.getUpgradeCost());
        // f.upgrade();
        // System.out.println(f.getLevel() + " " + f.getUpgradeCost());
        // f.upgrade();
        // System.out.println(f.getLevel() + " " + f.getUpgradeCost());


        Market m = new Market();
        
        System.out.println(m.getLevel() + " " + m.getUpgradeCost());
        m.upgrade();
        m.setCoolDown(false);
        System.out.println(m.getLevel() + " " + m.getUpgradeCost());
        m.upgrade();
        m.setCoolDown(false);
        System.out.println(m.getLevel() + " " + m.getUpgradeCost());
        m.upgrade();
        m.setCoolDown(false);
        System.out.println(m.getLevel() + " " + m.getUpgradeCost());


    }

}
