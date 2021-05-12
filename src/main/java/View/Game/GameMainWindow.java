package View.Game;

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
    
    private Image backgroundImage;
    
    public GameMainWindow(String title) throws HeadlessException, IOException {
	super(title);
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.setSize(new Dimension(MapSize.SIZE.getWidth()+8, MapSize.SIZE.getHeight() + 30));
	this.setResizable(false);
	pane = this.getContentPane();

        pane.setLayout(new CardLayout());
	gameBoard = new GameBoard();
	//pane.add(gameBoard, BorderLayout.CENTER);
	pane.add(gameBoard);
	this.setVisible(true);
    }
}
