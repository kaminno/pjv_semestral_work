package Model.Terrains;

/**
 * terrain class
 * @author honzuna
 */
public class Terrain {

    private final int width = TerrainSize.WIDTH.getSize();
    private final int height = TerrainSize.HEIGHT.getSize();
    private final String name;
    private final TerrainType type;

    /**
     *
     * @param name
     * @param type
     */
    public Terrain(String name, TerrainType type) {
	this.name = name;
	this.type = type;
    }

    /**
     *
     * @return
     */
    public int getWidth() {
	return width;
    }

    /**
     *
     * @return
     */
    public int getHeight() {
	return height;
    }

    /**
     *
     * @return
     */
    public String getName() {
	return name;
    }

    /**
     *
     * @return
     */
    public TerrainType getType() {
	return type;
    }
}
