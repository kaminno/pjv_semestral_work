package View.Engine;

import View.GameIcons;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EngineToolPaneTerrainTabSolidPanel extends JPanel{
    private JLabel labelRock;
    private JLabel labelWall;

    public EngineToolPaneTerrainTabSolidPanel() {
	init();
	add(labelRock);
	add(labelWall);
    }
    
    private void init(){
	labelRock = new JLabel(GameIcons.ROCK.getIcon());
	labelRock.setToolTipText("Rock");
	labelWall = new JLabel(GameIcons.WALL.getIcon());
	labelWall.setToolTipText("Wall");
    }

    public JLabel getLabelRock() {
	return labelRock;
    }

    public JLabel getLabelWall() {
	return labelWall;
    }
}
