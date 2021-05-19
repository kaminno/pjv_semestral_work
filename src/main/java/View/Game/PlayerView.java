package View.Game;

import Model.Figures.Player;
import Model.Map.MapSize;
import View.GameIcons;
import java.awt.Image;
import java.awt.event.KeyEvent;

/**
 * player view
 * @author honzuna
 */
public class PlayerView extends FigureView {

    private int dx;
    private int dy;
    private Image player_fore_1;
    private Image player_fore_2;
    private int swapTime = 0;
    private boolean moved;
    private InventoryView inventory;
    private EquipmentView equipment;
    private int speed;
    private boolean attackReady = false;
    private int attackTime;

    /**
     *
     * @param x
     * @param y
     * @param pl
     */
    public PlayerView(int x, int y, Player pl) {
	super(x, y);
	// create player's inventory and equipment view
	inventory = new InventoryView(pl.getInventory().getCurrentCapacity());
	equipment = new EquipmentView();
	equipment.getPlayerHealth().setText(Integer.toString(pl.getMaxHealth()));
	equipment.getPlayerSpeed().setText(Integer.toString(pl.getCurrentSpeed()));
	speed = pl.getCurrentSpeed();
	attackTime = 0;

	initPlayer();
    }

    private void initPlayer() {
	player_fore_1 = GameIcons.PLAYER_FORE_1.getIcon().getImage();
	player_fore_2 = GameIcons.PLAYER_FORE_2.getIcon().getImage();

	image = player_fore_1;
	getImageDimensions();
    }

    /**
     * swaping icont to make animation
     */
    public void swapIcons() {
	if (image.equals(player_fore_2)) {
	    image = player_fore_1;
	} else {
	    image = player_fore_2;
	}
    }

    /**
     * move player and update its attack and swap (animation) counter
     */
    public void move() {
	updateSwapTime();
	updateAttackTime();

	x += dx;
	y += dy;

	if (x < speed) {
	    x = speed;
	}

	if (y < speed) {
	    y = speed;
	}

	if (x > MapSize.getSIZE().getWidth() - 40) {
	    x = MapSize.getSIZE().getWidth() - 40;
	}

	if (y > MapSize.getSIZE().getHeight() - 40) {
	    y = MapSize.getSIZE().getHeight() - 40;
	}
    }

    private void updateSwapTime() {
	swapTime += 1;
	if (swapTime == 8) {
	    swapTime = 0;
	}
    }

    private void updateAttackTime() {
	if (attackTime < 20) {
	    attackTime += 1;
	}
    }

    /**
     *
     * @return
     */
    public int getSwapTime() {
	return swapTime;
    }

    /**
     *
     * @return
     */
    public boolean getMoved() {
	return moved;
    }

    /**
     * set key events to specific keys
     * @param e
     */
    public void keyPressed(KeyEvent e) {
	moved = true;

	int key = e.getKeyCode();

	if (key == KeyEvent.VK_ESCAPE) {
	    inventory.setVisible(false);
	    inventory.setShowen(false);

	    equipment.setVisible(false);
	    equipment.setShown(false);
	}

	if (key == KeyEvent.VK_I) {
	    if (inventory.isShowen()) {
		inventory.setVisible(false);
		inventory.setShowen(false);
	    } else {
		inventory.setVisible(true);
		inventory.setShowen(true);
	    }
	}

	if (key == KeyEvent.VK_C) {
	    if (equipment.isShown()) {
		equipment.setVisible(false);
		equipment.setShown(false);
	    } else {
		equipment.setVisible(true);
		equipment.setShown(true);
	    }
	}

	if (key == KeyEvent.VK_SPACE && attackTime == 20) {
	    attackReady = true;
	    attackTime = 0;
	}

	if (key == KeyEvent.VK_LEFT) {
	    dx = -speed;
	}

	if (key == KeyEvent.VK_RIGHT) {
	    dx = speed;
	}

	if (key == KeyEvent.VK_UP) {
	    dy = -speed;
	}

	if (key == KeyEvent.VK_DOWN) {
	    dy = speed;
	}
    }

    /**
     * set key events to key releases
     * @param e
     */
    public void keyReleased(KeyEvent e) {
	moved = false;

	int key = e.getKeyCode();

	if (key == KeyEvent.VK_LEFT) {
	    dx = 0;
	}

	if (key == KeyEvent.VK_RIGHT) {
	    dx = 0;
	}

	if (key == KeyEvent.VK_UP) {
	    dy = 0;
	}

	if (key == KeyEvent.VK_DOWN) {
	    dy = 0;
	}
    }

    /**
     *
     * @return
     */
    public InventoryView getInventory() {
	return inventory;
    }

    /**
     *
     * @return
     */
    public EquipmentView getEquipment() {
	return equipment;
    }

    /**
     *
     * @return
     */
    public int getDx() {
	return dx;
    }

    /**
     *
     * @return
     */
    public int getDy() {
	return dy;
    }

    /**
     *
     * @param dx
     */
    public void setDx(int dx) {
	this.dx = dx;
    }

    /**
     *
     * @param dy
     */
    public void setDy(int dy) {
	this.dy = dy;
    }

    /**
     *
     * @return
     */
    public boolean isMoved() {
	return moved;
    }

    /**
     *
     * @return
     */
    public int getSpeed() {
	return speed;
    }

    /**
     *
     * @return
     */
    public boolean isAttackReady() {
	return attackReady;
    }

    /**
     *
     * @param attackReady
     */
    public void setAttackReady(boolean attackReady) {
	this.attackReady = attackReady;
    }
}
