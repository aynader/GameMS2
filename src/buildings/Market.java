package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Market extends EconomicBuilding {

	public Market() {
		super(1500, 700);
	}

	public void upgrade() throws MaxLevelException, BuildingInCoolDownException {
		if (isCoolDown())
			throw new BuildingInCoolDownException("Building is cooling down, wait for the next turn!");
		if (getLevel() > 2)
			throw new MaxLevelException(
					"And then Java suddenly said, I am sorry.... mamaaa miaaaaa thats not how you upgrade a building,... cheat code for level 4: levelMEEEE ");
		else {
			setLevel(getLevel() + 1);
			setUpgradeCost(1000);
		}
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
				return 1000000; // cheat code you get 0 in ELCT ass
		}
	}

}
