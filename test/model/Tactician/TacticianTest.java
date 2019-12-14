package model.Tactician;

import model.Factories.Units.*;
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



    private void setSwordMasterFactory() {this.factory = new SwordMasterFactory();
    }

    private void setSorcererFactory() {this.factory = new SorcererFactory();
    }

    private void setHeroFactory() {this.factory = new HeroFactory();
    }

    private void setFighterFactory() {this.factory = new FighterFactory();
    }

    private void setClericFactory() {this.factory = new ClericFactory();
    }

    private void setArcherFactory() {this.factory = new ArcherFactory();
    }

    private void setAlpacaFactory() {this.factory = new AlpacaFactory();
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
        assertEquals(tactician1.getMap(), field);
    }

    @Test
    public void addUnitTest(){
        this.setHeroFactory();
        tactician1.addUnit(1000,1,1,1);
        tactician1.setSelectedUnit(field.getCell(1,1).getUnit());
        assertTrue(tactician1.getUnits().get(0) instanceof Hero);
    }

    @Test
    public void testChangeFactory(){
        heroFactory = new HeroFactory();
        this.setAlpacaFactory();
        tactician1.changeFactory(heroFactory);
        assertTrue(tactician1.getFactory() instanceof HeroFactory );
    }

    @Test
    public void testGetFactory(){
        this.setAlpacaFactory();
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
        tactician1 = new Tactician("player1",field,null);
        heroFactory = new HeroFactory();
        tactician1.changeFactory(heroFactory);
        tactician1.addUnit(100,4,2,3);


    }

}
