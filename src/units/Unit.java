package units;

public abstract class Unit {
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
}
