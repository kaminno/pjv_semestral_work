package View.Game;

import View.GameIcons;
import java.awt.BorderLayout;
import java.awt.Color;
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

// TODO DESIGN

public class GameMenu extends JPanel{
    private JButton exitGame;
    private JButton returnToGame;
    private boolean shown;
    private Color buttonColor;
    private JFrame frame;
    //private ImageIcon backgroundImage;

    public GameMenu(JFrame frame) {
	shown = false;
	buttonColor = new Color(171, 46, 9);
	
	//backgroundImage = new ImageIcon("resources/game_menu_image.jpg");//mageIO.read(new File("resources/game_menu_image.jpg"));
	//setSize(backgroundImage.getIconHeight(), backgroundImage.getIconWidth());
	//setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	//setLayout(new GridLayout(2, 1));
	setLayout(new BorderLayout());
	setSize(300, 100);
	setVisible(false);
	setBackground(new Color(99, 59, 19));
	setOpaque(true);
	this.frame = frame;
	init();
    }
    
    private void init(){
	JLabel jl = new JLabel("MENU");
	jl.setForeground(Color.WHITE);
	jl.setAlignmentX(CENTER_ALIGNMENT);
	//jl.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 20));
	
	add(jl, BorderLayout.NORTH);
	
	JPanel jp = new JPanel();
	jp.setBackground(new Color(99, 59, 19));
	jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
	exitGame = new JButton("Exit Game");
	//exitGame.setBorder(BorderFactory.createLineBorder(new Color(99, 59, 19), 10));
	exitGame.setBackground(buttonColor);
	exitGame.setAlignmentX(CENTER_ALIGNMENT);
	exitGame.setAlignmentY(TOP_ALIGNMENT);
	//add(exitGame, BorderLayout.CENTER);
	jp.add(exitGame);
	jp.add(new JLabel(GameIcons.TRANSPARENT.getIcon()));
	returnToGame = new JButton("Return To Game");
	//returnToGame.setBorder(BorderFactory.createLineBorder(new Color(99, 59, 19), 10));
	returnToGame.setBackground(buttonColor);
	returnToGame.setAlignmentX(CENTER_ALIGNMENT);
	returnToGame.setAlignmentY(BOTTOM_ALIGNMENT);
	//add(returnToGame, BorderLayout.SOUTH);
	jp.add(returnToGame);
	
	add(jp, BorderLayout.CENTER);
	add(new JLabel(GameIcons.TRANSPARENT.getIcon()), BorderLayout.SOUTH);
	
	exitGame.addMouseListener(new MouseAdapter(){  
	    public void mouseClicked(MouseEvent e)  {  
		frame.dispose();
	    }  
	});
	
	returnToGame.addMouseListener(new MouseAdapter(){  
	    public void mouseClicked(MouseEvent e)  {  
		setVisible(false);
	    }  
	});
    }
    
//    @Override
//    protected void paintComponent(Graphics g) {
//      super.paintComponent(g);
//	  g.drawImage(backgroundImage.getImage(), 0, 0, null);
//    }
    
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

    public JButton getExitGame() {
	return exitGame;
    }

    public JButton getReturnToGame() {
	return returnToGame;
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
    
    
}
