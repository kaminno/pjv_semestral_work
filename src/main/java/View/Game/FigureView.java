package View.Game;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author honzuna
 */
public class FigureView {

    /**
     *
     */
    protected int x;

    /**
     *
     */
    protected int y;

    /**
     *
     */
    protected int width;

    /**
     *
     */
    protected int height;

    /**
     *
     */
    protected boolean visible;

    /**
     *
     */
    protected Image image;

    /**
     *
     * @param x
     * @param y
     */
    public FigureView(int x, int y) {

	this.x = x;
	this.y = y;
	visible = true;
    }

    /**
     *
     */
    protected void getImageDimensions() {
	width = 40;
	height = 40;
    }

    /**
     *
     * @param imageName
     */
    protected void loadImage(String imageName) {

	ImageIcon ii = new ImageIcon(imageName);
	image = ii.getImage();
    }

    /**
     *
     * @return
     */
    public Image getImage() {
	return image;
    }

    /**
     *
     * @return
     */
    public int getX() {
	return x;
    }

    /**
     *
     * @return
     */
    public int getY() {
	return y;
    }

    /**
     *
     * @return
     */
    public boolean isVisible() {
	return visible;
    }

    /**
     *
     * @param visible
     */
    public void setVisible(Boolean visible) {
	this.visible = visible;
    }

    /**
     *
     * @return
     */
    public Rectangle getBounds() {
	return new Rectangle(x, y, width, height);
    }

    /**
     *
     * @param x
     */
    public void setX(int x) {
	this.x = x;
    }

    /**
     *
     * @param y
     */
    public void setY(int y) {
	this.y = y;
    }
}
