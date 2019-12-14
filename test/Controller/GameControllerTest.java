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
import model.items.Axe;
import model.items.Bow;
import model.items.IEquipableItem;
import model.items.Sword;
import model.map.Field;
import model.units.Alpaca;
import model.units.Archer;
import model.units.Fighter;
import model.units.Hero;
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
    int a = randomTurnSequence.nextInt();
    IntStream.range(0, 50).forEach(i -> {
      controller.initGame(a);
      assertEquals(a, controller.getMaxRounds());
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
    controller.setTacticianPlaying(secondPlayer);
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
    controller.getWinners().clear();
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

    controller = new GameController(1,10);
    controller.setTacticianPlaying(controller.getTacticians().get(0));
    assertEquals(controller.getTacticians().get(0),controller.getTacticianPlaying());
    controller.getTacticianPlaying().setSelectedUnit(controller.getTacticianPlaying().getUnits().get(0));
    assertTrue(controller.getSelectedUnit() instanceof Hero);

  }

  @Test
  void selectUnitIn() {
      controller = new GameController(1,10);
      controller.setTacticianPlaying(controller.getTacticians().get(0));
      Axe axe = new Axe("",1,1,1);
      Alpaca alpaca = new Alpaca(100,1,controller.getGameMap().getCell(5,5),axe);
      controller.getTacticianPlaying().getUnits().add(alpaca);
      controller.getGameMap().getCell(5,5).setUnit(alpaca);
      controller.selectUnitIn(5,5);
      assertEquals(controller.getSelectedUnit(),alpaca);
  }

  @Test
  void getItems() {
      controller = new GameController(1,10);
      controller.setTacticianPlaying(controller.getTacticians().get(0));
      Axe axe = new Axe("",1,1,1);
      Alpaca alpaca = new Alpaca(100,1,controller.getGameMap().getCell(5,5),axe);
      controller.selectUnitIn(5,5);
      controller.getTacticianPlaying().getUnits().add(alpaca);
      controller.getGameMap().getCell(5,5).setUnit(alpaca);
      controller.selectUnitIn(5,5);
      ArrayList<IEquipableItem> list = new ArrayList<>();
      list.add(axe);
      assertEquals(controller.getItems(),list);


  }

  @Test
  void equipItem() {
      controller = new GameController(1,10);
      controller.setTacticianPlaying(controller.getTacticians().get(0));
      Axe axe = new Axe("",1,1,1);
      Fighter fighter = new Fighter(100,1,controller.getGameMap().getCell(5,5),axe);
      controller.selectUnitIn(5,5);
      controller.getTacticianPlaying().getUnits().add(fighter);
      controller.getGameMap().getCell(5,5).setUnit(fighter);
      controller.selectUnitIn(5,5);
      ArrayList<IEquipableItem> list = new ArrayList<>();
      controller.equipItem(0);
      assertEquals(controller.getSelectedUnit().getEquippedItem(),axe);

  }

  @Test
  void useItemOn() {
      controller = new GameController(1,10);
      controller.setTacticianPlaying(controller.getTacticians().get(0));
      Bow bow = new Bow("",1,1,100);
      Archer archer = new Archer(100,2,controller.getGameMap().getCell(3,4),bow);
      archer.equipItem(bow);
      controller.getGameMap().getCell(3,4).setUnit(archer);
      controller.getTacticianPlaying().getUnits().add(archer);
      controller.selectUnitIn(3,4);
      controller.selectItem(0);
      Axe axe = new Axe("",1,1,1);
      Fighter fighter = new Fighter(100,1,controller.getGameMap().getCell(2,5),axe);
      fighter.equipItem(axe);
      controller.getGameMap().getCell(2,5).setUnit(fighter);
      //controller.useItemOn(2,5);
      //assertEquals(fighter.getCurrentHitPoints(),99);
  }

  @Test
  void selectItem() {
      controller = new GameController(1,10);
      controller.setTacticianPlaying(controller.getTacticians().get(0));
      Axe axe = new Axe("",1,1,1);
      Fighter fighter = new Fighter(100,1,controller.getGameMap().getCell(5,5),axe);
      controller.selectUnitIn(5,5);
      controller.getTacticianPlaying().getUnits().add(fighter);
      controller.getGameMap().getCell(5,5).setUnit(fighter);
      controller.selectUnitIn(5,5);
      ArrayList<IEquipableItem> list = new ArrayList<>();
      controller.selectItem(0);
      assertEquals(controller.getSelectedItem(),axe);

  }


  @Test
  void giveItemTo() {
      controller = new GameController(1,10);
      controller.setTacticianPlaying(controller.getTacticians().get(0));
      Axe axe = new Axe("",1,1,1);
      Alpaca alpaca = new Alpaca(100,1,controller.getGameMap().getCell(5,4),axe);
      Sword sword = new Sword("",1,1,1);
      Fighter fighter = new Fighter(100,1,controller.getGameMap().getCell(5,5),sword);

      controller.getTacticianPlaying().getUnits().add(alpaca);
      controller.getTacticianPlaying().getUnits().add(fighter);
      controller.getGameMap().getCell(5,4).setUnit(alpaca);
      controller.getGameMap().getCell(5,5).setUnit(fighter);
      controller.selectUnitIn(5,5);
      ArrayList<IEquipableItem> list = new ArrayList<>();
      controller.selectItem(0);
      list.add(axe);
      list.add(sword);
      //controller.giveItemTo(5,4);
      controller.selectUnitIn(5,4);
      assertEquals(controller.getItems(),list);


  }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}