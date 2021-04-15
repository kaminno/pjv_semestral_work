package Model.Terrains;

public enum GroundType {
    GRASS("grass"),
    LOAM("loam"),
    SAND("sand"),
    WATER("water");
    
    private final String name;

    private GroundType(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }
}