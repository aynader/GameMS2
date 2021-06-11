package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.math.*;

import buildings.Farm;
import exceptions.FriendlyFireException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Status;
import units.Unit;

public class Game {
	private Player player;
	private ArrayList<City> availableCities;
	private ArrayList<Distance> distances;
	private final int maxTurnCount = 30;
	private int currentTurnCount;

	public Game(String playerName, String playerCity) throws IOException {

		player = new Player(playerName);
		availableCities = new ArrayList<City>();
		distances = new ArrayList<Distance>();
		currentTurnCount = 1;
		loadCitiesAndDistances();
		for (City c : availableCities) {
			if (c.getName().equals(playerCity))

				player.getControlledCities().add(c);

			else
				loadArmy(c.getName(), c.getName().toLowerCase() + "_army.csv");

		}
	}

	private void loadCitiesAndDistances() throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("distances.csv"));
		String currentLine = br.readLine();
		ArrayList<String> names = new ArrayList<String>();

		while (currentLine != null) {

			String[] content = currentLine.split(",");
			if (!names.contains(content[0])) {
				availableCities.add(new City(content[0]));
				names.add(content[0]);
			} else if (!names.contains(content[1])) {
				availableCities.add(new City(content[1]));
				names.add(content[1]);
			}
			distances.add(new Distance(content[0], content[1], Integer.parseInt(content[2])));
			currentLine = br.readLine();

		}
		br.close();
	}

	public void loadArmy(String cityName, String path) throws IOException {
		City ci = getAvailableCities().get(0);
		for (int i = 0; i < getAvailableCities().size(); i++) {
			if (getAvailableCities().get(i).getName().equals(cityName)) {
				ci = getAvailableCities().get(i);
			}
		}
		BufferedReader br = new BufferedReader(new FileReader(path));
		String currentLine = br.readLine();
		Army resultArmy = new Army(cityName);
		while (currentLine != null) {
			String[] content = currentLine.split(",");
			String unitType = content[0].toLowerCase();
			int unitLevel = Integer.parseInt(content[1]);
			Unit u = null;
			if (unitType.equals("archer")) {

				if (unitLevel == 1)
					u = (new Archer(1, 60, 0.4, 0.5, 0.6));

				else if (unitLevel == 2)
					u = (new Archer(2, 60, 0.4, 0.5, 0.6));
				else
					u = (new Archer(3, 70, 0.5, 0.6, 0.7));
			} else if (unitType.equals("infantry")) {
				if (unitLevel == 1)
					u = (new Infantry(1, 50, 0.5, 0.6, 0.7));

				else if (unitLevel == 2)
					u = (new Infantry(2, 50, 0.5, 0.6, 0.7));
				else
					u = (new Infantry(3, 60, 0.6, 0.7, 0.8));
			} else if (unitType.equals("cavalry")) {
				if (unitLevel == 1)
					u = (new Cavalry(1, 40, 0.6, 0.7, 0.75));

				else if (unitLevel == 2)
					u = (new Cavalry(2, 40, 0.6, 0.7, 0.75));
				else
					u = (new Cavalry(3, 60, 0.7, 0.8, 0.9));
			}
			resultArmy.getUnits().add(u);
			currentLine = br.readLine();
			u.setParentArmy(resultArmy);
		}
		br.close();
		for (City c : availableCities) {
			if (c.getName().toLowerCase().equals(cityName.toLowerCase()))
				c.setDefendingArmy(resultArmy);
		}
	}

	public ArrayList<City> getAvailableCities() {
		return availableCities;
	}

	public ArrayList<Distance> getDistances() {
		return distances;
	}

	public int getCurrentTurnCount() {
		return currentTurnCount;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getMaxTurnCount() {
		return maxTurnCount;
	}

	public void setCurrentTurnCount(int currentTurnCount) {
		this.currentTurnCount = currentTurnCount;
	}

	public void targetCity(Army army, String targetName) {
		if (army.getCurrentStatus() != Status.MARCHING) {
			army.setTarget(targetName);
			for (int i = 0; i < getDistances().size(); i++) {
				if (
						(getDistances().get(i).getTo().equals(targetName) &&  
						getDistances().get(i).getFrom().equals(army.getCurrentLocation()))
						||
						(getDistances().get(i).getFrom().equals(targetName) &&
								getDistances().get(i).getTo().equals(army.getCurrentLocation())
								)
						
						
						) {
					army.setDistancetoTarget(getDistances().get(i).getDistance());
					army.setCurrentStatus(Status.MARCHING);
				}
			}
		}
	}

	public void endTurn() {
		setCurrentTurnCount(getCurrentTurnCount() + 1);

		for (int i = 0; i < player.getControlledCities().size(); i++) {
			for (int j = 0; j < player.getControlledCities().get(i).getEconomicalBuildings().size(); j++) {
				player.getControlledCities().get(i).getEconomicalBuildings().get(j).setCoolDown(false);
				if (player.getControlledCities().get(i).getEconomicalBuildings().get(j) instanceof Farm) {
					player.setFood(player.getFood()
							+ player.getControlledCities().get(i).getEconomicalBuildings().get(j).harvest());
				} else {
					player.setTreasury(player.getTreasury()
							+ player.getControlledCities().get(i).getEconomicalBuildings().get(j).harvest());
				}
			}
			for (int j = 0; j < player.getControlledCities().get(i).getMilitaryBuildings().size(); j++) {
				player.getControlledCities().get(i).getMilitaryBuildings().get(j).setCoolDown(false);
				player.getControlledCities().get(i).getMilitaryBuildings().get(j).setCurrentRecruit(0);
			}
		}
		double sumFood = 0.0;
		if(!player.getControlledArmies().isEmpty()) {
			for (int i = 0; i < player.getControlledArmies().size(); i++) {
				sumFood += player.getControlledArmies().get(i).foodNeeded();
				if (player.getControlledArmies().get(i).getCurrentStatus().equals(Status.MARCHING)) {
					Army a = player.getControlledArmies().get(i);
					a.setDistancetoTarget(a.getDistancetoTarget() - 1);
					if (a.getDistancetoTarget() == 0) {
						a.setCurrentStatus(Status.IDLE);
						a.setCurrentLocation(a.getTarget());
						a.setTarget("");
					}
				}
			}
		}
		if (player.getFood() <= sumFood) {
			for (int i = 0; i < player.getControlledArmies().size(); i++) {
				for (int j = 0; j < player.getControlledArmies().get(i).getUnits().size(); j++) {
					player.getControlledArmies().get(i).getUnits().get(j).setCurrentSoldierCount(
							(int) (player.getControlledArmies().get(i).getUnits().get(j).getCurrentSoldierCount()
									* 0.9));
				}
			}
			player.setFood(0);
		}
		else {
			player.setFood(player.getFood() - sumFood);
		}
		for (int i = 0; i < getAvailableCities().size(); i++) {
			if (getAvailableCities().get(i).isUnderSiege()) {
				getAvailableCities().get(i).setTurnsUnderSiege(getAvailableCities().get(i).getTurnsUnderSiege() + 1);
				for (int j = 0; j < getAvailableCities().get(i).getDefendingArmy().getUnits().size(); j++) {
					getAvailableCities().get(i).getDefendingArmy().getUnits().get(j)
							.setCurrentSoldierCount((int) (getAvailableCities().get(i).getDefendingArmy().getUnits()
									.get(j).getCurrentSoldierCount() * 0.9));
				}
			}
		}
	}
	public void autoResolve(Army attacker, Army defender) throws FriendlyFireException {
		if(player.getControlledArmies().contains(defender)){
			throw new FriendlyFireException("I am you, stupid");
		}
		
		boolean isAttacker = true;
		while (!(attacker.getUnits().isEmpty() || defender.getUnits().isEmpty())) {
			
			double attackerSize = attacker.getUnits().size()*Math.random();
			double defenderSize = defender.getUnits().size()*Math.random();
			int unitAttacker = (int)attackerSize;
			int unitDefender = (int)defenderSize;
			if (isAttacker) {
				attacker.getUnits().get(unitAttacker).attack(defender.getUnits().get(unitDefender));
			} else {
				defender.getUnits().get(unitDefender).attack(attacker.getUnits().get(unitAttacker));
			}
			isAttacker = !isAttacker;
			
		}
		
		if (defender.getUnits().isEmpty()) {
			occupy(attacker, attacker.getTarget());
		}
	}

	public void occupy(Army a, String cityName) {

		for (int i = 0; i < getAvailableCities().size(); i++) {
			if (getAvailableCities().get(i).getName().equals(cityName)) {
				getAvailableCities().get(i).setUnderSiege(false);
				getAvailableCities().get(i).setTurnsUnderSiege(-1);
				player.getControlledCities().add(getAvailableCities().get(i));
				getAvailableCities().get(i).setDefendingArmy(a);
			}
		}
	}
	
	

	public boolean isGameOver() {
		if (player.getControlledCities().size() == getAvailableCities().size() || currentTurnCount > maxTurnCount)
			return true;
		return false;
	}
}