package model.items;

import model.units.*;

/**
 * This interface represents the <i>weapons</i> that the units of the game can use.
 * <p>
 * The signature for all the common methods of the weapons are defined here. Every weapon have a
 * base damage and is strong or weak against other type of weapons.
 *
 * @author Ignacio Slater Muñoz
 * @subauthor Clemente Henriquez Muñoz
 * @since 1.0
 */
public interface IEquipableItem {

  /**
   * Equips this item to a unit.
   *
   * @param unit
   *     the unit that will be quipped with the item
   */
  void equipTo(IUnit unit);

  /**
   * @return the unit that has currently equipped this item
   */
  IUnit getOwner();

  /**
   * @return the name of the item
   */
  String getName();

  /**
   * @return the power of the item
   */
  int getPower();

  /**
   * @return the minimum range of the item
   */
  int getMinRange();

  /**
   * @return the maximum range of the item
   */
  int getMaxRange();
  void equipedAlpaca(Alpaca alpaca);
  /*/*
  @author Clemente Henriquez
  gives an item to an Alpaca, in the future it wont do anything
   */

  void equipedHero(Hero hero);
  /*/*
  @author Clemente Henriquez
  equips an Item to a Hero Unit
   */
  void equipedArcher(Archer archer);
  /*/*
  @author Clemente Henriquez
  equips an Item to an Archer
   */
  void equipedCleric(Cleric cleric);
  /*/*
  @author Clemente Henriquez
  equips an item to a cleric
   */


  void equipedFighter(Fighter fighter);
  /*/*
  @author Clemente Henriquez
 equips an item to a Fighter
   */
  void equipedSorcerer(Sorcerer sorcerer);
  /*/*
  @author Clemente Henriquez
 equips an item to a Sorcerer
   */
  void equipedSwordMaster(SwordMaster swordMaster);
  /*/*
  @author Clemente Henriquez
 equips an item to a SwordMaster
   */
  void weakAttackTo(IUnit unit);
  /*
  @author Clemente Henriquez

      makes the power of the item minus 20 points of damage to other Unit
          */
  void strongAttackTo(IUnit unit);
  /*
  @author Clemente Henriquez
   makes 1.5 times the damage of the item to an Unit

   */

  void normalAttackTo(IUnit unit);
  /*/*
  @author Clemente Henriquez
  this attack makes the damage of the weapon's power to an Unit.
   */


  /**void swordAttack(Sword sword);
  void animaMagicBookAttack(AnimaMagicBook animaMagicBook);
  void axeAttack(Axe axe);
  void bowAttack(Bow bow);
  void darknessMagicBookAttack(DarknessMagicBook darknessMagicBook);
  void lightMagicBookAttack(LightMagicBook lightMagicBook);
  void spearAttack(Spear spear);
  void staffAttack(Staff staff);
  void alpacaAttack(Alpaca alpaca);
   **/
  void getAttackedByAxe(IEquipableItem item);
  /*/*
  @author Clemente Henriquez
  An unit is attacked by an axe
   */

  void getAttackedBySword(IEquipableItem item);
  /*/*
  @author Clemente Henriquez
  An unit is attacked by a sword
   */

  void getAttackedByBow(IEquipableItem item);
  /*/*
  @author Clemente Henriquez
  An unit is attacked by a bow
   */
  void getAttackedBySpear(IEquipableItem item);
  /*/*
  @author Clemente Henriquez
  An unit is attacked by a spear
   */
  void getAttackedByStaff(IEquipableItem item);
  /*/*
  @author Clemente Henriquez
  does nothing
   */
  void getAttackedByMagicBook(IEquipableItem item);
  /*/*
  @author Clemente Henriquez
  An unit is attacked by a magicbook
   */
  void getAttackedByDarknessMagicBook(IEquipableItem item);
  /*/*
  @author Clemente Henriquez
  An unit is attacked by a darkmagicbook
   */
  void getAttackedByLightMagicBook(IEquipableItem item);
  /*/*
  @author Clemente Henriquez
  An unit is attacked by a lighmagicbook
   */
  void getAttackedByAnimaMagicBook(IEquipableItem item);
  /*/*
  @author Clemente Henriquez
  An unit is attacked by an animaMagibook
   */
}
