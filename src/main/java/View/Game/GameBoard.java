package View.Game;

import Controller.GameController;
import Model.Map.MapSize;
import Model.Terrains.Terrain;
import Model.Terrains.TerrainSize;
import View.GameIcons;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameBoard extends JPanel implements ActionListener{
    private Image backgroundImage;
    
    private Timer timer;
    private PlayerView player;
    private GameMenu gameMenu;
    private boolean ingame;
    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 300;
    private final int DELAY = 15;
    GameController controller;

    public GameBoard(GameMenu gameMenu, GameController controller) throws IOException {
	this.controller = controller;
	//this.setSize(new Dimension(MapSize.SIZE.getWidth(), MapSize.SIZE.getHeight()));
//	backgroundImage = ImageIO.read(new File("moje_mapa.png"));
	backgroundImage = ImageIO.read(new File("resources/current.map.png"));
	this.gameMenu = gameMenu;
	//this.add(new JLabel(new ImageIcon(backgroundImage)));
	initBoard();
    }
    
    private void initBoard() {
	//gameMenu = new GameMenu();
	
        addKeyListener(new TAdapter());
        setFocusable(true);
        ingame = true;

        setPreferredSize(new Dimension(MapSize.getSIZE().getWidth(), MapSize.getSIZE().getWidth()));

        player = new PlayerView(150, 300, 7);
	add(player.getInventory());
	add(player.getEquipment());
	add(gameMenu);
	//player.getInventory().setVisible(true);

        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {

            drawObjects(g);

        }

        Toolkit.getDefaultToolkit().sync();
    }
    
    private void drawObjects(Graphics g) {

	g.drawImage(backgroundImage, 0, 0, this);
	
        if (player.isVisible()) {
            g.drawImage(player.getImage(), player.getX(), player.getY(),
                    this);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();
	
	
        updatePlayer();

        repaint();
    }

    private void inGame() {

        if (!ingame) {
            timer.stop();
        }
    }

    private void updatePlayer() {

        if (player.isVisible()) {
	    controller.checkMove(player);
	    player.move();
	    if(player.getSwapTime() == 7 && player.getMoved()){
	        player.swapIcons();
	    }
	    
	    
//            player.move();
//	    if(player.getSwapTime() == 7 && player.getMoved()){
//		player.swapIcons();
//	    }
        }
    }
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
	    gameMenu.keyPressed(e);
        }
    }
}
