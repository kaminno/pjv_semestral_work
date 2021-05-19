package View.Engine;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 * 
 * @author honzuna
 */
public class EngineToolPanePlayerTab extends JPanel {

    private EngineToolPanePlayerTabNewPlayer panelNewPlayer;
    private EngineToolPanePlayerTabAttributes panelAttributes;
    private EngineToolPanePlayerTabInventory panelInventory;
    private EngineToolPanePlayerTabWeapons panelWeapons;

    /**
     *
     */
    public EngineToolPanePlayerTab() {
	setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
	init();
    }

    private void init() {
	// player tabbed pane panel design
	JPanel jp1 = new JPanel();
	JLabel jl1 = new JLabel("New Player");
	jl1.setAlignmentX(CENTER_ALIGNMENT);
	jp1.setLayout(new BoxLayout(jp1, BoxLayout.PAGE_AXIS));
	jp1.add(jl1);
	panelNewPlayer = new EngineToolPanePlayerTabNewPlayer();
	jp1.add(panelNewPlayer);
	add(jp1);

	add(new JSeparator(SwingConstants.VERTICAL));

	JPanel jp2 = new JPanel();
	JLabel jl2 = new JLabel("Attributes");
	jl2.setAlignmentX(CENTER_ALIGNMENT);
	jp2.setLayout(new BoxLayout(jp2, BoxLayout.PAGE_AXIS));
	jp2.add(jl2);
	panelAttributes = new EngineToolPanePlayerTabAttributes();
	jp2.add(panelAttributes);
	add(jp2);

	add(new JSeparator(SwingConstants.VERTICAL));

	JPanel jp3 = new JPanel();
	JLabel jl3 = new JLabel("Inventory");
	jl3.setAlignmentX(CENTER_ALIGNMENT);
	jp3.setLayout(new BoxLayout(jp3, BoxLayout.PAGE_AXIS));
	jp3.add(jl3);
	panelInventory = new EngineToolPanePlayerTabInventory();
	jp3.add(panelInventory);
	add(jp3);

	add(new JSeparator(SwingConstants.VERTICAL));

	JPanel jp4 = new JPanel();
	JLabel jl4 = new JLabel("Starting Weapons");
	jl4.setAlignmentX(CENTER_ALIGNMENT);
	jp4.setLayout(new BoxLayout(jp4, BoxLayout.PAGE_AXIS));
	jp4.add(jl4);
	panelWeapons = new EngineToolPanePlayerTabWeapons();
	jp4.add(panelWeapons);
	add(jp4);
    }

    /**
     *
     * @return
     */
    public EngineToolPanePlayerTabNewPlayer getPanelNewPlayer() {
	return panelNewPlayer;
    }

    /**
     *
     * @return
     */
    public EngineToolPanePlayerTabAttributes getPanelAttributes() {
	return panelAttributes;
    }

    /**
     *
     * @return
     */
    public EngineToolPanePlayerTabInventory getPanelInventory() {
	return panelInventory;
    }

    /**
     *
     * @return
     */
    public EngineToolPanePlayerTabWeapons getPanelWeapons() {
	return panelWeapons;
    }
}
