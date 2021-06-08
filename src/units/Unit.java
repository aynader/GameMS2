package units;

import java.util.Set;
import exceptions.FriendlyFireException;

public abstract class Unit {
	private Army parentArmy;
	private int level;
	private int maxSoldierCount;
	private int currentSoldierCount;
	private double idleUpkeep;
	private double marchingUpkeep;
	private double siegeUpkeep;

	public Unit(int level, int maxSoldierConunt, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
		this.level = level;
		this.maxSoldierCount = maxSoldierConunt;
		this.idleUpkeep = idleUpkeep;
		this.marchingUpkeep = marchingUpkeep;
		this.siegeUpkeep = siegeUpkeep;
		this.currentSoldierCount = maxSoldierConunt;

	}

	public Army getParentArmy() {
		return parentArmy;
	}

	public void setParentArmy(Army parentArmy) {
		this.parentArmy = parentArmy;
	}

	public int getCurrentSoldierCount() {
		return currentSoldierCount;
	}

	public void setCurrentSoldierCount(int currentSoldierCount) {
		this.currentSoldierCount = currentSoldierCount;
	}

	public int getLevel() {
		return level;
	}

	public int getMaxSoldierCount() {
		return maxSoldierCount;
	}

	public double getIdleUpkeep() {
		return idleUpkeep;
	}

	public double getMarchingUpkeep() {
		return marchingUpkeep;
	}

	public double getSiegeUpkeep() {
		return siegeUpkeep;
	}

	public void attack(Unit target) throws FriendlyFireException{
		if (this.getParentArmy().equals(target.getParentArmy())) {
			throw new FriendlyFireException("Sorry, You can't kill your own troops!");
		}
	}
}


// switch (this.level) {
// 	case 1:
// 		if (idleUpkeep == 0.4)
// 			factor = 0.3;
// 		else if (idleUpkeep == 0.5)
// 			factor = 0.2;
// 		else if (idleUpkeep == 0.6)
// 			factor = 0.1;
// 	case 2:
// 		if (idleUpkeep == 0.4)
// 			factor = 0.4;
// 		else if (idleUpkeep == 0.5)
// 			factor = 0.3;
// 		else if (idleUpkeep == 0.6)
// 			factor = 0.1;
// 	case 3:
// 		if (idleUpkeep == 0.5)
// 			factor = 0.5;
// 		else if (idleUpkeep == 0.6)
// 			factor = 0.4;
// 		else if (idleUpkeep == 0.7)
// 			factor = 0.2;
// 	default:
// 		break;
// }