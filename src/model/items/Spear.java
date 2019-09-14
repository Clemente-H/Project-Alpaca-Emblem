package model.items;

import model.units.Alpaca;
import model.units.Hero;

/**
 * This class represents a <i>spear</i>.
 * <p>
 * Spears are strong against swords and weak against axes
 *
 * @author Ignacio Slater Muñoz
 * @subauthor Clemente Henriquez Muñoz
 * @since 1.0
 */
public class Spear extends AbstractItem {

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
  public Spear(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }
  public void equippedHero(Hero hero) {this.equipTo(hero);}
  public void getAttackedByAxe(IEquipableItem item){item.strongAttackTo(this.getOwner());}
  public void getAttackedBySword(IEquipableItem item){item.weakAttackTo(this.getOwner());}


}
