package View.Engine;

import static java.awt.Component.CENTER_ALIGNMENT;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class EngineToolPaneBeastTab extends JPanel{
    private EngineToolPaneBeastTabWeakBeasts panelWeakBeasts;
    private EngineToolPaneBeastTabBosses panelBosses;

    public EngineToolPaneBeastTab() {
	setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
	init();
    }
    
    private void init(){
	JPanel jp1 = new JPanel();
	JLabel jl1 = new JLabel("Weak Beasts");
	jl1.setAlignmentX(CENTER_ALIGNMENT);
	jp1.setLayout(new BoxLayout(jp1, BoxLayout.PAGE_AXIS));
	jp1.add(jl1);
	panelWeakBeasts = new EngineToolPaneBeastTabWeakBeasts();
	jp1.add(panelWeakBeasts);
	add(jp1);
	
	add(new JSeparator(SwingConstants.VERTICAL));
	
	JPanel jp2 = new JPanel();
	JLabel jl2 = new JLabel("Bosses");
	jl2.setAlignmentX(CENTER_ALIGNMENT);
	jp2.setLayout(new BoxLayout(jp2, BoxLayout.PAGE_AXIS));
	jp2.add(jl2);
	panelBosses = new EngineToolPaneBeastTabBosses();
	jp2.add(panelBosses);
	add(jp2);
    }

    public EngineToolPaneBeastTabWeakBeasts getPanelWeakBeasts() {
	return panelWeakBeasts;
    }

    public EngineToolPaneBeastTabBosses getPanelBosses() {
	return panelBosses;
    }
}
