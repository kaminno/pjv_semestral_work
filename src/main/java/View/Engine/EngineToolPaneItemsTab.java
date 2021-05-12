package View.Engine;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class EngineToolPaneItemsTab extends JPanel{
    private EngineToolPaneItemsTabHelmet panelHelmet;

    public EngineToolPaneItemsTab() {
	setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
	init();
    }
    
    private void init(){
	JPanel jp1 = new JPanel();
	JLabel jl1 = new JLabel("Helmet");
	jl1.setAlignmentX(CENTER_ALIGNMENT);
	jp1.setLayout(new BoxLayout(jp1, BoxLayout.PAGE_AXIS));
	jp1.add(jl1);
	panelHelmet = new EngineToolPaneItemsTabHelmet();
	jp1.add(panelHelmet);
	add(jp1);
	
	add(new JSeparator(SwingConstants.VERTICAL));
    }
    
}
