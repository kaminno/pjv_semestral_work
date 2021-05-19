package Model.Terrains;

public enum TerrainSize {
    WIDTH(40),
    HEIGHT(40);

    private final int size;

    private TerrainSize(int size) {
	this.size = size;
    }

    public int getSize() {
	return size;
    }
}
