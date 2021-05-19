package View.Game;

import View.GameIcons;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class GameMenu extends JPanel{
    private JLabel exitGame;
    private JLabel returnToGame;
    private boolean shown;
    private Color buttonColor;
    private JFrame frame;
    private ImageIcon backgroundImage;

    public GameMenu(JFrame frame) {
	shown = false;
	buttonColor = new Color(171, 46, 9);
	
	backgroundImage = new ImageIcon("resources/game_menu_image.jpg");
	setLayout(null);
	setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
	setVisible(false);
	setOpaque(true);
	this.frame = frame;
	init();
    }
    
    private void init(){
	JLabel jl = new JLabel("MENU");
	jl.setForeground(new Color(140, 9, 0));
	jl.setAlignmentX(CENTER_ALIGNMENT);
	jl.setBounds(getWidth()/2 -45, 20, 100, 50);
	Font mf = new Font("Verdana", Font.BOLD, 30);
	jl.setFont(mf);
	add(jl);

	exitGame = new JLabel("Exit Game");
	exitGame.setAlignmentX(CENTER_ALIGNMENT);
	exitGame.setForeground(Color.BLACK);
	exitGame.setBounds(getWidth()/2 -70, 120, 160, 50);
	mf = new Font("Verdana", Font.BOLD, 22);
	exitGame.setFont(mf);
	add(exitGame);

	returnToGame = new JLabel("Return To Game");
	returnToGame.setAlignmentX(CENTER_ALIGNMENT);
	returnToGame.setForeground(Color.BLACK);
	returnToGame.setBounds(getWidth()/2 -100, 220, 260, 50);
	mf = new Font("Verdana", Font.BOLD, 22);
	returnToGame.setFont(mf);
	add(returnToGame);
	
	exitGame.addMouseListener(new MouseAdapter(){  
	    public void mouseClicked(MouseEvent e)  {  
		frame.dispose();
	    }  
	});
	
	exitGame.addMouseListener(new MouseAdapter(){  
	    public void mouseEntered(MouseEvent e)  {  
		exitGame.setForeground(new Color(138, 138, 138));
		exitGame.repaint();
	    }  
	});
	
	exitGame.addMouseListener(new MouseAdapter(){  
	    public void mouseExited(MouseEvent e)  {  
		exitGame.setForeground(Color.BLACK);
		exitGame.repaint();
	    }  
	});
	
	returnToGame.addMouseListener(new MouseAdapter(){  
	    public void mouseClicked(MouseEvent e)  {  
		setVisible(false);
	    }  
	});
	
	returnToGame.addMouseListener(new MouseAdapter(){  
	    public void mouseEntered(MouseEvent e)  {  
		returnToGame.setForeground(new Color(138, 138, 138));
		returnToGame.repaint();
	    }  
	});
	
	returnToGame.addMouseListener(new MouseAdapter(){  
	    public void mouseExited(MouseEvent e)  {  
		returnToGame.setForeground(Color.BLACK);
		returnToGame.repaint();
	    }  
	});
    }
    
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
	  g.drawImage(backgroundImage.getImage(), 0, 0, null);
    }
    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
	
	if(key == KeyEvent.VK_ESCAPE){
	    if(shown){
		setVisible(false);
		shown = false;
	    }
	    else{
		setVisible(true);
		shown = true;
	    }
	}
    }

    public boolean isShown() {
	return shown;
    }

    public Color getButtonColor() {
	return buttonColor;
    }

    public void setShown(boolean shown) {
	this.shown = shown;
    }

    public JLabel getExitGame() {
	return exitGame;
    }

    public JLabel getReturnToGame() {
	return returnToGame;
    }

    public JFrame getFrame() {
	return frame;
    }

    public ImageIcon getBackgroundImage() {
	return backgroundImage;
    }
    
    
}
