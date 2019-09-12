package model.items;

import model.units.*;

/**
 * Abstract class that defines some common information and behaviour between all items.
 *
 * @author Ignacio Slater Muñoz
 * @Subauthor Clemente Henriquez Munoz
 * @since 1.0
 */
public abstract class AbstractItem implements IEquipableItem {

  private  String name;
  private  int power;
  protected int maxRange;
  protected int minRange;
  private IUnit owner;

  /**
   * Constructor for a default item without any special behaviour.
   *
   * @param name
   *     the name of the item
   * @param power
   *     the power of the item (this could be the amount of damage or healing the item does)
   * @param minRange
   *     the minimum range of the item
   * @param maxRange
   *     the maximum range of the item
   */
  public AbstractItem(final String name, final int power, final int minRange, final int maxRange) {
    this.name = name;
    this.power = power;
    this.minRange = Math.max(minRange, 1);
    this.maxRange = Math.max(maxRange, this.minRange);
  }

  protected AbstractItem() {
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



  @Override
  public void equipedAlpaca(Alpaca alpaca) {}
  @Override
  public void equipedHero(Hero hero) {}
  @Override
  public void equipedArcher(Archer archer){}
  @Override
  public void equipedCleric(Cleric cleric){}
  @Override
  public void equipedFighter(Fighter fighter){}
  @Override
  public void equipedSorcerer(Sorcerer sorcerer){}
  @Override
  public void equipedSwordMaster(SwordMaster swordMaster){}
  @Override
  /**public void swordAttack(Sword sword){sword.normalAttackTo(this.owner);}
  @Override
  public void animaMagicBookAttack(AnimaMagicBook animaMagicBook){animaMagicBook.strongAttackTo(this.getOwner());}
  @Override
  public void axeAttack(Axe axe){this.normalAttackTo(axe.getOwner());}
  @Override
  public void bowAttack(Bow bow){this.normalAttackTo(bow.getOwner());}
  @Override
  public void darknessMagicBookAttack(DarknessMagicBook darknessMagicBook){darknessMagicBook.strongAttackTo(this.getOwner());}
  @Override
  public void lightMagicBookAttack(LightMagicBook lightMagicBook){lightMagicBook.strongAttackTo(this.getOwner());}
  @Override
  public void spearAttack(Spear spear){this.normalAttackTo(spear.getOwner());}
  @Override
  public void staffAttack(Staff staff){this.normalAttackTo(staff.getOwner());}
  @Override
  public void alpacaAttack(Alpaca alpaca){this.normalAttackTo(alpaca);}
   **/

  public void getAttackedByAxe(IEquipableItem item){item.normalAttackTo(this.getOwner());}
  @Override
  public void getAttackedBySword(IEquipableItem item){item.normalAttackTo(this.getOwner());}
  @Override
  public void getAttackedByBow(IEquipableItem item){item.normalAttackTo(this.getOwner());};
  @Override
  public void getAttackedBySpear(IEquipableItem item){item.normalAttackTo(this.getOwner());};
  @Override
  public void getAttackedByStaff(IEquipableItem item){item.normalAttackTo(this.getOwner());};
  @Override
  public void getAttackedByDarknessMagicBook(IEquipableItem item){item.strongAttackTo(this.getOwner());}
  @Override
  public void getAttackedByMagicBook(IEquipableItem item){item.strongAttackTo(this.getOwner());}
  @Override
  public void getAttackedByLightMagicBook(IEquipableItem item){item.strongAttackTo(this.getOwner());}
  @Override
  public void getAttackedByAnimaMagicBook(IEquipableItem item){item.strongAttackTo(this.getOwner());}

  /*
  /*
  @author Clemente Henriquez
  here, the attacks are described. There are three of them
  The first one in weakAttackto. As its name says, it's a weak attack to a unit
  (makes the power of the item minus 20 points of damage)
   */

  public void weakAttackTo(IUnit unit){
    if((this.getPower()-20)<0){
    }
    else{
      if(unit.getCurrentHitPoints()-((this.getPower()-20))<=0) {
        unit.setCurrentHitPoints(0);
      }
      else{
        unit.setCurrentHitPoints(unit.getCurrentHitPoints() - (this.getPower()-20));
      }
    }
  }
  /*
  @author Clemente Henriquez
  The second attack is strongAttackto, it bassicly the same that the previous attack, but this time
  it's a strong attack(makes 1.5 times the damage of the item)

   */

  public void strongAttackTo(IUnit unit){
    if((this.getPower())<0){
    }
    else{
      if(unit.getCurrentHitPoints()-(this.getPower()*1.5)<=0) {
        unit.setCurrentHitPoints(0);
      }
      else{
        unit.setCurrentHitPoints((int)unit.getCurrentHitPoints() - (this.getPower()*(3/2)));
      }
    }
  }

  /*/*
  @author Clemente Henriquez
  And finally the third attack is normal attack, this attack makes the damage of the weapon's power to an Unit.
   */

  public void normalAttackTo(IUnit unit){
    if((this.getPower())<0){
  }
  else{
    if(unit.getCurrentHitPoints()-(this.getPower())<=0) {
      unit.setCurrentHitPoints(0);
    }
    else{
      unit.setCurrentHitPoints((unit.getCurrentHitPoints() - (this.getPower())));
    }
  }
  }
}