package View.Engine;

import View.GameIcons;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class EngineToolPaneTerrainTab extends JPanel{
    private EngineToolPaneTerrainTabNewMapPanel panelNewMap;
    private EngineToolPaneTerrainTabGroundPanel panelGround;
    private EngineToolPaneTerrainTabSolidPanel panelSolid;
        
    private JTextArea ar;
    private JButton btn;
    
    public EngineToolPaneTerrainTab() throws IOException {
	GridLayout gr = new GridLayout(0, 4);
	setLayout(gr);
	init();
	//add(panelNewMap);
	
//	add(new JSeparator(SwingConstants.VERTICAL));
//	ar = new JTextArea(5, 20);
//	add(ar);
//	ImageIcon ic = new ImageIcon("resources/icon_main.png");
//	JLabel icc = new JLabel(ic);
//	JSeparator sep = new JSeparator();
//	sep.setOrientation(SwingConstants.VERTICAL);
//	add(sep);
//	add(icc);
//	add(new JSeparator(SwingConstants.VERTICAL));
//	ImageIcon imageIcon = new ImageIcon(new ImageIcon("resources/terrain_grass.jpg").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
//	JLabel wIcon = new JLabel(imageIcon);
//	add(wIcon);
//	wIcon.addMouseListener(new MouseAdapter(){
//	    public void mouseClicked(MouseEvent e) {
//		ar.setText("Nastaveno obrázkem");
//	    }
//	});
//	btn = new JButton(new ImageIcon(new ImageIcon("resources/terrain_grass.jpg").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
//	btn.setSize(new Dimension(20, 20));
//	add(btn);
//	
//	btn.addActionListener(new ActionListener(){  
//	    public void actionPerformed(ActionEvent e){  
//			ar.setText("Text co jsem napsal");
//		    }  
//		});
	
    }
    
    private void init(){
	panelNewMap = new EngineToolPaneTerrainTabNewMapPanel();
	add(panelNewMap);
	panelGround = new EngineToolPaneTerrainTabGroundPanel();
	add(panelGround);
	panelSolid = new EngineToolPaneTerrainTabSolidPanel();
	add(panelSolid);
    }

    public JTextArea getAr() {
	return ar;
    }

    public JButton getBtn() {
	return btn;
    }

    public EngineToolPaneTerrainTabNewMapPanel getPanelNewMap() {
	return panelNewMap;
    }

    public EngineToolPaneTerrainTabGroundPanel getPanelGround() {
	return panelGround;
    }

    public EngineToolPaneTerrainTabSolidPanel getPanelSolid() {
	return panelSolid;
    }
    
}
