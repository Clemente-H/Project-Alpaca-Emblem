package model.items;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.map.Location;
import model.units.Archer;
import model.units.IUnit;
import model.units.Sorcerer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public abstract class AbstractMagicBookTest extends AbstractTestItem {
    protected String expectedName;
    protected int expectedPower;
    protected short expectedMinRange;
    protected short expectedMaxRange;
    protected Sorcerer sorcerer1;
    protected Archer archer;
    protected Sorcerer sorcerer2;
    /**
     * sets up the items to be tested
     */
    @BeforeEach
    public void setUp() {
        setTestItem();
        setWrongRangeItem();
        setTestUnit();
    }

    /**
     * Sets up a correctly implemented item that's going to be tested
     */
    public abstract void setTestItem();

    /**
     * Sets up an item with wrong ranges setted.
     */
    public abstract void setWrongRangeItem();

    /**
     * Sets the unit that will be equipped with the test item
     */
    public abstract void setTestUnit();

    /**
     * Checks that the tested item cannot have ranges outside of certain bounds.
     */
    @Test
    public void incorrectRangeTest() {
        assertTrue(getWrongTestItem().getMinRange() >= 0);
        assertTrue(getWrongTestItem().getMaxRange() >= getWrongTestItem().getMinRange());
    }

    public abstract IEquipableItem getWrongTestItem();

    /**
     * Tests that the constructor for the tested item works properly
     */
    @Test
    public void constructorTest() {
        assertEquals(getExpectedName(), getTestItem().getName());
        assertEquals(getExpectedBasePower(), getTestItem().getPower());
        assertEquals(getExpectedMinRange(), getTestItem().getMinRange());
        assertEquals(getExpectedMaxRange(), getTestItem().getMaxRange());
    }

    /**
     * @return the expected name of the item
     */
    public String getExpectedName() {
        return expectedName;
    }

    /**
     * @return the item being tested
     */
    public abstract IEquipableItem getTestItem();

    /**
     * @return the expected power of the Item
     */
    public int getExpectedBasePower() {
        return expectedPower;
    }

    /**
     * @return the expected minimum range of the item
     */
    public int getExpectedMinRange() {
        return expectedMinRange;
    }

    /**
     * @return the expected maximum range of the item
     */
    public int getExpectedMaxRange() {
        return expectedMaxRange;
    }

    /**
     * Checks that the Item can be correctly equipped to a unit
     */
    @Test
    public void equippedToTest() {
        assertNull(getTestItem().getOwner());
        IUnit unit = getTestUnit();
        getTestItem().equipTo(unit);
        assertEquals(unit, getTestItem().getOwner());
    }

    /**
     * @return a unit that can equip the item being tested
     */
    public abstract IUnit getTestUnit();

    /*
      @test
      public void Fight(IUnit unit1,IUnit unit2);
      */
    public void getUnitTest() {

    }


    @Test
    public void SorcererAttackedByBow(){
        Bow bow = new Bow("", 40, 1,2);
        DarknessMagicBook dark = new DarknessMagicBook("",40,1,1);
        sorcerer1 = new Sorcerer(1000,1,new Location(0,1));
        sorcerer1.equipItem(dark);
        dark.getAttackedByBow(bow);
        assertEquals(sorcerer1.getCurrentHitPoints(),940);
    }

    @Test
    public void SorcererAttackedByAxe(){
        Axe axe = new Axe("", 40, 1,2);
        DarknessMagicBook dark = new DarknessMagicBook("",40,1,1);
        sorcerer2 = new Sorcerer(1000,1,new Location(0,1));
        sorcerer2.equipItem(dark);
        dark.getAttackedByAxe(axe);
        assertEquals(sorcerer2.getCurrentHitPoints(),940);
    }


}
