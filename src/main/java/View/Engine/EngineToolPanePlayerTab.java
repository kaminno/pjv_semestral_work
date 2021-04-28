package View.Engine;

import View.GameIcons;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class EngineToolPanePlayerTab extends JPanel{
    JLabel l1 = new JLabel("Label 1");
    JLabel l2 = new JLabel("Label 2");
    JLabel l3 = new JLabel(GameIcons.GRASS.getIcon());

    public EngineToolPanePlayerTab() {
	l1.setBackground(Color.red);
	l2.setBackground(Color.MAGENTA);
	l1.setOpaque(true);
	l2.setOpaque(true);
	//add(new JToolBar());
	
	setLayout(new GridBagLayout());
	
	//l2.setPreferredSize(new Dimension(160, 80));
	JSeparator sep = new JSeparator();
	sep.setOrientation(SwingConstants.VERTICAL);
	JPanel p = new JPanel();
	//p.setSize(new Dimension(100, 50));
	p.setBackground(Color.green);
	JPanel p2 = new JPanel();
	p.setSize(new Dimension(150, 200));
	p2.setSize(new Dimension(150, 100));
	p2.setBackground(Color.yellow);
	add(p);
	p.add(l1);
	add(sep);
	add(p2);
	add(new JSeparator(SwingConstants.VERTICAL));
	JPanel pan = new JPanel();
//	GridLayout gr = new GridLayout(2, 4);
//	gr.setHgap(50);
//	gr.setVgap(20);
//	pan.setLayout(gr);
	pan.setBackground(Color.blue);
	//pan.setSize(new Dimension(50, 100));
	add(pan);
	l3.setBackground(Color.blue);
	pan.add(l3);
	pan.add(new JButton("Hello there"));
	pan.add(new JLabel("JBL"));
	pan.add(new JLabel("QQE"));
	pan.add(new JLabel("HUGO"));
	add(new JSeparator(SwingConstants.VERTICAL));
	add(l2);
    }

    public JLabel getL1() {
	return l1;
    }

    public JLabel getL2() {
	return l2;
    }
    
}
