package model.items;

import model.map.Location;
import model.units.Sorcerer;
import model.units.IUnit;

/**
 * Test set for LightMagicBooks
 *
 * @author Clemente Henriquez Muñoz
 * @since 1.0
 */
class LightMagicBookTest extends AbstractTestItem {

    private LightMagicBook lightMagicBook;
    private LightMagicBook wrongLightMagicBook;
    private Sorcerer sorcerer;

    @Override
    public void setTestItem() {
        expectedName = "Common LightMagicBook";
        expectedPower = 10;
        expectedMinRange = 1;
        expectedMaxRange = 2;
        LightMagicBook  lightMagicBook= new LightMagicBook(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    }

    /**
     * Sets up an item with wrong ranges setted.
     */
    @Override
    public void setWrongRangeItem() {
        wrongLightMagicBook = new LightMagicBook("Wrong LightMagicBook", 0, -1, -2);
    }

    /**
     * Sets the unit that will be equipped with the test item
     */
    @Override
    public void setTestUnit() {
        Sorcerer sorcerer = new Sorcerer(10, 5, new Location(0, 0));
    }

    @Override
    public IEquipableItem getWrongTestItem() {
        return wrongLightMagicBook;
    }

    @Override
    public IEquipableItem getTestItem() {
        return lightMagicBook;
    }

    /**
     * @return a unit that can equip the item being tested
     */
    @Override
    public IUnit getTestUnit() {
        return sorcerer ;
    }
}