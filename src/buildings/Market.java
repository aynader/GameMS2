package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Market extends EconomicBuilding {

	public Market() {
		super(1500, 700);
	}

	public void upgrade() throws MaxLevelException, BuildingInCoolDownException {
		super.upgrade();
		setLevel(getLevel() + 1);
		setUpgradeCost(1000);
		setCoolDown(true);
		//System.out.println("Level: " + getLevel() + "Cool Down: " + isCoolDown() );
	}

	public int harvest() {
		switch (getLevel()) {
			case 1:
				return 1000;
			case 2:
				return 1500;
			case 3:
				return 2000;
			default:
				return 0;
		}
	}

}
