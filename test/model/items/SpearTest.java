package model.items;

import model.map.Location;
import model.units.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test set for spears
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SpearTest extends AbstractTestItem {

  private Spear javelin;
  private Spear wrongSpear;
  private Hero hero;

  private AnimaMagicBook animaMagicBook;
  private AnimaMagicBook wrongAnimaMagicBook;
  private Sorcerer sorcerer;
  private LightMagicBook lightMagicBook;
  private DarknessMagicBook darknessMagicBook;
  private Sorcerer lightSorcerer;
  private Sorcerer darkSorcerer;
  private Axe axe;
  private Fighter fighter;
  private Archer archer;
  private Bow bow;
  private Staff staff;
  private Cleric cleric;
  private SwordMaster swordMaster;
  private Sword sword;
  private Alpaca alpaca;
  private Axe wrongAxe;

  /**
   * Sets which item is going to be tested
   */
  @Override
  public void setTestItem() {
    expectedName = "Javelin";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 3;
    javelin = new Spear(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongSpear = new Spear("Wrong spear", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    hero = new Hero(1000,5, new Location(0, 0));
    animaMagicBook = new AnimaMagicBook("common animaBook",expectedPower,expectedMinRange,expectedMaxRange);
    lightMagicBook = new LightMagicBook("spellsfordummies",40,1,2);
    darknessMagicBook = new DarknessMagicBook("howToActColdAsSasuke",40,1,2);
    sorcerer= new Sorcerer(100,4,new Location(0,0));
    axe = new Axe("",40,1,2);
    fighter = new Fighter(100,4,new Location(1,0));
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

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongSpear;
  }

  /**
   * @return the item being tested
   */
  @Override
  public IEquipableItem getTestItem() {
    return javelin;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return hero;
  }

  @Test
  public void equipSpearTest(){
    javelin.equippedHero(hero);
    assertEquals(hero.getEquippedItem(),javelin);
  }


  @Test
  public void HeroesFighters(){
    darknessMagicBook = new DarknessMagicBook("",40,0,1);
    lightMagicBook = new LightMagicBook("",40,0,1);
    animaMagicBook = new AnimaMagicBook("",40,0,1);
    bow = new Bow("",40,0,1);
    axe = new Axe("",40,0,1);
    staff = new Staff("",40,0,1);
    sword = new Sword("",40,0,1);
    javelin = new Spear("",1,1,1);
    hero = new Hero(1000,1,null,javelin);
    hero.equipItem(javelin);
    javelin.getAttackedByDarknessMagicBook(darknessMagicBook);
    javelin.getAttackedByLightMagicBook(lightMagicBook);
    javelin.getAttackedByAnimaMagicBook(animaMagicBook);
    assertEquals(hero.getCurrentHitPoints(),820);
    javelin.getAttackedByBow(bow);
    javelin.getAttackedBySpear(axe);
    javelin.getAttackedByStaff(staff);
    assertEquals(hero.getCurrentHitPoints(),740);
    javelin.getAttackedBySword(sword);
    assertEquals(hero.getCurrentHitPoints(),720);


  }
}