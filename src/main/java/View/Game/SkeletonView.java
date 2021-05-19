package View.Game;

import Model.Map.MapSize;
import View.GameIcons;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.JPanel;

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

    public void drawBeast(Graphics g, JPanel p) {
	if (isVisible()) {
	    g.drawImage(this.getImage(), this.getX(), this.getY(), p);
	    g.setColor(Color.WHITE);
	    g.drawString(Integer.toString(hp), this.getX() + 40, this.getY() + 15);
	    g.drawString(Integer.toString(arm), this.getX() + 40, this.getY() + 25);
	    g.drawString(Integer.toString(dmg), this.getX() + 40, this.getY() + 35);
	}
    }

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

    public int getDx() {
	return dx;
    }

    public int getDy() {
	return dy;
    }

    public Image getSkeleton_1() {
	return skeleton_1;
    }

    public Image getSkeleton_2() {
	return skeleton_2;
    }

    public int getSwapTime() {
	return swapTime;
    }

    public boolean isMoved() {
	return moved;
    }

    public int getSpeed() {
	return speed;
    }

    public int getHp() {
	return hp;
    }

    public int getArm() {
	return arm;
    }

    public int getDmg() {
	return dmg;
    }

    public int getIdx() {
	return idx;
    }

    public void setDx(int dx) {
	this.dx = dx;
    }

    public void setDy(int dy) {
	this.dy = dy;
    }

    public void setSpeed(int speed) {
	this.speed = speed;
    }

    public void setHp(int hp) {
	this.hp = hp;
    }

    public void setArm(int arm) {
	this.arm = arm;
    }

    public void setDmg(int dmg) {
	this.dmg = dmg;
    }

    public void setIdx(int idx) {
	this.idx = idx;
    }

    public void setMoved(boolean moved) {
	this.moved = moved;
    }

    public void setTmpDx(int tmpDx) {
	this.tmpDx = tmpDx;
    }

    public void setTmpDy(int tmpDy) {
	this.tmpDy = tmpDy;
    }

    public void setEnemyNear(boolean enemyNear) {
	this.enemyNear = enemyNear;
    }

    public int getTmpDx() {
	return tmpDx;
    }

    public int getTmpDy() {
	return tmpDy;
    }

    public boolean isEnemyNear() {
	return enemyNear;
    }

    public int getAttackTime() {
	return attackTime;
    }

    public void setSwapTime(int swapTime) {
	this.swapTime = swapTime;
    }

    public void setAttackTime(int attackTime) {
	this.attackTime = attackTime;
    }
}
