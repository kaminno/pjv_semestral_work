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
    private LinkedList<Beast> bats;
    private LinkedList<Beast> skeletons;
    private Beast boss;
    private Map map;

    public GameModel() {
	startingItems = new LinkedList();
	bats = new LinkedList();
	skeletons = new LinkedList();
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

    public void setBoss(Beast boss) {
	this.boss = boss;
    }

    public void setMap(Map map) {
	this.map = map;
    }

    public LinkedList<Beast> getBats() {
	return bats;
    }

    public LinkedList<Beast> getSkeletons() {
	return skeletons;
    }

    public void setBats(LinkedList<Beast> bats) {
	this.bats = bats;
    }

    public void setSkeletons(LinkedList<Beast> skeletons) {
	this.skeletons = skeletons;
    }
    
    
}
