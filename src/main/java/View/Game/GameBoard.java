package View.Game;

import Model.Map.MapSize;
import Model.Terrains.TerrainSize;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
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
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameBoard extends JPanel implements ActionListener{
    private Image backgroundImage;
    
    private Timer timer;
    private PlayerView player;
    private boolean ingame;
    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 300;
    private final int DELAY = 15;

    public GameBoard() throws IOException {
	//this.setSize(new Dimension(MapSize.SIZE.getWidth(), MapSize.SIZE.getHeight()));
	backgroundImage = ImageIO.read(new File("moje_mapa.png"));
	//this.add(new JLabel(new ImageIcon(backgroundImage)));
	initBoard();
    }
    
    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        ingame = true;

        setPreferredSize(new Dimension(MapSize.getSIZE().getWidth(), MapSize.getSIZE().getWidth()));

        player = new PlayerView(150, 300);

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

        //g.setColor(Color.WHITE);
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
            player.move();
	    if(player.getSwapTime() == 7 && player.getMoved()){
		player.swapIcons();
	    }
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
        }
    }
}
