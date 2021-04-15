package Model.Terrains;

public class Terrain {
    private final int width = TerrainSize.WIDTH.getSize();
    private final int height = TerrainSize.HEIGHT.getSize();
    private final String name;
    private final TerrainType type;

    public Terrain(String name, TerrainType type) {
	this.name = name;
	this.type = type;
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public String getName() {
	return name;
    }
    
    public TerrainType getType(){
	return type;
    }
}
