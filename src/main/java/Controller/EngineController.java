package Controller;

import Model.Figures.Beast;
import Model.Figures.Player;
import Model.GameModel;
import Model.Map.Map;
import View.Engine.EngineMainWindow;
import View.Game.GameMainWindow;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class EngineController {
    private GameModel model;
    private GameController controller;

    public EngineController(GameModel model) {
	this.model = model;
    }
    
    public void createNewMap(Map map){
	model.setMap(map);
    }
    
    public void createNewPlayer(String name, int health, int armor, int damage, int moves, int speed, int baseInventorySpace){
	Player player = new Player(name, health, armor, damage, moves, speed, baseInventorySpace);
	model.setPlayer(player);
    }
    
    public void createBoss(String name, int health, int armor, int damage){
	int x = 0;
	int y = 0;
	Beast boss = new Beast(name, health, armor, damage, x, y);
	model.setBoss(boss);
    }

    public GameModel getModel() {
	return model;
    }
    
    public void runGame(){
	try {
	    controller = new GameController(model);
	    JFrame f = new GameMainWindow("Hra", controller);
	} catch (HeadlessException ex) {
	    Logger.getLogger(EngineController.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IOException ex) {
	    Logger.getLogger(EngineController.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
}
