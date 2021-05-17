package View;

import Model.Terrains.TerrainSize;
import java.awt.Image;
import javax.swing.ImageIcon;

public enum GameIcons {
    GRASS("grass", new ImageIcon(new ImageIcon("resources/terrain_grass.jpg").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    SAND("sand", new ImageIcon(new ImageIcon("resources/terrain_sand.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    LOAM("loam", new ImageIcon(new ImageIcon("resources/terrain_loam.jpg").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    WATER("water", new ImageIcon(new ImageIcon("resources/terrain_water.jpg").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    ROCK("rock", new ImageIcon(new ImageIcon("resources/terrain_rock.jpg").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    WALL("wall", new ImageIcon(new ImageIcon("resources/terrain_walls.jpg").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    BLANC("blanc", new ImageIcon(new ImageIcon("resources/terrain_blanc.jpg").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    REMOVE_MAP("remove map", new ImageIcon(new ImageIcon("resources/icon_remove_map.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    NEW_PLAYER("new player", new ImageIcon(new ImageIcon("resources/icon_new_player.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    REMOVE_PLAYER("remove player", new ImageIcon(new ImageIcon("resources/icon_remove_player.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    NEW_MAP("new map", new ImageIcon(new ImageIcon("resources/icon_new_map.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    PLAYER_FORE_1("player fore 1", new ImageIcon(new ImageIcon("resources/player.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    PLAYER_FORE_2("player fore 2", new ImageIcon(new ImageIcon("resources/player_2.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    PLAYER_RIGHT_1("player right 1", new ImageIcon(new ImageIcon("resources/player_right.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    ARMOR_HELMET("helmet", new ImageIcon(new ImageIcon("resources/equipment_helmet.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    ARMOR_CHEST("chest", new ImageIcon(new ImageIcon("resources/equipment_chest.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    ARMOR_LEGS("legs", new ImageIcon(new ImageIcon("resources/equipment_trousers.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    ARMOR_GLOVES("gloves", new ImageIcon(new ImageIcon("resources/equipment_gloves.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    ARMOR_BOOTS("boots", new ImageIcon(new ImageIcon("resources/equipment_boots.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    WEAPON_MAIN_SWORD("main sword", new ImageIcon(new ImageIcon("resources/weapon_main_sword.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    WEAPON_SECOND_DAGGER("second dagger", new ImageIcon(new ImageIcon("resources/weapon_second_dagger.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    BEAST_BAT_1("beast bat 1", new ImageIcon(new ImageIcon("resources/beast_bat_1.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    BEAST_BAT_2("beast bat 2", new ImageIcon(new ImageIcon("resources/beast_bat_2.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    BEAST_WAR_PIG("beast war pig", new ImageIcon(new ImageIcon("resources/beast_war_pig.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    BEAST_SKELETON("beast skeleton", new ImageIcon(new ImageIcon("resources/beast_skeleton.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    ATTACK("attack", new ImageIcon(new ImageIcon("resources/attack.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT))),
    TRANSPARENT("transparent", new ImageIcon(new ImageIcon("resources/icon_transparent.png").getImage().getScaledInstance(TerrainSize.WIDTH.getSize(), TerrainSize.HEIGHT.getSize(), Image.SCALE_DEFAULT)));
    
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
