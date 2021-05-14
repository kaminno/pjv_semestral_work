package Model;

import Model.Figures.Beast;
import Model.Figures.Player;
import Model.Items.Equipment;
import Model.Items.WearableItem;
import Model.Map.Map;
import java.util.LinkedList;

public class GameModel {
    private Player player;
    private LinkedList<WearableItem> startingItems;
    private LinkedList<Beast> beasts;
    private Beast boss;
    private Map map;

    public GameModel() {
	startingItems = new LinkedList();
	beasts = new LinkedList();
    }
    
    public void printNewMap(){
	map.printMapTerrain();
    }

    public Player getPlayer() {
	return player;
    }

    public LinkedList<WearableItem> getStartingItems() {
	return startingItems;
    }

    public LinkedList<Beast> getBeasts() {
	return beasts;
    }

    public Beast getBoss() {
	return boss;
    }

    public Map getMap() {
	return map;
    }

    public void setPlayer(Player player) {
	this.player = player;
    }

    public void setStartingItems(LinkedList<WearableItem> startingItems) {
	this.startingItems = startingItems;
    }

    public void setBeasts(LinkedList<Beast> beasts) {
	this.beasts = beasts;
    }

    public void setBoss(Beast boss) {
	this.boss = boss;
    }

    public void setMap(Map map) {
	this.map = map;
    }
    
    
}
