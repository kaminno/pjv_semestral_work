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
public class GearTest {
    
    private Gear gear;
    
    public GearTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws WrongEquipmentTypeForWearableItemException {
	gear = new Gear("sd", 3, 2, 2, EquipmentType.HANDS);
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void setDurability(){
	gear.setDurability(3);
	assertEquals(3, gear.getCurrentDurability());
	assertFalse(gear.isBroken());
	gear.setDurability(0);
	assertEquals(0, gear.getCurrentDurability());
	assertTrue(gear.isBroken());
    }
    
    @Test
    public void setNegativeDurability(){
	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    gear.setDurability(-3);
	});
	
	String expectedMessage = "";
	String actualMessage = exception.getMessage();

	assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    public void negativeDurabilityConstructor(){
	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    Gear g = new Gear("sdf", 3, 1, -1, EquipmentType.LEGS);
	});
	
	String expectedMessage = "positive number";
	String actualMessage = exception.getMessage();

	assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    public void negativeArmorConstructor(){
	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    Gear g = new Gear("sdf", 3, 0, 1, EquipmentType.CHEST);
	});
	
	String expectedMessage = "positive number";
	String actualMessage = exception.getMessage();

	assertTrue(actualMessage.contains(expectedMessage));
	
	exception = assertThrows(IllegalArgumentException.class, () -> {
	    Gear g = new Gear("sdf", 3, -2, 1, EquipmentType.HEAD);
	});
	
	expectedMessage = "positive number";
	actualMessage = exception.getMessage();

	assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    public void wrongWeaponType(){
	Exception exception = assertThrows(WrongEquipmentTypeForWearableItemException.class, () -> {
	    Gear g = new Gear("sdf", 3, 1, 1, EquipmentType.MAIN_HAND);
	});
	
	String expectedMessage = "Equipment";
	String actualMessage = exception.getMessage();

	assertTrue(actualMessage.contains(expectedMessage));
	
	exception = assertThrows(WrongEquipmentTypeForWearableItemException.class, () -> {
	    Gear g = new Gear("sdf", 3, 1, 1, EquipmentType.BAG);
	});
	
	expectedMessage = "Equipment";
	actualMessage = exception.getMessage();

	assertTrue(actualMessage.contains(expectedMessage));
    }
}
