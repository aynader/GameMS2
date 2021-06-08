package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class app {

    public static void main(String[] args) throws BuildingInCoolDownException, MaxLevelException {
        // Farm f = new Farm();
        // f.setLevel(1);
        // f.upgrade();

        Market m = new Market();
        m.setLevel(1);
        m.upgrade();
        m.upgrade();
        m.upgrade();
            
        };
    }


