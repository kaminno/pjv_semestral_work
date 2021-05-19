package View.Game;

import Model.Map.MapSize;
import View.GameIcons;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.JPanel;

/**
 * view class of bat beast
 * @author honzuna
 */
public class BatView extends FigureView {

    private int dx;
    private int dy;
    private Image bat_1;
    private Image bat_2;
    private int swapTime = 0;
    private boolean moved;
    private int speed;
    private int hp;
    private int arm;
    private int dmg;
    private int idx;
    private int tmpDx;
    private int tmpDy;
    private boolean enemyNear = false;
    private int attackTime;

    /**
     *
     * @param x
     * @param y
     */
    public BatView(int x, int y) {
	super(x, y);

	initBeast();
    }

    private void initBeast() {
	bat_1 = GameIcons.BEAST_BAT_1.getIcon().getImage();
	bat_2 = GameIcons.BEAST_BAT_2.getIcon().getImage();

	speed = 3;
	moved = true;
	attackTime = 0;
	Random rand = new Random();
	// choose random beast direction
	dx = rand.nextInt(2) * speed;
	if (dx == 0) {
	    dy = speed;
	} else {
	    dy = 0;
	}

	image = bat_1;
	getImageDimensions();
    }

    /**
     * draw beast icon with beast stats on the right side
     * @param g
     * @param p
     */
    public void drawBeast(Graphics g, JPanel p) {
	if (isVisible()) {
	    g.drawImage(this.getImage(), this.getX(), this.getY(), p);
	    g.setColor(Color.WHITE);
	    g.drawString(Integer.toString(hp), this.getX() + 40, this.getY() + 15);
	    g.drawString(Integer.toString(arm), this.getX() + 40, this.getY() + 25);
	    g.drawString(Integer.toString(dmg), this.getX() + 40, this.getY() + 35);
	}
    }

    /**
     * swap icon to do simple animation
     */
    public void swapIcons() {
	if (image.equals(bat_2)) {
	    image = bat_1;
	} else {
	    image = bat_2;
	}
    }

    private void updateSwapTime() {
	swapTime += 1;
	if (swapTime == 8) {
	    swapTime = 0;
	}
    }

    private void updateAttackTime() {
	attackTime += 1;
	if (attackTime == 21) {
	    attackTime = 0;
	}
    }

    private void stopMove() {
	tmpDx = dx;
	dx = 0;
	tmpDy = dy;
	dy = 0;
    }

    private void continueMove() {
	dx = tmpDx;
	dy = tmpDy;
    }

    /**
     * update beast position and stop moving if player is near
     */
    public void move() {
	updateSwapTime();
	updateAttackTime();

	if (enemyNear) {
	    if (moved) {
		stopMove();
		moved = false;
	    }
	} else {
	    if (!moved) {
		continueMove();
		moved = true;
	    }

	    x += dx;
	    y += dy;

	    if (x < speed) {
		x = speed;
		dx = (-1) * dx;
	    }

	    if (y < speed) {
		y = speed;
		dy = (-1) * dy;
	    }

	    if (x > MapSize.getSIZE().getWidth() - 40) {
		x = MapSize.getSIZE().getWidth() - 40;
		dx = (-1) * dx;
	    }

	    if (y > MapSize.getSIZE().getHeight() - 40) {
		y = MapSize.getSIZE().getHeight() - 40;
		dy = (-1) * dy;
	    }
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
     * @return
     */
    public Image getBat_1() {
	return bat_1;
    }

    /**
     *
     * @return
     */
    public Image getBat_2() {
	return bat_2;
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
    public int getHp() {
	return hp;
    }

    /**
     *
     * @return
     */
    public int getArm() {
	return arm;
    }

    /**
     *
     * @return
     */
    public int getDmg() {
	return dmg;
    }

    /**
     *
     * @return
     */
    public int getIdx() {
	return idx;
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
     * @param hp
     */
    public void setHp(int hp) {
	this.hp = hp;
    }

    /**
     *
     * @param arm
     */
    public void setArm(int arm) {
	this.arm = arm;
    }

    /**
     *
     * @param dmg
     */
    public void setDmg(int dmg) {
	this.dmg = dmg;
    }

    /**
     *
     * @return
     */
    public int getTmpDx() {
	return tmpDx;
    }

    /**
     *
     * @return
     */
    public int getTmpDy() {
	return tmpDy;
    }

    /**
     *
     * @return
     */
    public boolean isEnemyNear() {
	return enemyNear;
    }

    /**
     *
     * @param moved
     */
    public void setMoved(boolean moved) {
	this.moved = moved;
    }

    /**
     *
     * @param enemyNear
     */
    public void setEnemyNear(boolean enemyNear) {
	this.enemyNear = enemyNear;
    }

    /**
     *
     * @param idx
     */
    public void setIdx(int idx) {
	this.idx = idx;
    }

    /**
     *
     * @return
     */
    public int getAttackTime() {
	return attackTime;
    }
}
