package model.items;

import model.units.*;



public abstract class AbstractMagicBook extends AbstractItem{
    private final String name;
    private final int power;
    protected int maxRange;
    protected int minRange;
    private IUnit owner;


    public AbstractMagicBook(final String name, final int power, final int minRange, final int maxRange) {
        this.name = name;
        this.power = power;
        this.minRange = Math.max(minRange, 1);
        this.maxRange = Math.max(maxRange, this.minRange);
    }

    @Override
    public void equipTo(final IUnit unit) {
        unit.setEquippedItem(this);
        owner = unit;
    }

    @Override
    public IUnit getOwner() {
        return owner;
    }

    @Override
    public String getName() {
        return name;
    }




    @Override
    public int getPower() {
        return power;
    }

    @Override
    public int getMinRange() {
        return minRange;
    }

    @Override
    public int getMaxRange() {
        return maxRange;
    }


    public void equipAlpaca(Alpaca alpaca) {}
    @Override
    public void equipedHero(Hero hero) {}
    @Override
    public void equipedArcher(Archer archer){}
    @Override
    public void equipedCleric(Cleric cleric){}
    @Override
    public void equipedFighter(Fighter fighter){}
    @Override
    public void equipedSorcerer(Sorcerer sorcerer){this.equipTo(sorcerer);}
    @Override
    public void equipedSwordMaster(SwordMaster swordMaster){}
/**
    public void swordAttack(IEquipableItem sword){sword.strongAttackTo(this.getOwner());}
    public void animaMagicBookAttack(IEquipableItem animaMagicBook){animaMagicBook.normalAttackTo(this.getOwner());}
    public void axeAttack(IEquipableItem axe){axe.strongAttackTo(this.getOwner());}

    public void bowAttack(IEquipableItem bow){bow.strongAttackTo(this.getOwner());}
    public void darknessMagicBookAttack(IEquipableItem darknessMagicBook){darknessMagicBook.normalAttackTo(this.getOwner());}

    public void lightMagicBookAttack(IEquipableItem lightMagicBook){lightMagicBook.normalAttackTo(this.getOwner());}
    public void spearAttack(IEquipableItem spear){spear.strongAttackTo(this.getOwner());}

    public void staff(IEquipableItem staff){this.normalAttackTo(staff.getOwner());}

    public void alpacaAttack(IUnit alpaca){this.normalAttackTo(alpaca);}
**/
    @Override
    public void getAttackedByAxe(IEquipableItem item){item.strongAttackTo(this.getOwner());}
    @Override
    public void getAttackedBySword(IEquipableItem item){item.strongAttackTo(this.getOwner());}
    @Override
    public void getAttackedByBow(IEquipableItem item){item.strongAttackTo(this.getOwner());};
    @Override
    public void getAttackedBySpear(IEquipableItem item){item.strongAttackTo(this.getOwner());};
    @Override
    public void getAttackedByStaff(IEquipableItem item){}
    @Override
    public void getAttackedByDarknessMagicBook(IEquipableItem item){item.normalAttackTo(this.getOwner());}
    @Override
    public void getAttackedByLightMagicBook(IEquipableItem item){item.normalAttackTo(this.getOwner());}
    @Override
    public void getAttackedByAnimaMagicBook(IEquipableItem item){item.normalAttackTo(this.getOwner());}

}



