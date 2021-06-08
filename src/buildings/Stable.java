package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Cavalry;
import units.Unit;

public class Stable extends MilitaryBuilding {

	public Stable() {
		super(2500, 1500, 600);

	}

	public void upgrade() throws MaxLevelException, BuildingInCoolDownException {
		super.upgrade();
		setLevel(getLevel() + 1);
		setUpgradeCost(2000);
		setRecruitmentCost(getRecruitmentCost() + 50);
		setCoolDown(true);
		
	}

	public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException {
		if (isCoolDown())
			throw new BuildingInCoolDownException("Building is cooling down, wait for the next turn!");
		if (getMaxRecruit() == getCurrentRecruit())
			throw new MaxRecruitedException("Sorry, No more units can be added!");
		else {
			setCurrentRecruit(getCurrentRecruit() + 1);
			Cavalry cav;
			switch (getLevel()) {
				case 1:
					cav = new Cavalry(1, 50, 0.5, 0.6, 0.7);
					return cav;
				case 2:
					cav = new Cavalry(2, 50, 0.5, 0.6, 0.7);
					return cav;
				case 3:
					cav = new Cavalry(3, 60, 0.6, 0.7, 0.8);
					return cav;
				default:
					return null;

			}
		}
	}
}
