package model.Factories.Units;

import model.map.Field;
import model.units.Cleric;
import model.units.IUnit;

public class ClericFactory implements IFactory {
    @Override
    public IUnit create(int Hitpoints, int movement, Field map, int x, int y) {
        return new Cleric(Hitpoints,movement,map.getCell(x, y));
    }
}
