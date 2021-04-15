package Model.Terrains;

public enum SolidType {
    ROCK("rock"),
    STONE("stone"),
    TREE("tree");
    
    private final String name;

    private SolidType(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }
}
