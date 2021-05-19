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
	for (int i = 0; i < 10; i++) {
	    if (i < inventorySize) {
		JLabel jl = new JLabel(GameIcons.TRANSPARENT.getIcon());
		jl.setBackground(inventoryColor);
		jl.setBorder(itemBorder);
		jl.setOpaque(true);
		items.add(jl);
		itemSlots.add(jl);
	    } else {
		JLabel jl = new JLabel();
		jl.setBackground(borderColor);
		jl.setBorder(itemBorder);
		jl.setOpaque(true);
		items.add(jl);
		itemSlots.add(jl);
	    }
	}
    }

    public int getInventorySize() {
	return inventorySize;
    }

    public int getItemCount() {
	return itemCount;
    }

    public boolean isFreeSpace() {
	return freeSpace;
    }

    public boolean isShowen() {
	return showen;
    }

    public Color getInventoryColor() {
	return inventoryColor;
    }

    public Color getBorderColor() {
	return borderColor;
    }

    public Border getItemBorder() {
	return itemBorder;
    }

    public JPanel getItems() {
	return items;
    }

    public ArrayList<JLabel> getItemSlots() {
	return itemSlots;
    }

    public void setItemCount(int itemCount) {
	this.itemCount = itemCount;
    }

    public void setFreeSpace(boolean freeSpace) {
	this.freeSpace = freeSpace;
    }

    public void setShowen(boolean showen) {
	this.showen = showen;
    }
}
