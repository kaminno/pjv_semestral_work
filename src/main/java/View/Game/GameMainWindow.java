package View.Game;

import Controller.GameController;
import Model.Figures.Player;
import Model.Map.MapSize;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameMainWindow extends JFrame{
    private Container pane;
    private GameBoard gameBoard;
    private GameMenu gameMenu;
    GameController controller;
    
    private Image backgroundImage;
    
    public GameMainWindow(String title, GameController controller, Player pl) throws HeadlessException, IOException {
	super(title);
	this.controller = controller;
	
	Image icon = Toolkit.getDefaultToolkit().getImage("resources/helmet_icon.jpg");
	setIconImage(icon);
	
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.setSize(new Dimension(MapSize.SIZE.getWidth()+8, MapSize.SIZE.getHeight() + 30));
	this.setResizable(false);
	pane = this.getContentPane();

        pane.setLayout(new CardLayout());
	gameMenu = new GameMenu(this);
	gameBoard = new GameBoard(gameMenu, controller, pl);
	
	//pane.add(gameBoard, BorderLayout.CENTER);
	pane.add(gameBoard);
	//pane.add(gameMenu);
	this.setVisible(true);
	
	this.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		controller.clearData();
	    }
	}); 
    }
}
