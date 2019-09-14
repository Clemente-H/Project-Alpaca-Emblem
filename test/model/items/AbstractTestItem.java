package model.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.map.Location;
import model.units.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Defines some common methods for all the items tests
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractTestItem {

  protected String expectedName;
  protected int expectedPower;
  protected short expectedMinRange;
  protected short expectedMaxRange;
  protected Alpaca alpaca;
  protected Fighter fighter;
  protected Cleric cleric;
  protected SwordMaster swordMaster;
  protected Hero hero;
  protected Archer archer;

  /**
   * Sets up the items to be tested
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

  //de aqui hasta la siguiente esta bien

  @Test
  public void hittingAlpacas(){
    Axe axe=new Axe("Hacha de luna Creciente", 40, 1,2);
    Sword sword=new Sword("Espada de Artorias", 40,1,2);
    Spear spear = new Spear("Lanza Negra", 40,1,2);
    Bow bow=new Bow("",40,2,8);
    LightMagicBook light = new LightMagicBook("",40,1,1);
    DarknessMagicBook darkness = new DarknessMagicBook("",40,1,1);
    AnimaMagicBook anima = new AnimaMagicBook("",40,1,1);
    Alpaca alpaca= new Alpaca(1000,1,new Location(0,0));
    spear.attackAlpaca(alpaca);
    assertEquals(alpaca.getCurrentHitPoints(),960);
    sword.attackAlpaca(alpaca);
    assertEquals(alpaca.getCurrentHitPoints(),920);
    axe.attackAlpaca(alpaca);
    assertEquals(alpaca.getCurrentHitPoints(),880);
    bow.attackAlpaca(alpaca);
    assertEquals(alpaca.getCurrentHitPoints(),840);
    light.attackAlpaca(alpaca);
    assertEquals(alpaca.getCurrentHitPoints(),800);
    darkness.attackAlpaca(alpaca);
    assertEquals(alpaca.getCurrentHitPoints(),760);
    anima.attackAlpaca(alpaca);
    assertEquals(alpaca.getCurrentHitPoints(),720);
  }

  @Test
  public void FighterAttackedByLight(){
    Axe axe=new Axe("Hacha de luna Creciente", 40, 1,2);
    fighter = new Fighter(1000,1,new Location(0,1));
    fighter.equipItem(axe);
    LightMagicBook light = new LightMagicBook("",40,1,1);
    axe.getAttackedByLightMagicBook(light);
    assertEquals(fighter.getCurrentHitPoints(),940);



  }
  @Test
  public void FighterAttackedByDarkness(){
    Axe axe=new Axe("Hacha de luna Creciente", 40, 1,2);
    fighter = new Fighter(1000,1,new Location(0,1));
    fighter.equipItem(axe);
    DarknessMagicBook darkness = new DarknessMagicBook("",40,1,1);
    axe.getAttackedByDarknessMagicBook(darkness);
    assertEquals(fighter.getCurrentHitPoints(),940);
  }
  @Test
  public void FighterAttackedByAnima(){
    Axe axe=new Axe("Hacha de luna Creciente", 40, 1,2);
    fighter = new Fighter(1000,1,new Location(0,1));
    fighter.equipItem(axe);
    AnimaMagicBook anima = new AnimaMagicBook("",40,1,1);
    axe.getAttackedByAnimaMagicBook(anima);
    assertEquals(fighter.getCurrentHitPoints(),940);
  }

  @Test void ClericAttackedBySword(){
    Staff baston = new Staff("",1,1,2);
    cleric = new Cleric(1000,1,new Location(0,1));
    cleric.equipItem(baston);
    Sword sword=new Sword("Espada de Artorias", 40,1,2);
    baston.getAttackedBySword(sword);
    assertEquals(cleric.getCurrentHitPoints(),960);
  }
  @Test void ClericAttackedBySpear(){
    Staff baston = new Staff("",1,1,2);
    cleric = new Cleric(1000,1,new Location(0,1));
    cleric.equipItem(baston);
    Spear spear=new Spear("", 40,1,2);
    baston.getAttackedBySpear(spear);
    assertEquals(cleric.getCurrentHitPoints(),960);
  }
  @Test void ClericAttackedByAxe(){
    Staff baston = new Staff("",1,1,2);
    cleric = new Cleric(1000,1,new Location(0,1));
    cleric.equipItem(baston);
    Axe axe=new Axe("", 40,1,2);
    baston.getAttackedByAxe(axe);
    assertEquals(cleric.getCurrentHitPoints(),960);
  }

  @Test void ClericAttackedByBow(){
    Staff baston = new Staff("",1,1,2);
    cleric = new Cleric(1000,1,new Location(0,1));
    cleric.equipItem(baston);
    Bow bow=new Bow("", 40,1,2);
    baston.getAttackedByBow(bow);
    assertEquals(cleric.getCurrentHitPoints(),960);
  }

  @Test
  public void FighterAttackedByStaff(){
    Axe axe=new Axe("Hacha de luna Creciente", 40, 1,2);
    fighter = new Fighter(1000,1,new Location(0,1));
    fighter.equipItem(axe);
    Staff baston = new Staff("",40,1,1);
    axe.getAttackedByStaff(baston);
    assertEquals(fighter.getCurrentHitPoints(),1000);
  }
  @Test
  public void FighterAttackedByMagicBook(){
    Axe axe=new Axe("Hacha de luna Creciente", 40, 1,2);
    fighter = new Fighter(1000,1,new Location(0,1));
    fighter.equipItem(axe);
    DarknessMagicBook magicBook = new DarknessMagicBook("",40,1,1);
    axe.getAttackedByMagicBook(magicBook);
    assertEquals(fighter.getCurrentHitPoints(),940);
  }


//esta bien hasta aqui

  @Test
  public void equipAxeToSwordMaster(){
    Axe axe=new Axe("Hacha de luna Creciente", 40, 1,2);
    swordMaster = new SwordMaster(1000,1,new Location(0,1));
    axe.equippedSwordMaster(swordMaster);
    assertNull(swordMaster.getEquippedItem());
  }
  @Test
  public void equipAxeToHero(){
    Axe axe=new Axe("Hacha de luna Creciente", 40, 1,2);
    hero = new Hero(1000,1,new Location(0,1));
    axe.equippedHero(hero);
    assertNull(hero.getEquippedItem());
  }

  @Test
  public void equipAxeToArcher(){
    Axe axe=new Axe("Hacha de luna Creciente", 40, 1,2);
    archer = new Archer(1000,1,new Location(0,1));
    axe.equippedArcher(archer);
    assertNull(archer.getEquippedItem());
  }

  @Test
  public void equipAxeToCleric(){
    Axe axe=new Axe("Hacha de luna Creciente", 40, 1,2);
    cleric = new Cleric(1000,1,new Location(0,1));
    axe.equippedCleric(cleric);
    assertNull(cleric.getEquippedItem());
  }


  @Test
  public void equipSwordToFighter(){
    Sword sword=new Sword("Espada de Artorias", 40,1,2);
    fighter = new Fighter(1000,1,new Location(0,1));
    sword.equippedFighter(fighter);
    assertNull(fighter.getEquippedItem());
  }

  @Test
  public void testingStrongAttack(){
    Staff baston = new Staff("",1,1,2);
    cleric = new Cleric(1000,1,new Location(0,1));
    cleric.equipItem(baston);
    Bow bow=new Bow("", 40,1,2);
    bow.strongAttackTo(cleric);
    assertEquals(cleric.getCurrentHitPoints(),940);
  }

  @Test
  public void testingStrongAttack2(){
    Staff baston = new Staff("",1,1,2);
    cleric = new Cleric(60,1,new Location(0,1));
    cleric.equipItem(baston);
    Bow bow=new Bow("", 40,1,2);
    bow.strongAttackTo(cleric);
    assertEquals(cleric.getCurrentHitPoints(),0);
  }

  @Test
  public void testingWeakAttack(){
    Staff baston = new Staff("",1,1,2);
    cleric = new Cleric(1000,1,new Location(0,1));
    cleric.equipItem(baston);
    Bow bow=new Bow("", 40,1,2);
    bow.weakAttackTo(cleric);
    assertEquals(cleric.getCurrentHitPoints(),980);
  }
  @Test
  public void testingWeakAttack2(){
    Staff baston = new Staff("",1,1,2);
    cleric = new Cleric(10,1,new Location(0,1));
    cleric.equipItem(baston);
    Bow bow=new Bow("", 40,1,2);
    bow.weakAttackTo(cleric);
    assertEquals(cleric.getCurrentHitPoints(),0);
  }


  @Test
  public void testingNormalAttack(){
    Staff baston = new Staff("",1,1,2);
    cleric = new Cleric(1000,1,new Location(0,1));
    cleric.equipItem(baston);
    Bow bow=new Bow("", 40,1,2);
    bow.normalAttackTo(cleric);
    assertEquals(cleric.getCurrentHitPoints(),960);
  }
  @Test
  public void testingNormalAttack2(){
    Staff baston = new Staff("",1,1,2);
    cleric = new Cleric(30,1,new Location(0,1));
    cleric.equipItem(baston);
    Bow bow=new Bow("", 40,1,2);
    bow.weakAttackTo(cleric);
    assertEquals(cleric.getCurrentHitPoints(),0);
  }


}
