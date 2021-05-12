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
import javax.swing.BoxLayout;
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
	setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
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
	JPanel jp1 = new JPanel();
	JLabel jl1 = new JLabel("New Map");
	jl1.setAlignmentX(CENTER_ALIGNMENT);
	jp1.setLayout(new BoxLayout(jp1, BoxLayout.PAGE_AXIS));
	jp1.add(jl1);
	panelNewMap = new EngineToolPaneTerrainTabNewMapPanel();
	jp1.add(panelNewMap);
	add(jp1);
	
	//add(new JSeparator(SwingConstants.VERTICAL));
	
	JPanel jp2 = new JPanel();
	JLabel jl2 = new JLabel("Ground Terrains");
	jl2.setAlignmentX(CENTER_ALIGNMENT);
	jp2.setLayout(new BoxLayout(jp2, BoxLayout.PAGE_AXIS));
	jp2.add(jl2);
	panelGround = new EngineToolPaneTerrainTabGroundPanel();
	jp2.add(panelGround);
	add(jp2);
	
	//add(new JSeparator(SwingConstants.VERTICAL));
	
	JPanel jp3 = new JPanel();
	JLabel jl3 = new JLabel("Solid Terrains");
	jl3.setAlignmentX(CENTER_ALIGNMENT);
	jp3.setLayout(new BoxLayout(jp3, BoxLayout.PAGE_AXIS));
	jp3.add(jl3);
	panelSolid = new EngineToolPaneTerrainTabSolidPanel();
	jp3.add(panelSolid);
	add(jp3);
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
