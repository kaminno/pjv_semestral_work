/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Items;

import Exceptions.WrongEquipmentTypeForWearableItemException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author honzuna
 */
public class WeaponTest {
    
    private Weapon weapon;
    
    public WeaponTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws WrongEquipmentTypeForWearableItemException {
	weapon = new Weapon("mec", 2, 2, 1, 1, EquipmentType.SECOND_HAND);
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void setDurability(){
	weapon.setDurability(3);
	assertEquals(3, weapon.getCurrentDurability());
	assertFalse(weapon.isBroken());
	weapon.setDurability(0);
	assertEquals(0, weapon.getCurrentDurability());
	assertTrue(weapon.isBroken());	
    }
    
    @Test
    public void setNegativeDurability(){
	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    weapon.setDurability(-3);
	});
	
	String expectedMessage = "";
	String actualMessage = exception.getMessage();

	assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    public void negativeDurabilityConstructor(){
	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    Weapon w = new Weapon("sdf", 3, 3, 1, -1, EquipmentType.MAIN_HAND);
	});
	
	String expectedMessage = "positive number";
	String actualMessage = exception.getMessage();

	assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    public void negativeDamageConstructor(){
	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    Weapon w = new Weapon("sdf", 3, 0, 1, 1, EquipmentType.MAIN_HAND);
	});
	
	String expectedMessage = "positive number";
	String actualMessage = exception.getMessage();

	assertTrue(actualMessage.contains(expectedMessage));
	
	exception = assertThrows(IllegalArgumentException.class, () -> {
	    Weapon w = new Weapon("sdf", 3, -2, 1, 1, EquipmentType.SECOND_HAND);
	});
	
	expectedMessage = "positive number";
	actualMessage = exception.getMessage();

	assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    public void wrongWeaponType(){
	Exception exception = assertThrows(WrongEquipmentTypeForWearableItemException.class, () -> {
	    Weapon w = new Weapon("sdf", 3, 2, 1, 1, EquipmentType.CHEST);
	});
	
	String expectedMessage = "Equipment";
	String actualMessage = exception.getMessage();

	assertTrue(actualMessage.contains(expectedMessage));
	
	exception = assertThrows(WrongEquipmentTypeForWearableItemException.class, () -> {
	    Weapon w = new Weapon("sdf", 3, 2, 1, 1, EquipmentType.BAG);
	});
	
	expectedMessage = "Equipment";
	actualMessage = exception.getMessage();

	assertTrue(actualMessage.contains(expectedMessage));
    }
}
