package View;

import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame{

    public MainWindow(String title) throws HeadlessException {
	super(title);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setMinimumSize(new Dimension(414, 333));
	this.setMaximumSize(new Dimension(828, 666));
	this.setVisible(true);
    }
    
    private void createButtons(JPanel panel){
	JButton button = new JButton("Tlačítko");
    }
}
