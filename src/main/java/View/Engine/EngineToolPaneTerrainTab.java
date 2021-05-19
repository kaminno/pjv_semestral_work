package View.Engine;

import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * tabbed pane item with terrain types
 * @author honzuna
 */
public class EngineToolPaneTerrainTab extends JPanel {

    private EngineToolPaneTerrainTabNewMapPanel panelNewMap;
    private EngineToolPaneTerrainTabGroundPanel panelGround;
    private EngineToolPaneTerrainTabSolidPanel panelSolid;

    private JTextArea ar;
    private JButton btn;

    /**
     *
     * @throws IOException
     */
    public EngineToolPaneTerrainTab() throws IOException {
	setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
	init();
    }

    private void init() {
	// create layout
	JPanel jp1 = new JPanel();
	JLabel jl1 = new JLabel("New Map");
	jl1.setAlignmentX(CENTER_ALIGNMENT);
	jp1.setLayout(new BoxLayout(jp1, BoxLayout.PAGE_AXIS));
	jp1.add(jl1);
	panelNewMap = new EngineToolPaneTerrainTabNewMapPanel();
	jp1.add(panelNewMap);
	add(jp1);

	JPanel jp2 = new JPanel();
	JLabel jl2 = new JLabel("Ground Terrains");
	jl2.setAlignmentX(CENTER_ALIGNMENT);
	jp2.setLayout(new BoxLayout(jp2, BoxLayout.PAGE_AXIS));
	jp2.add(jl2);
	panelGround = new EngineToolPaneTerrainTabGroundPanel();
	jp2.add(panelGround);
	add(jp2);

	JPanel jp3 = new JPanel();
	JLabel jl3 = new JLabel("Solid Terrains");
	jl3.setAlignmentX(CENTER_ALIGNMENT);
	jp3.setLayout(new BoxLayout(jp3, BoxLayout.PAGE_AXIS));
	jp3.add(jl3);
	panelSolid = new EngineToolPaneTerrainTabSolidPanel();
	jp3.add(panelSolid);
	add(jp3);
    }

    /**
     *
     * @return
     */
    public JTextArea getAr() {
	return ar;
    }

    /**
     *
     * @return
     */
    public JButton getBtn() {
	return btn;
    }

    /**
     *
     * @return
     */
    public EngineToolPaneTerrainTabNewMapPanel getPanelNewMap() {
	return panelNewMap;
    }

    /**
     *
     * @return
     */
    public EngineToolPaneTerrainTabGroundPanel getPanelGround() {
	return panelGround;
    }

    /**
     *
     * @return
     */
    public EngineToolPaneTerrainTabSolidPanel getPanelSolid() {
	return panelSolid;
    }

}
