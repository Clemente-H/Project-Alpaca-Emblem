package Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import Controller.GameController;
import model.Factories.Units.HeroFactory;
import model.Tactician.Tactician;
import model.items.Sword;
import model.map.Field;
import model.units.Alpaca;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 * @since v2.0
 */
class GameControllerTest implements PropertyChangeListener {

  private GameController controller;
  private long randomSeed;
  private List<String> testTacticians;

  @BeforeEach
  void setUp() {
    // Se define la semilla como un número aleatorio para generar variedad en los tests
    randomSeed = new Random().nextLong();
    controller = new GameController(4, 128);
    testTacticians = List.of("Player 0", "Player 1", "Player 2", "Player 3");

  }

  @Test
  void getTacticiansTest() {
      List<Tactician> tacticians = controller.getTacticians();
      assertEquals(4, tacticians.size());
      for (int i = 0; i < tacticians.size(); i++) {
          assertEquals(testTacticians.get(i), tacticians.get(i).getName());
      }
  }

  @Test
  void getGameMap() {
    Field gameMap = controller.getGameMap();
    assertEquals(128, gameMap.getSize()); // getSize deben definirlo
    assertTrue(controller.getGameMap().isConnected());
    Random testRandom = new Random(randomSeed);
    testRandom.setSeed(randomSeed);
    // Para testear funcionalidades que dependen de valores aleatorios se hacen 2 cosas:
    //  - Comprobar las invariantes de las estructuras que se crean (en este caso que el mapa tenga
    //    las dimensiones definidas y que sea conexo.
    //  - Setear una semilla para el generador de números aleatorios. Hacer esto hace que la
    //    secuencia de números generada sea siempre la misma, así pueden predecir los
    //    resultados que van a obtener.
    //    Hay 2 formas de hacer esto en Java, le pueden pasar el seed al constructor de Random, o
    //    usar el método setSeed de Random.
    //  ESTO ÚLTIMO NO ESTÁ IMPLEMENTADO EN EL MAPA, ASÍ QUE DEBEN AGREGARLO (!)
  }

  @Test
  void getTurnOwner() {
    Random testRandom2 = new Random(randomSeed);
    Tactician tactician = controller.getTacticians().get(testRandom2.nextInt(controller.getTacticians().size()));
    controller.initRandomGame(new Random(randomSeed));
    List<Tactician> tacticians = controller.getTacticians();
    assertEquals(controller.getTacticians().size(),4);
    for(int i = 0;i<controller.getTacticians().size();i++){
        assertTrue(testTacticians.contains(tacticians.get(i).getName()));
      }
    assertEquals(controller.getTurnOwner(),tactician);
    controller.endTurn();
    controller.endTurn();
    assertEquals(controller.getTurnOwner(),controller.getTacticians().get(2));
  }

  @Test
  void getRoundNumber() {
    controller.initGame(10);
    for (int i = 1; i < 10; i++) {
      assertEquals(i, controller.getRoundNumber());
      for (int j = 0; j < 4; j++) {
        controller.endTurn();
      }
    }
  }

  @Test
  void getMaxRounds() {
    Random randomTurnSequence = new Random();
    IntStream.range(0, 50).forEach(i -> {
      controller.initGame(randomTurnSequence.nextInt());
      assertEquals(randomTurnSequence.nextInt(), controller.getMaxRounds());
    });
    controller.initEndlessGame();
    assertEquals(-1, controller.getMaxRounds());
  }

  @Test
  void endTurn() {
    Random random = new Random(randomSeed);
    controller.initRandomGame(random);
    Tactician firstPlayer = controller.getTurnOwner();
    controller.endTurn();


    Tactician secondPlayer = new Tactician("Player 2",controller.getGameMap(),controller);
    assertNotEquals(secondPlayer.getName(), firstPlayer.getName());

    assertNotEquals(firstPlayer.getName(), controller.getTurnOwner().getName());
    assertEquals(secondPlayer.getName(), controller.getTurnOwner().getName());
  }

  @Test
  void removeTactician() {
    assertEquals(4, controller.getTacticians().size());
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

    controller.removeTactician("Player 0");
    assertEquals(3, controller.getTacticians().size());
    controller.getTacticians().forEach(tactician -> assertNotEquals("Player 1", tactician));
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

    controller.removeTactician("Player 5");
    assertEquals(3, controller.getTacticians().size());
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));
  }

  @Test
  void getWinners() {
    controller.initGame(2);
    IntStream.range(0, 8).forEach(i -> controller.endTurn());
    assertEquals(4, controller.getWinners().size());
    controller.getWinners()
        .forEach(player -> Assertions.assertTrue(testTacticians.contains(player)));

    controller.initGame(2);
    IntStream.range(0, 4).forEach(i -> controller.endTurn());
    assertNull(controller.getWinners());
    controller.removeTactician("Player 0");
    controller.removeTactician("Player 2");
    IntStream.range(0, 2).forEach(i -> controller.endTurn());
    List<String> winners = controller.getWinners();
    assertEquals(2, winners.size());
    assertTrue(List.of("Player 1", "Player 2").containsAll(winners));

    controller.initEndlessGame();
    for (int i = 0; i < 3; i++) {
      assertNull(controller.getWinners());
      controller.removeTactician("Player " + i);
    }
    assertTrue(List.of("Player 3").containsAll(controller.getWinners()));
  }

  // Desde aquí en adelante, los tests deben definirlos completamente ustedes

    @Test
  void getSelectedUnit() {
    assertEquals(controller.getSelectedUnit(),null);






  }

  @Test
  void selectUnitIn() {
  }

  @Test
  void getItems() {
  }

  @Test
  void equipItem() {
  }

  @Test
  void useItemOn() {
  }

  @Test
  void selectItem() {
  }

  @Test
  void giveItemTo() {
    controller = new GameController(1,10);
    Tactician tactican1 = new Tactician("",controller.getGameMap(),controller);
    HeroFactory heroFactory = new HeroFactory();
    tactican1.changeFactory(heroFactory);

    tactican1.setMap(controller.getGameMap());
    tactican1.addUnit(10,1,2,2);
    Sword sword = new Sword("",1,1,1);
    Alpaca alpaca = new Alpaca(10,10,controller.getGameMap().getCell(2,3),sword);
    tactican1.getUnits().add(alpaca);
    tactican1.setSelectedUnit(alpaca);
    controller.selectItem(0);
    assertEquals(controller.getSelectedItem(), sword);
    controller.giveItemTo(2,2);



  }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}