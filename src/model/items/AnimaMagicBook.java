package model.items;

import model.units.Sorcerer;

public class AnimaMagicBook extends AbstractMagicBook {
    /**
     * Creates a new AnimaMagicBook item
     *
     * @param name
     *     the name of the AnimaMagicBook
     * @param power
     *     the damage of the AnimaMagicBook
     * @param minRange
     *     the minimum range of the AnimaMagicBook
     * @param maxRange
     *     the maximum range of the AnimaMagicBook
     */
    public AnimaMagicBook(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    public void getAttackedByDarknessMagicBook(IEquipableItem item){item.strongAttackTo(this.getOwner());}
    public void getAttackedByLightMagicBook(IEquipableItem item){item.weakAttackTo(this.getOwner());}


}
