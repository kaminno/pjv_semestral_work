package Model.Figures;

/**
 *
 * @author honzuna
 */
public abstract class Figure {
    private final String name;
    private int maxHealth;
    private int currentHealth;

    public Figure(String name, int health) {
	this.name = name;
	if (health <= 0){
	    throw new IllegalArgumentException("Health must be a positive number!");
	}
	this.maxHealth = health;
	this.currentHealth = health;
    }
    
    public abstract void attack(Figure enemy);
    public abstract void defend(int damage);
    public abstract boolean isAlive();

    /**
     *
     * @return
     */
    public String getName() {
	return name;
    }

    public int getMaxHealth() {
	return maxHealth;
    }

    /**
     *
     * @return
     */
    public int getCurrentHealth() {
	return currentHealth;
    }

    /**
     *
     * @param currentHealth
     */
    public void setCurrentHealth(int currentHealth) {
	this.currentHealth = currentHealth;
    }

    /**
     *
     * @param maxHealth
     */
    public void setMaxHealth(int maxHealth) {
	this.maxHealth = maxHealth;
    }
}
