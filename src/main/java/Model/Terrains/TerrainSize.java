package Model.Terrains;

/**
 *
 * @author honzuna
 */
public enum TerrainSize {

    /**
     *
     */
    WIDTH(40),

    /**
     *
     */
    HEIGHT(40);

    private final int size;

    private TerrainSize(int size) {
	this.size = size;
    }

    /**
     *
     * @return
     */
    public int getSize() {
	return size;
    }
}
