package View.Engine;

import View.GameIcons;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EngineToolPaneTerrainTabNewMapPanel extends JPanel{
    private JLabel labelNewMap;
    private JLabel labelRemoveMap;

    public EngineToolPaneTerrainTabNewMapPanel() {
	init();
    }
    
    private void init(){
	labelNewMap = new JLabel(GameIcons.NEW_MAP.getIcon());
	labelNewMap.setToolTipText("Add new map");
	add(labelNewMap);
	labelRemoveMap = new JLabel(GameIcons.REMOVE_MAP.getIcon());
	labelRemoveMap.setToolTipText("Remove map");
	add(labelRemoveMap);
    }

    public JLabel getLabelNewMap() {
	return labelNewMap;
    }

    public JLabel getLabelRemoveMap() {
	return labelRemoveMap;
    }
}
