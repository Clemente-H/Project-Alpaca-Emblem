package model.units;

import model.items.Spear;
import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.Test;

/**
 * Test set for the alpaca unit
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class AlpacaTest extends AbstractTestUnit {
  private Hero hero;
  private Alpaca alpaca;
  private Spear spear;

  @Override
  public void setTestUnit() {
    alpaca = new Alpaca(50, 2, field.getCell(0, 0));
  }

  @Override
  public Alpaca getTestUnit() {
    return alpaca;
  }

  @Test
  void heroAttackingtest(){
    field = new Field();
    for (int i = 0; i<5;i++){
      for (int j = 0; j<5;j++){
        this.field.addCells(false, new Location(i, j));
      }
    }
    spear = new Spear("",50,1,10);
    hero = new Hero(10,1,field.getCell(0,0),null);
    hero.equipItem(spear);
    alpaca = new Alpaca(100,2,field.getCell(2,2),null);

  }
}