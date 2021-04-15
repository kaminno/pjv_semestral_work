package Model.Terrains;

import Exceptions.WrongTerrainType;

public class Ground extends Terrain{
    private final int bonusXVelocity;
    private final int bonusYVelocity;
    

    public Ground(String name, TerrainType type, int xVel, int yVel) throws WrongTerrainType {
	super(name, type);
	boolean ok = false;
	for (GroundType g : GroundType.values()){
	    if (g.getName() == type.getName()){
		ok = true;
	    }
	}
	if (!ok){
	    throw new WrongTerrainType();
	}
	this.bonusXVelocity = xVel;
	this.bonusYVelocity = yVel;
    }

    public int getBonusXVelocity() {
	return bonusXVelocity;
    }

    public int getBonusYVelocity() {
	return bonusYVelocity;
    }    
}
