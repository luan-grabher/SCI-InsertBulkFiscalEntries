/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sci.insertbulkfiscalentries.Model;

import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import sci.insertbulkfiscalentries.Model.Entities.State;

/**
 *
 * @author TI01
 */
public class States_ModelTest {
    
    public States_ModelTest() {
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

    /**
     * Test of getStatesFromDb method, of class States_Model.
     */
    @Test
    public void testGetStatesFromDb() {
        System.out.println("getStatesFromDb");
        States_Model instance = new States_Model();
        Map<String, State> expResult = null;
        Map<String, State> result = instance.getStatesFromDb();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStates method, of class States_Model.
     */
    @Test
    public void testGetStates() {
        System.out.println("getStates");
        States_Model instance = new States_Model();
        Map<String, State> expResult = null;
        Map<String, State> result = instance.getStates();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStates method, of class States_Model.
     */
    @Test
    public void testSetStates() {
        System.out.println("setStates");
        Map<String, State> states = null;
        States_Model instance = new States_Model();
        instance.setStates(states);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
