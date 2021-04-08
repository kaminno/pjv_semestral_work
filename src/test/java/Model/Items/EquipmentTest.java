/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Items;

import Exceptions.ItemEquipedYetException;
import Exceptions.ItemNotEquipedException;
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
public class EquipmentTest {
    
    private Equipment equipment;
    
    public EquipmentTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
	equipment = new Equipment();
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void addEquipment() throws ItemEquipedYetException{
	WearableItem w1 = new WearableItem("s", 3, EquipmentType.FEET);
	equipment.add(w1);
	assertEquals(w1.getType(), equipment.getPieceOfEquipment(EquipmentType.FEET).getType());
	assertEquals(w1, equipment.getPieceOfEquipment(EquipmentType.FEET));
	assertTrue(w1.isEquiped());
	WearableItem w2 = new WearableItem("s", 3, EquipmentType.FEET);
	equipment.add(w2);
	assertEquals(w2.getType(), equipment.getPieceOfEquipment(EquipmentType.FEET).getType());
	assertEquals(w2, equipment.getPieceOfEquipment(EquipmentType.FEET));
	assertTrue(w2.isEquiped());
    }
    
    @Test
    public void addEquipedEquipment(){
	WearableItem w = new WearableItem("s", 3, EquipmentType.FEET);
	Exception exception = assertThrows(ItemEquipedYetException.class, () -> {
	    equipment.add(w);
	    System.out.println("Adding equiped item");
	    equipment.add(w);
	});
	
	String expectedMessage = "equiped";
	String actualMessage = exception.getMessage();

	assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    public void removeEquipment() throws ItemEquipedYetException, ItemNotEquipedException{
	WearableItem w1 = new WearableItem("s", 3, EquipmentType.FEET);
	equipment.add(w1);
	equipment.remove(w1);
	assertEquals(null, equipment.getPieceOfEquipment(EquipmentType.FEET));
	assertFalse(w1.isEquiped());
    }
    
    @Test
    public void removeUnequipedEquipment() throws ItemEquipedYetException{
	WearableItem w = new WearableItem("s", 3, EquipmentType.FEET);
	Exception exception = assertThrows(ItemNotEquipedException.class, () -> {
	    equipment.remove(w);
	});
	
	String expectedMessage = "equiped";
	String actualMessage = exception.getMessage();

	assertTrue(actualMessage.contains(expectedMessage));
    }
}
