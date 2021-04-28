package View;

import Model.Terrains.TerrainSize;
import java.awt.Image;
import javax.swing.ImageIcon;

public enum GameIcons {
    GRASS("grass", new ImageIcon(new ImageIcon("resources/terrain_grass.jpg").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    SAND("sand", new ImageIcon(new ImageIcon("resources/terrain_sand.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    //SAND("sand", new ImageIcon(new ImageIcon("resources/terrain_sand_texture.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT))),
    LOAM("loam", new ImageIcon(new ImageIcon("resources/terrain_loam.jpg").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    WATER("water", new ImageIcon(new ImageIcon("resources/terrain_water.jpg").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    ROCK("rock", new ImageIcon(new ImageIcon("resources/terrain_rock.jpg").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    WALL("wall", new ImageIcon(new ImageIcon("resources/terrain_walls.jpg").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    BLANC("blanc", new ImageIcon(new ImageIcon("resources/terrain_blanc.jpg").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    REMOVE_MAP("remove map", new ImageIcon(new ImageIcon("resources/icon_remove_map.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    NEW_MAP("new map", new ImageIcon(new ImageIcon("resources/icon_new_map.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT)));
    
    private String label;
    private ImageIcon icon;

    private GameIcons(String label, ImageIcon icon) {
	this.label = label;
	this.icon = icon;
    }

    public String getLabel() {
	return label;
    }

    public ImageIcon getIcon() {
	return icon;
    }
}
