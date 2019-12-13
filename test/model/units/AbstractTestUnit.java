package model.units;

import org.junit.jupiter.api.BeforeEach;

import model.items.*;

import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractTestUnit implements ITestUnit {

  protected Alpaca targetAlpaca;
  protected Bow bow;
  protected Field field;
  protected Axe axe;
  protected Sword sword;
  protected Staff staff;
  protected Spear spear;
  protected Archer archer;
  protected SwordMaster swordMaster;
  protected Fighter fighter;
  protected Hero hero;
  protected Staff baston;
  protected Cleric cleric;
  protected LightMagicBook light;
  protected DarknessMagicBook darkness;
  protected AnimaMagicBook anima;
  protected Alpaca alpaca;
  protected Sorcerer sorcerer;

  @Override
  public void setTargetAlpaca() {
    targetAlpaca = new Alpaca(50, 2, field.getCell(1, 0));
  }

  /**
   * Sets up the units and weapons to be tested
   */

  @Test
  public void setHitpoints(){
    targetAlpaca.setCurrentHitPoints(55);
    assertEquals(targetAlpaca.getCurrentHitPoints(),55);
  }
  @Test
  public void selectItemTest(){
    fighter = new Fighter(50,2,field.getCell(1, 0));
    axe = new Axe("",1,1,1);
    sword = new Sword("",1,1,1);
    fighter.items.add(0,axe);
    fighter.items.add(1,sword);
    assertEquals(axe,fighter.selectItem(0));
  }

  /**
   * Set up the game field
   */
  @Override
  public void setField() {
    this.field = new Field();
    this.field.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
        new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
        new Location(2, 1), new Location(2, 2));
  }

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public abstract void setTestUnit();

  /**
   * Creates a set of testing weapons
   */
  @Override
  public void setWeapons() {
    this.axe = new Axe("Axe", 10, 1, 2);
    this.sword = new Sword("Sword", 10, 1, 2);
    this.spear = new Spear("Spear", 10, 1, 2);
    this.staff = new Staff("Staff", 10, 1, 2);
    this.bow = new Bow("Bow", 10, 2, 3);
  }

  /**
   * Checks that the constructor works properly.
   */
  @Override
  @Test
  public void constructorTest() {
    assertEquals(50, getTestUnit().getCurrentHitPoints());
    assertEquals(2, getTestUnit().getMovement());
    assertEquals(new Location(0, 0), getTestUnit().getLocation());
    assertTrue(getTestUnit().getItems().isEmpty());
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public abstract IUnit getTestUnit();

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Override
  @Test
  public void equipAxeTest() {
    assertNull(getTestUnit().getEquippedItem());
    checkEquippedItem(getAxe());
  }

  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped
   *
   * @param item
   *     to be equipped
   */
  @Override
  public void checkEquippedItem(IEquipableItem item) {
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().equipItem(item);
    assertNull(getTestUnit().getEquippedItem());
  }

  /**
   * @return the test axe
   */
  @Override
  public Axe getAxe() {
    return axe;
  }

  @Override
  @Test
  public void equipSwordTest() {
    checkEquippedItem(getSword());
  }

  /**
   * @return the test sword
   */
  @Override
  public Sword getSword() {
    return sword;
  }

  @Override
  @Test
  public void equipSpearTest() {
    checkEquippedItem(getSpear());
  }

  /**
   * @return the test spear
   */
  @Override
  public Spear getSpear() {
    return spear;
  }

  @Override
  @Test
  public void invalidEquipSword(){archer = new Archer(100,2,new Location(1,3));
  sword.equippedArcher(archer);
    assertNull(archer.equippedItem);
  }

  @Override
  @Test
  public void equipStaffTest() {
    checkEquippedItem(getStaff());
  }

  /**
   * @return the test staff
   */
  @Override
  public Staff getStaff() {
    return staff;
  }

  @Override
  @Test
  public void equipBowTest() {
    checkEquippedItem(getBow());
  }

  /**
   * @return the test bow
   */
  @Override
  public Bow getBow() {
    return bow;
  }

  /**
   * Checks if the unit moves correctly
   */
  @Override
  @Test
  public void testMovement() {
    getTestUnit().moveTo(getField().getCell(2, 2));
    assertEquals(new Location(0, 0), getTestUnit().getLocation());

    getTestUnit().moveTo(getField().getCell(0, 2));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());

    getField().getCell(0, 1).setUnit(getTargetAlpaca());
    getTestUnit().moveTo(getField().getCell(0, 1));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());
  }

  /**
   * @return the test field
   */
  @Override
  public Field getField() {
    return field;
  }

  /**
   * @return the target Alpaca
   */
  @Override
  public Alpaca getTargetAlpaca() {
    return targetAlpaca;
  }

  @Test
  public void combatsTest(){
    field = new Field();
    for (int i = 0; i<5;i++){
      for (int j = 0; j<5;j++){
        this.field.addCells(false, new Location(i, j));
      }
    }
    bow = new Bow("",20,0,150);
    spear = new Spear("",20,1,5);
    axe = new Axe("",20,1,5);
    hero = new Hero(100,5,field.getCell(0,0),spear);
    fighter = new Fighter(100,5,field.getCell(1,1),axe);
    archer = new Archer(100,3,field.getCell(4,4),bow);
    hero.equipItem(spear);
    fighter.equipItem(axe);
    archer.equipItem(bow);
    hero.Combat(fighter);
    assertTrue(hero.isHeroAlive());
    assertEquals(fighter.getCurrentHitPoints(),100);
    assertTrue(fighter.isHeroAlive());
    assertEquals(hero.getCurrentHitPoints(),70);
    archer.Combat(hero);
    assertEquals(hero.getCurrentHitPoints(),50);
    assertEquals(archer.getCurrentHitPoints(),100);
    archer.Combat(hero);
    assertEquals(hero.getCurrentHitPoints(),30);
    archer.Combat(hero);
    archer.Combat(hero);
    assertFalse(hero.isHeroAlive());


  }


   @Test
  public void testExchange() {
     field = new Field();
     for (int i = 0; i<4;i++){
       for (int j = 0; j<4;j++){
         this.field.addCells(false, new Location(i, j));
       }
     }
    swordMaster.moveTo(field.getCell(0,0));
     alpaca.moveTo(field.getCell(1,0));
    swordMaster.trade(sword, alpaca);
    assertTrue(alpaca.items.contains(sword));
  }

  @Test
  public void combatSwordMasterAnima(){
    Sword sword =new Sword("", 40, 1,2);
    swordMaster = new SwordMaster(1000,1,new Location(0,1));
    swordMaster.equipItem(sword);
    AnimaMagicBook anima = new AnimaMagicBook("",40,1,1);
    sorcerer = new Sorcerer(1000,1,new Location(0,0) );
    sorcerer.equipItem(anima);
    swordMaster.getLocation().addNeighbour(sorcerer.getLocation());

    swordMaster.Combat(sorcerer);
    assertEquals(sorcerer.getCurrentHitPoints(),940);
    assertEquals(swordMaster.getCurrentHitPoints(),940);
  }

  @Test
    public void testingMovement(){
    swordMaster = new SwordMaster(1000,1,new Location(0,1));
    Location location2 = new Location(1,1);
    location2.addNeighbour(swordMaster.getLocation());
    swordMaster.moveTo(location2);
    assertEquals(swordMaster.getLocation(),location2);}


  @Test
  public void testingMovement2(){
    sorcerer = new Sorcerer(1000,3,new Location(0,0));
    Location location2 = new Location(0,1);
    Location location3 = new Location(0,2);
    Location location4 = new Location(0,3);
    location2.addNeighbour(location3);
    location3.addNeighbour(location4);
    sorcerer.getLocation().addNeighbour(location2);
    sorcerer.moveTo(location3);
    assertEquals(sorcerer.getLocation(),location3);


  }



    @Test
  public void testingMaxHitpoints(){
      swordMaster = new SwordMaster(1000,1,new Location(0,1));
      swordMaster.setCurrentHitPoints(800);
      assertEquals(1000,swordMaster.getMaxHitPoints());
    }

    @Test
  public void testingMaxItems(){
      swordMaster = new SwordMaster(1000,1,new Location(0,1));
      assertEquals(swordMaster.getMaxItems(),3);
    }

    @Test
  public void testingAddItemToUnit(){
      swordMaster = new SwordMaster(1000,1,new Location(0,1));
      bow = new Bow("",1,5,5);
      swordMaster.setItems(bow);
      assertTrue(swordMaster.items.contains(bow));
    }
    @Test
  public void testingAttack(){
      Sword sword =new Sword("", 40, 1,2);
      swordMaster = new SwordMaster(1000,1,new Location(0,1));
      swordMaster.equipItem(sword);
      AnimaMagicBook anima = new AnimaMagicBook("",40,1,1);
      sorcerer = new Sorcerer(1000,1,new Location(0,0) );
      sorcerer.equipItem(anima);
      swordMaster.attack(sorcerer);
      assertEquals(sorcerer.getCurrentHitPoints(),940);

    }
    @Test
  public void testingAttack2(){
      swordMaster = new SwordMaster(1000,1,new Location(0,1));
      AnimaMagicBook anima = new AnimaMagicBook("",40,1,1);
      sorcerer = new Sorcerer(1000,1,new Location(0,0) );
      sorcerer.equipItem(anima);
      swordMaster.attack(sorcerer);
      assertEquals(sorcerer.getCurrentHitPoints(),1000);

    }
    @Test
  public void testingInvalidHeal(){
      swordMaster = new SwordMaster(1000,1,new Location(0,1));
      AnimaMagicBook anima = new AnimaMagicBook("",40,1,1);
      sorcerer = new Sorcerer(1000,1,new Location(0,0) );
      sorcerer.equipItem(anima);
      swordMaster.heal(sorcerer);
      assertEquals(sorcerer.getCurrentHitPoints(),1000);

    }
    public void testingValidHeal(){
      swordMaster = new SwordMaster(1000,1,new Location(0,1));
      Staff baculo = new Staff("",100,1,2);
      cleric = new Cleric(1000,1,new Location(0,0) );
      cleric.getLocation().addNeighbour(swordMaster.getLocation());
      cleric.equipItem(baculo);
      sword = new Sword("",1,1,2);
      swordMaster.equipItem(sword);
      bow = new Bow("",500,1,2);
      archer = new Archer(1000,1,new Location(0,0) );
      swordMaster.Combat(archer);
      cleric.heal(swordMaster);
      assertEquals(swordMaster.getCurrentHitPoints(),600);
    }

    @Test
    public void testingValidExchange(){
      field = new Field();
      for (int i = 0; i<4;i++){
        for (int j = 0; j<4;j++){
          this.field.addCells(false, new Location(i, j));
        }
      }



      swordMaster = new SwordMaster(1000,1,field.getCell(0,0));
      sword = new Sword("",1,1,1);
      swordMaster.items.add(sword);
      archer = new Archer(1000,1,field.getCell(1,0));
      axe = new Axe("",1,1,1);
      swordMaster.trade(sword,archer);
      assertFalse(swordMaster.items.contains(sword));
      assertTrue(archer.items.contains(sword));
    }
  @Test
  public void testingHittingAlpaca(){
    swordMaster = new SwordMaster(1000,1,new Location(0,1));
    sword = new Sword("",40,1,2);
    swordMaster.equipItem(sword);
    alpaca = new Alpaca(1000,1,new Location(0,0) );
    alpaca.getLocation().addNeighbour(swordMaster.getLocation());
    alpaca.attackedAlpaca(swordMaster);
    assertEquals(alpaca.getCurrentHitPoints(),960);
  }
  @Test
  public void alpacaTriesToHit(){
    swordMaster = new SwordMaster(1000,1,new Location(0,1));
    sword = new Sword("",40,1,2);
    swordMaster.equipItem(sword);
    alpaca = new Alpaca(1000,1,new Location(0,0) );
    alpaca.getLocation().addNeighbour(swordMaster.getLocation());
    alpaca.attack(swordMaster);
    assertEquals(swordMaster.getCurrentHitPoints(),1000);

  }
@Test
  public void equipArcher(){
    bow = new Bow("",1,1,2);
    archer = new Archer(1000,1, new Location(1,1));
    archer.equipItem(bow);
    assertEquals(archer.equippedItem,bow);
}
@Test
  public void archerAttacking(){
  bow = new Bow("",40,1,2);
  archer = new Archer(1000,1, new Location(1,1));
  archer.equipItem(bow);
  swordMaster = new SwordMaster(1000,1,new Location(0,1));
  sword = new Sword("",40,1,2);
  swordMaster.equipItem(sword);
  archer.getLocation().addNeighbour(swordMaster.getLocation());
  archer.attack(swordMaster);
  assertEquals(swordMaster.getCurrentHitPoints(),960);

}
  @Test
  public void clericAttacking(){
    baston = new Staff("",40,1,2);
    cleric = new Cleric(1000,1, new Location(1,1));
    cleric.equipItem(baston);
    swordMaster = new SwordMaster(1000,1,new Location(0,1));
    sword = new Sword("",40,1,2);
    swordMaster.equipItem(sword);
    cleric.getLocation().addNeighbour(swordMaster.getLocation());
    cleric.attack(swordMaster);
    assertEquals(swordMaster.getCurrentHitPoints(),1000);


  }
  @Test
  public void heroAttacking(){
    bow = new Bow("",40,1,2);
    archer = new Archer(1000,1, new Location(1,1));
    archer.equipItem(bow);
    hero = new Hero(1000,1,new Location(0,1));
    spear = new Spear("",40,1,2);
    hero.equipItem(spear);
    archer.getLocation().addNeighbour(hero.getLocation());
    hero.attack(archer);
    assertEquals(archer.getCurrentHitPoints(),960);
  }

  @Test
  public void equipFighter(){
    axe = new Axe("",1,1,2);
    fighter = new Fighter(1000,1, new Location(1,1));
    fighter.equipItem(axe);
    assertEquals(fighter.equippedItem,axe);
  }
    @Test
    public void fighterAttacking(){
      bow = new Bow("",40,1,2);
      archer = new Archer(1000,1, new Location(1,1));
      archer.equipItem(bow);
      axe = new Axe("",40,1,2);
      fighter = new Fighter(1000,1, new Location(0,1));
      fighter.equipItem(axe);
      archer.getLocation().addNeighbour(fighter.getLocation());
      fighter.attack(archer);
      assertEquals(archer.getCurrentHitPoints(),960);
    }
    @Test
  public void alpacaTriesToEquipAnItem(){
      sword = new Sword("",40,1,2);
      alpaca = new Alpaca(1000,1,new Location(0,0) );
      alpaca.equipItem(sword);
      assertNull(alpaca.getEquippedItem());

    }

    @Test
  public void mulitpleCombats(){
      axe = new Axe("",40,1,2);
      fighter = new Fighter(1000,1, new Location(1,0));
      fighter.equipItem(axe);
      Location location2 = new Location(1,1);
      Location location3 = new Location(1,2);

      bow = new Bow("",40,1,10);
      archer = new Archer(1000,1, new Location(1,3));
      archer.equipItem(bow);
      swordMaster = new SwordMaster(1000,1,new Location(0,0));
      sword = new Sword("",40,1,2);
      swordMaster.equipItem(sword);
      swordMaster.getLocation().addNeighbour(fighter.getLocation());
      fighter.getLocation().addNeighbour(location2);
      location2.addNeighbour(location3);
      location3.addNeighbour(archer.getLocation());
      archer.Combat(swordMaster);
      swordMaster.Combat(fighter);
      fighter.Combat(archer);
      assertEquals(swordMaster.getCurrentHitPoints(),940);
      assertEquals(fighter.getCurrentHitPoints(),940);
      assertEquals(archer.getCurrentHitPoints(),1000);
    }

}