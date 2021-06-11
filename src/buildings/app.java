package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Infantry;
import units.Unit;

public class app {

    public static void main(String[] args) throws BuildingInCoolDownException, MaxLevelException, MaxRecruitedException {
        Barracks b = new Barracks();
        b.setCoolDown(true);
        Unit i = b.recruit();
        System.out.println(i.getCurrentSoldierCount());
        };
    }


