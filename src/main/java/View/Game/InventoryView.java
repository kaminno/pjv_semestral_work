package View.Game;

import View.GameIcons;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * player's inventory view
 * @author honzuna
 */
public class InventoryView extends JPanel {

    private int inventorySize;
    private int itemCount;
    private boolean freeSpace;
    private boolean showen;
    private Color inventoryColor;
    private Color borderColor;
    private Border itemBorder;
    private JPanel items;
    private ArrayList<JLabel> itemSlots;

    /**
     *
     * @param inventorySize
     */
    public InventoryView(int inventorySize) {
	this.inventorySize = inventorySize;
	freeSpace = true;
	showen = false;
	itemCount = 0;
	itemSlots = new ArrayList(inventorySize);
	items = new JPanel();
	items.setLayout(new GridLayout(2, 5));
	items.setBackground(borderColor);
	setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

	setSize(280, 130);

	inventoryColor = new Color(242, 201, 65);
	borderColor = new Color(99, 59, 19);
	itemBorder = BorderFactory.createLineBorder(borderColor, 3);
	setVisible(false);
	setBackground(borderColor);
	setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
	init();
    }

    private void init() {
	JLabel ji = new JLabel("Inventory");
	ji.setForeground(Color.WHITE);
	ji.setAlignmentX(CENTER_ALIGNMENT);
	add(ji);
	add(items);
	// iterate and create item slot or empty slot based on given inventory size
	for (int i = 0; i < 10; i++) {
	    if (i < inventorySize) {
		// create item slot
		JLabel jl = new JLabel(GameIcons.TRANSPARENT.getIcon());
		jl.setBackground(inventoryColor);
		jl.setBorder(itemBorder);
		jl.setOpaque(true);
		items.add(jl);
		itemSlots.add(jl);
	    } else {
		// let the rest of inventory empty
		JLabel jl = new JLabel();
		jl.setBackground(borderColor);
		jl.setBorder(itemBorder);
		jl.setOpaque(true);
		items.add(jl);
		itemSlots.add(jl);
	    }
	}
    }

    /**
     *
     * @return
     */
    public int getInventorySize() {
	return inventorySize;
    }

    /**
     *
     * @return
     */
    public int getItemCount() {
	return itemCount;
    }

    /**
     *
     * @return
     */
    public boolean isFreeSpace() {
	return freeSpace;
    }

    /**
     *
     * @return
     */
    public boolean isShowen() {
	return showen;
    }

    /**
     *
     * @return
     */
    public Color getInventoryColor() {
	return inventoryColor;
    }

    /**
     *
     * @return
     */
    public Color getBorderColor() {
	return borderColor;
    }

    /**
     *
     * @return
     */
    public Border getItemBorder() {
	return itemBorder;
    }

    /**
     *
     * @return
     */
    public JPanel getItems() {
	return items;
    }

    /**
     *
     * @return
     */
    public ArrayList<JLabel> getItemSlots() {
	return itemSlots;
    }

    /**
     *
     * @param itemCount
     */
    public void setItemCount(int itemCount) {
	this.itemCount = itemCount;
    }

    /**
     *
     * @param freeSpace
     */
    public void setFreeSpace(boolean freeSpace) {
	this.freeSpace = freeSpace;
    }

    /**
     *
     * @param showen
     */
    public void setShowen(boolean showen) {
	this.showen = showen;
    }
}
