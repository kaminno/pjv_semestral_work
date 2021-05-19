package View.Engine;

import View.GameIcons;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * panel with icons to create and remove new map in the game
 * @author honzuna
 */
public class EngineToolPaneTerrainTabNewMapPanel extends JPanel {

    private JLabel labelNewMap;
    private JLabel labelRemoveMap;

    /**
     *
     */
    public EngineToolPaneTerrainTabNewMapPanel() {
	init();
    }

    private void init() {
	// layout
	labelNewMap = new JLabel(GameIcons.NEW_MAP.getIcon());
	labelNewMap.setToolTipText("Add new map");
	add(labelNewMap);
	labelRemoveMap = new JLabel(GameIcons.REMOVE_MAP.getIcon());
	labelRemoveMap.setToolTipText("Remove map");
	add(labelRemoveMap);
    }

    /**
     *
     * @return
     */
    public JLabel getLabelNewMap() {
	return labelNewMap;
    }

    /**
     *
     * @return
     */
    public JLabel getLabelRemoveMap() {
	return labelRemoveMap;
    }
}
