package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class ArcheryRange extends MilitaryBuilding {

	public ArcheryRange() {
		super(1500, 800, 400);

	}

	public void upgrade() throws MaxLevelException, BuildingInCoolDownException {
		//if(chat.getInput == "Mohamed salah")
		if (isCoolDown())
			throw new BuildingInCoolDownException("Building is cooling down, wait for the next turn!");
		if (getLevel() > 2)
			throw new MaxLevelException("You will try to upgrade the level and java will tell you ehhem Im sorry....");
		else {
			setLevel(getLevel() + 1);
			setUpgradeCost(700);
			setRecruitmentCost(getRecruitmentCost() + 50);
		}
	}

}
