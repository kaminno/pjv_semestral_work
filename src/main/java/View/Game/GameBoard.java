package View.Game;

import Controller.GameController;
import Model.Figures.Player;
import Model.Map.MapSize;
import Model.Terrains.Terrain;
import Model.Terrains.TerrainSize;
import View.GameIcons;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameBoard extends JPanel implements ActionListener{
    private Image backgroundImage;
    
    private Timer timer;
    private PlayerView player;
    private GameMenu gameMenu;
    private boolean ingame;
    private final int DELAY = 15;
    private GameController controller;
    private Font font;
    private ArrayList<BatView> bats;
    private ArrayList<SkeletonView> skeletons;
    private WarPigView boss;

    public GameBoard(GameMenu gameMenu, GameController controller, Player pl) throws IOException {
	this.controller = controller;
	//this.setSize(new Dimension(MapSize.SIZE.getWidth(), MapSize.SIZE.getHeight()));
//	backgroundImage = ImageIO.read(new File("moje_mapa.png"));
	backgroundImage = ImageIO.read(new File("resources/current.map.png"));
	this.gameMenu = gameMenu;
	font = new Font("Verdana", Font.BOLD, 18);
	
	setLayout(null);
	//this.add(new JLabel(new ImageIcon(backgroundImage)));
	initBoard(pl);
    }
    
    private void initBoard(Player pl) {
	//gameMenu = new GameMenu();
	
        addKeyListener(new TAdapter());
        setFocusable(true);
        ingame = true;

        setPreferredSize(new Dimension(MapSize.getSIZE().getWidth(), MapSize.getSIZE().getWidth()));

        player = new PlayerView(controller.getModel().getPlayer().getxPosition(), controller.getModel().getPlayer().getyPosition(), pl);
	controller.updateEquipmentInfo(player);
	add(player.getInventory());
	player.getInventory().setBounds(MapSize.getSIZE().getWidth() - 300, 100, player.getInventory().getWidth(), player.getInventory().getHeight());
	add(player.getEquipment());
	player.getEquipment().setBounds(30, 100, player.getEquipment().getWidth(), player.getEquipment().getHeight());
	add(gameMenu);
	gameMenu.setBounds((MapSize.getSIZE().getWidth() - gameMenu.getWidth())/2, 100, gameMenu.getWidth(), gameMenu.getHeight());
	//gameMenu.setBounds(400, 100, gameMenu.getWidth(), gameMenu.getHeight());

	bats = new ArrayList();
	for(int i = 0; i < controller.getModel().getBats().size(); i++){
	    BatView bv = new BatView(controller.getModel().getBats().get(i).getxPosition(),
				    controller.getModel().getBats().get(i).getyPosition());
	    bv.setHp(controller.getModel().getBats().get(i).getCurrentHealth());
	    bv.setArm(controller.getModel().getBats().get(i).getCurrentArmor());
	    bv.setDmg(controller.getModel().getBats().get(i).getCurrentAttackDamage());
	    bv.setIdx(i);
	    bats.add(bv);
	}
	
	skeletons = new ArrayList();
	for(int i = 0; i < controller.getModel().getSkeletons().size(); i++){
	    SkeletonView sv = new SkeletonView(controller.getModel().getSkeletons().get(i).getxPosition(),
				    controller.getModel().getSkeletons().get(i).getyPosition());
	    sv.setHp(controller.getModel().getSkeletons().get(i).getCurrentHealth());
	    sv.setArm(controller.getModel().getSkeletons().get(i).getCurrentArmor());
	    sv.setDmg(controller.getModel().getSkeletons().get(i).getCurrentAttackDamage());
	    sv.setIdx(i);
	    skeletons.add(sv);
	}
	
	boss = new WarPigView(controller.getModel().getBoss().getxPosition(), controller.getModel().getBoss().getyPosition());
	boss.setHp(controller.getModel().getBoss().getCurrentHealth());
	boss.setArm(controller.getModel().getBoss().getCurrentArmor());
	boss.setDmg(controller.getModel().getBoss().getCurrentAttackDamage());
	//System.out.println("Bat length: " + bats.size());
//	BatView bv = new BatView(controller.getModel().getBats().get(0).getxPosition(),
//				    controller.getModel().getBats().get(0).getyPosition());
//	bv.setHp(controller.getModel().getBats().get(0).getCurrentHealth());
//	bv.setArm(controller.getModel().getBats().get(0).getCurrentArmor());
//	bv.setDmg(controller.getModel().getBats().get(0).getCurrentAttackDamage());
//	bats.add(bv);
        
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
	
	for(BatView b : bats){
	    b.drawBeast(g, this);
	}
	
	for(SkeletonView s : skeletons){
	    s.drawBeast(g, this);
	}
	boss.drawBeast(g, this);
	//bats.get(0).drawBeast(g, this);
	g.setFont(font);
	g.setColor(Color.RED);
	g.drawString("HP: " + Integer.toString(controller.getModel().getPlayer().getCurrentHealth()), 10, 20);
	if(!player.isVisible()){
	    g.drawImage(new ImageIcon("resources/game_over.png").getImage(), 250, 150, this);
	}
	if(!boss.isVisible()){
	    g.drawImage(new ImageIcon("resources/victory.png").getImage(), 250, 150, this);
	}
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
	
	if(player.isVisible() && boss.isVisible()){
	    if(!gameMenu.isVisible()){
		inGame();

		updatePlayer();

		updateWarPig();
		updateBats();
		updateSkeletons();
	    }
        repaint();
	}
    }

    private void inGame() {

        if (!ingame) {
            timer.stop();
        }
    }
    
    private void updateWarPig(){
	try{
	    if(boss.isVisible()){
		controller.updateWarPig(boss);
		if(controller.playerNearEnemy(player, boss)){
		    boss.setEnemyNear(true);
		}
		else{
		    boss.setEnemyNear(false);
		}
		if(!boss.isMoved() && boss.getAttackTime() == 20){
		    controller.beastAttackPlayer(boss);
		    controller.updateWarPig(boss);
		}
		controller.checkBossMove(boss);
		boss.move();
		if(boss.getSwapTime() == 7){
		    boss.swapIcons();
		}
		if(boss.getHp() <= 0){
		    boss.setVisible(false);
		}
	    }
	}catch(ConcurrentModificationException cme){}
    }
    
    private void updateBats(){
	try{
	for(BatView b : bats){
	    if(b.isVisible()){
		controller.updateBat(b);
		if(controller.playerNearEnemy(player, b)){
		    b.setEnemyNear(true);
		}
		else{
		    b.setEnemyNear(false);
		}
		if(!b.getMoved() && b.getAttackTime() == 20){
		    controller.beastAttackPlayer(b);
		    controller.updateBat(b);
		}
		controller.checkBatMove(b);
		b.move();
		if(b.getSwapTime() == 5){
		    b.swapIcons();
		}
		if(b.getHp() <= 0){
		    b.setVisible(false);
		}
	    }
	}
	}catch(ConcurrentModificationException cme){}
    }
    
    private void updateSkeletons(){
	try{
	for(SkeletonView s : skeletons){
	    if(s.isVisible()){
		controller.updateSkeleton(s);
		if(controller.playerNearEnemy(player, s)){
		    s.setEnemyNear(true);
		}
		else{
		    s.setEnemyNear(false);
		}
		if(!s.isMoved() && s.getAttackTime() == 20){
		    controller.beastAttackPlayer(s);
		    controller.updateSkeleton(s);
		}
		controller.checkSkeletonMove(s);
		s.move();
		if(s.getSwapTime() == 7){
		    s.swapIcons();
		}
		if(s.getHp() <= 0){
		    s.setVisible(false);
		}
	    }
	}
	}catch(ConcurrentModificationException cme){}
    }

    private void updatePlayer() {

        if (player.isVisible()) {
	    controller.checkMove(player);
	    player.move();
	    controller.getModel().getPlayer().setxPosition(player.getX());
	    controller.getModel().getPlayer().setyPosition(player.getY());
	    if(player.getSwapTime() == 7 && player.getMoved()){
	        player.swapIcons();
	    }
	    if(controller.getModel().getPlayer().getCurrentHealth() <= 0){
		player.setVisible(false);
	    }
	    controller.updateEquipmentInfo(player);
	    controller.updateInventoryInfo(player);
	    for(BatView b : bats){
		if(controller.playerNearEnemy(player, b) && player.isAttackReady()){
		    controller.playerAttackBeast(b);
		    player.setAttackReady(false);
		}
	    }
	    for(SkeletonView s : skeletons){
		if(controller.playerNearEnemy(player, s) && player.isAttackReady()){
		    controller.playerAttackBeast(s);
		    player.setAttackReady(false);
		}
	    }
	    if(controller.playerNearEnemy(player, boss) && player.isAttackReady()){
		    controller.playerAttackBeast(boss);
		    player.setAttackReady(false);
		}
	    
//            player.move();
//	    if(player.getSwapTime() == 7 && player.getMoved()){
//		player.swapIcons();
//	    }
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
	    gameMenu.keyPressed(e);
        }
    }
}
