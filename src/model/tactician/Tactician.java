package model.Tactician;
import Controller.GameController;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import model.units.AbstractUnit;
import model.map.Field;
import model.units.IUnit;

public class Tactician implements ITactician {
    private String name;
    private Field Field;
    private final List<IUnit> Unit = new ArrayList<>();
    private int SelectedUnit;






    private ModuleLayer.Controller Controller = new Controller();



}
