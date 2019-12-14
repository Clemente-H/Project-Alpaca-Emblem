package model.Tactician;
import Controller.GameController;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

import model.Factories.Units.*;

import model.items.IEquipableItem;
import model.map.Location;
import model.map.Field;
import model.units.IUnit;

import static java.lang.Math.min;

/**
 * @author Ignacio Slater Muñoz
 * @since
    /**
     * Creates a new Tactician.
     * <p>
     * Tacticians are
     * one.
 *@Param name
 *  of the tactician
 * @Param map
 * the field where the game will be played
 * @Param Units
 * the units that the tactician has
 * @Param SelectedUnit
 * the unit that will do or receive an action
 * @Param SelectedItem
 * the item from any of it's units that
 * @Param MovedUnits
 * a list that shows the units that already move in a turn
 *
     */
public class Tactician implements PropertyChangeListener {
    private GameController controller;
    private String name;
    private Field map;
    private List<IUnit> Units = new ArrayList<>();
    private List<IUnit> MovedUnits = new ArrayList<>();
    private IUnit SelectedUnit;
    private IEquipableItem selectedItem;
    private IFactory factory;
    private boolean LifeState = true;
    private Tactician tacticianPlaying ;


    public Tactician(String name, Field map, GameController controller){
        this.name = name;
        this.map = map;
        this.controller = controller;


    }


    /**
     @author Clemente Henriquez
     sets the name of a tactician
     @param name1
     the name that will be given
     */
    public void setName(String name1){
        this.name = name1 ;
    }

    /**
     @author Clemente Henriquez
     returns the name of a tactician
     */
    public String getName(){
        return this.name;
    }

    /**
     @author Clemente Henriquez
     returns the game map of a tactician
     */
    public Field getMap(){return this.map;}

    /**
     @author Clemente Henriquez
     sets the game map of a tactician
     */
    public void setMap(Field field){this.map= field;}

    /**
     @author Clemente Henriquez
     returns a list of the units of an tactician
     */
    public List<IUnit> getUnits(){return Units;}

    /**
     @author Clemente Henriquez
     returns the selected Unit of the tactician
     */
    public IUnit getSelectedUnit(){return this.SelectedUnit;}

    /**
     @author Clemente Henriquez
     changes the selected unit
     @param unit
     the unit that now is selected
     */
    public void setSelectedUnit(IUnit unit){this.SelectedUnit = unit;}
    /**
     @author Clemente Henriquez
     changes the selected unit
     @param location
     the location of the unit that now is selected
     */
    public void setSelectedUnit(Location location){
        if(Units.contains(location.getUnit())==true){
            this.SelectedUnit=location.getUnit();}
    }

    /**
     @author Clemente Henriquez
     returns the selected Item
     */
    public IEquipableItem getSelectedItem(){return this.selectedItem;}

    /**
     @author Clemente Henriquez
     changes the selected Item
     @param Item
     the item that now is selected
     */
    public void setSelectedItem(IEquipableItem Item){this.selectedItem = Item;}

    /**
     @author Clemente Henriquez
     equips the selected Item to the selected Unit
     */
    public void equipSelectedItem(){this.getSelectedUnit().equipItem(this.getSelectedItem());}
    /**
     @author Clemente Henriquez
     sets the lifestate of a tactician
     */

    public void killTactician(){
        Units.clear();
        this.LifeState = false;}

    /**
     @author Clemente Henriquez
     gets the lifestate of a tactician
     */
    public Boolean getLifeStateTactician(){
        return this.LifeState;
    }


    /**
     @author Clemente Henriquez
     moves a unit to a location
     @param location
     the location where the unit will be
     */
    public void moveUnit(Location location){
        if(!MovedUnits.contains(this.getSelectedUnit()) ) {
            if(this.SelectedUnit.getLocation().distanceTo(location)<=this.SelectedUnit.getMovement()){
                if (location.getUnit() ==null) {
                    this.getSelectedUnit().moveTo(location);
                    MovedUnits.add(this.getSelectedUnit());

                }
            }
        }
    }

    /**
     @author Clemente Henriquez
     creates and adds a unit to the tactician
     @param HitPoints
     the hitpoints of the unit
     @param movement
     the movement of the unit
     @param x
     the horizontal coordinate
     @param y
     the vertical coordinate
     */
    public void addUnit (int HitPoints, int movement, int x, int y ){
        IUnit unit = this.getFactory().create(HitPoints,movement,this.getMap(),x,y);
        this.Units.add(unit);
        this.SelectedUnit = unit;

    }

    /**
     @author Clemente Henriquez
     changes the tipe of unit that is in the factory
     @param factory
     the type of factory that is created
     */

    public void changeFactory(IFactory factory){this.factory = factory;}

    /**
     @author Clemente Henriquez
     this methods is implemented for some tests
     @return
     it should return the factory of the Tactician
     */
    public IFactory getFactory(){return this.factory;}



    /**
    @author Clemente Henriquez
     adds an Item that is already created to the unit
    **/
    public void addItem(IEquipableItem item, IUnit unit){
        unit.setItems(item);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        evt.getNewValue();
    }


    /**
     @author Clemente Henriquez
     @return
     shows the units that were already moved in a turn
     **/
    public List<IUnit> getMovedUnits(){return MovedUnits;}

    /**
     @author Clemente Henriquez
     When the turn is finished, the list of unitsMoveds gets cleared
     **/
    public void clearMovedUnits(){ MovedUnits.clear();}

    /**
     @author Clemente Henriquez
     @return
     the current hitpoints of the selected Unit
     **/
    public int HitPoinsSelectedUnit(){ return this.getSelectedUnit().getCurrentHitPoints();}

    /**
     @author Clemente Henriquez
     @return
     the current location of the selected Unit
     **/
    public Location locationSelectedUnit(){return this.getSelectedUnit().getLocation();}

    /**
     @author Clemente Henriquez
     @return
     the current movement of the selected Unit
     **/
    public int movementSelectedUnit(){return this.getSelectedUnit().getMovement();}

    /**
     @author Clemente Henriquez
     @return
     the current items of the selected Unit
     **/
    public List<IEquipableItem> ItemsSelectedUnit(){return this.getSelectedUnit().getItems();}

    /**
     @author Clemente Henriquez
     @return an String that says the type of a unit.
     This wont be used for ohters methods.
     This is only used as a help, for the tactician to know better its units.
     and to make it easier to play the game
     **/
    public String typeSelectedUnit(){return this.getSelectedUnit().type();}


    /**
     @author Clemente Henriquez
     @return
     the name of the selected item
     **/
    public String nameSelectedItem(){return this.selectedItem.getName();}
    /**
     @author Clemente Henriquez
     @return
     the power of the selected item
     **/
    public int powerSelectedItem(){return this.selectedItem.getPower(); }
    /**
     @author Clemente Henriquez
     @return
     the min Range of the selected item
     **/
    public int minRangeSelectedItem(){return this.selectedItem.getMinRange();}
    /**
     @author Clemente Henriquez
     @return
     the max Range of the selectedItem;
     **/
    public int maxRangeSelectedItem(){return this.selectedItem.getMaxRange();}


}
