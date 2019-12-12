package model.Factories.Units;

import model.map.Field;
import model.units.Sorcerer;
import model.units.IUnit;

public class SorcererFactory implements IFactory {
    @Override
    public IUnit create(int Hitpoints, int movement, Field map, int x, int y) {
        return new Sorcerer(Hitpoints,movement,map.getCell(x, y));
    }
}
