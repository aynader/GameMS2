package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Farm extends EconomicBuilding {

	public Farm() {
		super(1000, 500);
	}

	public void upgrade() throws MaxLevelException, BuildingInCoolDownException {
		if (isCoolDown())
			throw new BuildingInCoolDownException("Building is cooling down, wait for the next turn!");
		if (getLevel() > 2)
			throw new MaxLevelException("You will try to upgrade the level and java will tell you ehhem Im sorry....");
		else {
			setLevel(getLevel() + 1);
			setUpgradeCost(700);
		}
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
				return 1000000; // cheat code you get 0 in ELCT ass
		}
	}

}
