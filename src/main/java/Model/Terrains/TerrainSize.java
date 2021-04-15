package Model.Terrains;

public enum TerrainSize {
    WIDTH(20),
    HEIGHT(20);
    
    private final int size;

    private TerrainSize(int size) {
	this.size = size;
    }

    public int getSize() {
	return size;
    }
}
