package model.units;

import java.util.List;

import model.Tactician.Tactician;
import model.items.IEquipableItem;
import model.map.Location;

/**
 * This interface represents all units in the game.
 * <p>
 * The signature of all the common methods that a unit can execute are defined here. All units
 * except some special ones can carry at most 3 weapons.
 *
 * @author Ignacio Slater Muñoz
 * @Subautor Clemente Henriquez Muñoz
 * @since 1.0
 */
public interface IUnit{

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  void equipItem(IEquipableItem item);

  /**
   * @return hit points of the unit
   */
  int getCurrentHitPoints();

  /**
   * @return the items carried by this unit
   */

  int getMaxHitPoints();
  /**
   * @return the maxHitpoints of this unit
   */

  Tactician getTactician();
    /**
     * @return the tactician of this unit
     */


  List<IEquipableItem> getItems();

  /**
   * @return the currently equipped item
   */
  IEquipableItem getEquippedItem();

  /**
   * @param item
   *     the item to be equipped
   */
  void setEquippedItem(IEquipableItem item);

  /**
   * @return the current location of the unit
   */
  Location getLocation();

  /**
   * Sets a new location for this unit,
   */
  void setLocation(final Location location);

  /**
   * @return the number of cells this unit can move
   */
  int getMovement();
  /**
   * Moves this unit to another location.
   * <p>
   * If the other location is out of this unit's movement range, the unit doesn't move.
   */
  int getMaxItems();
  /**@author Clemente Henriquez Muñoz
   * @return number of maxItems that an Unit can have
   */

  void moveTo(Location targetLocation);
  /**
   * @return the final location of the unit
   */
  void setCurrentHitPoints(int hitPoints);
  /**
   * @author Clemente Henriquez
   * sets the current hitpoints
   */

  /*
    @author Clemente Henriquez
    combat is a method that given 2 units, sets the conditions for the combat to begin,
    after this, it activates the attacks of the units(in case that each unit CAN attack)

     */

  void Combat(IUnit unit2);


  /*
  @author Clemente Henriquez
   * Heal, is a method that given a unit restores hitpoints
   this method will only work if it's called by a Cleric
   *
   * */


  void heal(IUnit unit);
  /*
    @author Clemente Henriquez
    select an item from the list of items of the unit

     */

  void selectItem(int i);
    /*
   @author Clemente Henriquez
   trade is a method that given an units and a item, sets the conditions for an exchange of items to be accomplished.

    */

  void trade(IEquipableItem item, IUnit unit2);

  IEquipableItem getSelectedItem();

  /*
   @author Clemente Henriquez
   returns the item selected from the list of items

    */
  void attack(IUnit unit);
 /*@author Clemente Henriquez
  attack is an abstract method that given a units, activates an attack in other unit.
  this is to make sure that the unit accomplishes the condition of its weapon.



*/
  void setItems(IEquipableItem item);
  /*adds an item to the list of items from an Unit*/

    void attackedAlpaca(IUnit unit);

}
