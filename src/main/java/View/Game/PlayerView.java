package View.Game;

import Model.Map.MapSize;
import View.GameIcons;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class PlayerView extends FigureView {

    private int dx;
    private int dy;
    private Image player_fore_1;
    private Image player_fore_2;
    private int swapTime = 0;
    private boolean moved;
    private InventoryView inventory;
    private EquipmentView equipment;
    //private List<Missile> missiles;

    public PlayerView(int x, int y, int inventorySize) {
        super(x, y);
	inventory = new InventoryView(inventorySize);
	equipment = new EquipmentView();
	
        initPlayer();
    }

    private void initPlayer() {
        
        //missiles = new ArrayList<>();
        //loadImage("resources/player.png");
	player_fore_1 = GameIcons.PLAYER_FORE_1.getIcon().getImage();
	player_fore_2 = GameIcons.PLAYER_FORE_2.getIcon().getImage();
	
	
	
	image = player_fore_1;
        getImageDimensions();
    }
    
    public void swapIcons(){
	if(image.equals(player_fore_2)){
	    image = player_fore_1;
	}
	else{
	    image = player_fore_2;
	}
    }

    public void move() {
	updateSwapTime();
	
        x += dx;
        y += dy;

        if (x < 2) {
            x = 2;
        }

        if (y < 2) {
            y = 2;
        }
	
	if(x > MapSize.getSIZE().getWidth()-40){
	    x = MapSize.getSIZE().getWidth()-40;
	}
	
	if(y > MapSize.getSIZE().getHeight()-40){
	    y = MapSize.getSIZE().getHeight()-40;
	}
    }
    
    private void updateSwapTime(){
	swapTime += 1;
	if(swapTime == 8){
	    swapTime = 0;
	}
    }
    
    public int getSwapTime(){
	return swapTime;
    }
    
    public boolean getMoved(){
	return moved;
    }

//    public List<Missile> getMissiles() {
//        return missiles;
//    }

    public void keyPressed(KeyEvent e) {
	moved = true;

        int key = e.getKeyCode();

//        if (key == KeyEvent.VK_SPACE) {
//            fire();
//        }

	if(key == KeyEvent.VK_ESCAPE){
	    inventory.setVisible(false);
	    inventory.setShowen(false);
	    
	    equipment.setVisible(false);
	    equipment.setShown(false);
	}
	
	if(key == KeyEvent.VK_I){
	    if(inventory.isShowen()){
		inventory.setVisible(false);
		inventory.setShowen(false);
	    }
	    else{
		inventory.setVisible(true);
		inventory.setShowen(true);
	    }
	}
	
	if(key == KeyEvent.VK_C){
	    if(equipment.isShown()){
		equipment.setVisible(false);
		equipment.setShown(false);
	    }
	    else{
		equipment.setVisible(true);
		equipment.setShown(true);
	    }
	}

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
    }

//    public void fire() {
//        missiles.add(new Missile(x + width, y + height / 2));
//    }

    public void keyReleased(KeyEvent e) {
	moved = false;

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    public InventoryView getInventory() {
	return inventory;
    }

    public EquipmentView getEquipment() {
	return equipment;
    }

    public int getDx() {
	return dx;
    }

    public int getDy() {
	return dy;
    }

    public void setDx(int dx) {
	this.dx = dx;
    }

    public void setDy(int dy) {
	this.dy = dy;
    }
}