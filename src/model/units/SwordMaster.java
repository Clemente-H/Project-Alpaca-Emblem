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
 * @subauthor Clemente Henriquez Muñoz
 * @since 1.0
 */
public class SwordMaster extends AbstractUnit {

  public SwordMaster(final int hitPoints, final int movement, final Location location, final Tactician tactician,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, tactician,items);
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
}
