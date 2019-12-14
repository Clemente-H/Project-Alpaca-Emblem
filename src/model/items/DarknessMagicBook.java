package model.items;

import model.units.Sorcerer;

public class DarknessMagicBook extends AbstractMagicBook{


    /**
     * Creates a new DarknessMagicBook item
     *
     * @param name
     *     the name of the DarknessMagicBook
     * @param power
     *     the damage of the DarknessMagicBook
     * @param minRange
     *     the minimum range of the DarknessMagicBook
     * @param maxRange
     *     the maximum range of the DarknessMagicBook
     */
    public DarknessMagicBook(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }
    /**
     * Sets weakness and strength to this item
     * in this case darkness is strong against anima, and weak against light
     *
     *
     * */

    public void getAttackedByAnimaMagicBook(IEquipableItem item){item.weakAttackTo(this.getOwner());}
    public void getAttackedByLightMagicBook(IEquipableItem item){item.strongAttackTo(this.getOwner());}
}