package model.items;

import model.units.*;

public class ItemNull  extends AbstractItem{

    /**
     * This class represents an ItemNull.
     * <p>
     * ItemNulls are strong against spears but weak agains swords.
     *
     * @author Ignacio Slater Muñoz
     *
     * @since 1.0
     */
    /**
         * Creates a new ItemNull item
         *
         * @param name
         *     the name of the ItemNull
         * @param power
         *     the damage of the ItemNull
         * @param minRange
         *     the minimum range of the ItemNull
         * @param maxRange
         *     the maximum range of the ItemNull
         */
        public ItemNull(final String name, final int power, final int minRange, final int maxRange) {
            super(name, 0, minRange, maxRange);
        }
    @Override
    public void equipedAlpaca(Alpaca alpaca) {this.equipTo(alpaca);}
    @Override
    public void equipedHero(Hero hero) {this.equipTo(hero);};
    @Override
    public void equipedArcher(Archer archer){this.equipTo(archer);}
    @Override
    public void equipedCleric(Cleric cleric){this.equipTo(cleric);}
    @Override
    public void equipedFighter(Fighter fighter){this.equipTo(fighter);}
    @Override
    public void equipedSorcerer(Sorcerer sorcerer){this.equipTo(sorcerer);}
    @Override
    public void equipedSwordMaster(SwordMaster swordMaster){this.equipTo(swordMaster);}

    @Override
    public void getAttackedByMagicBook(IEquipableItem item){item.normalAttackTo(this.getOwner());}
    @Override
    public void getAttackedByLightMagicBook(IEquipableItem item){item.normalAttackTo(this.getOwner());}
    @Override
    public void getAttackedByAnimaMagicBook(IEquipableItem item){item.normalAttackTo(this.getOwner());}

}
    

