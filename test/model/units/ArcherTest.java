package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.items.*;
import model.map.Location;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;




/**
 * Test set for the Archer unit.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class ArcherTest extends AbstractTestUnit {

  private Archer archer;
//  private Cleric cleric;
//  private SwordMaster swordMaster;
//  private Alpaca alpaca;
//  private Fighter fighter;
//  private Hero hero;
//  private Sorcerer sorcerer1;
//  private Sorcerer sorcerer2;
//  private Sorcerer sorcerer3;



  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    archer = new Archer(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return archer;
  }

  /**
   * Checks if the bow is equipped correctly to the unit
   */

  @Test
  public void equipInvalidItem(){
    sword= new Sword("",1,1,1);
    archer = new Archer(1,1,null,sword);
    assertNull(archer.getEquippedItem());
    archer.equipItem(sword);
    assertNull(archer.getEquippedItem());
  }


  @Test
  @Override
  public void equipBowTest() {
    bow = new Bow("",1,1,1);
    archer = new Archer(10,1,null,bow);
    assertNull(archer.getEquippedItem());
    archer.equipItem(bow);
    assertEquals(bow, archer.getEquippedItem());
  }

//  @Test
//  public void Fighting(){
//    Location local1 = new Location(1,1);
//    Location local2 = new Location(1,2);
//    Location local3 = new Location(1,3);
//    Location local4 = new Location(1,4);
//    Location local5 = new Location(2,2);
//    Location local6 = new Location(2,1);
//    Location local7 = new Location(2,3);
//    Location local8 = new Location(2,4);
//    Location local9 = new Location(3,1);
//    Location local10 = new Location(3,2);
//    Location local11 = new Location(3,3);
//    Location local12 = new Location(3,4);
//
//    local1.addNeighbour(local2);
//    local2.addNeighbour(local3);
//    local3.addNeighbour(local4);
//    local4.addNeighbour(local8);
//    local1.addNeighbour(local5);
//    local2.addNeighbour(local6);
//    local3.addNeighbour(local7);
//    local4.addNeighbour(local8);
//    local5.addNeighbour(local6);
//    local6.addNeighbour(local7);
//    local7.addNeighbour(local8);
//    local5.addNeighbour(local9);
//    local6.addNeighbour(local10);
//    local7.addNeighbour(local11);
//    local8.addNeighbour(local12);
//    local9.addNeighbour(local10);
//    local10.addNeighbour(local11);
//    local11.addNeighbour(local12);


//    axe=new Axe("", 40, 1,2);
//    sword=new Sword("", 40,1,2);
//    spear = new Spear("", 40,1,2);
//    bow=new Bow("",40,2,8);
//    baston = new Staff("",1,1,1);
//    light = new LightMagicBook("",40,1,7);
//    darkness = new DarknessMagicBook("",40,1,7);
//    anima = new AnimaMagicBook("",40,1,7);
//
//    cleric= new Cleric(1000,1,local5,baston);
//    alpaca= new Alpaca(100,1,local7,null);
//    fighter = new Fighter(100,1,local1,axe);
//    swordMaster = new SwordMaster(100,1,local6,sword);
//    archer = new Archer(1000, 1, local3,bow );
//    hero = new Hero(1000,1,local2,spear);
//    sorcerer1 = new Sorcerer(100,1,local9,
//




//    archer.Combat(fighter);
//    assertEquals(archer.getCurrentHitPoints(),960);
//    archer.Combat(hero);
//    assertEquals(archer.getCurrentHitPoints(),920);
//    archer.Combat(alpaca);
//    assertEquals(archer.getCurrentHitPoints(),920);
//    archer.Combat(swordMaster);
//    assertEquals(archer.getCurrentHitPoints(),880);
//    archer.Combat(sorcerer1);

}
