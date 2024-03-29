package model.units;

import model.Tactician.Tactician;
import model.items.IEquipableItem;
import model.items.Staff;
import model.map.Location;

/**
 * This class represents a cleric type unit. A cleric can only use staff type weapons, which means
 * that it can receive attacks but can't counter attack any of those.
 *
 * @author Ignacio Slater Muñoz
 * @author Clemente Henriquez Muñoz
 * @since 1.0
 */
public class Cleric extends AbstractUnit {

  /**
   * Creates a new Unit.
   *  @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   */
  public Cleric(final int hitPoints, final int movement, final Location location,
                final IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(final IEquipableItem item) {
      item.equippedCleric(this);
  }

  public void heal(IUnit unit){
    unit.setCurrentHitPoints(Math.min(unit.getMaxHitPoints(),unit.getCurrentHitPoints()+this.getEquippedItem().getPower())); }
  public void attack(IUnit unit){
  }

  @Override
  public String type(){return "Cleric";}
}
