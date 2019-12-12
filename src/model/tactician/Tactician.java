package model.Tactician;
import Controller.GameController;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

import model.Factories.Units.*;

import model.items.IEquipableItem;
import model.map.Location;
import model.units.AbstractUnit;
import model.map.Field;
import model.units.Hero;
import model.units.IUnit;

import javax.lang.model.element.Name;
import javax.swing.*;

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
     */
public class Tactician implements PropertyChangeListener {
    private GameController controller;
    private String name;
    private Field map;
    private final List<IUnit> Units = new ArrayList<>();
    private int SelectedUnit;
    private IEquipableItem selectedItem;
    private IFactory factory;
    private boolean LifeState = true;


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
     returns a list of the units of an tactician
     */
    public List<IUnit> getUnits(){return Units;}

    /**
     @author Clemente Henriquez
     returns the selected Unit of the tactician
     */
    public IUnit getSelectedUnit(){return this.Units.get(SelectedUnit);}


    /**
     @author Clemente Henriquez
     changes the selected unit
     @param unit
     the unit that now is selected
     */
    public void setSelectedUnit(IUnit unit){this.SelectedUnit = Units.indexOf(unit);}

    /**
     @author Clemente Henriquez
     changes the selected unit
     @param location
     the location of the unit that now is selected
     */
    public void setSelectedUnit(Location location){this.SelectedUnit = Units.indexOf(location.getUnit());}

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
    public void setSelectedItem(IEquipableItem Item){this.selectedItem.equals(Item);}

    /**
     @author Clemente Henriquez
     sets the lifestate of a tactician
     */
    public void killTactician(){this.LifeState = false;}


    /**
     @author Clemente Henriquez
     moves a unit to a location
     @param location
     the location where the unit will be
     */
    public void moveUnit(Location location){
        this.getSelectedUnit().moveTo(location);
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
        IUnit unit = factory.create(HitPoints,movement,this.getMap(),x,y);
        this.Units.add(unit);

    }

    /**
     @author Clemente Henriquez
     changes the tipe of unit that is in the factory
     @param factory
     the type of factory that is created
     */

    public void changeFactory(IFactory factory){this.factory = factory;}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        evt.getNewValue();
    }

}
