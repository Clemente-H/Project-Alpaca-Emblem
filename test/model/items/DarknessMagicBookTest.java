package model.items;
import model.map.Location;
import model.units.IUnit;
import model.units.Sorcerer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DarknessMagicBookTest extends AbstractTestItem {
    private DarknessMagicBook darknessMagicBook;
    private DarknessMagicBook wrongDarknessMagicBook;
    private Sorcerer sorcerer2;
    private LightMagicBook lightMagicBook;
    private AnimaMagicBook animaMagicBook;

    /**
     * sets wich items is going to be tested
     */
    @Override
    public void setTestItem(){
        expectedName = "Common darknessMagicBook";
        expectedPower = 10;
        expectedMinRange = 1;
        expectedMaxRange = 2;
        darknessMagicBook = new DarknessMagicBook(expectedName,expectedPower,expectedMinRange,expectedMaxRange);
        lightMagicBook = new LightMagicBook("spellsfordummies",30,1,2);
        animaMagicBook = new AnimaMagicBook("verySpiritualLife",30,1,2);
    }
    /**
     * sets up an item with wrong ranges setted.
     *
     */
    @Override
    public void setWrongRangeItem() { wrongDarknessMagicBook = new DarknessMagicBook("Wrong darknessMagicBook",0,-1,-2);}
    /**
     * sets the unit that will be equipped with the test item
     */
    @Override
    public void setTestUnit(){ sorcerer2 = new Sorcerer(10,5,new Location(0,0));}

    @Override
    public IEquipableItem getWrongTestItem(){return wrongDarknessMagicBook;}
    /**
     * @return the item being tested
     */
    @Override
    public IUnit getTestUnit(){return sorcerer2;}


    @Override
    public IEquipableItem getTestItem() {return darknessMagicBook;}

    @Test
    public void equipDarkBookTest(){
        darknessMagicBook.equippedSorcerer(sorcerer2);
        assertEquals(darknessMagicBook, sorcerer2.getEquippedItem());
    }

}