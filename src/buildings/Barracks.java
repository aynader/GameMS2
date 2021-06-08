package buildings;

import java.lang.System.Logger.Level;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Infantry;
import units.Unit;

public class Barracks extends MilitaryBuilding {

	public Barracks() {
		super(2000, 1000, 500);

	}

	public void upgrade() throws MaxLevelException, BuildingInCoolDownException {
		super.upgrade();
		setLevel(getLevel() + 1);
		setUpgradeCost(1500);
		setRecruitmentCost(getRecruitmentCost() + 50);
		setCoolDown(true);
		
	}

	public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException {
		if (isCoolDown())
			throw new BuildingInCoolDownException("Building is cooling down, wait for the next turn!");
		if (getMaxRecruit() == getCurrentRecruit())
			throw new MaxRecruitedException("Sorry, No more units can be recruited in this turn!");
		else {
			setCurrentRecruit(getCurrentRecruit() + 1);
			Infantry inf;
			switch (getLevel()) {
				case 1:
					inf = new Infantry(1, 50, 0.5, 0.6, 0.7);
					return inf;
				case 2:
					inf = new Infantry(2, 50, 0.5, 0.6, 0.7);
					return inf;
				case 3:
					inf = new Infantry(3, 60, 0.6, 0.7, 0.8);
					return inf;
				default:
					return null;
			}
		}
	}
}
