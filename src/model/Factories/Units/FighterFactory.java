package model.Factories.Units;

import model.map.Field;
import model.units.Fighter;
import model.units.IUnit;

public class FighterFactory implements IFactory {
    @Override
    public IUnit create(int Hitpoints, int movement, Field map, int x, int y) {
        return new Fighter(Hitpoints,movement,map.getCell(x, y));
    }
}
