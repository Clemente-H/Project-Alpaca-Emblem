package model.items;

import model.units.Sorcerer;

public class LightMagicBook extends AbstractItem{
        /**
         * Creates a new LightMagicBook item
         *
         * @param name
         *     the name of the LightMagicBook
         * @param power
         *     the damage of the LightMagicBook
         * @param minRange
         *     the minimum range of the LightMagicBook
         * @param maxRange
         *     the maximum range of the LightMagicBook
         */
        public LightMagicBook(final String name, final int power, final int minRange, final int maxRange) {
            super(name, power, minRange, maxRange);
        }


    public void getAttackedByDarknessMagicBook(IEquipableItem item){item.weakAttackTo(this.getOwner());}
    public void getAttackedByAnimaMagicBook(IEquipableItem item){item.strongAttackTo(this.getOwner());}
}
