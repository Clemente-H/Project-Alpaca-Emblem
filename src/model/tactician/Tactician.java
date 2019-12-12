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
    private String name;
    private Field map;
    private final List<IUnit> Units = new ArrayList<>();
    private int SelectedUnit;
    private IEquipableItem selectedItem;
    private IFactory factory;
    private boolean LifeState = true;


    public Tactician(String name, Field map,  int SelectedUnit, IEquipableItem selectedItem,  IUnit... Units){
        this.name = name;
        this.map = map;
        this.SelectedUnit = SelectedUnit;
        this.selectedItem = selectedItem;
        this.Units.addAll(Arrays.asList(Units).subList(0, Units.length));


    }



    public void setName(String name1){
        this.name = name1 ;
    }

    public String getName(){
        return this.name;
    }

    public Field getMap(){return this.map;}

    public IUnit getSelectedUnit(){return this.Units.get(SelectedUnit);}

    public void setSelectedUnit(IUnit unit){this.SelectedUnit = Units.indexOf(unit);}

    public void setSelectedUnit(Location location){this.SelectedUnit = Units.indexOf(location.getUnit());}

    public IEquipableItem getSelectedItem(){return this.selectedItem;}

    public void setSelectedItem(IEquipableItem Item){this.selectedItem.equals(Item);}

    public void killTactician(){this.LifeState = false;}

    public void moveUnit(Location location){
        this.getSelectedUnit().moveTo(location);
    }


    public void addUnit (int HitPoints, int movement, int x, int y ){
        IUnit unit = factory.create(HitPoints,movement,this.getMap(),x,y);
        this.Units.add(unit);

    }

    public void changeFactor(IFactory factory){this.factory = factory;}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        evt.getNewValue();
    }


    public void StillAlive(Hero Unit){
        if (Unit.getCurrentHitPoints()==(0)){
            this.killTactician();
        }
    }
}
