package View.Engine;

import View.GameIcons;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EngineToolPanePlayerTabNewPlayer extends JPanel{
    private JLabel labelNewPlayer;
    private JLabel labelRemovePlayer;

    public EngineToolPanePlayerTabNewPlayer() {
	init();
    }
    
    private void init(){
	labelNewPlayer = new JLabel(GameIcons.NEW_PLAYER.getIcon());
	labelNewPlayer.setToolTipText("Add new player");
	add(labelNewPlayer);
	add(new JLabel(" "));
	labelRemovePlayer = new JLabel(GameIcons.REMOVE_PLAYER.getIcon());
	labelRemovePlayer.setToolTipText("Remove player");
	add(labelRemovePlayer);
    }

    public JLabel getLabelNewPlayer() {
	return labelNewPlayer;
    }

    public JLabel getLabelRemovePlayer() {
	return labelRemovePlayer;
    }
}
