package Model.Figures;

/**
 * figure class
 * @author honzuna
 */
public abstract class Figure {

    /**
     *
     */
    protected final String name;

    /**
     *
     */
    protected int maxHealth;

    /**
     *
     */
    protected int currentHealth;

    /**
     *
     * @param name
     * @param health
     */
    public Figure(String name, int health) {
	this.name = name;
	// checks if the health is positive
	if (health <= 0) {
	    throw new IllegalArgumentException("Health must be a positive number!");
	}
	this.maxHealth = health;
	this.currentHealth = health;
    }

    /**
     * methods that has to be implemented by childs
     * @param enemy
     */
    public abstract void attack(Figure enemy);

    /**
     *
     * @param damage
     */
    public abstract void defend(int damage);

    /**
     *
     * @return
     */
    public abstract boolean isAlive();

    /**
     *
     * @return
     */
    public String getName() {
	return name;
    }

    /**
     *
     * @return
     */
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
