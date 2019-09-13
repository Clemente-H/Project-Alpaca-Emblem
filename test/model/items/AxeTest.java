package model.items;

import model.map.Location;
import model.units.Fighter;
import model.units.IUnit;

import model.units.Hero;
import model.units.ArcherTest;
import model.units.SwordMaster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

  private Axe axe;
  private Axe wrongAxe;
  private Fighter fighter;

  @Override
  public void setTestItem() {
    expectedName = "Common axe";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 2;
    axe = new Axe(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
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
    fighter = new Fighter(10, 5, new Location(0, 0));
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

}