package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Barracks extends MilitaryBuilding {

	public Barracks() {
		super(2000, 1000, 500);

	}


	public void upgrade() throws MaxLevelException, BuildingInCoolDownException {
		
		if (isCoolDown())
			throw new BuildingInCoolDownException("Building is cooling down, wait for the next turn!");
		if (getLevel() > 2)
			throw new MaxLevelException("You will try to upgrade the level and java will tell you ehhem Im sorry....");
		else {
			setLevel(getLevel() + 1);
			setUpgradeCost(1500);
			setRecruitmentCost(getRecruitmentCost() + 50);
		}
	}

}
