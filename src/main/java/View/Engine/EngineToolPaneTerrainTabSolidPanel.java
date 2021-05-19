package View.Engine;

import View.GameIcons;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * solid terrain panel in terrain tabbed pane
 * @author honzuna
 */
public class EngineToolPaneTerrainTabSolidPanel extends JPanel {

    private JLabel labelRock;
    private JLabel labelWall;

    /**
     *
     */
    public EngineToolPaneTerrainTabSolidPanel() {
	init();
	add(labelRock);
	add(labelWall);
    }

    private void init() {
	// layout
	labelRock = new JLabel(GameIcons.ROCK.getIcon());
	labelRock.setToolTipText("Rock");
	labelWall = new JLabel(GameIcons.WALL.getIcon());
	labelWall.setToolTipText("Wall");
    }

    /**
     *
     * @return
     */
    public JLabel getLabelRock() {
	return labelRock;
    }

    /**
     *
     * @return
     */
    public JLabel getLabelWall() {
	return labelWall;
    }
}
