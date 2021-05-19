package View.Engine;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EngineBottomPanel extends JPanel{
    private JLabel lblTab;
    private JLabel lblCurrentTab;
    private JLabel lblTerrain;
    private JLabel lblCurrentTerrain;
    private JLabel lblFigure;
    private JLabel lblCurrentFigure;
    private JPanel pnlTab;
    private JPanel pnlTerrain;
    private JPanel pnlFigure;

    public EngineBottomPanel() {
	init();
    }
    
    private void init(){
//	pnlTab = new JPanel();
//	lblTab = new JLabel("Tab: ");
//	lblCurrentTab = new JLabel();
//	lblTab.setAlignmentX(LEFT_ALIGNMENT);
//	lblCurrentTab.setAlignmentX(LEFT_ALIGNMENT);
//	pnlTab.add(lblTab);
//	pnlTab.add(lblCurrentTab);
	
	pnlTerrain = new JPanel();
	lblTerrain = new JLabel("Terrain: ");
	lblCurrentTerrain = new JLabel();
	lblTerrain.setAlignmentX(RIGHT_ALIGNMENT);
	lblCurrentTerrain.setAlignmentX(RIGHT_ALIGNMENT);
	pnlTerrain.add(lblTerrain);
	pnlTerrain.add(lblCurrentTerrain);
	
	//add(pnlTab);
	add(pnlTerrain);
    }

    public JLabel getLblTab() {
	return lblTab;
    }

    public JLabel getLblCurrentTab() {
	return lblCurrentTab;
    }

    public JLabel getLblTerrain() {
	return lblTerrain;
    }

    public JLabel getLblCurrentTerrain() {
	return lblCurrentTerrain;
    }

    public JLabel getLblFigure() {
	return lblFigure;
    }

    public JLabel getLblCurrentFigure() {
	return lblCurrentFigure;
    }

    public JPanel getPnlTab() {
	return pnlTab;
    }

    public JPanel getPnlTerrain() {
	return pnlTerrain;
    }

    public JPanel getPnlFigure() {
	return pnlFigure;
    }

    public void setLblTab(JLabel lblTab) {
	this.lblTab = lblTab;
    }

    public void setLblCurrentTab(JLabel lblCurrentTab) {
	this.lblCurrentTab = lblCurrentTab;
    }

    public void setLblTerrain(JLabel lblTerrain) {
	this.lblTerrain = lblTerrain;
    }

    public void setLblCurrentTerrain(JLabel lblCurrentTerrain) {
	this.lblCurrentTerrain = lblCurrentTerrain;
    }

    public void setLblFigure(JLabel lblFigure) {
	this.lblFigure = lblFigure;
    }

    public void setLblCurrentFigure(JLabel lblCurrentFigure) {
	this.lblCurrentFigure = lblCurrentFigure;
    }

    public void setPnlTab(JPanel pnlTab) {
	this.pnlTab = pnlTab;
    }

    public void setPnlTerrain(JPanel pnlTerrain) {
	this.pnlTerrain = pnlTerrain;
    }

    public void setPnlFigure(JPanel pnlFigure) {
	this.pnlFigure = pnlFigure;
    }
    
    
}
