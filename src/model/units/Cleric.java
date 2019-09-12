package model.units;

import model.items.IEquipableItem;
import model.items.Staff;
import model.map.Location;

/**
 * This class represents a cleric type unit. A cleric can only use staff type weapons, which means
 * that it can receive attacks but can't counter attack any of those.
 *
 * @author Ignacio Slater Muñoz
 * @subauthor Clemente Henriquez Muñoz
 * @since 1.0
 */
public class Cleric extends AbstractUnit {

  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   */
  public Cleric(final int hitPoints, final int movement, final Location location,
      IEquipableItem... items) {
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
    if(this.items.contains(item)){
      item.equipedCleric(this);
    }

  }
  public void heal(IUnit unit){
    if(this.getEquippedItem()!=null){
      if(this.getLocation().distanceTo(unit.getLocation())>=this.getEquippedItem().getMinRange() && this.getLocation().distanceTo(unit.getLocation())>=this.getEquippedItem().getMaxRange()){
        if(unit.getMaxHitPoints()<unit.getCurrentHitPoints()+this.getEquippedItem().getPower()){
          unit.setCurrentHitPoints(unit.getCurrentHitPoints());
        }
        else{
          unit.setCurrentHitPoints(unit.getCurrentHitPoints()+ this.getEquippedItem().getPower());
        }
      }
    }
  }
  public void attack(IUnit unit){
  }
}
