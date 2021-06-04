package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Archer;
import units.Unit;

public class ArcheryRange extends MilitaryBuilding {

	public ArcheryRange() {
		super(1500, 800, 400);

	}

	public void upgrade() throws MaxLevelException, BuildingInCoolDownException {
		// if(chat.getInput == "Mohamed salah")
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
	////////////////////////

	public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException {
		if (isCoolDown())
			throw new BuildingInCoolDownException("Building is cooling down, wait for the next turn!");
		if (getMaxRecruit() == getCurrentRecruit())
			throw new MaxRecruitedException("Sorry, No more units can be added!");
		else {
			setCurrentRecruit(getCurrentRecruit() + 1);
			Archer arch;
			switch (getLevel()) {
				case 1:
					arch = new Archer(1, 60, 0.4, 0.5, 0.6);
					return arch;
				case 2:
					arch = new Archer(2, 60, 0.4, 0.5, 0.6);
					return arch;
				case 3:
					arch = new Archer(3, 70, 0.5, 0.6, 0.7);
					return arch;
				default:
					return null;
			}
		}
	}
}