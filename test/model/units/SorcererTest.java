/**
 * Test set for the Archer unit.
 *
 * @author Clemente Henriquez Muñoz
 * @since 1.0
 */
public class SorcererTest extends AbstractTestUnit {

    private Sorcerer sorcerer;

    /**
     * Set up the main unit that's going to be tested in the test set
     */
    @Override
    public void setTestUnit() {
        sorcerer = new sorcerer(50, 2, field.getCell(0, 0));
    }

    /**
     * @return the current unit being tested
     */
    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }

    /**
     * Checks if the bow is equipped correctly to the unit
     */
    @Test
    @Override
    public void equipLightMagicBookTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(lightmagicBook);
        assertEquals(lightMagicBook, sorcerer.getEquippedItem());
    }
}