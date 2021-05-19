package Model.Terrains;

import Exceptions.WrongTerrainType;

public class Solid extends Terrain {

    public Solid(String name, TerrainType type) throws WrongTerrainType {
	super(name, type);
	boolean ok = false;
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
