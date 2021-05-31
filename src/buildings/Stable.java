package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Stable extends MilitaryBuilding {

	public Stable() {
		super(2500, 1500, 600);

	}

	public void upgrade() throws MaxLevelException, BuildingInCoolDownException {
		
		if (isCoolDown())
			throw new BuildingInCoolDownException("Building is cooling down, wait for the next turn!");
		if (getLevel() > 2)
			throw new MaxLevelException("You will try to upgrade the level and java will tell you ehhem Im sorry....");
		else {
			setLevel(getLevel() + 1);
			setUpgradeCost(2000);
			setRecruitmentCost(getRecruitmentCost() + 50);
		}
	}

}
