package Model.Map;

/**
 *
 * @author honzuna
 */
public enum MapSize {

    /**
     *
     */
    SIZE(1000, 600);

    private final int width;
    private final int height;

    private MapSize(int width, int height) {
	this.width = width;
	this.height = height;
    }

    /**
     *
     * @return
     */
    public static MapSize getSIZE() {
	return SIZE;
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
}
