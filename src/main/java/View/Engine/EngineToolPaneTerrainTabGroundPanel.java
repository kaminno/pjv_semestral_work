package View.Engine;

import View.GameIcons;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EngineToolPaneTerrainTabGroundPanel extends JPanel {

    private JLabel labelGrass;
    private JLabel labelSand;
    private JLabel labelLoam;
    private JLabel labelWater;

    public EngineToolPaneTerrainTabGroundPanel() {
	init();
	add(labelGrass);
	add(labelSand);
	add(labelWater);
	add(labelLoam);
    }

    private void init() {
	labelGrass = new JLabel(GameIcons.GRASS.getIcon());
	labelGrass.setToolTipText("Grass");
	labelSand = new JLabel(GameIcons.SAND.getIcon());
	labelSand.setToolTipText("Sand");
	labelWater = new JLabel(GameIcons.WATER.getIcon());
	labelWater.setToolTipText("Water");
	labelLoam = new JLabel(GameIcons.LOAM.getIcon());
	labelLoam.setToolTipText("Loam");
    }

    public JLabel getLabelGrass() {
	return labelGrass;
    }

    public JLabel getLabelSand() {
	return labelSand;
    }

    public JLabel getLabelLoam() {
	return labelLoam;
    }

    public JLabel getLabelWater() {
	return labelWater;
    }
}
