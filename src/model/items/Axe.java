package model.items;

import model.units.Fighter;

/**
 * This class represents an Axe.
 * <p>
 * Axes are strong against spears but weak agains swords.
 *
 * @author Ignacio Slater Muñoz
 *
 * @since 1.0
 */
public class Axe extends AbstractItem {

  /**
   * Creates a new Axe item
   *
   * @param name
   *     the name of the Axe
   * @param power
   *     the damage of the axe
   * @param minRange
   *     the minimum range of the axe
   * @param maxRange
   *     the maximum range of the axe
   */
  public Axe(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }
  public void equippedFighter(Fighter fighter){this.equipTo(fighter);}
  /**
   * Sets weakness and strength to this item
   * in this case axe is strong against spears, and weak against swords
   *
   *
   * */
  public void getAttackedBySword(IEquipableItem item){item.strongAttackTo(this.getOwner());}
  public void getAttackedBySpear(IEquipableItem item){item.weakAttackTo(this.getOwner());}

}
