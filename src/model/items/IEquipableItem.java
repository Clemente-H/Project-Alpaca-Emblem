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

  /**
  @author Clemente Henriquez
  equips an Item to a Hero Unit
   @param hero
   the hero that will equip a weapon
   */

  void equippedHero(Hero hero);
  /**
  @author Clemente Henriquez
  equips an Item to an Archer
   @param archer
   the archer that will get an item
   */
  void equippedArcher(Archer archer);
  /**
  @author Clemente Henriquez
  equips an item to a cleric
   @param cleric
   the cleric that will be equipped
   */
  void equippedCleric(Cleric cleric);

  /**
  @author Clemente Henriquez
 equips an item to a Fighter
   @param fighter
   the fighter that will be equipped
   */
  void equippedFighter(Fighter fighter);
  /**
  @author Clemente Henriquez
  equips an item to a Sorcerer
   @param sorcerer
   the sorcerer that will be equipped
   */
  void equippedSorcerer(Sorcerer sorcerer);

   /**
  @author Clemente Henriquez
 equips an item to a SwordMaster
    @param swordMaster
    the swordmaster that will be equipped
   */

  void equippedSwordMaster(SwordMaster swordMaster);

  /**
   @author Clemente Henriquez
       makes the power of the item minus 20 points of damage to other Unit
   @param unit
   the unit that will be attacked
           */
  void weakAttackTo(IUnit unit);

  /**
  @author Clemente Henriquez
   makes 1.5 times the damage of the item to an Unit
  @param unit
   the unit that will be attacked
   */
  void strongAttackTo(IUnit unit);

  /**
  @author Clemente Henriquez
  this attack makes the damage of the weapon's power to an Unit.
   @param unit
   the unit that will be attacked
   */
  void normalAttackTo(IUnit unit);

  /**
    @author Clemente Henriquez
    An unit is attacked by an axe
   @param item
   the item that an unit's has equipped, this is to check if the attack is strong weak or normal
     */
  void getAttackedByAxe(IEquipableItem item);

  /**
   @author Clemente Henriquez
   An unit is attacked by an sword
   @param item
   the item that an unit's has equipped, this is to check if the attack is strong weak or normal
   */

  void getAttackedBySword(IEquipableItem item);

  /**
   @author Clemente Henriquez
   An unit is attacked by an bow
   @param item
   the item that an unit's has equipped, this is to check if the attack is strong weak or normal
   */
  void getAttackedByBow(IEquipableItem item);

  /**
   @author Clemente Henriquez
   An unit is attacked by an spear
   @param item
   the item that an unit's has equipped, this is to check if the attack is strong weak or normal
   */
  void getAttackedBySpear(IEquipableItem item);

  /**
   @author Clemente Henriquez
   An unit is attacked by an staff, does nothing
   @param item
   the item that an unit's has equipped, this is to check if the attack is strong weak or normal
   */
  void getAttackedByStaff(IEquipableItem item);


  /**
   @author Clemente Henriquez
   An unit is attacked by an magicBook
   @param item
   the item that an unit's has equipped, this is to check if the attack is strong weak or normal
   */
  void getAttackedByMagicBook(IEquipableItem item);

  /**
   @author Clemente Henriquez
   An unit is attacked by an DarknessMagicBook
   @param item
   the item that an unit's has equipped, this is to check if the attack is strong weak or normal
   */
  void getAttackedByDarknessMagicBook(IEquipableItem item);

  /**
   @author Clemente Henriquez
   An unit is attacked by an light magic book
   @param item
   the item that an unit's has equipped, this is to check if the attack is strong weak or normal
   */
  void getAttackedByLightMagicBook(IEquipableItem item);


  /**
   @author Clemente Henriquez
   An unit is attacked by an anima magic book
   @param item
   the item that an unit's has equipped, this is to check if the attack is strong weak or normal
   */
  void getAttackedByAnimaMagicBook(IEquipableItem item);


  /**
   @author Clemente Henriquez
   an alpaca is attacked
   @param alpaca
   the alpaca that will recieve the atack
   */
  void attackAlpaca(Alpaca alpaca);
}
