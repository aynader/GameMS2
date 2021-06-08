package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Farm extends EconomicBuilding {

	public Farm() {
		super(1000, 500);
	}

	public void upgrade() throws MaxLevelException, BuildingInCoolDownException {
		super.upgrade();
		setLevel(getLevel() + 1);
		setUpgradeCost(700);
		setCoolDown(true);
		//System.out.println("Level: " + getLevel() + "Cool Down: " + isCoolDown() );
	}

	public int harvest() {
		switch (getLevel()) {
			case 1:
				return 500; 
			case 2:
				return 700;
			case 3:
				return 1000;
			default:
				return 0; 
		}
	}

}
