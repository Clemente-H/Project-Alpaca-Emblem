/**
 * Test set for the Archer unit.
 *
 * @author Clemente Henriquez Muñoz
 * @since 1.0
 */
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.items.Axe;
import model.units.AbstractTestUnit;
import model.units.Alpaca;
import model.units.IUnit;
import model.units.Sorcerer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SorcererTest extends AbstractTestUnit {

    private Sorcerer sorcerer;

    /**
     * Set up the main unit that's going to be tested in the test set
     */
    @Override
    public void setTestUnit() {sorcerer = new Sorcerer(50, 2, field.getCell(0, 0));    }

    @Override
    public IUnit getTestUnit() {
        return null;
    }
/*
    @override
    public void equipItemtestUnit() {sorcerer = new sorcerer(50, 2, field.getCell(0, 0));
    lightMagicBook = new LightMagicBook("SoulArrow", 40, 1,2);
    sword = new Sword("Espada de Artorias", 40,1,2);
    sorecerer.setItems(lightmagicBook);
    assertTrue(sorcerer.items.contains(lighMagicBook));
    assertNull(sorcerer.items.contains(sword));
    sorcerer.setEquipedItem(lightMagicBook);
    assertEquals(sorcerer.getEquipedItem());
    }

    /**
     * @return the current unit being tested
     */
/*
    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }

    /**
     * Checks if the bow is equipped correctly to the unit
     */
/*
    @Test
    @Override
    public void equipLightMagicBookTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(lightmagicBook);
        assertEquals(lightMagicBook, sorcerer.getEquippedItem());
    }
    */

    @Test
    public void testType(){
        axe = new Axe("",1,1,1);
        sorcerer = new Sorcerer(1,1,null,axe);
        assertEquals(sorcerer.type(),"Sorcerer");
    }
}