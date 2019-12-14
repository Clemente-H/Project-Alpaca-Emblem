package model.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.map.Field;
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
  protected Sorcerer sorcerer;
  protected Sorcerer darksorcerer;
  protected Sorcerer lightsorcerer;
  protected Field field;

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
  public void SwordMasterAttackedByLight(){
    Sword sword = new Sword("Hacha de luna Creciente", 40, 1,2);
    swordMaster = new SwordMaster(1000,1,new Location(0,1));
    swordMaster.equipItem(sword);
    LightMagicBook light = new LightMagicBook("",40,1,1);
    sword.getAttackedByLightMagicBook(light);
    assertEquals(swordMaster.getCurrentHitPoints(),940);

  }
  @Test
  public void animaAttackedBySpear(){
    Spear spear = new Spear("Hacha de luna Creciente", 40, 1,2);
    hero = new Hero(1000,1,new Location(0,1));
    hero.equipItem(spear);
    sorcerer = new Sorcerer(1000,1,new Location(1,1));
    AnimaMagicBook anima = new AnimaMagicBook("",40,1,1);
    sorcerer.equipItem(anima);
    anima.getAttackedBySpear(spear);
    assertEquals(sorcerer.getCurrentHitPoints(),940);

  }
  @Test
  public void darknessAttackedBySpear(){
    Spear spear = new Spear("Hacha de luna Creciente", 40, 1,2);
    hero = new Hero(1000,1,new Location(0,1));
    hero.equipItem(spear);
    sorcerer = new Sorcerer(1000,1,new Location(1,1));
    DarknessMagicBook darkness = new DarknessMagicBook("",40,1,1);
    sorcerer.equipItem(darkness);
    darkness.getAttackedBySpear(spear);
    assertEquals(sorcerer.getCurrentHitPoints(),940);

  }
  @Test
  public void animaAttackedByAxe(){
    Axe axe = new Axe("Hacha de luna Creciente", 40, 1,2);
    fighter = new Fighter(1000,1,new Location(0,1));
    fighter.equipItem(axe);
    sorcerer = new Sorcerer(1000,1,new Location(1,1));
    AnimaMagicBook anima = new AnimaMagicBook("",40,1,1);
    sorcerer.equipItem(anima);
    anima.getAttackedByAxe(axe);
    assertEquals(sorcerer.getCurrentHitPoints(),940);

  }
  @Test
  public void AnimaAttackedByBow(){
   Bow bow =  new Bow("", 40, 1,2);
    archer = new Archer(1000,1,new Location(0,1));
    archer.equipItem(bow);
    sorcerer = new Sorcerer(1000,1,new Location(1,1));
    AnimaMagicBook anima = new AnimaMagicBook("",40,1,1);
    sorcerer.equipItem(anima);
    anima.getAttackedByBow(bow);
    assertEquals(sorcerer.getCurrentHitPoints(),940);

  }


  @Test
  public void darknessAttackedBydark(){
    DarknessMagicBook dark = new DarknessMagicBook("Hacha de luna Creciente", 40, 1,2);
    sorcerer = new Sorcerer(1000,1,new Location(1,1));
    DarknessMagicBook darkness = new DarknessMagicBook("",40,1,1);
    sorcerer.equipItem(darkness);
    darksorcerer = new Sorcerer(1000,1,new Location(0,1));
    darksorcerer.equipItem(dark);
    dark.getAttackedByDarknessMagicBook(darkness);
    darkness.getAttackedByDarknessMagicBook(dark);
    assertEquals(darksorcerer.getCurrentHitPoints(),960);

  }














  @Test
  public void HeroAttackedByLight(){
    Spear spear = new Spear("Hacha de luna Creciente", 40, 1,2);
    hero = new Hero(1000,1,new Location(0,1));
    hero.equipItem(spear);
    LightMagicBook light = new LightMagicBook("",40,1,1);
    spear.getAttackedByLightMagicBook(light);
    assertEquals(hero.getCurrentHitPoints(),940);

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
  public void heroAttackedByStaff(){
   Spear spear = new Spear("Hacha de luna Creciente", 40, 1,2);
   hero = new Hero(1000,1,new Location(0,1));
    hero.equipItem(spear);
    Staff baston = new Staff("",40,1,1);
    spear.getAttackedByStaff(baston);
    assertEquals(hero.getCurrentHitPoints(),1000);
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
    assertEquals(cleric.getCurrentHitPoints(),10);
  }


  @Test
  public void SorcererAttackedByAxe(){
    field = new Field();
    for (int i = 0; i<3;i++){
      for (int j = 0; j<3;j++){
        this.field.addCells(true, new Location(i, j));
      }
    }


    Axe axe=new Axe("Hacha de luna Creciente", 40, 0,3);
    fighter = new Fighter(100,4,field.getCell(0,1),axe);
    fighter.equipItem(axe);
    DarknessMagicBook dark = new DarknessMagicBook("",40,0,3);
    sorcerer = new Sorcerer(1000,1,field.getCell(0,0),dark);
    sorcerer.equipItem(dark);
    fighter.Combat(sorcerer);
    assertEquals(sorcerer.getCurrentHitPoints(),940);
  }

  @Test
  public void SwordMasterAttackedByAxe2(){
    Sword sword = new Sword("",1,1,2);
    swordMaster = new SwordMaster(1000,1,new Location(0,1));
    swordMaster.equipItem(sword);
    Axe axe=new Axe("", 40,1,2);
    sword.getAttackedByAxe(axe);
    assertEquals(swordMaster.getCurrentHitPoints(),980);
  }

  @Test void SwordMasterAttackedBySpear2(){
    Sword sword = new Sword("",1,1,2);
    swordMaster = new SwordMaster(1000,1,new Location(0,1));
    swordMaster.equipItem(sword);
    Spear spear=new Spear("", 40,1,2);
    sword.getAttackedBySpear(spear);
    assertEquals(swordMaster.getCurrentHitPoints(),940);
  }

  @Test
  public void HeroAttackedByAxe2(){
    Spear spear = new Spear("",1,1,2);
    hero = new Hero(1000,1,new Location(0,1));
    hero.equipItem(spear);
    Axe axe=new Axe("", 40,1,2);
    spear.getAttackedByAxe(axe);
    assertEquals(hero.getCurrentHitPoints(),940);
  }

  @Test
  public void HeroAttackedBySword2(){
    Spear spear = new Spear("",1,1,2);
    hero = new Hero(1000,1,new Location(0,1));
    hero.equipItem(spear);
    Sword sword = new Sword("", 40,1,2);
    spear.getAttackedBySword(sword);
    assertEquals(hero.getCurrentHitPoints(),980);
  }

  @Test
  public void FighterAttackedBySword2(){
    Axe axe = new Axe("",1,1,2);
    fighter = new Fighter(1000,1,new Location(0,1));
    fighter.equipItem(axe);
    Sword sword = new Sword("", 40,1,2);
    axe.getAttackedBySword(sword);
    assertEquals(fighter.getCurrentHitPoints(),940);
  }

  @Test
  public void FighterAttackedBySpear2(){
    Axe axe = new Axe("",1,1,2);
    fighter = new Fighter(1000,1,new Location(0,1));
    fighter.equipItem(axe);
    Spear spear=new Spear("", 40,1,2);
    axe.getAttackedBySpear(spear);
    assertEquals(fighter.getCurrentHitPoints(),980);
  }

  @Test
  public void SorcererAttackedBySword(){
    Sword sword = new Sword("Hacha de luna Creciente", 40, 1,2);
    DarknessMagicBook dark = new DarknessMagicBook("",40,1,1);
    sorcerer = new Sorcerer(1000,1,new Location(0,1));
    sorcerer.equipItem(dark);
    dark.getAttackedBySword(sword);
    assertEquals(sorcerer.getCurrentHitPoints(),940);
  }

  @Test
  public void SwordMasterAttackedByAnima(){
    Sword sword =new Sword("", 40, 1,2);
    swordMaster = new SwordMaster(1000,1,new Location(0,1));
    swordMaster.equipItem(sword);
    AnimaMagicBook anima = new AnimaMagicBook("",40,1,1);
    sword.getAttackedByAnimaMagicBook(anima);
    assertEquals(swordMaster.getCurrentHitPoints(),940);
  }

  @Test
  public void LightSorcererAttackedByAnima(){
    field = new Field();
    for (int i = 0; i<2;i++){
      for (int j = 0; j<2;j++){
        this.field.addCells(false, new Location(i, j));
      }
    }
    LightMagicBook light =new LightMagicBook("Spells for dummies", 40, 1,4);
    sorcerer = new Sorcerer(1000,1,field.getCell(0,0),light);
    sorcerer.equipItem(light);

    AnimaMagicBook anima = new AnimaMagicBook("",40,1,4);
    Sorcerer sorcerer1 = new Sorcerer(1000,1,field.getCell(1,1),anima);
    sorcerer1.equipItem(anima);
    sorcerer.Combat(sorcerer1);
    assertEquals(sorcerer.getCurrentHitPoints(),940);
    }
  @Test
  public void LightSorcererAttackedByDarkness(){
    field = new Field();

    LightMagicBook light =new LightMagicBook("Spells for dummies", 40, 1,2);
    sorcerer = new Sorcerer(1000,1,new Location(0,1));
    sorcerer.equipItem(light);
    DarknessMagicBook darkness = new DarknessMagicBook("",40,1,1);
    light.getAttackedByDarknessMagicBook(darkness);
    assertEquals(sorcerer.getCurrentHitPoints(),980);
  }

  @Test
  public void darkSorcererAttackedByAnima(){
    DarknessMagicBook darkness =new DarknessMagicBook("How to act cold as Sasuke", 40, 1,2);
    sorcerer = new Sorcerer(1000,1,new Location(0,1));
    sorcerer.equipItem(darkness);
    AnimaMagicBook anima = new AnimaMagicBook("",40,1,1);
    darkness.getAttackedByAnimaMagicBook(anima);
    assertEquals(sorcerer.getCurrentHitPoints(),980);
  }

  @Test
  public void animaSorcererAttackedByLight(){
    AnimaMagicBook anima =new AnimaMagicBook("Ayahuasca Style", 40, 1,2);
    sorcerer = new Sorcerer(1000,1,new Location(0,1));
    sorcerer.equipItem(anima);
    LightMagicBook light = new LightMagicBook("",40,1,1);
    anima.getAttackedByLightMagicBook(anima);
    assertEquals(sorcerer.getCurrentHitPoints(),980);
  }

  @Test
  public void animaSorcererAttackedByAnima(){
    AnimaMagicBook anima =new AnimaMagicBook("Ayahuasca Style", 40, 1,2);
    sorcerer = new Sorcerer(1000,1,new Location(0,1));
    sorcerer.equipItem(anima);
    AnimaMagicBook anima2 = new AnimaMagicBook("",40,1,1);
    anima.getAttackedByAnimaMagicBook(anima2);
    assertEquals(sorcerer.getCurrentHitPoints(),960);
  }

  @Test
  public void darkSorcererAttackedByDarkness(){
    DarknessMagicBook darkness =new DarknessMagicBook("How to act cold as Sasuke", 40, 1,2);
    sorcerer = new Sorcerer(1000,1,new Location(0,1));
    sorcerer.equipItem(darkness);
    DarknessMagicBook dark2 = new DarknessMagicBook("",40,1,1);
    darkness.getAttackedByDarknessMagicBook(dark2);
    assertEquals(sorcerer.getCurrentHitPoints(),960);
    sorcerer.equipItem(dark2);
    dark2.getAttackedByDarknessMagicBook(darkness);
    assertEquals(sorcerer.getCurrentHitPoints(),920);
  }

  @Test
  public void LightSorcererAttackedByLight(){
    LightMagicBook light =new LightMagicBook("Spells for dummies", 40, 1,2);
    sorcerer = new Sorcerer(1000,1,new Location(0,1));
    sorcerer.equipItem(light);
    LightMagicBook light2 = new LightMagicBook("",40,1,1);
    light.getAttackedByLightMagicBook(light2);
    assertEquals(sorcerer.getCurrentHitPoints(),960);
  }






}
