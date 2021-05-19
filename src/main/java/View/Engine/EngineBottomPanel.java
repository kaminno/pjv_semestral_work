package View.Engine;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * GUI bottom panel that shows current terrain type
 * @author honzuna
 */
public class EngineBottomPanel extends JPanel {

    private JLabel lblTerrain;
    private JLabel lblCurrentTerrain;
    private JPanel pnlTerrain;

    /**
     *
     */
    public EngineBottomPanel() {
	init();
    }

    private void init() {
	pnlTerrain = new JPanel();
	lblTerrain = new JLabel("Terrain: ");
	lblCurrentTerrain = new JLabel();
	lblTerrain.setAlignmentX(RIGHT_ALIGNMENT);
	lblCurrentTerrain.setAlignmentX(RIGHT_ALIGNMENT);
	pnlTerrain.add(lblTerrain);
	pnlTerrain.add(lblCurrentTerrain);

	add(pnlTerrain);
    }

    /**
     *
     * @return
     */
    public JLabel getLblTerrain() {
	return lblTerrain;
    }

    /**
     *
     * @return
     */
    public JLabel getLblCurrentTerrain() {
	return lblCurrentTerrain;
    }

    /**
     *
     * @return
     */
    public JPanel getPnlTerrain() {
	return pnlTerrain;
    }

    /**
     *
     * @param lblTerrain
     */
    public void setLblTerrain(JLabel lblTerrain) {
	this.lblTerrain = lblTerrain;
    }

    /**
     *
     * @param lblCurrentTerrain
     */
    public void setLblCurrentTerrain(JLabel lblCurrentTerrain) {
	this.lblCurrentTerrain = lblCurrentTerrain;
    }

    /**
     *
     * @param pnlTerrain
     */
    public void setPnlTerrain(JPanel pnlTerrain) {
	this.pnlTerrain = pnlTerrain;
    }
}
