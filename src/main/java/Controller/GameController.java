package Controller;

import Model.Figures.Player;
import Model.GameModel;
import Model.Terrains.SolidType;
import Model.Terrains.Terrain;
import View.Game.PlayerView;
import java.awt.Rectangle;

public class GameController {
    GameModel model;

    public GameController(GameModel model) {
	this.model = model;
    }
    
    public void checkMove(PlayerView pw){
	int x = pw.getX() + 20 + pw.getDx() + (int)Math.signum(pw.getDx())*20;
	int y = pw.getY() + 20 + pw.getDy() + (int)Math.signum(pw.getDy())*20;
	int sx = (x - x%40)/40;
	int sy = (y - y%40)/40;
	
	if(sy < model.getMap().getMapHeight() && sx < model.getMap().getMapWidth()){
	    Terrain terrain = model.getMap().getMapTerrain().get(sy).get(sx);

	    Rectangle rec_player = new Rectangle(x, y, 40, 40);
	    Rectangle rec_terrain = new Rectangle((sx)*40, (sy)*40, 40, 40);
	    for(SolidType st : SolidType.values()){
		if(terrain.getName() == st.getName() && rec_player.intersects(rec_terrain)){
		    pw.setDx(0);
		    pw.setDy(0);
		    break;
		}
	    }
	    if(terrain.getName() == "water" && rec_player.intersects(rec_terrain)){
		    pw.setX(pw.getX() + 4);
		}
	}
    }
}
