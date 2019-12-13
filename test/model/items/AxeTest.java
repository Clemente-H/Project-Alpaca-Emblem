package model.items;

import model.map.Location;
import model.units.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
/*
import org.junit.jupiter.api.Assertions.assertEqual;
Location local1, local2, local3;
IUnit fighter, swordMaster, hero;
IEquipableItem axe, sword, spear;
*/

/**
 * Test set for Axes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
class AxeTest extends AbstractTestItem {
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
  private Axe wrongAxe;


  @Override
  public void setTestItem() {
    expectedName = "Common axe";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 2;
    axe = new Axe(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    animaMagicBook = new AnimaMagicBook("common animaBook",expectedPower,expectedMinRange,expectedMaxRange);
    lightMagicBook = new LightMagicBook("spellsfordummies",40,1,2);
    darknessMagicBook = new DarknessMagicBook("howToActColdAsSasuke",40,1,2);
    sorcerer= new Sorcerer(100,4,new Location(0,0));
    spear = new Spear("",40,1,2);
    hero = new Hero(100,4,new Location(1,0));
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
/*
  @BeforeEach
  void setUp(){
    local1 = new Location(1,1);
    local2 = new Location(1,2);
    local3 = new Location(2,2);
    local1.addNeighbour(local2);
    local2.addNeighbour(local3);
    axe=new Axe("Hacha de luna Creciente", 40, 1,2);
    sword=new Sword("Espada de Artorias", 40,1,2);
    spear = new Spear("Lanza Negra", 40,1,2);
    fighter = new Fighter(1000,1,local1,axe);
    hero = new hero(1000,1,local2,spear);
    swordMaster = new SwordMaster(1000,1, local3, spear);

    @test
            void testCombat(){
      fighter.equipItem(axe);
      swordMaster.equipItem(sword);
      combat(fighter,swordMaster);
      assertEquals(980, swordmaster.getCurrentHitPoints());
      assertEquals(940, fighter.getCurrentHitPoints());
      Combat(fighter, hero);
      assertEquals(920, hero.getCurrentHitPoints());
      assertEquals(980, swordmaster.getCurrentHitPoints());



    }





  }



    /**
     * Sets up an item with wrong ranges setted.
     */
  @Override
  public void setWrongRangeItem() {
    wrongAxe = new Axe("Wrong axe", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    fighter = new Fighter(100, 5, new Location(0, 0));
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongAxe;
  }

  @Override
  public IEquipableItem getTestItem() {
    return axe;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return fighter;
  }

  @Test
  public void equipAxeTest(){
    axe.equippedFighter(fighter);
    assertEquals(fighter.getEquippedItem(),axe);
  }
  @Test
  public void attack(){
    swordMaster = new SwordMaster(100, 5, new Location(1, 0));
    swordMaster.getLocation().addNeighbour(fighter.getLocation());
    hero = new Hero(100,5,new Location(0,1));
    hero.getLocation().addNeighbour(fighter.getLocation());
    swordMaster.equipItem(sword);
    hero.equipItem(spear);
    fighter.equipItem(axe);

    axe.getAttackedBySword(sword);
    //sword makes an strongAttack to an axe

    assertEquals(fighter.getCurrentHitPoints(),40);
    fighter.Combat(hero);
    assertEquals(hero.getCurrentHitPoints(),85);
    axe.getAttackedBySpear(spear);
    assertEquals(fighter.getCurrentHitPoints(),0);




  }

  @Test
  public void hittinFighters(){

    animaMagicBook = new AnimaMagicBook("common animaBook",40,1,2);
    lightMagicBook = new LightMagicBook("spellsfordummies",40,1,2);
    darknessMagicBook = new DarknessMagicBook("howToActColdAsSasuke",40,1,2);
    bow = new Bow("",40,2,4);
    sword = new Sword("",40,1,2);
    axe = new Axe("",40,1,5);
    staff = new Staff("",40,1,1);




    fighter = new Fighter(1000,4,new Location(1,0),axe);
    fighter.equipItem(axe);
    axe.getAttackedByDarknessMagicBook(darknessMagicBook);
    assertEquals(fighter.getCurrentHitPoints(),940);
    axe.getAttackedByLightMagicBook(lightMagicBook);
    assertEquals(fighter.getCurrentHitPoints(),880);
    axe.getAttackedByAnimaMagicBook(animaMagicBook);
    assertEquals(fighter.getCurrentHitPoints(),820);
    axe.getAttackedByBow(bow);
    assertEquals(fighter.getCurrentHitPoints(),780);
    axe.getAttackedBySpear(spear);
    axe.getAttackedByStaff(staff);
    axe.getAttackedBySword(sword);
    assertEquals(fighter.getCurrentHitPoints(),700);


  }

}