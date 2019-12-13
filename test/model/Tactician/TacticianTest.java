package model.Tactician;

import model.Factories.Units.HeroFactory;
import model.Factories.Units.IFactory;
import model.units.Alpaca;
import model.units.Hero;
import model.units.IUnit;
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
    private HeroFactory heroFactory;
    private IFactory factory2;


    @BeforeEach
    void setUp() {
        setTacticians();
        setUnitFactory();
        setField();
    }

    public void setField(){
        field = new Field();
        for (int i = 0; i<64;i++){
            for (int j = 0; j<64;j++){
                this.field.addCells(false, new Location(i, j));
            }
        }

    }
    public void setTacticians() {
        tactician1 = new Tactician("Player 1", this.field, null);
        tactician2 = new Tactician("Player 2", this.field, null);
    }
    public void setUnitFactory(){};


    @Test
    public void setNameTest(){
        tactician1.setName("Player0");
        assertEquals(tactician1.getName(),"Player0");



    }
    @Test
    public void getMapTest(){
        assertEquals(tactician1.getMap(), field);
    }

    @Test
    public void addUnitTest(){
        tactician1.changeFactory(heroFactory);
        tactician1.addUnit(1000,1,1,1);
        tactician1.setSelectedUnit(field.getCell(1,1).getUnit());
        assertEquals(field.getCell(1,1).getUnit(),tactician1.getSelectedUnit());
    }

}
