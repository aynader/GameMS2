package engine;

import java.util.ArrayList;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyCityException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import exceptions.TargetNotReachedException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
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
		if(!getControlledCities().isEmpty()) {
			for(int i = 0 ; i < getControlledCities().size();i++) {
				City currentCity = getControlledCities().get(i);
				if(!currentCity.getMilitaryBuildings().isEmpty()) {
					if(!currentCity.getMilitaryBuildings().isEmpty()) {
						for(int j = 0 ; j < currentCity.getMilitaryBuildings().size();j++) {
							MilitaryBuilding b = currentCity.getMilitaryBuildings().get(j);
							if(type.equals("Archer")) {
								if(b instanceof ArcheryRange) {
									if(b.isCoolDown()) {
										throw new BuildingInCoolDownException("Cool down");
									}
									if(b.getCurrentRecruit() >= b.getMaxRecruit()) {
										throw new MaxRecruitedException("Max recruit");
									}
									if(b.getRecruitmentCost() > getTreasury()) {
										throw new NotEnoughGoldException("Earn some gold");
									}
									Archer a = (Archer)b.recruit();
									a.setParentArmy(currentCity.getDefendingArmy());
									currentCity.getDefendingArmy().getUnits().add(a);
									setTreasury(getTreasury() - b.getRecruitmentCost());
								}
							}
							else if(type.equals("Infantry")) {
								if(b instanceof Barracks) {
									if(b.isCoolDown()) {
										throw new BuildingInCoolDownException("Cool down");
									}
									if(b.getCurrentRecruit() >= b.getMaxRecruit()) {
										throw new MaxRecruitedException("Max recruit");
									}
									if(b.getRecruitmentCost() > getTreasury()) {
										throw new NotEnoughGoldException("Earn some gold");
									}
									Infantry a = (Infantry)b.recruit();
									a.setParentArmy(currentCity.getDefendingArmy());
									currentCity.getDefendingArmy().getUnits().add(a);
									setTreasury(getTreasury() - b.getRecruitmentCost());
								}
								
							}
							else if(type.equals("Cavalry")) {
								if(b instanceof Stable) {
									if(b.isCoolDown()) {
										throw new BuildingInCoolDownException("Cool down");
									}
									if(b.getCurrentRecruit() >= b.getMaxRecruit()) {
										throw new MaxRecruitedException("Max recruit");
									}
									if(b.getRecruitmentCost() > getTreasury()) {
										throw new NotEnoughGoldException("Earn some gold");
									}
									Cavalry a = (Cavalry)b.recruit();
									a.setParentArmy(currentCity.getDefendingArmy());
									currentCity.getDefendingArmy().getUnits().add(a);
									setTreasury(getTreasury() - b.getRecruitmentCost());
								}
							}
						}
					}
				}
			}
		}
	}

	public void build(String type, String cityName) throws NotEnoughGoldException {
		City c = null;
		c = getControlledCities().get(getControlledCities().size() -1);
		for (int i = 0; i < getControlledCities().size(); i++) {
			if (getControlledCities().get(i).getName() == cityName) {
				c = getControlledCities().get(i);
				break;
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
				else if (c.getEconomicalBuildings().get(i).getCost() == 1500)
					market = true;
			}
		}
		// this loop acts as a flag to check if this type of building has already been
		// built
		if (!c.getMilitaryBuildings().isEmpty()) {
			for (int i = 0; i < c.getMilitaryBuildings().size(); i++) {
				if (c.getMilitaryBuildings().get(i).getCost() == 2000)
					barracks = true;
				else if (c.getMilitaryBuildings().get(i).getCost() == 1500)
					archery = true;
				else if (c.getMilitaryBuildings().get(i).getCost() == 2500)
					stable = true;
			}
		}
		switch (type) {
			case "Market":
				if (getTreasury() < 1500) {
					throw new NotEnoughGoldException("money is a problem for you, ha?");
				}
				if (!market) {
					Market m = new Market();
					c.getEconomicalBuildings().add(m);
					cost = 1500;
					m.setCoolDown(true);
				}
				break;
			case "Farm":
				if (getTreasury() < 1000) {
					throw new NotEnoughGoldException("money is a problem for you, ha?");
				}
				if (!farm) {
					Farm f = new Farm();
					c.getEconomicalBuildings().add(f);
					cost = 1000;
					f.setCoolDown(true);
				}
				break;
			case "Barracks":
				if (getTreasury() < 2000) {
					throw new NotEnoughGoldException("money is a problem for you, ha?");
				}
				if (!barracks) {
					Barracks br = new Barracks();
					c.getMilitaryBuildings().add(br);
					cost = 2000;
					br.setCoolDown(true);
				}
				break;
			case "ArcheryRange":
				if (getTreasury() < 1500) {
					throw new NotEnoughGoldException("money is a problem for you, ha?");
				}
				if (!archery) {
					ArcheryRange a = new ArcheryRange();
					c.getMilitaryBuildings().add(a);
					cost = 1500;
					a.setCoolDown(true);
				}
				break;
			case "Stable":
				if (getTreasury() < 2500) {
					throw new NotEnoughGoldException("money is a problem for you, ha?");
				}
				if (!stable) {
					Stable s = new Stable();
					c.getMilitaryBuildings().add(s);
					cost = 2500;
					s.setCoolDown(true);
				}
				break;
		}

		setTreasury(getTreasury() - cost);
	}

	public void upgradeBuilding(Building b)
			throws NotEnoughGoldException, BuildingInCoolDownException, MaxLevelException {
		if( getTreasury() < b.getUpgradeCost()){
			throw new NotEnoughGoldException("Cash money please!");
		}
		if(b.isCoolDown()){
			throw new BuildingInCoolDownException("I am building a building please wait!");
		}
		if(b.getLevel() > 2){
			throw new MaxLevelException("Reach the maxed level");
		}
		if (getTreasury() >= b.getUpgradeCost()) {
			setTreasury(getTreasury() - b.getUpgradeCost());
			b.upgrade();
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
		if(army.getCurrentLocation() != city.getName()){
			throw new TargetNotReachedException("You are so close yet so far away,havent reached the target yet!");
		}
		for(int i = 0 ; i < getControlledCities().size();i++){
			if(getControlledCities().get(i).getName().equals(city.getName())){
				throw new FriendlyCityException("Why are you punching yourself");
			}
		}
		army.setCurrentStatus(Status.BESIEGING);
		city.setUnderSiege(true);
		city.setTurnsUnderSiege(city.getTurnsUnderSiege() + 1);
	}
}
