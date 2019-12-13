package model.units;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import model.Tactician.Tactician;
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

 * @since 1.0
 */
public abstract class AbstractUnit extends Observable implements IUnit{

  protected List<IEquipableItem> items = new ArrayList<>();
  private int currentHitPoints;
  private final int movement;
  protected IEquipableItem equippedItem;
  private Location location;
  private final int maxHitpoints;
  private final int maxItems;
  private int selectedItem;

  /**
   * Creates a new Unit.
   *  @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   * @param location
 *     the current position of this unit on the map
   * @param maxItems
   */
  protected AbstractUnit(final int hitPoints, final int movement,
                         final Location location, final int maxItems,final IEquipableItem... items) {
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
    this.currentHitPoints = hitPoints;
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
  @Override
  public IEquipableItem selectItem(int i){if(i<this.maxItems){return this.items.get(i);}
    return null;
  }

@Override
  public void attack(IUnit unit){};
  @Override
  public void attackedAlpaca(IUnit unit){};
  public void Combat(IUnit unit2) {
    if(this.getLocation().distanceTo(unit2.getLocation()) <= this.getEquippedItem().getMaxRange() &&
            this.getLocation().distanceTo(unit2.getLocation())>= this.getEquippedItem().getMinRange()) {
        if(this.getCurrentHitPoints()>0 && unit2.getCurrentHitPoints()>0) {
          this.attack(unit2);
          unit2.attackedAlpaca(this);
          if(unit2.getCurrentHitPoints()> 0 && unit2.getEquippedItem()!=null){
            if(unit2.getLocation().distanceTo(this.getLocation()) <=unit2.getEquippedItem().getMaxRange() && unit2.getLocation().distanceTo(this.getLocation())>=unit2.getEquippedItem().getMinRange()) {
             /*counter*/
              unit2.attack(this);
            }
            }
            }
        }
    }
@Override
  public void trade(IEquipableItem item, IUnit unit2){
    if(unit2.getMaxItems()>unit2.getItems().size()) {
      if (this.getLocation().distanceTo(unit2.getLocation())==1) {
        unit2.setItems(item);
        this.items.remove(item);
      }
    }
  }
  @Override
  public void heal(IUnit unit){
  }
  @Override
  public boolean isHeroAlive(){return true;}
}
