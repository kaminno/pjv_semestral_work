package View.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * game menu class
 * @author honzuna
 */
public class GameMenu extends JPanel {

    private JLabel exitGame;
    private JLabel returnToGame;
    private boolean shown;
    private Color buttonColor;
    private JFrame frame;
    private ImageIcon backgroundImage;

    /**
     *
     * @param frame
     */
    public GameMenu(JFrame frame) {
	shown = false;
	buttonColor = new Color(171, 46, 9);

	// create background as image
	backgroundImage = new ImageIcon("resources/game_menu_image.jpg");
	setLayout(null);
	setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
	setVisible(false);
	setOpaque(true);
	this.frame = frame;
	init();
    }

    private void init() {
	// create labels and position them on the game menu image
	JLabel jl = new JLabel("MENU");
	jl.setForeground(new Color(140, 9, 0));
	jl.setAlignmentX(CENTER_ALIGNMENT);
	jl.setBounds(getWidth() / 2 - 51, 20, 100, 50);
	Font mf = new Font("Verdana", Font.BOLD, 30);
	jl.setFont(mf);
	add(jl);

	exitGame = new JLabel("Exit Game");
	exitGame.setAlignmentX(CENTER_ALIGNMENT);
	exitGame.setForeground(Color.BLACK);
	exitGame.setBounds(getWidth() / 2 - 64, 120, 160, 50);
	mf = new Font("Verdana", Font.BOLD, 22);
	exitGame.setFont(mf);
	add(exitGame);

	returnToGame = new JLabel("Return To Game");
	returnToGame.setAlignmentX(CENTER_ALIGNMENT);
	returnToGame.setForeground(Color.BLACK);
	returnToGame.setBounds(getWidth() / 2 - 100, 220, 260, 50);
	mf = new Font("Verdana", Font.BOLD, 22);
	returnToGame.setFont(mf);
	add(returnToGame);

	// add listeners to labels to make them 'exit' or 'return' game. Add mouse hover effect too
	exitGame.addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		frame.dispose();
	    }
	});

	exitGame.addMouseListener(new MouseAdapter() {
	    public void mouseEntered(MouseEvent e) {
		exitGame.setForeground(new Color(138, 138, 138));
		exitGame.repaint();
	    }
	});

	exitGame.addMouseListener(new MouseAdapter() {
	    public void mouseExited(MouseEvent e) {
		exitGame.setForeground(Color.BLACK);
		exitGame.repaint();
	    }
	});

	returnToGame.addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		setVisible(false);
	    }
	});

	returnToGame.addMouseListener(new MouseAdapter() {
	    public void mouseEntered(MouseEvent e) {
		returnToGame.setForeground(new Color(138, 138, 138));
		returnToGame.repaint();
	    }
	});

	returnToGame.addMouseListener(new MouseAdapter() {
	    public void mouseExited(MouseEvent e) {
		returnToGame.setForeground(Color.BLACK);
		returnToGame.repaint();
	    }
	});
    }

    /**
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.drawImage(backgroundImage.getImage(), 0, 0, null);
    }

    /**
     * show / hide menu on pressing ESC key
     * @param e
     */
    public void keyPressed(KeyEvent e) {
	int key = e.getKeyCode();

	if (key == KeyEvent.VK_ESCAPE) {
	    if (shown) {
		setVisible(false);
		shown = false;
	    } else {
		setVisible(true);
		shown = true;
	    }
	}
    }

    /**
     *
     * @return
     */
    public boolean isShown() {
	return shown;
    }

    /**
     *
     * @return
     */
    public Color getButtonColor() {
	return buttonColor;
    }

    /**
     *
     * @param shown
     */
    public void setShown(boolean shown) {
	this.shown = shown;
    }

    /**
     *
     * @return
     */
    public JLabel getExitGame() {
	return exitGame;
    }

    /**
     *
     * @return
     */
    public JLabel getReturnToGame() {
	return returnToGame;
    }

    /**
     *
     * @return
     */
    public JFrame getFrame() {
	return frame;
    }

    /**
     *
     * @return
     */
    public ImageIcon getBackgroundImage() {
	return backgroundImage;
    }

}
