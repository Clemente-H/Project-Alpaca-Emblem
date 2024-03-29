package model.units;

import model.Tactician.Tactician;
import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents an <i>Alpaca</i> type unit.
 * <p>
 * This are a special kind of unit that can carry an unlimited amount of items but can't use any of
 * them.
 *
 * @author Ignacio Slater Muñoz

 * @since 1.0
 */
public class Alpaca extends AbstractUnit {

  /**
   * Creates a new Alpaca.
   *  @param hitPoints
   *     the amount of damage this unit can receive
   * @param movement
   *     number of cells the unit can move
   * @param location
   */
  public Alpaca(final int hitPoints, final int movement, final Location location,
                final IEquipableItem... items) {
    super(hitPoints, movement, location, Integer.MAX_VALUE , items);
  }

  /**
   * {@inheritDoc}
   * <p>
   * The <i>Alpaca</i> cannot equip any item.
   */
  @Override
  public void equipItem(final IEquipableItem item) {
    // Method body intentionally left empty
  }

  public void attackedAlpaca(IUnit unit){
    unit.getEquippedItem().attackAlpaca(this);
  }

  @Override
  public String type(){return "Alpaca";}
}
