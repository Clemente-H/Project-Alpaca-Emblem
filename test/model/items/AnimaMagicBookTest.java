package model.items;
import model.map.Field;
import model.map.Location;
import model.units.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AnimaMagicBookTest extends AbstractTestItem {
    private AnimaMagicBook animaMagicBook;
    private AnimaMagicBook wrongAnimaMagicBook;
    private Sorcerer sorcerer;
    private LightMagicBook lightMagicBook;
    private DarknessMagicBook darknessMagicBook;
    private Sorcerer lightSorcerer;
    private Sorcerer darkSorcerer;
    private Spear spear;
    private Hero hero;
    private Axe axe;
    private Fighter fighter;
    private Archer archer;
    private Bow bow;
    private Staff staff;
    private Cleric cleric;
    private SwordMaster swordMaster;
    private Sword sword;
    private Alpaca alpaca;




    /**
     * sets wich items is going to be tested
     */
    @Override
    public void setTestItem(){

        expectedName = "Common anima magic book";
        expectedPower = 10;
        expectedMinRange = 1;
        expectedMaxRange = 1;
        animaMagicBook = new AnimaMagicBook("common animaBook",expectedPower,expectedMinRange,expectedMaxRange);
        lightMagicBook = new LightMagicBook("spellsfordummies",40,1,2);
        darknessMagicBook = new DarknessMagicBook("howToActColdAsSasuke",40,1,2);
        sorcerer= new Sorcerer(100,4,new Location(0,0));
        spear = new Spear("",40,1,2);
        hero = new Hero(100,4,new Location(1,0));
        fighter = new Fighter(100,4,new Location(1,0));
        axe = new Axe("",40,1,2);
        archer = new Archer(100,4,new Location(1,0));
        cleric = new Cleric(100,4,new Location(1,0));
        alpaca = new Alpaca(100,4,new Location(1,0));
        swordMaster = new SwordMaster(100,4,new Location(1,0));
        sword = new Sword("",40,1,2);
        bow = new Bow("",40,2,4);
        archer = new Archer(100,4,new Location(1,0));
        lightSorcerer = new Sorcerer(100, 4,new Location(1,0));
        darkSorcerer = new Sorcerer(100,1,new Location(1,0));






    }
/**
 * sets up an item with wrong ranges setted.
 *
 */
    @Override
    public void setWrongRangeItem() { wrongAnimaMagicBook = new AnimaMagicBook("Wrong animaMagicBook",0,-1,-2);}
    /**
     * sets the unit that will be equipped with the test item
     */
    @Override
    public void setTestUnit(){ sorcerer = new Sorcerer(100,5,new Location(0,0));}

    @Override
    public IEquipableItem getWrongTestItem(){return wrongAnimaMagicBook;}
    /**
     * @return the item being tested
     */
    @Override
    public IUnit getTestUnit(){return sorcerer;}


    @Override
    public IEquipableItem getTestItem() { return animaMagicBook;}

    @Test
    public void equipAnimaBookTest(){
        animaMagicBook.equippedSorcerer(sorcerer);
        assertEquals(sorcerer.getEquippedItem(),animaMagicBook);

    }
    @Test
    public void hittinAnimaSorcerer(){
        field = new Field();
        Location location = new Location(0,0);
        field.addCells(true,location);
        animaMagicBook = new AnimaMagicBook("",1,1,1);
        sorcerer = new Sorcerer(1000,0,field.getCell(0,0),animaMagicBook);
        sorcerer.equipItem(animaMagicBook);
        darknessMagicBook = new DarknessMagicBook("",40,0,2);
        lightMagicBook = new LightMagicBook("",40,0,2);
        axe = new Axe("",40,0,2);
        bow = new Bow("",40,0,2);
        spear = new Spear("",40,0,2);
        staff = new Staff("",40,0,2);
        sword = new Sword("",40,0,2);

        animaMagicBook.getAttackedByDarknessMagicBook(darknessMagicBook);
        assertEquals(sorcerer.getCurrentHitPoints(),940);
        animaMagicBook.getAttackedByLightMagicBook(lightMagicBook);
        animaMagicBook.getAttackedByAxe(axe);
        animaMagicBook.getAttackedByBow(bow);
        assertEquals(sorcerer.getCurrentHitPoints(),800);
        animaMagicBook.getAttackedBySpear(spear);
        animaMagicBook.getAttackedByStaff(staff);
        assertEquals(sorcerer.getCurrentHitPoints(),740);
        animaMagicBook.getAttackedBySword(sword);
        assertEquals(sorcerer.getCurrentHitPoints(),680);


    }
}
