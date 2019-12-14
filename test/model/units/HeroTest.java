package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.items.Axe;
import model.items.Spear;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

/**
 * @author Ignacio Slater Muñoz
 */
public class HeroTest extends AbstractTestUnit {

  private Hero hero;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    Hero hero = new Hero(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return hero;
  }

  @Override
  @Test
  public void equipSpearTest() {
    spear = new Spear("",1,1,1);
    hero = new Hero(1,1,null,spear);
    assertNull(hero.getEquippedItem());
    hero.equipItem(spear);
    assertEquals(spear, hero.getEquippedItem());
  }

  @Test
  public void testType(){
    axe = new Axe("",1,1,1);
    hero = new Hero(1,1,null,axe);
    assertEquals(hero.type(),"Hero");
  }
}