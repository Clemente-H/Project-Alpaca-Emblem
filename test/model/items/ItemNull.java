package model.items;
import model.units.IUnit;


public class ItemNull extends AbstractTestItem {
    private ItemNull itemNull;
    private ItemNull wrongItemNull;
    private IUnit unit;

    /**
     * sets wich items is going to be tested
     */
    @Override
    public void setTestItem(){
        expectedName = "Null";
        expectedPower = 0;
        expectedMinRange = 0;
        expectedMaxRange = 0;
        itemNull = new ItemNull();
    }
    /**
     * sets up an item with wrong ranges setted.
     *
     */
    @Override
    public void setWrongRangeItem() { wrongItemNull = new ItemNull();}
    /**
     * sets the unit that will be equipped with the test item
     */
    @Override
    public void setTestUnit(){}
    @Override
    public IEquipableItem getWrongTestItem(){return null;}
    /**
     * @return the item being tested
     */
    @Override
    public IUnit getTestUnit(){return unit;}


    @Override
    public IEquipableItem getTestItem() { return null;}
}
