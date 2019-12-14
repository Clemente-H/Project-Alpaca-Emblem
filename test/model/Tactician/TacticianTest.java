package model.Tactician;

import model.Factories.Units.*;
import model.units.*;
import org.junit.jupiter.api.BeforeEach;

import model.items.*;
import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Controller.GameController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TacticianTest {
    protected Tactician tactician1,tactician2;
    protected Hero hero1,hero2;
    protected Alpaca alpaca1, alpaca2;
    protected Axe axe1, axe2;
    protected Spear spear1,spear2;
    private GameController controller;
    private long randomSeed;
    private List<String> testTacticians;
    protected Field field;
    private IFactory factory;
    private HeroFactory heroFactory ;
    private AlpacaFactory alpacaFactory;
    private ArcherFactory archerFactory;
    private ClericFactory clericFactory;
    private FighterFactory fighterFactory;
    private SorcererFactory sorcererFactory;
    private SwordMasterFactory swordMasterFactory;
    private IFactory unitFactory;


    @BeforeEach
    void setUp() {
        setTacticians();
        setField();

    }



    public void setField(){
        this.field = new Field();
        for (int i = 0; i<10;i++){
            for (int j = 0; j<10;j++){
                this.field.addCells(false, new Location(i, j));
            }
        }

    }

    public void setTacticians() {
        tactician1 = new Tactician("Player 1", this.field, null);
        tactician2 = new Tactician("Player 2", this.field, null);
    }



    @Test
    public void setNameTest(){
        tactician1.setName("Player0");
        assertEquals(tactician1.getName(),"Player0");



    }
    @Test
    public void getMapTest(){
        field = new Field();
        for (int i = 0; i<10;i++){
            for (int j = 0; j<10;j++){
                this.field.addCells(false, new Location(i, j));
            }
        }
        tactician1 = new Tactician("",field,null);
        tactician1.setMap(field);
        assertEquals(tactician1.getMap(), field);
    }

    @Test
    public void addUnitTest(){
        field = new Field();
        Location location = new Location(1,1);
        field.addCells(true,location);
        heroFactory = new HeroFactory();
        tactician1.setMap(field);
        tactician1.changeFactory(heroFactory);
        tactician1.addUnit(1000,1,1,1);
        assertTrue(tactician1.getUnits().get(0) instanceof Hero);
    }

    @Test
    public void testChangeFactory(){
        heroFactory = new HeroFactory();
        tactician1.changeFactory(heroFactory);
        assertTrue(tactician1.getFactory() instanceof HeroFactory );
    }

    @Test
    public void testGetFactory(){
        heroFactory = new HeroFactory();
        tactician2.changeFactory(heroFactory);
        assertEquals(tactician2.getFactory(),heroFactory);
    }

    @Test
    public void killTacticianTest(){
        assertTrue(tactician1.getLifeStateTactician());
        tactician1.killTactician();
        assertEquals(tactician1.getLifeStateTactician(),false);
    }

    @Test
    public void TestAlpacaFactory(){
        field = new Field();
        for (int i = 0; i<10;i++){
            for (int j = 0; j<10;j++){
                this.field.addCells(false, new Location(i, j));
            }
        }
        tactician1 = new Tactician("",field,null);
        tactician2 = new Tactician("",field,null);
        alpacaFactory = new AlpacaFactory();
        tactician1.changeFactory(alpacaFactory);
        tactician1.addUnit(100,1,0,0);
        assertTrue(tactician1.getSelectedUnit() instanceof Alpaca);
        tactician2.changeFactory(alpacaFactory);
        tactician2.addUnit(100,1,3,3);
        assertEquals(tactician2.getUnits().get(0),tactician2.getSelectedUnit());


    }
    @Test
    public void seeingAttributesOfTheSelectedUnitTest(){
        field = new Field();
        for (int i = 0; i<10;i++){
            for (int j = 0; j<10;j++){
                this.field.addCells(false, new Location(i, j));
            }
        }
        Spear javeline = new Spear("",10,1,1);
        tactician1 = new Tactician("player1",field,null);
        heroFactory = new HeroFactory();
        tactician1.changeFactory(heroFactory);
        tactician1.addUnit(100,4,2,3);
        tactician1.getSelectedUnit().setItems(javeline);
        assertEquals(tactician1.HitPoinsSelectedUnit(),100);
        assertEquals(tactician1.movementSelectedUnit(),4);
        assertEquals(tactician1.locationSelectedUnit(),field.getCell(2,3));
        assertEquals(tactician1.typeSelectedUnit(),"Hero");
        ArrayList<IEquipableItem> a = new ArrayList<>();
        a.add(javeline);
        assertEquals(tactician1.ItemsSelectedUnit(), a );
    }


    @Test
    public void seeingAttributesOfTheSelectedItemTest() {
        //and checking is setSelectItem Works
        field = new Field();
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 1; j++) {
                this.field.addCells(false, new Location(i, j));
            }
        }
        DarknessMagicBook dark = new DarknessMagicBook("HowToActColdAsSasuke", 100, 1, 10);
        tactician1 = new Tactician("player1", field, null);
        tactician1.setSelectedItem(dark);
        assertEquals(tactician1.getSelectedItem(),dark);
        assertEquals(tactician1.nameSelectedItem(), "HowToActColdAsSasuke");
        assertEquals(tactician1.powerSelectedItem(), 100);
        assertEquals(tactician1.minRangeSelectedItem(), 1);
        assertEquals(tactician1.maxRangeSelectedItem(), 10);
    }



    @Test
    public  void equipSelectedItemTest(){
        field = new Field();
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 2; j++) {
                this.field.addCells(true, new Location(i, j));
            }
        }
        spear1 = new Spear("",1,1,1);
        tactician1 = new Tactician("",field,null);
        tactician1.setMap(field);
        heroFactory = new HeroFactory();
        tactician1.changeFactory(heroFactory);
        tactician1.addUnit(100,2,0,1);
        tactician1.setSelectedItem(spear1);
        tactician1.equipSelectedItem();
        assertEquals(tactician1.getSelectedUnit().getEquippedItem(),spear1);

    }

    @Test
    public void setSelectedUnitTest(){
        field = new Field();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.field.addCells(true, new Location(i, j));
            }
        }
        Hero hero = new Hero(100,1,field.getCell(0,0));
        Alpaca alpaca = new Alpaca(100,1,field.getCell(1,1));
        tactician1 = new Tactician("",field,null);
        tactician1.getUnits().add(hero);
        tactician1.getUnits().add(alpaca);
        tactician1.setSelectedUnit(hero);
        assertEquals(tactician1.getSelectedUnit(),hero);
        tactician1.setSelectedUnit(alpaca);
        assertEquals(tactician1.getSelectedUnit(),alpaca);



    }
    @Test
    public void setSelectedUnitTest2(){
        field = new Field();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.field.addCells(true, new Location(i, j));
            }
        }
        Hero hero = new Hero(100,1,field.getCell(0,0));
        Alpaca alpaca = new Alpaca(100,1,field.getCell(1,1));
        tactician1.getUnits().add(hero);
        tactician1.getUnits().add(alpaca);
        tactician1.setSelectedUnit(alpaca);
        field.getCell(0,0).setUnit(hero);
        tactician1.setSelectedUnit(hero.getLocation());
        assertEquals(tactician1.getSelectedUnit(),hero);

    }

    @Test
    public void movingUnitsTest(){
        field = new Field();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                this.field.addCells(true, new Location(i, j));
            }
        }
        axe1 = new Axe("",1,1,1);
        tactician1.setMap(field);
        heroFactory = new HeroFactory();
        tactician1.changeFactory(heroFactory);
        tactician1.addUnit(100,10,0,0);
        tactician1.getSelectedUnit().moveTo(field.getCell(3,3));
        assertEquals(tactician1.getSelectedUnit().getLocation(),field.getCell(3,3));
         }












    @Test
    public void movingUnitsTest2(){
        field = new Field();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                this.field.addCells(true, new Location(i, j));
            }
        }
        axe1 = new Axe("",1,1,1);
        tactician1.setMap(field);
        heroFactory = new HeroFactory();
        tactician1.changeFactory(heroFactory);
        tactician1.addUnit(100,10,0,0);
        alpaca1 = new Alpaca(100,10,field.getCell(4,4),axe1);
        field.getCell(4,4).setUnit(alpaca1);
        tactician1.getSelectedUnit().moveTo(field.getCell(3,3));
        assertEquals(tactician1.getSelectedUnit().getLocation(),field.getCell(3,3));
        tactician1.getSelectedUnit().moveTo(field.getCell(4,4));
        assertEquals(tactician1.getSelectedUnit().getLocation(),field.getCell(3,3));
        alpacaFactory = new AlpacaFactory();
        tactician1.changeFactory(alpacaFactory);
        tactician1.addUnit(100,10,2,2);
        assertTrue(tactician1.getSelectedUnit() instanceof Alpaca);
        tactician1.getSelectedUnit().moveTo(field.getCell(4,4));
        assertEquals(tactician1.getSelectedUnit().getLocation(),field.getCell(2,2));

    }
    @Test
    public void testingCombatAndHeal(){
        field = new Field();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                this.field.addCells(true, new Location(i, j));
            }
        }
        tactician1.setMap(field);
        tactician2.setMap(field);
        Bow bow = new Bow("",20,0,150);
        Spear spear = new Spear("",20,1,20);
        Staff staff = new Staff("",20,1,40);
        Hero hero = new Hero(100,5,field.getCell(0,0),spear);
        Cleric cleric =new Cleric(100,5,field.getCell(1,4),staff);
        Archer archer = new Archer(100,3,field.getCell(4,4),bow);
        field.getCell(4,4).setUnit(archer);
        archer.setLocation(field.getCell(4,4));
        cleric.setLocation(field.getCell(1,4));
        cleric.equipItem(staff);
        tactician2.getUnits().add(archer);
        tactician2.getUnits().add(cleric);
        tactician2.setSelectedUnit(cleric);
        tactician2.setSelectedItem(staff);
        tactician1.getUnits().add(hero);
        tactician1.setSelectedUnit(hero);
        hero.setItems(spear);
        tactician1.getSelectedUnit().equipItem(spear);
        archer.equipItem(bow);
        assertTrue(hero.isHeroAlive());
        tactician1.combatWithUnitIn(archer);
        assertEquals(tactician1.getSelectedUnit().getCurrentHitPoints(),80);
        assertEquals(archer.getCurrentHitPoints(),80);
        tactician2.healUnitIn(archer);
        assertEquals(archer.getCurrentHitPoints(),100);

    }


    @Test
    public void clearingListTest(){
        field = new Field();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                this.field.addCells(true, new Location(i, j));
            }
        }
        ArrayList<Object> list = new ArrayList();
        tactician1.setMap(field);
        tactician1.clearActingUnits();
        tactician1.clearMovedUnits();
        assertEquals(tactician1.getActingUnitsUnits(),list);
        assertEquals(tactician1.getMovedUnits(), list);
    }

    @Test
    public void addItemTest(){
        field = new Field();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                this.field.addCells(true, new Location(i, j));
            }
        }
        axe1 = new Axe("",1,1,1);
        Sword sword = new Sword("",1,1,1);
        tactician1.setMap(field);
        heroFactory = new HeroFactory();
        Hero hero = new Hero(100,1,field.getCell(0,0),axe1);
        tactician1.getUnits().add(hero);
        tactician1.addItem(sword,hero);
        assertTrue(hero.getItems().contains(sword));
    }
}
