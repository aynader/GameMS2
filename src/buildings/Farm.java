package buildings;

public class Farm extends EconomicBuilding {

	public Farm() {
		super(1000, 500);
	}

	public void upgrade() throws BuildingInCoolDownException, MaxLevelException{
		coolDown = true;
		try{
			if(level<3){
			level++;
			upgradeCost = 700;
		}
	}
		}catch(MaxLevelException e){
			System.out.println("error in max level");
		}

}
