/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Items;

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
public class ItemTest {
    
    public ItemTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void negativeWeightInConstructor(){
	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    Item i = new Item("sdf", -3);
	});
	
	String expectedMessage = "Weight";
	String actualMessage = exception.getMessage();

	assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    public void toShortNameInConstructor(){
	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    Item i = new Item("", 2);
	});
	
	String expectedMessage = "Name";
	String actualMessage = exception.getMessage();

	assertTrue(actualMessage.contains(expectedMessage));
    }
}
