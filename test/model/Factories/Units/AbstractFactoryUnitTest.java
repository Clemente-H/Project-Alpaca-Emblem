package model.Factories.Units;

import Controller.GameController;
import model.Tactician.Tactician;
import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractFactoryUnitTest {
    private Field field;
    protected Tactician tactician;
    protected IFactory factory;
    private AlpacaFactory alpacaFactory;
    private HeroFactory heroFactory;
    private ArcherFactory archerFactory;
    private ClericFactory clericFactory;
    private FighterFactory fighterFactory;
    private SorcererFactory sorcererFactory;
    private SwordMasterFactory swordMasterFactory;
    protected GameController controller;
    @BeforeAll
    void setUp(){
        field = new Field();
        for (int i = 0; i<64;i++){
            for (int j = 0; j<64;j++){
                this.field.addCells(false, new Location(i, j));
            }
        }

        tactician = new Tactician("",field,controller);
        tactician.changeFactory(factory);

    }
    @Test
    public void testingAlpacaFactory(){
       tactician.changeFactory(alpacaFactory);
       tactician.addUnit(100,1,0,0);
       assertEquals(field.getCell(0,0).getUnit(),tactician.getUnits().get(1));
    }
    @Test
    public void testingArcherFactory(){
        tactician.changeFactory(archerFactory);
        tactician.addUnit(100,1,1,1);
        assertEquals(field.getCell(1,1).getUnit(),tactician.getUnits().get(2));
    }

}