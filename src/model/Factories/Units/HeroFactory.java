package model.Factories.Units;

import model.map.Field;
import model.units.Hero;
import model.units.IUnit;

public class HeroFactory implements IFactory {
    @Override
    public IUnit create(int Hitpoints, int movement, Field map, int x, int y) {
        return new Hero(Hitpoints,movement,map.getCell(x, y));
    }
}
