package View.Engine;

import View.GameIcons;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * new player icon with labels for x and y coordinates
 * @author honzuna
 */
public class EngineToolPanePlayerTabNewPlayer extends JPanel {

    private JLabel labelNewPlayer;
    private JLabel labelX;
    private JLabel labelY;
    private JLabel coorX;
    private JLabel coorY;
    private JPanel panelCoordinates;

    /**
     *
     */
    public EngineToolPanePlayerTabNewPlayer() {
	init();
    }

    private void init() {
	// layout
	labelNewPlayer = new JLabel(GameIcons.NEW_PLAYER.getIcon());
	labelNewPlayer.setToolTipText("Add new player");
	add(labelNewPlayer);

	labelX = new JLabel("X: ");
	labelY = new JLabel("Y: ");
	coorX = new JLabel();
	coorY = new JLabel();
	JPanel jp1 = new JPanel();
	jp1.add(labelX);
	jp1.add(coorX);
	JPanel jp2 = new JPanel();
	jp2.add(labelY);
	jp2.add(coorY);
	panelCoordinates = new JPanel();
	panelCoordinates.setLayout(new BoxLayout(panelCoordinates, BoxLayout.PAGE_AXIS));
	panelCoordinates.add(jp1);
	panelCoordinates.add(jp2);
	add(panelCoordinates);
    }

    /**
     *
     * @return
     */
    public JLabel getLabelNewPlayer() {
	return labelNewPlayer;
    }

    /**
     *
     * @return
     */
    public JLabel getLabelX() {
	return labelX;
    }

    /**
     *
     * @return
     */
    public JLabel getLabelY() {
	return labelY;
    }

    /**
     *
     * @return
     */
    public JPanel getPanelCoordinates() {
	return panelCoordinates;
    }

    /**
     *
     * @return
     */
    public JLabel getCoorX() {
	return coorX;
    }

    /**
     *
     * @return
     */
    public JLabel getCoorY() {
	return coorY;
    }
}
