package model.units;

import model.Tactician.Tactician;
import model.items.*;
import model.map.Location;
/**
 * This class represents an <i>Sorcerer</i> type unit.
 * <p>
 * This are an unit that can equip magicbooks, and can use them.
 *
 * @author Ignacio Slater Muñoz
 * @subauthor Clemente Henriquez Muñoz
 * @since 1.0
 */

public class Sorcerer  extends AbstractUnit {
    /**
     * Creates a new Sorcerer
     *
     * @param hitPoints
     *     the amount of damage this unit can receive
     * @param movement
     *     number of cells the unit can move
     * @param location
     *     current position of the unit
     */
    public Sorcerer(final int hitPoints, final int movement, final Location location,
                    final IEquipableItem... items) {
        super(hitPoints, movement, location, 3,items);
    }

    /**
     * Sets the currently equipped item of this unit.
     *
     * @param item
     *     the item to equip
     */
    @Override
    public void equipItem(final IEquipableItem item) {
            item.equippedSorcerer(this);
    }

    /* sets the attack of the unit to other unit.
    */
    public void attack(IUnit unit){
        unit.getEquippedItem().getAttackedByMagicBook(this.getEquippedItem());
    }


    @Override
    public String type(){return "Sorcerer";}
}
