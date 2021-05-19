package View.Game;

import Model.Map.MapSize;
import View.GameIcons;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.JPanel;

/**
 * skeleton beast class
 * @author honzuna
 */
public class SkeletonView extends FigureView {

    private int dx;
    private int dy;
    private Image skeleton_1;
    private Image skeleton_2;
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
    public SkeletonView(int x, int y) {
	super(x, y);

	initBeast();
    }

    private void initBeast() {
	skeleton_1 = GameIcons.BEAST_SKELETON.getIcon().getImage();
	skeleton_2 = GameIcons.BEAST_SKELETON.getIcon().getImage();
	attackTime = 0;
	moved = true;
	speed = 1;

	// choose random skeleton moving direction
	Random rand = new Random();
	dx = rand.nextInt(2) * speed;
	if (dx == 0) {
	    dy = speed;
	} else {
	    dy = 0;
	}

	image = skeleton_1;
	getImageDimensions();
    }

    /**
     * draw skeleton on the game board with its hp, armor and damage labels on the right side of the image icon
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
     * swap icons
     */
    public void swapIcons() {
	if (image.equals(skeleton_2)) {
	    image = skeleton_1;
	} else {
	    image = skeleton_2;
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
     * change skeleton coordinates and stop/continue if the player is enar. Also update the swap and attack counter
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
    public Image getSkeleton_1() {
	return skeleton_1;
    }

    /**
     *
     * @return
     */
    public Image getSkeleton_2() {
	return skeleton_2;
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
     * @param speed
     */
    public void setSpeed(int speed) {
	this.speed = speed;
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
     * @param idx
     */
    public void setIdx(int idx) {
	this.idx = idx;
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
     * @param tmpDx
     */
    public void setTmpDx(int tmpDx) {
	this.tmpDx = tmpDx;
    }

    /**
     *
     * @param tmpDy
     */
    public void setTmpDy(int tmpDy) {
	this.tmpDy = tmpDy;
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
     * @return
     */
    public int getAttackTime() {
	return attackTime;
    }

    /**
     *
     * @param swapTime
     */
    public void setSwapTime(int swapTime) {
	this.swapTime = swapTime;
    }

    /**
     *
     * @param attackTime
     */
    public void setAttackTime(int attackTime) {
	this.attackTime = attackTime;
    }
}
