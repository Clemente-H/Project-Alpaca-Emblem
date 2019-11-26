package model.Tactician;
import Controller.GameController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observer;
import model.units.AbstractUnit;
import model.map.Field;
import model.units.IUnit;

import javax.lang.model.element.Name;

import static java.lang.Math.min;

public class Tactician  {
    private ModuleLayer.Controller Controller;
    private String name;
    private Field Field;
    private final List<IUnit> Units = new ArrayList<>();
    private int SelectedUnit;

    public Tactician(final ModuleLayer.Controller Controller, final String name, final Field Field, final int SelectedUnit, final IUnit... Units){
        this.Controller = Controller;
        this.name = name;
        this.Field = Field;
        this.SelectedUnit = SelectedUnit;
        this.Units.addAll(Arrays.asList(Units).subList(0, Units.length));

    }




    public void setName(String name1){
        this.name = name1 ;
    }

    public String getName(){
        return this.name;
    }

    public IUnit getSelectedUnit(){return this.Units.get(SelectedUnit);}



    private ModuleLayer.Controller getControl(){
        return this.Controller;
    }
    public void setController(ModuleLayer.Controller controller) {
        this.Controller=controller;
    }

    public ModuleLayer.Controller getController() {
        return Controller;
    }
    public void addUnit (IUnit unit){this.Units.add(unit);}

}
