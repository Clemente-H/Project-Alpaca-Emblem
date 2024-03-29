package model.units;

import model.Tactician.Tactician;
import model.items.IEquipableItem;
import model.items.Spear;
import model.map.Location;

/**
 * A <i>Hero</i> is a special kind of unit, the player that defeats this unit wins the game.
 * <p>
 * This unit <b>can only use spear weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @subauthor Clemente Henriquez Muñoz
 * @since 1.0
 */
public class Hero extends AbstractUnit {

  /**
   * Creates a new Unit.
   *  @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   */
  public Hero(final int hitPoints, final int movement, final Location location,
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
      item.equippedHero(this);
  }
  public void attack(IUnit unit){
    unit.getEquippedItem().getAttackedBySpear(this.getEquippedItem());
  }
  @Override
  public boolean isHeroAlive() {
    if (this.getCurrentHitPoints() <= 0) {
      return false;}
    return true;
  }

  @Override
  public String type(){return "Hero";}
}
