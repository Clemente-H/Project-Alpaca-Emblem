package model.units;

import model.Tactician.Tactician;
import model.items.IEquipableItem;
import model.items.Sword;
import model.map.Location;

/**
 * This class represents a <i>SwordMaster</i> type unit.
 * <p>
 * A <i>SwordMaster</i> is a unit that <b>can only use sword type weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SwordMaster extends AbstractUnit {

  public SwordMaster(final int hitPoints, final int movement, final Location location,
                     final IEquipableItem... items) {
    super(hitPoints, movement, location, 3,items);
  }

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(final IEquipableItem item) {
      item.equippedSwordMaster(this);
  }
  public void attack(IUnit unit){
    unit.getEquippedItem().getAttackedBySword(this.getEquippedItem());
}

  @Override
  public String type(){return "SwordMaster";}
}
