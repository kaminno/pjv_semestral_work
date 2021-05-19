package game.gameengine;

import Controller.EngineController;
import Model.GameModel;
import View.Engine.EngineMainWindow;

public class NewMain {

    public static void main(String[] args) {

	GameModel model = new GameModel();
	EngineController engineController = new EngineController(model);
	EngineMainWindow emw = new EngineMainWindow("PJV Engine", engineController);

    }
}
