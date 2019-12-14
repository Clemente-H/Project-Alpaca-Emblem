package model.Factories.Units;

import model.Tactician.Tactician;
import model.map.Field;
import model.map.Location;
import model.units.Cleric;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClericFactoryTest {
    private Field field;
    @Test
    public void TestClericFactory() {
        field = new Field();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.field.addCells(false, new Location(i, j));
            }
        }
        Tactician tactician1 = new Tactician("", field, null);
        Tactician tactician2 = new Tactician("", field, null);
        ClericFactory ClericFactory = new ClericFactory();
        tactician1.changeFactory(ClericFactory);
        tactician1.addUnit(100, 1, 0, 0);
        assertTrue(tactician1.getSelectedUnit() instanceof Cleric);
        tactician2.changeFactory(ClericFactory);
        tactician2.addUnit(100, 1, 3, 3);
        assertEquals(tactician2.getUnits().get(0), tactician2.getSelectedUnit());
    }
}