package engine;

import java.util.ArrayList;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.Farm;
import buildings.Market;
import buildings.Stable;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyCityException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import exceptions.TargetNotReachedException;
import units.Army;
import units.Status;
import units.Unit;

public class Player {
	private String name;
	private ArrayList<City> controlledCities;
	private ArrayList<Army> controlledArmies;
	private double treasury;
	private double food;

	public Player(String name) {
		this.name = name;
		this.controlledCities = new ArrayList<City>();
		this.controlledArmies = new ArrayList<Army>();
	}

	public double getTreasury() {
		return treasury;
	}

	public void setTreasury(double treasury) {
		this.treasury = treasury;
	}

	public double getFood() {
		return food;
	}

	public void setFood(double food) {
		this.food = food;
	}

	public String getName() {
		return name;
	}

	public ArrayList<City> getControlledCities() {
		return controlledCities;
	}

	public ArrayList<Army> getControlledArmies() {
		return controlledArmies;
	}

	public void recruitUnit(String type, String cityName)
			throws BuildingInCoolDownException, MaxRecruitedException, NotEnoughGoldException {
		City c = getControlledCities().get(0);
		for (int i = 0; i < getControlledCities().size(); i++) {
			if (getControlledCities().get(i).getName() == "cityName") {
				c = getControlledCities().get(i);
			}
		}
		Unit u = c.getMilitaryBuildings().get(0).recruit();
		int cost = 0;
		for (int i = 0; i < c.getMilitaryBuildings().size(); i++) {
			switch (type) {
				case "Archer":
					if (c.getMilitaryBuildings().get(i) instanceof ArcheryRange) {
						if (getTreasury() < c.getMilitaryBuildings().get(i).getRecruitmentCost()) {
							u = c.getMilitaryBuildings().get(i).recruit();
							cost = c.getMilitaryBuildings().get(i).getRecruitmentCost();
							u.setParentArmy(c.getDefendingArmy());

						}
					}
				case "Cavalry":
					if (c.getMilitaryBuildings().get(i) instanceof Stable) {
						if (getTreasury() < c.getMilitaryBuildings().get(i).getRecruitmentCost()) {
							u = c.getMilitaryBuildings().get(i).recruit();
							cost = c.getMilitaryBuildings().get(i).getRecruitmentCost();
							u.setParentArmy(c.getDefendingArmy());
						}
					}
				case "Infantry":
					if (c.getMilitaryBuildings().get(i) instanceof Barracks) {
						if (getTreasury() < c.getMilitaryBuildings().get(i).getRecruitmentCost()) {
							u = c.getMilitaryBuildings().get(i).recruit();
							cost = c.getMilitaryBuildings().get(i).getRecruitmentCost();
							u.setParentArmy(c.getDefendingArmy());
						}
					}
			}
		}
		c.getDefendingArmy().getUnits().add(u);
		setTreasury(getTreasury() - cost);
	}

	public void build(String type, String cityName) throws NotEnoughGoldException {
		City c = getControlledCities().get(0);
		for (int i = 0; i < getControlledCities().size(); i++) {
			if (getControlledCities().get(i).getName() == "cityName") {
				c = getControlledCities().get(i);
			}
		}
		int cost = 0;
		boolean market = false;
		boolean farm = false;
		boolean barracks = false;
		boolean archery = false;
		boolean stable = false;
		if (!c.getEconomicalBuildings().isEmpty()) {
			for (int i = 0; i < c.getEconomicalBuildings().size(); i++) {
				if (c.getEconomicalBuildings().get(i).getCost() == 1000)
					farm = true;
				if (c.getEconomicalBuildings().get(i).getCost() == 1500)
					market = true;
			}
		}
		// this loop acts as a flag to check if this type of building has already been
		// built
		if (!c.getMilitaryBuildings().isEmpty()) {
			for (int i = 0; i < c.getMilitaryBuildings().size(); i++) {
				if (c.getMilitaryBuildings().get(i).getCost() == 2000)
					barracks = true;
				if (c.getMilitaryBuildings().get(i).getCost() == 1500)
					archery = true;
				if (c.getMilitaryBuildings().get(i).getCost() == 2500)
					stable = true;
			}
		}
		switch (type) {
			case "Market":
				if (getTreasury() >= 1500 && !market) {
					Market m = new Market();
					c.getEconomicalBuildings().add(m);
					cost = 1500;
					m.setCoolDown(true);
				}
			case "Farm":
				if (getTreasury() >= 1000 && !farm) {
					Farm f = new Farm();
					c.getEconomicalBuildings().add(f);
					cost = 1000;
					f.setCoolDown(true);
				}
			case "Barracks":
				if (getTreasury() >= 2000 && !barracks) {
					Barracks br = new Barracks();
					c.getMilitaryBuildings().add(br);
					cost = 2000;
					br.setCoolDown(true);
				}
			case "ArcheryRange":
				if (getTreasury() >= 1500 && !archery) {
					ArcheryRange a = new ArcheryRange();
					c.getMilitaryBuildings().add(a);
					cost = 1500;
					a.setCoolDown(true);
				}
			case "Stable":
				if (getTreasury() >= 2500 && !stable) {
					Stable s = new Stable();
					c.getMilitaryBuildings().add(s);
					cost = 2500;
					s.setCoolDown(true);
				}
		}
		setTreasury(getTreasury() - cost);
	}

	public void upgradeBuilding(Building b)
			throws NotEnoughGoldException, BuildingInCoolDownException, MaxLevelException {
		if (getTreasury() >= b.getUpgradeCost()) {
			b.upgrade();
			setTreasury(getTreasury() - b.getUpgradeCost());
		}
	}

	public void initiateArmy(City city, Unit unit) {
		city.getDefendingArmy().getUnits().remove(unit);
		Army a = new Army(city.getName());
		a.getUnits().add(unit);
		unit.setParentArmy(a);
		getControlledArmies().add(a);
	}

	public void laySiege(Army army, City city) throws TargetNotReachedException, FriendlyCityException {
		army.setCurrentStatus(Status.BESIEGING);
		city.setUnderSiege(true);
		city.setTurnsUnderSiege(city.getTurnsUnderSiege()++);
	}
}
