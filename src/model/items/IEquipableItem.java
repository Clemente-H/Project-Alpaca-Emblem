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
  void equipedHero(Hero hero);
  void equipedArcher(Archer archer);
  void equipedCleric(Cleric cleric);
  void equipedFighter(Fighter fighter);
  void equipedSorcerer(Sorcerer sorcerer);
  void equipedSwordMaster(SwordMaster swordMaster);
  void weakAttackTo(IUnit unit);
  void strongAttackTo(IUnit unit);
  void normalAttackTo(IUnit unit);

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
  void getAttackedBySword(IEquipableItem item);
  void getAttackedByBow(IEquipableItem item);
  void getAttackedBySpear(IEquipableItem item);
  void getAttackedByStaff(IEquipableItem item);
  void getAttackedByMagicBook(IEquipableItem item);
  void getAttackedByDarknessMagicBook(IEquipableItem item);
  void getAttackedByLightMagicBook(IEquipableItem item);
  void getAttackedByAnimaMagicBook(IEquipableItem item);
}
