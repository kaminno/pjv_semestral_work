package Controller;

import Exceptions.ItemEquipedYetException;
import Exceptions.ItemNotEquipedException;
import Exceptions.ItemNotStoredException;
import Exceptions.ItemStoredYetException;
import Exceptions.NotEnoughInventoryFreeSpaceException;
import Model.Figures.Beast;
import Model.GameModel;
import Model.Items.Equipment;
import Model.Items.EquipmentType;
import Model.Items.Gear;
import Model.Items.Inventory;
import Model.Items.Item;
import Model.Items.Weapon;
import Model.Items.WearableItem;
import Model.Terrains.SolidType;
import Model.Terrains.Terrain;
import View.Game.BatView;
import View.Game.FigureView;
import View.Game.PlayerView;
import View.Game.SkeletonView;
import View.Game.WarPigView;
import View.GameIcons;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * controller class that update view data based on model data
 * @author honzuna
 */
public class GameController {

    GameModel model;

    /**
     *
     * @param model
     */
    public GameController(GameModel model) {
	this.model = model;
    }

    /**
     * checks if the player view could move to specific location or not
     * @param pw
     */
    public void checkMove(PlayerView pw) {
	// recount the coordinates to set the origin to middle of the player icon
	int x = pw.getX() + 20 + pw.getDx() + (int) Math.signum(pw.getDx()) * 20;
	int y = pw.getY() + 20 + pw.getDy() + (int) Math.signum(pw.getDy()) * 20;
	int sx = (x - x % 40) / 40;
	int sy = (y - y % 40) / 40;

	// if the player is in the map
	if (sy < model.getMap().getMapHeight() && sx < model.getMap().getMapWidth()) {
	    Terrain terrain = model.getMap().getMapTerrain().get(sy).get(sx);

	    // create rectangles of player and the terrain and checks if the terrain is of solid type and intersects the player
	    Rectangle rec_player = new Rectangle(x, y, 40, 40);
	    Rectangle rec_terrain = new Rectangle((sx) * 40, (sy) * 40, 40, 40);
	    for (SolidType st : SolidType.values()) {
		if (terrain.getName().equals(st.getName()) && rec_player.intersects(rec_terrain)) {
		    pw.setDx(0);
		    pw.setDy(0);
		    break;
		}
	    }
	    // adding the flow to the water terrain
	    if (terrain.getName().equals("water") && rec_player.intersects(rec_terrain)) {
		pw.setX(pw.getX() + 1);
	    }
	}
    }

    /**
     * checks the bat move - if it is correct
     * @param bat
     */
    public void checkBatMove(BatView bat) {
	// recount the coordinates to center it
	int x = bat.getX() + 20 + bat.getDx() + (int) Math.signum(bat.getDx()) * 20;
	int y = bat.getY() + 20 + bat.getDy() + (int) Math.signum(bat.getDy()) * 20;
	int sx = (x - x % 40) / 40;
	int sy = (y - y % 40) / 40;

	// if bat is in the map
	if (sy < model.getMap().getMapHeight() && sx < model.getMap().getMapWidth()) {
	    Terrain terrain = model.getMap().getMapTerrain().get(sy).get(sx);

	    // create rectangles of bat and the terrain where bat now is and if the terrain is of solid type, let the bat bounc back
	    Rectangle rec_bat = new Rectangle(x, y, 40, 40);
	    Rectangle rec_terrain = new Rectangle((sx) * 40, (sy) * 40, 40, 40);
	    for (SolidType st : SolidType.values()) {
		if (terrain.getName().equals(st.getName()) && rec_bat.intersects(rec_terrain)) {
		    bat.setDx((-1) * bat.getDx());
		    bat.setDy((-1) * bat.getDy());
		    break;
		}
	    }
	}
    }

    /**
     * checks the skeleton move - if it is correct
     * @param skeleton
     */
    public void checkSkeletonMove(SkeletonView skeleton) {
	// recount the coordinates to center it
	int x = skeleton.getX() + 20 + skeleton.getDx() + (int) Math.signum(skeleton.getDx()) * 20;
	int y = skeleton.getY() + 20 + skeleton.getDy() + (int) Math.signum(skeleton.getDy()) * 20;
	int sx = (x - x % 40) / 40;
	int sy = (y - y % 40) / 40;

	// if skeleton is in the map
	if (sy < model.getMap().getMapHeight() && sx < model.getMap().getMapWidth()) {
	    Terrain terrain = model.getMap().getMapTerrain().get(sy).get(sx);

	    // create rectangles of skeleton and the terrain where skeleton now is and if the terrain is of solid type, let the skeleton bounc back
	    Rectangle rec_skeleton = new Rectangle(x, y, 40, 40);
	    Rectangle rec_terrain = new Rectangle((sx) * 40, (sy) * 40, 40, 40);
	    for (SolidType st : SolidType.values()) {
		if (terrain.getName().equals(st.getName()) && rec_skeleton.intersects(rec_terrain)) {
		    skeleton.setDx((-1) * skeleton.getDx());
		    skeleton.setDy((-1) * skeleton.getDy());
		    break;
		}
	    }
	}
    }

    /**
     * checks the boss move - if it is correct
     * @param warPig
     */
    public void checkBossMove(WarPigView warPig) {
	// recount the coordinates to center it
	int x = warPig.getX() + 20 + warPig.getDx() + (int) Math.signum(warPig.getDx()) * 20;
	int y = warPig.getY() + 20 + warPig.getDy() + (int) Math.signum(warPig.getDy()) * 20;
	int sx = (x - x % 40) / 40;
	int sy = (y - y % 40) / 40;

	// if boss is in the map
	if (sy < model.getMap().getMapHeight() && sx < model.getMap().getMapWidth()) {
	    Terrain terrain = model.getMap().getMapTerrain().get(sy).get(sx);

	    // create rectangles of boss and the terrain where boss now is and if the terrain is of solid type, let the boss bounc back
	    Rectangle rec_war_pig = new Rectangle(x, y, 40, 40);
	    Rectangle rec_terrain = new Rectangle((sx) * 40, (sy) * 40, 40, 40);
	    for (SolidType st : SolidType.values()) {
		if (terrain.getName().equals(st.getName()) && rec_war_pig.intersects(rec_terrain)) {
		    warPig.setDx((-1) * warPig.getDx());
		    warPig.setDy((-1) * warPig.getDy());
		    break;
		}
	    }
	}
    }

    /**
     * checks if the player and enemy are in contact, which means if their rectangles intersects
     * @param player
     * @param beast
     * @return
     */
    public boolean playerNearEnemy(PlayerView player, FigureView beast) {
	if (beast.getBounds().intersects(player.getBounds())) {
	    return true;
	}
	return false;
    }

    /**
     * checks if the player and enemy are in contact, which means if their rectangles intersects
     * @param player
     * @param beast
     * @return
     */
    public boolean enemyNearPlyer(PlayerView player, FigureView beast) {
	if (player.getBounds().intersects(beast.getBounds())) {
	    return true;
	}
	return false;
    }

    /**
     * update bat of class beast position based on the batview class, and the batview class attributes based on beast class
     * @param batView
     */
    public void updateBat(BatView batView) {
	int beastIndex = batView.getIdx();
	try {
	    Beast currentBat = model.getBats().get(beastIndex);
	    currentBat.setxPosition(batView.getX());
	    currentBat.setyPosition(batView.getY());
	    batView.setHp(currentBat.getCurrentHealth());
	    batView.setArm(currentBat.getCurrentArmor());
	    batView.setDmg(currentBat.getCurrentAttackDamage());
	} catch (IndexOutOfBoundsException iobe) {
	}
    }

    /**
     * update skeleton of class beast position based on the skeletonview class, and the skeletonview class attributes based on beast class
     * @param skeletonView
     */
    public void updateSkeleton(SkeletonView skeletonView) {
	int beastIndex = skeletonView.getIdx();
	try {
	    Beast currentSkeleton = model.getSkeletons().get(beastIndex);
	    currentSkeleton.setxPosition(skeletonView.getX());
	    currentSkeleton.setyPosition(skeletonView.getY());
	    skeletonView.setHp(currentSkeleton.getCurrentHealth());
	    skeletonView.setArm(currentSkeleton.getCurrentArmor());
	    skeletonView.setDmg(currentSkeleton.getCurrentAttackDamage());
	} catch (IndexOutOfBoundsException iobe) {
	}
    }

    /**
     * update boss of class beast position based on the warpigview class, and the warpigview class attributes based on beast class
     * @param warPigView
     */
    public void updateWarPig(WarPigView warPigView) {
	try {
	    Beast warPig = model.getBoss();
	    warPig.setxPosition(warPigView.getX());
	    warPig.setyPosition(warPigView.getY());
	    warPigView.setHp(warPig.getCurrentHealth());
	    warPigView.setArm(warPig.getCurrentArmor());
	    warPigView.setDmg(warPig.getCurrentAttackDamage());
	} catch (IndexOutOfBoundsException iobe) {
	}
    }

    /**
     * call attack method on beast class
     * @param beastView
     */
    public void beastAttackPlayer(FigureView beastView) {
	Beast beast;
	if (beastView instanceof BatView) {
	    beast = model.getBats().get(((BatView) beastView).getIdx());
	} else if (beastView instanceof SkeletonView) {
	    beast = model.getSkeletons().get(((SkeletonView) beastView).getIdx());
	} else {
	    beast = model.getBoss();
	}
	beast.attack(model.getPlayer());
    }

    /**
     * call attack method on player class
     * @param beastView
     */
    public void playerAttackBeast(FigureView beastView) {
	Beast beast;
	if (beastView instanceof BatView) {
	    beast = model.getBats().get(((BatView) beastView).getIdx());
	} else if (beastView instanceof SkeletonView) {
	    beast = model.getSkeletons().get(((SkeletonView) beastView).getIdx());
	} else {
	    beast = model.getBoss();
	}
	model.getPlayer().attack(beast);
    }

    /**
     * clear model data (use on game window close)
     */
    public void clearData() {
	model.setStartingItems(new LinkedList());
	model.setBats(new LinkedList());
	model.setSkeletons(new LinkedList());
    }

    /**
     * update equipmentview attributes in playerview class based on model equipment values
     * @param pl
     */
    public void updateEquipmentInfo(PlayerView pl) {
	Equipment eiq = model.getPlayer().equipment();
	// iterate through model player equipment and if there is some piece of it, update its info
	for (EquipmentType eqt : eiq.getEquipment().keySet()) {
	    if (eiq.getEquipment().get(eqt) != null) {
		// if equipment is a helmet
		if (eqt.label.equals("head")) {
		    // set icon and the text on mouse hover
		    pl.getEquipment().getEquipmentHelmet().setIcon(findEquipmentIcon("helmet"));
		    pl.getEquipment().getEquipmentHelmet().setToolTipText("<html>"
			    + model.getPlayer().getEquipment().get(eqt).getName()
			    + "<br>"
			    + "Armor: " + ((Gear) model.getPlayer().equipment().getPieceOfEquipment(eqt)).getArmor()
			    + "<br>"
			    + "Durability: " + ((Gear) model.getPlayer().equipment().getPieceOfEquipment(eqt)).getMaximumDurability()
			    + " / " + ((Gear) model.getPlayer().equipment().getPieceOfEquipment(eqt)).getCurrentDurability()
			    + "</html>");
		    // add listener to unequip helmet (change the icon) and repaint the data
		    pl.getEquipment().getEquipmentHelmet().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    if (model.getPlayer().getInventory().getFreeSpace() != 0) {
				pl.getEquipment().getEquipmentHelmet().setIcon(findEquipmentIcon("transparent"));
				pl.getEquipment().getEquipmentHelmet().setToolTipText(null);
				if (((Gear) model.getPlayer().getEquipment().get(eqt)) != null) {
				    try {
					model.getPlayer().unequip(((Gear) model.getPlayer().equipment().getPieceOfEquipment(eqt)));
				    } catch (ItemNotEquipedException ex) {
					JOptionPane.showMessageDialog(pl.getEquipment(),
						ex,
						"Error",
						JOptionPane.ERROR_MESSAGE);
					Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				    } catch (NotEnoughInventoryFreeSpaceException ex) {
					JOptionPane.showMessageDialog(pl.getEquipment(),
						"Inventory is full",
						"Info",
						JOptionPane.INFORMATION_MESSAGE);
					Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				    } catch (ItemStoredYetException ex) {
					JOptionPane.showMessageDialog(pl.getEquipment(),
						ex,
						"Error",
						JOptionPane.ERROR_MESSAGE);
					Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				    }
				}
			    }
			    pl.getEquipment().getPlayerArmor().setText(Integer.toString(model.getPlayer().getCurrentArmor()));
			    pl.getEquipment().revalidate();
			    pl.getEquipment().repaint();
			}
		    });
		}
		if (eqt.label.equals("chest")) {
		    pl.getEquipment().getEquipmentChest().setIcon(findEquipmentIcon("chest"));
		    pl.getEquipment().getEquipmentChest().setToolTipText("<html>"
			    + model.getPlayer().getEquipment().get(eqt).getName()
			    + "<br>"
			    + "Armor: " + ((Gear) model.getPlayer().equipment().getPieceOfEquipment(eqt)).getArmor()
			    + "<br>"
			    + "Durability: " + ((Gear) model.getPlayer().equipment().getPieceOfEquipment(eqt)).getMaximumDurability()
			    + " / " + ((Gear) model.getPlayer().equipment().getPieceOfEquipment(eqt)).getCurrentDurability()
			    + "</html>");
		    pl.getEquipment().getEquipmentChest().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			    if (model.getPlayer().getInventory().getFreeSpace() != 0) {
				pl.getEquipment().getEquipmentChest().setIcon(findEquipmentIcon("transparent"));
				pl.getEquipment().getEquipmentChest().setToolTipText(null);
				if (((Gear) model.getPlayer().getEquipment().get(eqt)) != null) {
				    try {
					model.getPlayer().unequip(((Gear) model.getPlayer().equipment().getPieceOfEquipment(eqt)));
				    } catch (ItemNotEquipedException ex) {
					JOptionPane.showMessageDialog(pl.getEquipment(),
						ex,
						"Error",
						JOptionPane.ERROR_MESSAGE);
					Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				    } catch (NotEnoughInventoryFreeSpaceException ex) {
					JOptionPane.showMessageDialog(pl.getEquipment(),
						"Inventory is full",
						"Info",
						JOptionPane.INFORMATION_MESSAGE);
					Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				    } catch (ItemStoredYetException ex) {
					JOptionPane.showMessageDialog(pl.getEquipment(),
						ex,
						"Error",
						JOptionPane.ERROR_MESSAGE);
					Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				    }
				}
			    }
			    pl.getEquipment().getPlayerArmor().setText(Integer.toString(model.getPlayer().getCurrentArmor()));
			    pl.getEquipment().revalidate();
			    pl.getEquipment().repaint();
			}
		    });
		}
		if (eqt.label.equals("legs")) {
		    pl.getEquipment().getEquipmentLegs().setIcon(findEquipmentIcon("legs"));
		    pl.getEquipment().getEquipmentLegs().setToolTipText("<html>"
			    + model.getPlayer().getEquipment().get(eqt).getName()
			    + "<br>"
			    + "Armor: " + ((Gear) model.getPlayer().equipment().getPieceOfEquipment(eqt)).getArmor()
			    + "<br>"
			    + "Durability: " + ((Gear) model.getPlayer().equipment().getPieceOfEquipment(eqt)).getMaximumDurability()
			    + " / " + ((Gear) model.getPlayer().equipment().getPieceOfEquipment(eqt)).getCurrentDurability()
			    + "</html>");
		    pl.getEquipment().getEquipmentLegs().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			    if (model.getPlayer().getInventory().getFreeSpace() != 0) {
				pl.getEquipment().getEquipmentLegs().setIcon(findEquipmentIcon("transparent"));
				pl.getEquipment().getEquipmentLegs().setToolTipText(null);
				if (((Gear) model.getPlayer().getEquipment().get(eqt)) != null) {
				    try {
					model.getPlayer().unequip(((Gear) model.getPlayer().equipment().getPieceOfEquipment(eqt)));
				    } catch (ItemNotEquipedException ex) {
					JOptionPane.showMessageDialog(pl.getEquipment(),
						ex,
						"Error",
						JOptionPane.ERROR_MESSAGE);
					Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				    } catch (NotEnoughInventoryFreeSpaceException ex) {
					JOptionPane.showMessageDialog(pl.getEquipment(),
						"Inventory is full",
						"Info",
						JOptionPane.INFORMATION_MESSAGE);
					Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				    } catch (ItemStoredYetException ex) {
					JOptionPane.showMessageDialog(pl.getEquipment(),
						ex,
						"Error",
						JOptionPane.ERROR_MESSAGE);
					Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				    }
				}
			    }
			    pl.getEquipment().getPlayerArmor().setText(Integer.toString(model.getPlayer().getCurrentArmor()));
			    pl.getEquipment().revalidate();
			    pl.getEquipment().repaint();
			}
		    });
		}
		if (eqt.label.equals("hands")) {
		    pl.getEquipment().getEquipmentGloves().setIcon(findEquipmentIcon("gloves"));
		    pl.getEquipment().getEquipmentGloves().setToolTipText("<html>"
			    + model.getPlayer().getEquipment().get(eqt).getName()
			    + "<br>"
			    + "Armor: " + ((Gear) model.getPlayer().equipment().getPieceOfEquipment(eqt)).getArmor()
			    + "<br>"
			    + "Durability: " + ((Gear) model.getPlayer().equipment().getPieceOfEquipment(eqt)).getMaximumDurability()
			    + " / " + ((Gear) model.getPlayer().equipment().getPieceOfEquipment(eqt)).getCurrentDurability()
			    + "</html>");
		    pl.getEquipment().getEquipmentGloves().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			    if (model.getPlayer().getInventory().getFreeSpace() != 0) {
				pl.getEquipment().getEquipmentGloves().setIcon(findEquipmentIcon("transparent"));
				pl.getEquipment().getEquipmentGloves().setToolTipText(null);
				if (((Gear) model.getPlayer().getEquipment().get(eqt)) != null) {
				    try {
					if (model.getPlayer().getInventory().getFreeSpace() == 0) {
					    JOptionPane.showMessageDialog(pl.getEquipment(),
						    "Inventory is full",
						    "Info",
						    JOptionPane.INFORMATION_MESSAGE);
					    return;
					}
					model.getPlayer().unequip(((Gear) model.getPlayer().equipment().getPieceOfEquipment(eqt)));
				    } catch (ItemNotEquipedException ex) {
					JOptionPane.showMessageDialog(pl.getEquipment(),
						ex,
						"Error",
						JOptionPane.ERROR_MESSAGE);
					Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				    } catch (NotEnoughInventoryFreeSpaceException ex) {
					JOptionPane.showMessageDialog(pl.getEquipment(),
						"Inventory is full",
						"Info",
						JOptionPane.INFORMATION_MESSAGE);
					Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				    } catch (ItemStoredYetException ex) {
					JOptionPane.showMessageDialog(pl.getEquipment(),
						ex,
						"Error",
						JOptionPane.ERROR_MESSAGE);
					Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				    }
				}
			    }
			    pl.getEquipment().getPlayerArmor().setText(Integer.toString(model.getPlayer().getCurrentArmor()));
			    pl.getEquipment().revalidate();
			    pl.getEquipment().repaint();
			}
		    });
		}
		if (eqt.label.equals("feet")) {
		    pl.getEquipment().getEquipmentBoots().setIcon(findEquipmentIcon("boots"));
		    pl.getEquipment().getEquipmentBoots().setToolTipText("<html>"
			    + model.getPlayer().getEquipment().get(eqt).getName()
			    + "<br>"
			    + "Armor: " + ((Gear) model.getPlayer().equipment().getPieceOfEquipment(eqt)).getArmor()
			    + "<br>"
			    + "Durability: " + ((Gear) model.getPlayer().equipment().getPieceOfEquipment(eqt)).getMaximumDurability()
			    + " / " + ((Gear) model.getPlayer().equipment().getPieceOfEquipment(eqt)).getCurrentDurability()
			    + "</html>");
		    pl.getEquipment().getEquipmentBoots().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			    if (model.getPlayer().getInventory().getFreeSpace() != 0) {
				pl.getEquipment().getEquipmentBoots().setIcon(findEquipmentIcon("transparent"));
				pl.getEquipment().getEquipmentBoots().setToolTipText(null);
				if (((Gear) model.getPlayer().getEquipment().get(eqt)) != null) {
				    try {
					if (model.getPlayer().getInventory().getFreeSpace() == 0) {
					    JOptionPane.showMessageDialog(pl.getEquipment(),
						    "Inventory is full",
						    "Info",
						    JOptionPane.INFORMATION_MESSAGE);
					    return;
					}
					model.getPlayer().unequip(((Gear) model.getPlayer().equipment().getPieceOfEquipment(eqt)));
				    } catch (ItemNotEquipedException ex) {
					JOptionPane.showMessageDialog(pl.getEquipment(),
						ex,
						"Error",
						JOptionPane.ERROR_MESSAGE);
					Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				    } catch (NotEnoughInventoryFreeSpaceException ex) {
					JOptionPane.showMessageDialog(pl.getEquipment(),
						"Inventory is full",
						"Info",
						JOptionPane.INFORMATION_MESSAGE);
					Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				    } catch (ItemStoredYetException ex) {
					JOptionPane.showMessageDialog(pl.getEquipment(),
						ex,
						"Error",
						JOptionPane.ERROR_MESSAGE);
					Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				    }
				}
			    }
			    pl.getEquipment().getPlayerArmor().setText(Integer.toString(model.getPlayer().getCurrentArmor()));
			    pl.getEquipment().revalidate();
			    pl.getEquipment().repaint();
			}

		    });
		}
		if (eqt.label.equals("main hand")) {
		    pl.getEquipment().getEquipmentMainHand().setIcon(findEquipmentIcon("main sword"));
		    pl.getEquipment().getEquipmentMainHand().setToolTipText("<html>"
			    + model.getPlayer().getEquipment().get(eqt).getName()
			    + "<br>"
			    + "Damage: " + ((Weapon) model.getPlayer().equipment().getPieceOfEquipment(eqt)).getDamage()
			    + "<br>"
			    + "Durability: " + ((Weapon) model.getPlayer().equipment().getPieceOfEquipment(eqt)).getMaximumDurability()
			    + " / " + ((Weapon) model.getPlayer().equipment().getPieceOfEquipment(eqt)).getCurrentDurability()
			    + "</html>");
		    MouseListener msl = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			    if (model.getPlayer().getInventory().getFreeSpace() != 0) {
				pl.getEquipment().getEquipmentMainHand().setIcon(findEquipmentIcon("transparent"));
				pl.getEquipment().getEquipmentMainHand().setToolTipText(null);
				if (((Weapon) model.getPlayer().getEquipment().get(eqt)) != null) {
				    try {

					model.getPlayer().unequip(((Weapon) model.getPlayer().equipment().getPieceOfEquipment(eqt)));
				    } catch (ItemNotEquipedException ex) {
					JOptionPane.showMessageDialog(pl.getEquipment(),
						ex,
						"Error",
						JOptionPane.ERROR_MESSAGE);
					Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				    } catch (NotEnoughInventoryFreeSpaceException ex) {
					Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				    } catch (ItemStoredYetException ex) {
					JOptionPane.showMessageDialog(pl.getEquipment(),
						ex,
						"Error",
						JOptionPane.ERROR_MESSAGE);
					Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				    }
				}
				pl.getEquipment().getPlayerDamage().setText(Integer.toString(model.getPlayer().getCurrentDamage()));
				pl.getEquipment().revalidate();
				pl.getEquipment().repaint();
				pl.getEquipment().getEquipmentMainHand().removeMouseListener(this);
			    }
			}
		    };
		    pl.getEquipment().getEquipmentMainHand().addMouseListener(msl);
		}
		if (eqt.label.equals("second hand")) {
		    pl.getEquipment().getEquipmentSecondHand().setIcon(findEquipmentIcon("second dagger"));
		    pl.getEquipment().getEquipmentSecondHand().setToolTipText("<html>"
			    + model.getPlayer().getEquipment().get(eqt).getName()
			    + "<br>"
			    + "Damage: " + ((Weapon) model.getPlayer().equipment().getPieceOfEquipment(eqt)).getDamage()
			    + "<br>"
			    + "Durability: " + ((Weapon) model.getPlayer().equipment().getPieceOfEquipment(eqt)).getMaximumDurability()
			    + " / " + ((Weapon) model.getPlayer().equipment().getPieceOfEquipment(eqt)).getCurrentDurability()
			    + "</html>");
		    pl.getEquipment().getEquipmentSecondHand().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			    if (model.getPlayer().getInventory().getFreeSpace() != 0) {
				pl.getEquipment().getEquipmentSecondHand().setIcon(findEquipmentIcon("transparent"));
				pl.getEquipment().getEquipmentSecondHand().setToolTipText(null);
				if (((Weapon) model.getPlayer().getEquipment().get(eqt)) != null) {
				    try {
					if (model.getPlayer().getInventory().getFreeSpace() == 0) {
					    JOptionPane.showMessageDialog(pl.getEquipment(),
						    "Inventory is full",
						    "Info",
						    JOptionPane.INFORMATION_MESSAGE);
					    return;
					}
					model.getPlayer().unequip(((Weapon) model.getPlayer().equipment().getPieceOfEquipment(eqt)));
				    } catch (ItemNotEquipedException ex) {
					JOptionPane.showMessageDialog(pl.getEquipment(),
						ex,
						"Error",
						JOptionPane.ERROR_MESSAGE);
					Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				    } catch (NotEnoughInventoryFreeSpaceException ex) {
					JOptionPane.showMessageDialog(pl.getEquipment(),
						"Inventory is full",
						"Info",
						JOptionPane.INFORMATION_MESSAGE);
					Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				    } catch (ItemStoredYetException ex) {
					JOptionPane.showMessageDialog(pl.getEquipment(),
						ex,
						"Error",
						JOptionPane.ERROR_MESSAGE);
					Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				    }
				}
				pl.getEquipment().getPlayerDamage().setText(Integer.toString(model.getPlayer().getCurrentDamage()));
				pl.getEquipment().revalidate();
				pl.getEquipment().repaint();
			    }
			}
		    });
		}
	    }
	    pl.getEquipment().getPlayerArmor().setText(Integer.toString(model.getPlayer().getCurrentArmor()));
	    pl.getEquipment().getPlayerDamage().setText(Integer.toString(model.getPlayer().getCurrentDamage()));
	    pl.getEquipment().revalidate();
	    pl.getEquipment().repaint();
	}
    }

    /**
     * update inventory icons based on model-player-inventory values
     * @param pl
     */
    public void updateInventoryInfo(PlayerView pl) {
	Inventory inv = model.getPlayer().getInventory();
	int count = 0;
	// iterate through the player's inventory
	for (Item item : inv.getItems()) {
	    // if the current item is a wearabel item
	    if (item instanceof WearableItem) {
		// for each type of equipment update info and icon
		if (((WearableItem) item).getType() == EquipmentType.HEAD) {
		    final int c = count;
		    // set icon
		    pl.getInventory().getItemSlots().get(c).setIcon(findEquipmentIcon("helmet"));
		    // set on mouse hover item info
		    pl.getInventory().getItemSlots().get(c).setToolTipText("<html>"
			    + model.getPlayer().getInventory().getItems().get(c).getName()
			    + "<br>"
			    + "Armor: " + ((Gear) model.getPlayer().getInventory().getItems().get(c)).getArmor()
			    + "<br>"
			    + "Durability: " + ((Gear) model.getPlayer().getInventory().getItems().get(c)).getMaximumDurability()
			    + " / " + ((Gear) model.getPlayer().getInventory().getItems().get(c)).getCurrentDurability()
			    + "</html>");
		    // when clicked, change icon and remove text
		    MouseListener ml = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			    try {
				if (item.isStored()) {
				    model.getPlayer().equip((WearableItem) item);
				}
				pl.getInventory().getItemSlots().get(c).setIcon(findEquipmentIcon("transparent"));
				pl.getInventory().getItemSlots().get(c).setToolTipText(null);
			    } catch (ItemNotStoredException ex) {
				JOptionPane.showMessageDialog(pl.getEquipment(),
					ex,
					"Error",
					JOptionPane.WARNING_MESSAGE);
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				return;
			    } catch (ItemEquipedYetException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			    } catch (NotEnoughInventoryFreeSpaceException ex) {
				JOptionPane.showMessageDialog(pl.getEquipment(),
					ex,
					"Error",
					JOptionPane.WARNING_MESSAGE);
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				return;
			    } catch (ItemNotEquipedException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			    } catch (ItemStoredYetException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			    }
			    pl.getEquipment().revalidate();
			    pl.getEquipment().repaint();
			    pl.getInventory().revalidate();
			    pl.getInventory().repaint();
			    pl.getInventory().getItemSlots().get(c).removeMouseListener(this);
			}
		    };
		    pl.getInventory().getItemSlots().get(c).addMouseListener(ml);
		} else if (((WearableItem) item).getType() == EquipmentType.CHEST) {
		    final int c = count;
		    pl.getInventory().getItemSlots().get(c).setIcon(findEquipmentIcon("chest"));
		    pl.getInventory().getItemSlots().get(c).setToolTipText("<html>"
			    + model.getPlayer().getInventory().getItems().get(c).getName()
			    + "<br>"
			    + "Armor: " + ((Gear) model.getPlayer().getInventory().getItems().get(c)).getArmor()
			    + "<br>"
			    + "Durability: " + ((Gear) model.getPlayer().getInventory().getItems().get(c)).getMaximumDurability()
			    + " / " + ((Gear) model.getPlayer().getInventory().getItems().get(c)).getCurrentDurability()
			    + "</html>");
		    MouseListener ml = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    pl.getInventory().getItemSlots().get(c).setIcon(findEquipmentIcon("transparent"));
			    pl.getInventory().getItemSlots().get(c).setToolTipText(null);
			    try {
				if (item.isStored()) {
				    model.getPlayer().equip((WearableItem) item);
				}
			    } catch (ItemNotStoredException ex) {
				JOptionPane.showMessageDialog(pl.getEquipment(),
					ex,
					"Error",
					JOptionPane.WARNING_MESSAGE);
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				return;
			    } catch (ItemEquipedYetException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			    } catch (NotEnoughInventoryFreeSpaceException ex) {
				JOptionPane.showMessageDialog(pl.getEquipment(),
					ex,
					"Error",
					JOptionPane.WARNING_MESSAGE);
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				return;
			    } catch (ItemNotEquipedException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			    } catch (ItemStoredYetException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			    }
			    pl.getEquipment().revalidate();
			    pl.getEquipment().repaint();
			    pl.getInventory().getItemSlots().get(c).removeMouseListener(this);
			}
		    };
		    pl.getInventory().getItemSlots().get(count).addMouseListener(ml);
		}
		if (((WearableItem) item).getType() == EquipmentType.LEGS) {
		    final int c = count;
		    pl.getInventory().getItemSlots().get(count).setIcon(findEquipmentIcon("legs"));
		    pl.getInventory().getItemSlots().get(count).setToolTipText("<html>"
			    + model.getPlayer().getInventory().getItems().get(count).getName()
			    + "<br>"
			    + "Armor: " + ((Gear) model.getPlayer().getInventory().getItems().get(count)).getArmor()
			    + "<br>"
			    + "Durability: " + ((Gear) model.getPlayer().getInventory().getItems().get(count)).getMaximumDurability()
			    + " / " + ((Gear) model.getPlayer().getInventory().getItems().get(count)).getCurrentDurability()
			    + "</html>");
		    MouseListener msl = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    pl.getInventory().getItemSlots().get(c).setIcon(findEquipmentIcon("transparent"));
			    pl.getInventory().getItemSlots().get(c).setToolTipText(null);
			    try {
				if (item.isStored()) {
				    model.getPlayer().equip((WearableItem) item);
				}
			    } catch (ItemNotStoredException ex) {
				JOptionPane.showMessageDialog(pl.getEquipment(),
					ex,
					"Error",
					JOptionPane.WARNING_MESSAGE);
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				return;
			    } catch (ItemEquipedYetException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			    } catch (NotEnoughInventoryFreeSpaceException ex) {
				JOptionPane.showMessageDialog(pl.getEquipment(),
					ex,
					"Error",
					JOptionPane.WARNING_MESSAGE);
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				return;
			    } catch (ItemNotEquipedException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			    } catch (ItemStoredYetException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			    }
			    pl.getEquipment().revalidate();
			    pl.getEquipment().repaint();
			    pl.getInventory().getItemSlots().get(c).removeMouseListener(this);
			}
		    };
		    pl.getInventory().getItemSlots().get(count).addMouseListener(msl);
		}
		if (((WearableItem) item).getType() == EquipmentType.HANDS) {
		    final int c = count;
		    pl.getInventory().getItemSlots().get(count).setIcon(findEquipmentIcon("gloves"));
		    pl.getInventory().getItemSlots().get(count).setToolTipText("<html>"
			    + model.getPlayer().getInventory().getItems().get(count).getName()
			    + "<br>"
			    + "Armor: " + ((Gear) model.getPlayer().getInventory().getItems().get(count)).getArmor()
			    + "<br>"
			    + "Durability: " + ((Gear) model.getPlayer().getInventory().getItems().get(count)).getMaximumDurability()
			    + " / " + ((Gear) model.getPlayer().getInventory().getItems().get(count)).getCurrentDurability()
			    + "</html>");
		    MouseListener msl = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    pl.getInventory().getItemSlots().get(c).setIcon(findEquipmentIcon("transparent"));
			    pl.getInventory().getItemSlots().get(c).setToolTipText(null);
			    try {
				if (item.isStored()) {
				    model.getPlayer().equip((WearableItem) item);
				}
			    } catch (ItemNotStoredException ex) {
				JOptionPane.showMessageDialog(pl.getEquipment(),
					ex,
					"Error",
					JOptionPane.WARNING_MESSAGE);
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				return;
			    } catch (ItemEquipedYetException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			    } catch (NotEnoughInventoryFreeSpaceException ex) {
				JOptionPane.showMessageDialog(pl.getEquipment(),
					ex,
					"Error",
					JOptionPane.WARNING_MESSAGE);
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				return;
			    } catch (ItemNotEquipedException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			    } catch (ItemStoredYetException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			    }
			    pl.getEquipment().revalidate();
			    pl.getEquipment().repaint();
			    pl.getInventory().getItemSlots().get(c).removeMouseListener(this);
			}
		    };
		    pl.getInventory().getItemSlots().get(count).addMouseListener(msl);
		}
		if (((WearableItem) item).getType() == EquipmentType.FEET) {
		    final int c = count;
		    pl.getInventory().getItemSlots().get(count).setIcon(findEquipmentIcon("boots"));
		    pl.getInventory().getItemSlots().get(count).setToolTipText("<html>"
			    + model.getPlayer().getInventory().getItems().get(count).getName()
			    + "<br>"
			    + "Armor: " + ((Gear) model.getPlayer().getInventory().getItems().get(count)).getArmor()
			    + "<br>"
			    + "Durability: " + ((Gear) model.getPlayer().getInventory().getItems().get(count)).getMaximumDurability()
			    + " / " + ((Gear) model.getPlayer().getInventory().getItems().get(count)).getCurrentDurability()
			    + "</html>");
		    MouseListener msl = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    pl.getInventory().getItemSlots().get(c).setIcon(findEquipmentIcon("transparent"));
			    pl.getInventory().getItemSlots().get(c).setToolTipText(null);
			    try {
				if (item.isStored()) {
				    model.getPlayer().equip((WearableItem) item);
				}
			    } catch (ItemNotStoredException ex) {
				JOptionPane.showMessageDialog(pl.getEquipment(),
					ex,
					"Error",
					JOptionPane.WARNING_MESSAGE);
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				return;
			    } catch (ItemEquipedYetException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			    } catch (NotEnoughInventoryFreeSpaceException ex) {
				JOptionPane.showMessageDialog(pl.getEquipment(),
					ex,
					"Error",
					JOptionPane.WARNING_MESSAGE);
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				return;
			    } catch (ItemNotEquipedException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			    } catch (ItemStoredYetException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			    }
			    pl.getEquipment().revalidate();
			    pl.getEquipment().repaint();
			    pl.getInventory().getItemSlots().get(c).removeMouseListener(this);
			}
		    };
		    pl.getInventory().getItemSlots().get(count).addMouseListener(msl);
		}
		if (((WearableItem) item).getType() == EquipmentType.MAIN_HAND) {
		    final int c = count;
		    pl.getInventory().getItemSlots().get(count).setIcon(findEquipmentIcon("main sword"));
		    pl.getInventory().getItemSlots().get(count).setToolTipText("<html>"
			    + model.getPlayer().getInventory().getItems().get(count).getName()
			    + "<br>"
			    + "Damage: " + ((Weapon) model.getPlayer().getInventory().getItems().get(count)).getDamage()
			    + "<br>"
			    + "Durability: " + ((Weapon) model.getPlayer().getInventory().getItems().get(count)).getMaximumDurability()
			    + " / " + ((Weapon) model.getPlayer().getInventory().getItems().get(count)).getCurrentDurability()
			    + "</html>");
		    MouseListener msl = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    pl.getInventory().getItemSlots().get(c).setIcon(findEquipmentIcon("transparent"));
			    pl.getInventory().getItemSlots().get(c).setToolTipText(null);
			    try {
				if (item.isStored()) {
				    model.getPlayer().equip((WearableItem) item);
				}
			    } catch (ItemNotStoredException ex) {
				JOptionPane.showMessageDialog(pl.getEquipment(),
					ex,
					"Error",
					JOptionPane.WARNING_MESSAGE);
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				return;
			    } catch (ItemEquipedYetException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			    } catch (NotEnoughInventoryFreeSpaceException ex) {
				JOptionPane.showMessageDialog(pl.getEquipment(),
					ex,
					"Error",
					JOptionPane.WARNING_MESSAGE);
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				return;
			    } catch (ItemNotEquipedException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			    } catch (ItemStoredYetException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			    }
			    pl.getEquipment().revalidate();
			    pl.getEquipment().repaint();
			    pl.getInventory().getItemSlots().get(c).removeMouseListener(this);
			}
		    };
		    pl.getInventory().getItemSlots().get(count).addMouseListener(msl);
		}
		if (((WearableItem) item).getType() == EquipmentType.SECOND_HAND) {
		    final int c = count;
		    pl.getInventory().getItemSlots().get(count).setIcon(findEquipmentIcon("second dagger"));
		    pl.getInventory().getItemSlots().get(count).setToolTipText("<html>"
			    + model.getPlayer().getInventory().getItems().get(count).getName()
			    + "<br>"
			    + "Damage: " + ((Weapon) model.getPlayer().getInventory().getItems().get(count)).getDamage()
			    + "<br>"
			    + "Durability: " + ((Weapon) model.getPlayer().getInventory().getItems().get(count)).getMaximumDurability()
			    + " / " + ((Weapon) model.getPlayer().getInventory().getItems().get(count)).getCurrentDurability()
			    + "</html>");
		    MouseListener msl = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    pl.getInventory().getItemSlots().get(c).setIcon(findEquipmentIcon("transparent"));
			    pl.getInventory().getItemSlots().get(c).setToolTipText(null);
			    try {
				if (item.isStored()) {
				    model.getPlayer().equip((WearableItem) item);
				}
			    } catch (ItemNotStoredException ex) {
				JOptionPane.showMessageDialog(pl.getEquipment(),
					ex,
					"Error",
					JOptionPane.WARNING_MESSAGE);
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				return;
			    } catch (ItemEquipedYetException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			    } catch (NotEnoughInventoryFreeSpaceException ex) {
				JOptionPane.showMessageDialog(pl.getEquipment(),
					ex,
					"Error",
					JOptionPane.WARNING_MESSAGE);
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
				return;
			    } catch (ItemNotEquipedException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			    } catch (ItemStoredYetException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			    }
			    pl.getEquipment().revalidate();
			    pl.getEquipment().repaint();
			    pl.getInventory().getItemSlots().get(c).removeMouseListener(this);
			}
		    };
		    pl.getInventory().getItemSlots().get(count).addMouseListener(msl);
		}

	    }
	    count++;
	}
	// update inventory free space icons
	for (int j = 0; j < model.getPlayer().getInventory().getFreeSpace(); j++) {
	    pl.getInventory().getItemSlots().get(count + j).setIcon(GameIcons.TRANSPARENT.getIcon());
	}
	pl.getEquipment().revalidate();
	pl.getEquipment().repaint();
    }

    private ImageIcon findEquipmentIcon(String name) {
	ImageIcon i = null;
	for (GameIcons gi : GameIcons.values()) {
	    if (gi.getLabel().equals(name)) {
		i = gi.getIcon();
	    }
	}
	return i;
    }

    /**
     *
     * @return
     */
    public GameModel getModel() {
	return model;
    }
}
