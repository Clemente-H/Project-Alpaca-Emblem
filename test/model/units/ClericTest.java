package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.items.Axe;
import model.items.Staff;
import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 */
public class ClericTest extends AbstractTestUnit {

  private Cleric cleric;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    cleric = new Cleric(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return cleric;
  }

  @Test
  @Override
  public void equipStaffTest() {
    staff = new Staff("",1,1,2);
    cleric = new Cleric(1,1,null,staff);
    assertNull(cleric.getEquippedItem());
    cleric.equipItem(staff);
    assertEquals(staff, cleric.getEquippedItem());
  }


  @Test
  public void healTest(){
    field = new Field();
    for (int i = 0; i<5;i++){
      for (int j = 0; j<5;j++){
        this.field.addCells(false, new Location(i, j));
      }
    }
    staff = new Staff("",1,1,2);
    cleric = new Cleric(1,1,null,staff);
    assertNull(cleric.getEquippedItem());
    cleric.equipItem(staff);
    alpaca = new Alpaca(100,1,new Location(1,0));
    alpaca.setCurrentHitPoints(20);
    cleric.heal(alpaca);
    assertEquals(alpaca.getCurrentHitPoints(),21);
  }

  @Test
  public void testType(){
    axe = new Axe("",1,1,1);
    cleric = new Cleric(1,1,null,axe);
    assertEquals(cleric.type(),"Cleric");
  }
}