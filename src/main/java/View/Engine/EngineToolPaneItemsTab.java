package View.Engine;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class EngineToolPaneItemsTab extends JPanel {

    private EngineToolPaneItemsTabHelmet panelHelmet;
    private EngineToolPaneItemsTabChest panelChest;
    private EngineToolPaneItemsTabLegs panelLegs;
    private EngineToolPaneItemsTabGloves panelGloves;
    private EngineToolPaneItemsTabBoots panelBoots;

    public EngineToolPaneItemsTab() {
	setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
	init();
    }

    private void init() {
	JPanel jp1 = new JPanel();
	JLabel jl1 = new JLabel("Helmet");
	jl1.setAlignmentX(CENTER_ALIGNMENT);
	jp1.setLayout(new BoxLayout(jp1, BoxLayout.PAGE_AXIS));
	jp1.add(jl1);
	panelHelmet = new EngineToolPaneItemsTabHelmet();
	jp1.add(panelHelmet);
	add(jp1);

	add(new JSeparator(SwingConstants.VERTICAL));

	JPanel jp2 = new JPanel();
	JLabel jl2 = new JLabel("Chest");
	jl2.setAlignmentX(CENTER_ALIGNMENT);
	jp2.setLayout(new BoxLayout(jp2, BoxLayout.PAGE_AXIS));
	jp2.add(jl2);
	panelChest = new EngineToolPaneItemsTabChest();
	jp2.add(panelChest);
	add(jp2);

	add(new JSeparator(SwingConstants.VERTICAL));

	JPanel jp3 = new JPanel();
	JLabel jl3 = new JLabel("Trousers");
	jl3.setAlignmentX(CENTER_ALIGNMENT);
	jp3.setLayout(new BoxLayout(jp3, BoxLayout.PAGE_AXIS));
	jp3.add(jl3);
	panelLegs = new EngineToolPaneItemsTabLegs();
	jp3.add(panelLegs);
	add(jp3);

	add(new JSeparator(SwingConstants.VERTICAL));

	JPanel jp4 = new JPanel();
	JLabel jl4 = new JLabel("Gloves");
	jl4.setAlignmentX(CENTER_ALIGNMENT);
	jp4.setLayout(new BoxLayout(jp4, BoxLayout.PAGE_AXIS));
	jp4.add(jl4);
	panelGloves = new EngineToolPaneItemsTabGloves();
	jp4.add(panelGloves);
	add(jp4);

	add(new JSeparator(SwingConstants.VERTICAL));

	JPanel jp5 = new JPanel();
	JLabel jl5 = new JLabel("Boots");
	jl5.setAlignmentX(CENTER_ALIGNMENT);
	jp5.setLayout(new BoxLayout(jp5, BoxLayout.PAGE_AXIS));
	jp5.add(jl5);
	panelBoots = new EngineToolPaneItemsTabBoots();
	jp5.add(panelBoots);
	add(jp5);
    }

    public EngineToolPaneItemsTabHelmet getPanelHelmet() {
	return panelHelmet;
    }

    public EngineToolPaneItemsTabChest getPanelChest() {
	return panelChest;
    }

    public EngineToolPaneItemsTabLegs getPanelLegs() {
	return panelLegs;
    }

    public EngineToolPaneItemsTabGloves getPanelGloves() {
	return panelGloves;
    }

    public EngineToolPaneItemsTabBoots getPanelBoots() {
	return panelBoots;
    }
}
