package model.Factories.Units;

import model.map.Field;
import model.units.SwordMaster;
import model.units.IUnit;

public class SwordMasterFactory implements IFactory {
    @Override
    public IUnit create(int Hitpoints, int movement, Field map, int x, int y) {
        return new SwordMaster(Hitpoints,movement,map.getCell(x, y));
    }

}
