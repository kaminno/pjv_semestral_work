package View.Engine;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EngineToolPanePlayerTabInventory extends JPanel{
    private JLabel lblInventorySize;
    private String inventorySizes[] = {"5", "6", "7", "8", "9", "10"};
    private int inventorySize;
    private JComboBox comboBox;

    public EngineToolPanePlayerTabInventory() {
	lblInventorySize = new JLabel("Base inventory size");
	add(lblInventorySize);
	comboBox = new JComboBox(inventorySizes);
	add(comboBox);
    }

    public int getInventorySize() {
	inventorySize = Integer.parseInt(comboBox.getItemAt(comboBox.getSelectedIndex()).toString());
	return inventorySize;
    }

    public JComboBox getComboBox() {
	return comboBox;
    }
    
    
}
