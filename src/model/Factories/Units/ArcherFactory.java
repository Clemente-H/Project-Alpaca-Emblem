package model.Factories.Units;

import model.map.Field;
import model.units.Archer;
import model.units.IUnit;

public class ArcherFactory implements IFactory {
    @Override
    public IUnit create(int Hitpoints, int movement, Field map, int x, int y) {
        return new Archer(Hitpoints,movement,map.getCell(x, y));
    }
}
