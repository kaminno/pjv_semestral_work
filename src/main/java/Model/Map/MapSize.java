package Model.Map;

public enum MapSize {
    SIZE(1000, 600);
    
    private final int width;
    private final int height;
    
    private MapSize(int width, int height){
	this.width = width;
	this.height = height;
    }

    public static MapSize getSIZE() {
	return SIZE;
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }
}
