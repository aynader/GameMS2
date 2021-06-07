package engine;

import java.util.ArrayList;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Stable;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import units.Army;
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

}
