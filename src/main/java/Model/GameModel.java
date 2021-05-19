package Model;

import Model.Figures.Beast;
import Model.Figures.Player;
import Model.Items.WearableItem;
import Model.Map.Map;
import java.util.LinkedList;

/**
 * class to store game data
 * @author honzuna
 */
public class GameModel {

    private Player player;
    private LinkedList<WearableItem> startingItems;
    private LinkedList<Beast> bats;
    private LinkedList<Beast> skeletons;
    private Beast boss;
    private Map map;

    /**
     *
     */
    public GameModel() {
	startingItems = new LinkedList();
	bats = new LinkedList();
	skeletons = new LinkedList();
    }

    /**
     *
     */
    public void printNewMap() {
	map.printMapTerrain();
    }

    /**
     *
     * @return
     */
    public Player getPlayer() {
	return player;
    }

    /**
     *
     * @return
     */
    public LinkedList<WearableItem> getStartingItems() {
	return startingItems;
    }

    /**
     *
     * @return
     */
    public Beast getBoss() {
	return boss;
    }

    /**
     *
     * @return
     */
    public Map getMap() {
	return map;
    }

    /**
     *
     * @param player
     */
    public void setPlayer(Player player) {
	this.player = player;
    }

    /**
     *
     * @param startingItems
     */
    public void setStartingItems(LinkedList<WearableItem> startingItems) {
	this.startingItems = startingItems;
    }

    /**
     *
     * @param boss
     */
    public void setBoss(Beast boss) {
	this.boss = boss;
    }

    /**
     *
     * @param map
     */
    public void setMap(Map map) {
	this.map = map;
    }

    /**
     *
     * @return
     */
    public LinkedList<Beast> getBats() {
	return bats;
    }

    /**
     *
     * @return
     */
    public LinkedList<Beast> getSkeletons() {
	return skeletons;
    }

    /**
     *
     * @param bats
     */
    public void setBats(LinkedList<Beast> bats) {
	this.bats = bats;
    }

    /**
     *
     * @param skeletons
     */
    public void setSkeletons(LinkedList<Beast> skeletons) {
	this.skeletons = skeletons;
    }
}
