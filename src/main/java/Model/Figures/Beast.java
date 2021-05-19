package Model.Figures;

import Model.Items.Item;
import java.util.ArrayList;

public class Beast extends Figure {

    private int currentArmor;
    private int currentAttackDamage;
    ArrayList<Item> items;

    private int xPosition;
    private int yPosition;

    public Beast(String name, int health, int armor, int damage, int x, int y) {
	super(name, health);
	if (armor < 0 || damage <= 0 || x < 0 || y < 0) {
	    throw new IllegalArgumentException("Wrong values of parameters");
	}
	currentArmor = armor;
	currentAttackDamage = damage;
	xPosition = x;
	yPosition = y;
    }

    public void attack(Figure enemy) {
	enemy.defend(currentAttackDamage);
    }

    public void defend(int damage) {
	int injury = damage - getCurrentArmor();
	if (injury < 0) {
	    injury = 0;
	}
	setCurrentHealth(getCurrentHealth() - injury);
    }

    public boolean isAlive() {
	return getCurrentHealth() > 0;
    }

    public int getCurrentArmor() {
	return currentArmor;
    }

    public int getCurrentAttackDamage() {
	return currentAttackDamage;
    }

    public ArrayList<Item> getItems() {
	return items;
    }

    public int getxPosition() {
	return xPosition;
    }

    public int getyPosition() {
	return yPosition;
    }

    public void setCurrentArmor(int currentArmor) {
	this.currentArmor = currentArmor;
    }

    public void setCurrentAttackDamage(int currentAttackDamage) {
	this.currentAttackDamage = currentAttackDamage;
    }

    public void setxPosition(int xPosition) {
	this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition) {
	this.yPosition = yPosition;
    }
}
