package Model.Map;

import Exceptions.WrongTerrainType;
import Model.Figures.Figure;
import Model.Terrains.Ground;
import Model.Terrains.Terrain;
import Model.Terrains.TerrainType;
import java.util.ArrayList;

public class Map {
    private static int mapIds = 0;
    private final int id;
    private final int mapHeight;
    private final int mapWidth;
    private ArrayList<ArrayList<Terrain>> mapTerrain;
    private ArrayList<ArrayList<Figure>> mapFigures;

    public Map(int mapHeight, int mapWidth) {
	if (mapHeight > 0 && mapWidth > 0){
	    this.id = Map.getNewId();
	    this.mapHeight = mapHeight;
	    this.mapWidth = mapWidth;
	    this.mapTerrain = new ArrayList(mapHeight);
	    this.mapFigures = new ArrayList(mapHeight);
	    for (int i = 0; i < mapHeight; i++){
		this.mapTerrain.add(i, new ArrayList(mapWidth));
		this.mapFigures.add(i, new ArrayList(mapWidth));
		for (int j = 0; j < mapWidth; j++){
		    mapTerrain.get(i).add(j, null);
		    mapFigures.get(i).add(j, null);
		}
	    }
	}
	else{
	    throw new IllegalArgumentException("Map size must be greater than zero!");
	}
    }
    
    public void fillTheMapWithGround(String name, TerrainType terrainType, int xVel, int yVel) throws WrongTerrainType{
	for (int h = 0; h < this.mapHeight; h++){
	    for (int w = 0; w < this.mapWidth; w++){
		mapTerrain.get(h).add(w, new Ground(name, terrainType, xVel, yVel));
	    }
	}
    }
    
    public void addTerrain(int h, int w, Terrain terrain){
	mapTerrain.get(h).add(w, terrain);
    }
    
    public void addFigure(int h, int w, Figure figure){
	mapFigures.get(h).add(w, figure);
    }
    
    public void printMapTerrain(){
	for (int h = 0; h < this.mapHeight + 2; h++){
	    for (int w = 0; w < this.mapWidth; w++){
		if (h == 0){
		    System.out.print("________");
		    continue;
		}
		if (h == this.mapHeight + 1){
		    System.out.print((char)0x203E);
		    System.out.print((char)0x203E);
		    System.out.print((char)0x203E);
		    System.out.print((char)0x203E);
		    System.out.print((char)0x203E);
		    System.out.print((char)0x203E);
		    System.out.print((char)0x203E);
		    System.out.print((char)0x203E);
		    continue;
		}
		Terrain t = mapTerrain.get(h - 1).get(w);
		if (w == 0){
		    System.out.print("| ");
		}
		if (t != null){
		    System.out.print(t.getType().getName() + " | ");
		    continue;
		}
		System.out.print("\t| ");
	    }
	    System.out.println("");
	}
    }
    
    public void printMapFigures(){
	for (int h = 0; h < this.mapHeight + 2; h++){
	    //System.out.println("length " + mapFigures.get(6).size());
	    
	    for (int w = 0; w < this.mapWidth; w++){
		if (h == 0){
		    System.out.print("________");
		    continue;
		}
		if (h == this.mapHeight + 1){
		    System.out.print((char)0x203E);
		    System.out.print((char)0x203E);
		    System.out.print((char)0x203E);
		    System.out.print((char)0x203E);
		    System.out.print((char)0x203E);
		    System.out.print((char)0x203E);
		    System.out.print((char)0x203E);
		    System.out.print((char)0x203E);
		    continue;
		}
		if (w == 0){
		    System.out.print("| ");
		}
		Figure f = mapFigures.get(h - 1).get(w);
		if (f != null){
		    System.out.print(f.getName() + " | ");
		    continue;
		}
		System.out.print("\t| ");
	    }
	    System.out.println("");
	}
    }
    
    private static int getNewId(){
	int id = Map.getMapIds();
	Map.updateId();
	return id;
    }
    
    private static int getMapIds(){
	return mapIds;
    }
    
    private static void updateId(){
	mapIds += 1;
    }
    
    public int getId(){
	return id;
    }
}
