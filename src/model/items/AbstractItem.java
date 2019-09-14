package model.items;

import model.units.*;

/**
 * Abstract class that defines some common information and behaviour between all items.
 *
 * @author Ignacio Slater Muñoz
 * @author Clemente Henriquez Munoz
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
  public void equippedHero(Hero hero){};
  @Override
  public void equippedArcher(Archer archer){}
  @Override
  public void equippedCleric(Cleric cleric){}
  @Override
  public void equippedFighter(Fighter fighter){};
  @Override
  public void equippedSorcerer(Sorcerer sorcerer){};
  @Override
  public void equippedSwordMaster(SwordMaster swordMaster){};

  public void attackAlpaca(Alpaca alpaca){this.normalAttackTo(alpaca);}

  public void getAttackedByAxe(IEquipableItem item){item.normalAttackTo(this.getOwner());}
  @Override
  public void getAttackedBySword(IEquipableItem item){item.normalAttackTo(this.getOwner());}
  @Override
  public void getAttackedByBow(IEquipableItem item){item.normalAttackTo(this.getOwner());};
  @Override
  public void getAttackedBySpear(IEquipableItem item){item.normalAttackTo(this.getOwner());};
  @Override
  public void getAttackedByStaff(IEquipableItem item){};
  @Override
  public void getAttackedByDarknessMagicBook(IEquipableItem item){item.strongAttackTo(this.getOwner());}
  @Override
  public void getAttackedByMagicBook(IEquipableItem item){item.strongAttackTo(this.getOwner());}
  @Override
  public void getAttackedByLightMagicBook(IEquipableItem item){item.strongAttackTo(this.getOwner());}
  @Override
  public void getAttackedByAnimaMagicBook(IEquipableItem item){item.strongAttackTo(this.getOwner());}
  @Override
  public void weakAttackTo(IUnit unit){
    if((this.getPower()-20)>0){
      unit.setCurrentHitPoints(Math.max(unit.getCurrentHitPoints()-((this.getPower()-20)),0)); } }
  @Override
  public void strongAttackTo(IUnit unit){
    if((this.getPower())>0){ int value = (int) ((int) unit.getCurrentHitPoints() - (this.getPower()*1.5));
    unit.setCurrentHitPoints(Math.max(value,0));
      } }
  @Override
  public void normalAttackTo(IUnit unit){
    if(this.getPower()>0){unit.setCurrentHitPoints(Math.max(unit.getCurrentHitPoints()-(this.getPower()),0));}}}