package units;

import exceptions.FriendlyFireException;

public class Cavalry extends Unit {

	public Cavalry(int level, int maxSoldierConunt, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
		super(level, maxSoldierConunt, idleUpkeep, marchingUpkeep, siegeUpkeep);
	}

	public void attack(Unit target) throws FriendlyFireException {
		if (this.getParentArmy() == target.getParentArmy()) {
			throw new FriendlyFireException("Sorry, You can't kill your own troops!");
		}
		double factor = 0.0;
		switch (getLevel()) {
			case 1:
				if (target instanceof Archer)
					factor = 0.5;
				else if (target instanceof Infantry)
					factor = 0.3;
				else if (target instanceof Cavalry)
					factor = 0.2;
			case 2:
				if (target instanceof Archer)
					factor = 0.6;
				else if (target instanceof Infantry)
					factor = 0.4;
				else if (target instanceof Cavalry)
					factor = 0.2;
			case 3:
				if (target instanceof Archer)
					factor = 0.7;
				else if (target instanceof Infantry)
					factor = 0.5;
				else if (target instanceof Cavalry)
					factor = 0.3;
			default:
				break;
		}

		target.setCurrentSoldierCount((int) (target.getCurrentSoldierCount() - factor * getCurrentSoldierCount()));

		if (target.getCurrentSoldierCount() <= 0) target.getParentArmy().handleAttackedUnit(target);
	}

}
