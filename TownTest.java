

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TownTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TownTest
{
    Town town;
    /**
     * Default constructor for test class TownTest
     */
    public TownTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Test
    public void prueba()
    {
        town = new Town(600, 600);
        town.addLocation("1", 430, 580);
        town.addLocation("2", 580, 550);
        town.addLocation("3", 0, 580);
        town.addLocation("4", 400, 460);
        town.addLocation("5", 400, 0);
        town.addLocation("6", 200, 0);
        town.addStreet("2", "1");
        town.addSing("2","1");
        town.addStreet("4", "3");
        town.addSing("4","3");
        town.addStreet("2", "5");
        town.addSing("2","5");
        town.addStreet("4", "6");
        town.addSing("4","6");
        town.addStreet("2", "4");
        town.addSing("4","2");
        town.addStreet("6", "3");
        town.addSing("6","3");
        town.delLocation("4");
        town.allLocation();
        town.allStreets();
        town.allSigns();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
