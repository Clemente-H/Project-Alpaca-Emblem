package model.Factories.Units;
import model.map.Field;
import model.units.IUnit;
public interface IFactory {
    IUnit create(int Hitpoints, int movement, Field map, int x, int y);
}
