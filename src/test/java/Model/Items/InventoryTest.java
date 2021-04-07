/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Items;

import Exceptions.NotEnoughInventoryFreeSpaceException;
import java.util.ArrayList;
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
public class InventoryTest {
    
        
    private Inventory inventory;
    
    public InventoryTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
	inventory = new Inventory(2);
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void updateCapacity() throws NotEnoughInventoryFreeSpaceException{
	assertEquals(2, inventory.getCurrentCapacity());
	assertEquals(2, inventory.getFreeSpace());
	inventory.updateCapacity(0);
	assertEquals(2, inventory.getCurrentCapacity());
	assertEquals(2, inventory.getFreeSpace());
	inventory.updateCapacity(3);
	assertEquals(5, inventory.getCurrentCapacity());
	assertEquals(5, inventory.getFreeSpace());
	inventory.updateCapacity(-2);
	assertEquals(3, inventory.getCurrentCapacity());
	assertEquals(3, inventory.getFreeSpace());
	
    }
    
    @Test
    public void updateCapacityWithToLowNegativeNumber() throws NotEnoughInventoryFreeSpaceException {
	inventory.updateCapacity(3);
	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    inventory.updateCapacity(-10);
	});
	
	assertEquals(5, inventory.getCurrentCapacity());
	assertEquals(5, inventory.getFreeSpace());
	
	String expectedMessage = "To low capacity argument";
	String actualMessage = exception.getMessage();

	assertTrue(actualMessage.contains(expectedMessage));
	
	inventory.updateCapacity(-3);
	exception = assertThrows(IllegalArgumentException.class, () -> {
	    inventory.updateCapacity(-1);
	});
	
	assertEquals(2, inventory.getCurrentCapacity());
	assertEquals(2, inventory.getFreeSpace());
	
	expectedMessage = "To low capacity argument";
	actualMessage = exception.getMessage();

	assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    public void updateCapacityWithNegativeNumberInFullInventory() throws NotEnoughInventoryFreeSpaceException {
	inventory.updateCapacity(2);
	Item i1 = new Item("", 2);
	Item i2 = new Item("", 2);
	Item i3 = new Item("", 2);
	Item i4 = new Item("", 2);
	inventory.storeItem(i1);
	inventory.storeItem(i2);
	inventory.storeItem(i3);
	inventory.storeItem(i4);
	Exception exception = assertThrows(NotEnoughInventoryFreeSpaceException.class, () -> {
	    inventory.updateCapacity(-1);
	});
	
	assertEquals(4, inventory.getCurrentCapacity());
	assertEquals(0, inventory.getFreeSpace());
	
	String expectedMessage = "Remove some items";
	String actualMessage = exception.getMessage();

	assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test of updateCapacity method, of class Inventory.
     */
//    @Test
//    public void testUpdateCapacity() throws Exception {
//	System.out.println("updateCapacity");
//	int bonusCapacity = 0;
//	Inventory instance = null;
//	instance.updateCapacity(bonusCapacity);
//	// TODO review the generated test code and remove the default call to fail.
//	fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getItems method, of class Inventory.
//     */
//    @Test
//    public void testGetItems() {
//	System.out.println("getItems");
//	Inventory instance = null;
//	ArrayList<Item> expResult = null;
//	ArrayList<Item> result = instance.getItems();
//	assertEquals(expResult, result);
//	// TODO review the generated test code and remove the default call to fail.
//	fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of storeItem method, of class Inventory.
//     */
//    @Test
//    public void testStoreItem() throws Exception {
//	System.out.println("storeItem");
//	Item item = null;
//	Inventory instance = null;
//	instance.storeItem(item);
//	// TODO review the generated test code and remove the default call to fail.
//	fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of removeItem method, of class Inventory.
//     */
//    @Test
//    public void testRemoveItem() throws Exception {
//	System.out.println("removeItem");
//	Item item = null;
//	Inventory instance = null;
//	instance.removeItem(item);
//	// TODO review the generated test code and remove the default call to fail.
//	fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCurrentCapacity method, of class Inventory.
//     */
//    @Test
//    public void testGetCurrentCapacity() {
//	System.out.println("getCurrentCapacity");
//	Inventory instance = null;
//	int expResult = 0;
//	int result = instance.getCurrentCapacity();
//	assertEquals(expResult, result);
//	// TODO review the generated test code and remove the default call to fail.
//	fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getFreeSpace method, of class Inventory.
//     */
//    @Test
//    public void testGetFreeSpace() {
//	System.out.println("getFreeSpace");
//	Inventory instance = null;
//	int expResult = 0;
//	int result = instance.getFreeSpace();
//	assertEquals(expResult, result);
//	// TODO review the generated test code and remove the default call to fail.
//	fail("The test case is a prototype.");
//    }
    
}
