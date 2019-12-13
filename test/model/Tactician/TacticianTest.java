package model.Tactician;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.Factories.Units.HeroFactory;
import model.Factories.Units.IFactory;
import model.units.Alpaca;
import model.units.Hero;
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
import java.util.List;
import java.util.Random;

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
    private HeroFactory heroFactory;


    @BeforeEach
    void setUp() {
        field = new Field();
        for (int i = 0; i<64;i++){
            for (int j = 0; j<64;j++){
                this.field.addCells(false, new Location(i, j));
            }
        }
        axe1  = new Axe("",1,2,50);
        axe2 = new Axe("",1,1,10);
        spear1 = new Spear("",50,1,10);
        spear2 = new Spear("",1,1,10);

        tactician1 = new Tactician("t1",field,null);
        tactician2 = new Tactician("t2",field,null);
        hero1 = new Hero(100,5,field.getCell(0,0),null);
        hero2 = new Hero(50,3,field.getCell(64,64),null);
        hero1.setItems(spear1);
        hero1.setItems(axe1);
        hero1.equipItem(spear1);
        hero2.setItems(spear1);
        hero2.setItems(axe1);
        hero2.equipItem(spear1);

    }

    @Test
    public void setNameTest(){
        tactician1.setName("Player 0");
        assertEquals(tactician1.getName(),"PLayer 0");
        tactician2.setName("Player 1");
        assertEquals(tactician2.getName(),"PLayer 0");


    }
    @Test
    public void getMapTest(){
        assertEquals(tactician1.getMap(),field);
    }

    @Test
    public void addUnitTest(){
        tactician1.changeFactory(heroFactory);
        tactician1.addUnit(1000,1,1,1);
        tactician1.setSelectedUnit(field.getCell(1,1).getUnit());
        assertEquals(field.getCell(1,1).getUnit(),tactician1.getSelectedUnit());
    }

}
