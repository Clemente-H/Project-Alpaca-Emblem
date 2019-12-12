package model.Factories.Units;

import model.map.Field;
import model.units.Alpaca;
import model.units.IUnit;

public class AlpacaFactory implements IFactory{
    @Override
    public IUnit create(int Hitpoints, int movement, Field map, int x, int y) {
        return new Alpaca(Hitpoints,movement,map.getCell(x, y));
    }
}
