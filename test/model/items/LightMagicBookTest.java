package model.items;
import model.map.Location;
import model.units.IUnit;
import model.units.Sorcerer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class LightMagicBookTest extends AbstractTestItem {
    private LightMagicBook lightMagicBook;
    private LightMagicBook wrongLightMagicBook;
    private Sorcerer sorcerer1;
    private DarknessMagicBook darknessMagicBook1;
    private AnimaMagicBook animaMagicBook1;
    private Sorcerer darkSorcerer2;
    private Sorcerer animaSorcerer2;


    /**
     * sets wich items is going to be tested
     */
    @Override
    public void setTestItem(){
        expectedName = "Common lightMagicBook";
        expectedPower = 10;
        expectedMinRange = 1;
        expectedMaxRange = 2;
        lightMagicBook = new LightMagicBook(expectedName,expectedPower,expectedMinRange,expectedMaxRange);
        animaMagicBook1 = new AnimaMagicBook("",30,1,2);
        darknessMagicBook1 = new DarknessMagicBook("",30,1,2);
    }
    /**
     * sets up an item with wrong ranges setted.
     *
     */
    @Override
    public void setWrongRangeItem() { wrongLightMagicBook = new LightMagicBook("Wrong lightMagicBook",0,-1,-2);}
    /**
     * sets the unit that will be equipped with the test item
     */
    @Override
    public void setTestUnit(){ sorcerer1 = new Sorcerer(10,5,new Location(0,0));}

    @Override
    public IEquipableItem getWrongTestItem(){return wrongLightMagicBook;}
    /**
     * @return the item being tested
     */
    @Override
    public IUnit getTestUnit(){return sorcerer1;}


    @Override
    public IEquipableItem getTestItem() {return lightMagicBook;}

    @Test
    public void equipDarkBookTest(){
        darknessMagicBook1 = new DarknessMagicBook("",1,1,1);
        sorcerer = new Sorcerer(100,1,null,darknessMagicBook1);
        sorcerer.equipItem(darknessMagicBook1);
        assertEquals(sorcerer.getEquippedItem(),darknessMagicBook1);
    }


    @Test
    public void attacks2(){
        animaSorcerer2 = new Sorcerer(100, 5, new Location(1, 0));
        animaSorcerer2.getLocation().addNeighbour(sorcerer1.getLocation());
        darkSorcerer2 = new Sorcerer(100,5,new Location(0,1));
        darkSorcerer2.getLocation().addNeighbour(sorcerer1.getLocation());
        sorcerer1.equipItem(lightMagicBook);
        darkSorcerer2.equipItem(darknessMagicBook1);
        lightMagicBook.getAttackedByAnimaMagicBook(animaMagicBook1);
        assertEquals(sorcerer1.getCurrentHitPoints(),0);

        lightMagicBook.getAttackedByDarknessMagicBook(darknessMagicBook1);
        assertEquals(sorcerer1.getCurrentHitPoints(),0);
    }

}
