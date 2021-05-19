package View.Engine;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EngineBottomPanel extends JPanel {

    private JLabel lblTerrain;
    private JLabel lblCurrentTerrain;
    private JPanel pnlTerrain;

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

    public JLabel getLblTerrain() {
	return lblTerrain;
    }

    public JLabel getLblCurrentTerrain() {
	return lblCurrentTerrain;
    }

    public JPanel getPnlTerrain() {
	return pnlTerrain;
    }

    public void setLblTerrain(JLabel lblTerrain) {
	this.lblTerrain = lblTerrain;
    }

    public void setLblCurrentTerrain(JLabel lblCurrentTerrain) {
	this.lblCurrentTerrain = lblCurrentTerrain;
    }

    public void setPnlTerrain(JPanel pnlTerrain) {
	this.pnlTerrain = pnlTerrain;
    }
}
