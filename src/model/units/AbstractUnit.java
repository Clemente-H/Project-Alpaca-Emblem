package model.units;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents an abstract unit.
 * <p>
 * An abstract unit is a unit that cannot be used in the
 * game, but that contains the implementation of some of the methods that are common for most
 * units.
 *
 * @author Ignacio Slater Muñoz
 * @Subauthor Clemente Henriquez Muñoz
 * @since 1.0
 */
public abstract class AbstractUnit implements IUnit {

  protected final List<IEquipableItem> items = new ArrayList<>();
  private int currentHitPoints;
  private final int movement;
  protected IEquipableItem equippedItem;
  private Location location;
  private final int maxHitpoints;
  private final int maxItems;

  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   * @param location
   *     the current position of this unit on the map
   * @param maxItems
   *     maximum amount of items this unit can carry
   */
  protected AbstractUnit(final int hitPoints, final int movement,
      final Location location, final int maxItems, final IEquipableItem... items) {
    this.currentHitPoints = hitPoints;
    this.movement = movement;
    this.location = location;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
    this.maxHitpoints= hitPoints;
    this.maxItems=maxItems;
  }

  @Override
  public int getCurrentHitPoints() {
    return currentHitPoints;
  }

  @Override
  public List<IEquipableItem> getItems() {
    return List.copyOf(items);
  }

  @Override
  public IEquipableItem getEquippedItem() {
    return equippedItem;
  }

  @Override
  public void setEquippedItem(final IEquipableItem item) {
    this.equippedItem = item;
  }

  @Override
  public void setCurrentHitPoints(int hitPoints){
    this.currentHitPoints= hitPoints;
  }

  @Override
  public Location getLocation() {
    return location;
  }

  @Override
  public int getMaxHitPoints() { return maxHitpoints ;}

  @Override
  public void setLocation(final Location location) {
    this.location = location;
  }

  @Override
  public int getMovement() {
    return movement;
  }

  public int getMaxItems() {
    return maxItems;
  }
  @Override
  public void setItems(IEquipableItem item){
    items.add(item);
  }
  @Override
  public void moveTo(final Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement()
        && targetLocation.getUnit() == null) {
      setLocation(targetLocation);
    }
  }

  public abstract void attack(IUnit unit);
  /*
  @author Clemente Henriquez
  setCurrentHitPoints, change the hitpoints of an Unit, this is done so that when 2 units are fighting, the hitpoints
  change and don't get stucked in time

   */

  /*
    @author Clemente Henriquez
    combat is a method that given 2 units, sets the conditions for the combat to begin,
    after this, it activates the attacks of the units(in case that each unit CAN attack)

     */
  public void Combat(IUnit unit1,IUnit unit2) {
    if(unit2.getLocation().distanceTo(unit1.getLocation()) <=unit1.getEquippedItem().getMaxRange() && unit2.getLocation().distanceTo(unit1.getLocation())>=unit1.getEquippedItem().getMinRange()) {
        if(unit1.getCurrentHitPoints()>0 && unit2.getCurrentHitPoints()>0) {
          if(unit1.getEquippedItem()!= null) {
            unit1.attack(unit2);
            if(unit2.getCurrentHitPoints()> 0 && unit2.getEquippedItem()!=null){
              if(unit2.getLocation().distanceTo(unit1.getLocation()) <=unit2.getEquippedItem().getMaxRange() && unit2.getLocation().distanceTo(unit1.getLocation())>=unit2.getEquippedItem().getMinRange()) {
             /*counter*/
              unit2.attack(unit1);
            }
            }
            }
        }
    }

  }

  /*
  @author Clemente Henriquez
  exchange is a method that given 2 units, sets the conditions for an exchange of items to be accomplished.

   */

  public void exchange(IEquipableItem item, IUnit unit2){
    if(this.items.contains(item)){
      if(unit2.getLocation().distanceTo(item.getOwner().getLocation())==1){
        if(unit2.getItems().size()<unit2.getMaxItems()){
          if(this.equippedItem.equals(item)){
            this.equippedItem.equals(null);
          }
          unit2.setItems(item);
          this.items.remove(item);
        }
      }
    }
  }
  /*
  @author Clemente Henriquez
  attack is an abstract method that given a units, activates an attack in other unit.
  this is to make sure that the unit accomplishes the condition of its weapon.

   */
  public void heal(IUnit unit){

  }

}
