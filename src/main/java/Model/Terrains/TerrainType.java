package Model.Terrains;

public enum TerrainType {
    GRASS("grass"),
    LOAM("loam"),
    SAND("sand"),
    WATER("water"),
    ROCK("rock"),
    STONE("stone"),
    TREE("tree"),
    DOOR("door");
    
    private final String name;

    private TerrainType(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }
}
