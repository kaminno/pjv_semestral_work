package Model.Terrains;

public enum SolidType {
    ROCK("rock"),
    WALL("wall");

    private final String name;

    private SolidType(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }
}
