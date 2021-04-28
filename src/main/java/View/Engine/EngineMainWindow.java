package View.Engine;

import Exceptions.WrongTerrainType;
import Model.Map.Map;
import Model.Terrains.TerrainType;
import View.Engine.EngineMenu;
import View.GameIcons;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EngineMainWindow extends JFrame{
    Container pane;
    EngineMenu menu;
    EngineToolPane toolPane;
    EngineMainWorkingPanel mainPanel;
    TerrainType currentTerrainType = null;
    
    JTextField tf;
    JButton b;

    public EngineMainWindow(String title) throws HeadlessException {
	super(title);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setMinimumSize(new Dimension(1200, 800));
	//this.setSize(1200, 800);
	//this.setMaximumSize(new Dimension(828, 666));
	pane = this.getContentPane();
	
        pane.setLayout(new BorderLayout());

	createWindow();
	setActions();
	this.setVisible(true);
    }
    
    private void createWindow(){
	menu = new EngineMenu();
	//pane.add(m, BorderLayout.PAGE_START);
	setJMenuBar(menu);
	Image icon = Toolkit.getDefaultToolkit().getImage("resources/helmet_icon.jpg");
	setIconImage(icon);
	tf=new JTextField();
	tf.setText("Toto je text a tak to nezůstane pokud ho někdo změní");
	
	toolPane = new EngineToolPane();
	pane.add(toolPane, BorderLayout.NORTH);
	mainPanel = new EngineMainWorkingPanel();
	pane.add(mainPanel, BorderLayout.CENTER);
	

//	JPanel panel = new JPanel();
//	panel.setBackground(Color.red);
//	JPanel mapPanel = new JPanel(new GridLayout(20, 20));
//	mapPanel.setMinimumSize(new Dimension(200, 50));
//	mapPanel.setBackground(Color.GRAY);
//	//mapPanel.setOpaque(true);
//	panel.add(mapPanel);
//	//mapPanel.add(new JButton("b"));
//	ImageIcon imageIconG = new ImageIcon(new ImageIcon("resources/terrain_grass.jpg").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
//	ImageIcon imageIconS = new ImageIcon(new ImageIcon("resources/terrain_sand.jpg").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
//	ImageIcon imageIconW = new ImageIcon(new ImageIcon("resources/terrain_water.jpg").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
//	JLabel specialLabel = new JLabel(imageIconS);
//	ImageIcon currentIcon = imageIconW;
//	for(int i = 0; i < 20; i++){
//	    for(int j = 0; j < 20; j++){
//		if(i == 7 && j == 13){
//		    mapPanel.add(specialLabel);
//		}
//		else if(((i%2 == 0) && (j % 2 == 1)) || ((i%2 == 1)&&(j%2==0))){
//		    mapPanel.add(new JLabel(imageIconG));
//		}
//		else{
//		    mapPanel.add(new JLabel(imageIconS));
//		}
//		
//	    }
//	}
//	mapPanel.addMouseListener(new MouseAdapter(){
//	    public void mouseClicked(MouseEvent e) {
//		int x = e.getX();
//		int y = e.getY();
//		System.out.println("x:" + x);
//		System.out.println("Y:" + y);
//		System.out.println("sx:"+(x - x%40)/40);
//		System.out.println("sy:"+(y - y%40)/40);
//		mapPanel.remove(4);
//		//mapPanel.remove((int)(x - x%40)/40 + (int)(y - y%40)/40);
//		//mapPanel.add(new JLabel(imageIconW), (int)(x - x%40)/40 + (int)(y - y%40)/40);
//		//mapPanel.add(new JLabel(imageIconW), 0);
//		specialLabel.setIcon(currentIcon);
//		mapPanel.add(new JLabel(imageIconW), 4);
//		//mapPanel.add(new JButton("dlksf"));
//		System.out.println("index: " + (int)(x - x%40)/40 + (int)(y - y%40)/40);
//		x = 0;
//		y = 0;
//		//mapPanel.add(new JLabel(imageIconW), (int)x/40 * (int)y/40);
//		mapPanel.repaint();
//		//pane.repaint();
//		
//	    }
//	});
//	pane.add(panel, BorderLayout.CENTER);
	
//	mapPanel.addMouseListener(new MouseAdapter() {
//	    public void mousePressed(MouseEvent e) {
//		currentLocation = e.getPoint();
//	     }
//	 });
//	mapPanel.addMouseMotionListener(new MouseAdapter() {
//	    public void mouseDragged(MouseEvent e) {
//		Point currentScreenLocation = e.getLocationOnScreen();
//		setLocation(currentScreenLocation.x - currentLocation.x, currentScreenLocation.y - currentLocation.y);
//	    }
//	});
	
	
	
//	bb.addActionListener(new ActionListener(){  
//	    public void actionPerformed(ActionEvent e){  
//			etp.getTerrainTab().getAr().setText("Huhůůůů");
//			etp.getTerrainTab().add(new JButton("Pfff"));
//		    }  
//		});
//	etp.getTerrainTab().getBtn().addActionListener(new ActionListener(){  
//	    public void actionPerformed(ActionEvent e){  
//			currentIcon.setImage(imageIconG.getImage());
//		    }
//		});
    }
    
    private void setActions(){
	b = new JButton("Tlačítko");
	b.setVisible(false);
	pane.add(tf, BorderLayout.SOUTH);
	pane.add(b, BorderLayout.LINE_END);
	
	toolPane.getTerrainTab().getPanelNewMap().getLabelNewMap().addMouseListener(new MouseAdapter(){
	    public void mouseClicked(MouseEvent e) {
		int height = 10;
		int width = 15;
		Object[] possibilities = {"blanc", "grass", "sand", "loam", "water"};
		String s = (String)JOptionPane.showInputDialog(
                    mainPanel,
                    "Fill with: ",
                    "Choose terrain to fill",
                    JOptionPane.PLAIN_MESSAGE,
                    GameIcons.NEW_MAP.getIcon(),
                    possibilities,
                    "blanc");
		
		TerrainType type = null;
		for(TerrainType t : TerrainType.values()){
		    if(t.getName() == s){
			type = t;
			break;
		    }
		}
		//JLabel terrain = new JLabel(GameIcons.GRASS.getIcon());
		if(type != null){
		    if(mainPanel.getCurrentMap() != null){
			mainPanel.removeMap(mainPanel.getCurrentMap().getId());
		    }
		    EngineMapPanel newMap = new EngineMapPanel(width, height);
		    Map map = new Map(height, width);
		    try {
			map.fillTheMapWithGround(type.getName(), type, 0, 0);
			System.out.println("try");
		    } catch (WrongTerrainType ex) {
			Logger.getLogger(EngineMainWindow.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("catch");
		    }
		    map.printMapTerrain();
		    newMap.fillTheMapWithGround(type);
		    mainPanel.addNewMap(newMap);
		    
		    mainPanel.getCurrentMap().addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
			    int x = e.getX();
			    int y = e.getY();
			    System.out.println("x:" + x);
			    System.out.println("Y:" + y);
			    System.out.println("sx:"+(x - x%40)/40);
			    System.out.println("sy:"+(y - y%40)/40);
			    System.out.println("idx:" + ((mainPanel.getWidth()*((y - y%40)/40) + (x - x%40)/40)));
			    
			    ImageIcon icon = GameIcons.BLANC.getIcon();
			    if(currentTerrainType != null){
				for(GameIcons gi : GameIcons.values()){
				    if(gi.getLabel() == currentTerrainType.getName()){
					icon = gi.getIcon();
					break;
				    }
				}
			    }
			    mainPanel.getCurrentMap().removeTerrain((x - x%40)/40, (y - y%40)/40);
			    mainPanel.getCurrentMap().addTerrain(new JLabel(icon), (x - x%40)/40, (y - y%40)/40);
			    System.out.println("Component count: " + mainPanel.getCurrentMap().getComponentCount());
			    System.out.println("Array length: " + mainPanel.getCurrentMap().getMapBoard().size());

			}
		    });
		}}});
	
	toolPane.getTerrainTab().getPanelNewMap().getLabelRemoveMap().addMouseListener(new MouseAdapter(){
	    public void mouseClicked(MouseEvent e) {
		if(mainPanel.getCurrentMap() != null){
		    int n = JOptionPane.showConfirmDialog(
			mainPanel,
			"Do you really want to remove this map?",
			"Remove Map?",
			JOptionPane.YES_NO_OPTION);
		    if(n == 0){
			mainPanel.removeMap(mainPanel.getCurrentMap().getId());
		    }
		}
		}});
	
	menu.getMenuTools().getTool2().addActionListener(new ActionListener(){  
	    public void actionPerformed(ActionEvent e){  
			b.setVisible(true);
		    }  
		});
	menu.getMenuEdit().getEdit1().addActionListener(new ActionListener(){  
	    public void actionPerformed(ActionEvent e){  
			b.setVisible(false);
		    }  
		});
	menu.getMenuTools().getTool1().addActionListener(new ActionListener(){  
	    public void actionPerformed(ActionEvent e){  
			toolPane.setVisible(true);
		    }  
		});
	menu.getMenuFile().getMenuItemExit().addActionListener(new ActionListener(){  
	    public void actionPerformed(ActionEvent e){  
			System.exit(0);
		    }  
		});
	menu.getMenuFile().getMenuItemLoadGame().addActionListener(new ActionListener(){  
	    public void actionPerformed(ActionEvent e){  
			JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"JPG & GIF Images", "jpg", "gif");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(getParent());
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       System.out.println("You chose to open this file: " +
				    chooser.getSelectedFile().getName());
			    }
		}  
		});
	toolPane.getTerrainTab().getPanelGround().getLabelGrass().addMouseListener(new MouseAdapter(){  
	    public void actionPerformed(ActionEvent e){  
			JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"JPG & GIF Images", "jpg", "gif");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(getParent());
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       System.out.println("You chose to open this file: " +
				    chooser.getSelectedFile().getName());
			    }
		}  
		});
	toolPane.getTerrainTab().getPanelGround().getLabelGrass().addMouseListener(new MouseAdapter(){  
	    public void mouseClicked(MouseEvent e)  {  
	       currentTerrainType = TerrainType.GRASS;
	       System.out.println("GRASS");
	    }  
	});
	
	toolPane.getTerrainTab().getPanelGround().getLabelSand().addMouseListener(new MouseAdapter(){  
	    public void mouseClicked(MouseEvent e)  {  
	       currentTerrainType = TerrainType.SAND;
	       System.out.println("SAND");
	    }  
	});
	
	toolPane.getTerrainTab().getPanelGround().getLabelLoam().addMouseListener(new MouseAdapter(){  
	    public void mouseClicked(MouseEvent e)  {  
	       currentTerrainType = TerrainType.LOAM;
	       System.out.println("LOAM");
	    }  
	});
	
	toolPane.getTerrainTab().getPanelGround().getLabelWater().addMouseListener(new MouseAdapter(){  
	    public void mouseClicked(MouseEvent e)  {  
	       currentTerrainType = TerrainType.WATER;
	       System.out.println("WATER");
	    }  
	});
	
//	mainPanel.getCurrentMap().addMouseListener(new MouseAdapter(){
//	    public void mouseClicked(MouseEvent e) {
//		int x = e.getX();
//		int y = e.getY();
//		System.out.println("x:" + x);
//		System.out.println("Y:" + y);
//		System.out.println("sx:"+(x - x%40)/40);
//		System.out.println("sy:"+(y - y%40)/40);
//		System.out.println("idx:" + ((mainPanel.getWidth()*((y - y%40)/40) + (x - x%40)/40)));
//		
//		
//	    }
//	});
    }

    public EngineToolPane getToolPane() {
	return toolPane;
    }

    public EngineMainWorkingPanel getMainPanel() {
	return mainPanel;
    }

    public EngineMenu getMenu() {
	return menu;
    }

    public JTextField getTf() {
	return tf;
    }

    public JButton getB() {
	return b;
    }
    
    
}
