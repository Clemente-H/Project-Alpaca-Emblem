package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;

import model.items.*;

import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractTestUnit implements ITestUnit {

  protected Alpaca targetAlpaca;
  protected SwordMaster Artorias;
  protected Bow bow;
  protected Field field;
  protected Axe axe;
  protected Sword sword;
  protected Staff staff;
  protected Spear spear;
  protected Sorcerer sorcerer;

  @Override
  public void setTargetAlpaca() {
    targetAlpaca = new Alpaca(50, 2, field.getCell(1, 0));
  }

  /**
   * Sets up the units and weapons to be tested
   */




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




  @BeforeEach
  void setUp(){
    setTestUnit();
    setTargetAlpaca();
    setWeapons();
   /*
   * BigGameField*/

    Location local1 = new Location(1,1);
    Location local2 = new Location(1,2);
    Location local3 = new Location(1,3);
    Location local4 = new Location(1,4);
    Location local5 = new Location(2,2);
    Location local6 = new Location(2,1);
    Location local7 = new Location(2,3);
    Location local8 = new Location(2,4);
    Location local9 = new Location(3,1);
    Location local10 = new Location(3,2);
    Location local11 = new Location(3,3);
    Location local12 = new Location(3,4);

    local1.addNeighbour(local2);
    local2.addNeighbour(local3);
    local3.addNeighbour(local4);
    local4.addNeighbour(local8);
    local1.addNeighbour(local5);
    local2.addNeighbour(local6);
    local3.addNeighbour(local7);
    local4.addNeighbour(local8);
    local5.addNeighbour(local6);
    local6.addNeighbour(local7);
    local7.addNeighbour(local8);
    local5.addNeighbour(local9);
    local6.addNeighbour(local10);
    local7.addNeighbour(local11);
    local8.addNeighbour(local12);
    local9.addNeighbour(local10);
    local10.addNeighbour(local11);
    local11.addNeighbour(local12);


    axe=new Axe("Hacha de luna Creciente", 40, 1,2);
    Alpaca alpaca= new Alpaca(1000,1,local7,null);
    sword=new Sword("Espada de Artorias", 40,1,2);
    spear = new Spear("Lanza Negra", 40,1,2);
    Fighter fighter = new Fighter(1000,1,local1,axe);

    Hero hero = new Hero(1000,1,local2,spear);
    SwordMaster swordMaster = new SwordMaster(1000,1, local6, spear);

  }
  @Test
  public void testExchange() {
    Sword sable =new Sword("Espada de Artorias", 40,1,2);
    Artorias.exchange(sable, targetAlpaca);
    assertTrue(targetAlpaca.items.contains(sable));
  }
}