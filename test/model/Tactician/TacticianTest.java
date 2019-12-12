package model.Tactician;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    protected Tactician tactician;
    protected Hero hero;
    protected Spear spear;
    private GameController controller;
    private long randomSeed;
    private List<String> testTacticians;


    @BeforeEach
    void setUp() {
        randomSeed = new Random().nextLong();
        controller = new GameController(4, 128);
        testTacticians = List.of("Player 0", "Player 1", "Player 2", "Player 3");
        assertEquals(controller.getTacticians(), testTacticians);


    }

    @Test
    public void setNameTest(){
        controller.getTurnOwner().setName("Player0");
        assertEquals(controller.getTurnOwner().getName(),"PLayer0");

    }

}
