package model.Factories.Units;

import model.Tactician.Tactician;
import model.map.Field;
import model.map.Location;
import model.units.Fighter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FighterFactoryTest {
    private Field field;
    @Test
    public void TestFighterFactory() {
        field = new Field();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.field.addCells(false, new Location(i, j));
            }
        }
        Tactician tactician1 = new Tactician("", field, null);
        Tactician tactician2 = new Tactician("", field, null);
        FighterFactory FighterFactory = new FighterFactory();
        tactician1.changeFactory(FighterFactory);
        tactician1.addUnit(100, 1, 0, 0);
        assertTrue(tactician1.getSelectedUnit() instanceof Fighter);
        tactician2.changeFactory(FighterFactory);
        tactician2.addUnit(100, 1, 3, 3);
        assertEquals(tactician2.getUnits().get(0), tactician2.getSelectedUnit());
    }
}