package View.Game;

import Controller.GameController;
import Model.Map.MapSize;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
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
    
    public GameMainWindow(String title, GameController controller) throws HeadlessException, IOException {
	super(title);
	this.controller = controller;
	
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.setSize(new Dimension(MapSize.SIZE.getWidth()+8, MapSize.SIZE.getHeight() + 30));
	this.setResizable(false);
	pane = this.getContentPane();

        pane.setLayout(new CardLayout());
	gameMenu = new GameMenu(this);
	gameBoard = new GameBoard(gameMenu, controller);
	
	//pane.add(gameBoard, BorderLayout.CENTER);
	pane.add(gameBoard);
	//pane.add(gameMenu);
	this.setVisible(true);
    }
    
    
}
