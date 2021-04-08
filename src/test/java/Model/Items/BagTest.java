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
public class BagTest {
    
    public BagTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp(){
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void expectedConstructorValue() throws WrongEquipmentTypeForWearableItemException{
	Bag bag = new Bag("a", 3, 2, EquipmentType.BAG);
	assertEquals(2, bag.getSize());
    }
    
    @Test
    public void negativeSizeInBagConstructor(){
	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    Bag bag = new Bag("12", 3, -2, EquipmentType.BAG);
	});
	
	String expectedMessage = "Bag size";
	String actualMessage = exception.getMessage();
	
	assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    public void wrongTypeInBagConstructor(){
	Exception exception = assertThrows(WrongEquipmentTypeForWearableItemException.class, () -> {
	    Bag bag = new Bag("ew", 3, 2, EquipmentType.CHEST);
	});
	
	String expectedMessage = "Wrong";
	String actualMessage = exception.getMessage();
	
	assertTrue(actualMessage.contains(expectedMessage));
    }
}
