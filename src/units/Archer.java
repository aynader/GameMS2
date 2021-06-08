package units;

import exceptions.FriendlyFireException;

public class Archer extends Unit {

	public Archer(int level, int maxSoldierConunt, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
		super(level, maxSoldierConunt, idleUpkeep, marchingUpkeep, siegeUpkeep);
	}

	public void attack(Unit target) throws FriendlyFireException {
		super.attack(target);
		double factor = 0.0;
		switch (getLevel()) {
			case 1:
				if (target instanceof Archer)
					factor = 0.3;
				else if (target instanceof Infantry)
					factor = 0.2;
				else if (target instanceof Cavalry)
					factor = 0.1;
			case 2:
				if (target instanceof Archer)
					factor = 0.4;
				else if (target instanceof Infantry)
					factor = 0.3;
				else if (target instanceof Cavalry)
					factor = 0.1;
			case 3:
				if (target instanceof Archer)
					factor = 0.5;
				else if (target instanceof Infantry)
					factor = 0.4;
				else if (target instanceof Cavalry)
					factor = 0.2;
			default:
				break;
		}

		target.setCurrentSoldierCount((int) (target.getCurrentSoldierCount() - factor * getCurrentSoldierCount()));

		if (target.getCurrentSoldierCount() <= 0) target.getParentArmy().handleAttackedUnit(target);
		
	}

}
