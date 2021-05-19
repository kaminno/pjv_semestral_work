package Model.Terrains;

import Exceptions.WrongTerrainType;

/**
 * ground terrain class
 * @author honzuna
 */
public class Ground extends Terrain {

    private final int bonusXVelocity;
    private final int bonusYVelocity;

    /**
     *
     * @param name
     * @param type
     * @param xVel
     * @param yVel
     * @throws WrongTerrainType
     */
    public Ground(String name, TerrainType type, int xVel, int yVel) throws WrongTerrainType {
	super(name, type);
	boolean ok = false;
	// checks if the terrain type is really ground type
	for (GroundType g : GroundType.values()) {
	    if (g.getName() == type.getName()) {
		ok = true;
	    }
	}
	if (!ok) {
	    throw new WrongTerrainType();
	}
	this.bonusXVelocity = xVel;
	this.bonusYVelocity = yVel;
    }

    /**
     *
     * @return
     */
    public int getBonusXVelocity() {
	return bonusXVelocity;
    }

    /**
     *
     * @return
     */
    public int getBonusYVelocity() {
	return bonusYVelocity;
    }
}
