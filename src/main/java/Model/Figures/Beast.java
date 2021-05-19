package Model.Figures;

import Model.Items.Item;
import java.util.ArrayList;

/**
 * beast class
 * @author honzuna
 */
public class Beast extends Figure {

    private int currentArmor;
    private int currentAttackDamage;
    ArrayList<Item> items;

    private int xPosition;
    private int yPosition;

    /**
     *
     * @param name
     * @param health
     * @param armor
     * @param damage
     * @param x
     * @param y
     */
    public Beast(String name, int health, int armor, int damage, int x, int y) {
	super(name, health);
	// check it the params have correct values
	if (armor < 0 || damage <= 0 || x < 0 || y < 0) {
	    throw new IllegalArgumentException("Wrong values of parameters");
	}
	currentArmor = armor;
	currentAttackDamage = damage;
	xPosition = x;
	yPosition = y;
    }

    /**
     * attack method that calls defend method on enemy
     * @param enemy
     */
    public void attack(Figure enemy) {
	enemy.defend(currentAttackDamage);
    }

    /**
     * defend method counts the injury and sets hp to new value
     * @param damage
     */
    public void defend(int damage) {
	int injury = damage - getCurrentArmor();
	if (injury < 0) {
	    injury = 0;
	}
	setCurrentHealth(getCurrentHealth() - injury);
    }

    /**
     * checks if the beast is still alive
     * @return
     */
    public boolean isAlive() {
	return getCurrentHealth() > 0;
    }

    /**
     *
     * @return
     */
    public int getCurrentArmor() {
	return currentArmor;
    }

    /**
     *
     * @return
     */
    public int getCurrentAttackDamage() {
	return currentAttackDamage;
    }

    /**
     *
     * @return
     */
    public ArrayList<Item> getItems() {
	return items;
    }

    /**
     *
     * @return
     */
    public int getxPosition() {
	return xPosition;
    }

    /**
     *
     * @return
     */
    public int getyPosition() {
	return yPosition;
    }

    /**
     *
     * @param currentArmor
     */
    public void setCurrentArmor(int currentArmor) {
	this.currentArmor = currentArmor;
    }

    /**
     *
     * @param currentAttackDamage
     */
    public void setCurrentAttackDamage(int currentAttackDamage) {
	this.currentAttackDamage = currentAttackDamage;
    }

    /**
     *
     * @param xPosition
     */
    public void setxPosition(int xPosition) {
	this.xPosition = xPosition;
    }

    /**
     *
     * @param yPosition
     */
    public void setyPosition(int yPosition) {
	this.yPosition = yPosition;
    }
}
