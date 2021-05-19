package Model.Terrains;

import Exceptions.WrongTerrainType;

/**
 * solid terrain class
 * @author honzuna
 */
public class Solid extends Terrain {

    /**
     *
     * @param name
     * @param type
     * @throws WrongTerrainType
     */
    public Solid(String name, TerrainType type) throws WrongTerrainType {
	super(name, type);
	boolean ok = false;
	// checks if the terrain type is really solid type
	for (GroundType g : GroundType.values()) {
	    if (g.getName() == type.getName()) {
		ok = true;
	    }
	}
	if (!ok) {
	    throw new WrongTerrainType();
	}
    }

}
