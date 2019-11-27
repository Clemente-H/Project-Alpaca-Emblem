package Controller;

import model.Tactician.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.units.IUnit;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Controller of the game.
 * The controller manages all the input received from de game's GUI.
 *
 * @author Ignacio Slater Muñoz
 * @version 2.0
 * @since 2.0
 */
public class GameController implements Observer {
  private int numberOfPlayers;
  private int mapSize;
  private Field field;
  private ArrayList<Tactician> tacticians = new ArrayList<>();
  private int selectedTactician;
  private int round=0;
  private int maxRound;
  private Tactician tacticianPlaying;

  GameController(final int NumberOfPlayers, final int MapSize,
                 final Field FIELD, final int SelectedTactician,final int Round,
                 final int MaxRound, final Tactician... Tacticians){
    this.numberOfPlayers = NumberOfPlayers;
    this.mapSize = MapSize;
    this.field = FIELD;
    this.selectedTactician=SelectedTactician;
    this.round=Round;
    this.maxRound = MaxRound;
    this.tacticians.addAll(Arrays.asList(Tacticians).subList(0,Tacticians.length));
  }



  /**
   * Creates the controller for a new game.
   *
   * @param numberOfPlayers
   *     the number of players for this game
   * @param mapSize
   *     the dimensions of the map, for simplicity, all maps are squares
   */
  public GameController(int numberOfPlayers, int mapSize) {
    for(int i=1;i<=numberOfPlayers+1; i++){
      Tactician tactician = new Tactician;
      String name = "Player"+ i;
      tactician.setName(name);
      tacticians.add(tactician);
      //factory para crear un tactician;
    }


  }

  /**
   * @return the list of all the tacticians participating in the game.
   */
  public List<Tactician> getTacticians() {
      return List.copyOf(tacticians);
  }

  /**
   * @return the map of the current game
   */
  public Field getGameMap() {
    return this.field;
  }

  /**
   * @return the tactician that's currently playing
   */
  public Tactician getTurnOwner() {
    return tacticians.get(selectedTactician);
  }

  /**
   * @return the number of rounds since the start of the game.
   */
  public int getRoundNumber() {
    return this.round;
  }

  /**
   * @return the maximum number of rounds a match can last
   */
  public int getMaxRounds() {
    return this.maxRound;
  }

  /**
   * Finishes the current player's turn.
   */
  public void endTurn() {
    if(this.selectedTactician==tacticians.size()){
      this.selectedTactician=0;
    }
    else{
    this.selectedTactician++;
    }

  }

  /**
   * Removes a tactician and all of it's units from the game.
   *
   * @param tactician
   *     the player to be removed
   */
  public void removeTactician(String tactician) {
    this.tacticians.remove(tactician);
  }

  /**
   * Starts the game.
   * @param maxTurns
   *  the maximum number of turns the game can last
   */
  public void initGame(final int maxTurns) {
    this.maxRound=maxTurns;

  }

  /**
   * Starts a game without a limit of turns.
   */
  public void initEndlessGame() {
    maxRound = -1;

  }

  /**
   * @return the winner of this game, if the match ends in a draw returns a list of all the winners
   */


  public List<String> getWinners() {
    List<String> winners = new ArrayList<>();
    for(int i =0;i<this.tacticians.size();i++){
      String n = this.tacticians.get(i).getName();
      winners.add(n);
    }
    return winners;
  }

  /**
   * @return the current player's selected unit
   */
  public IUnit getSelectedUnit() {
    return tacticians.get(selectedTactician).getSelectedUnit();
  }

  /**
   * Selects a unit in the game map
   *
   * @param x
   *     horizontal position of the unit
   * @param y
   *     vertical position of the unit
   */
  public void selectUnitIn(int x, int y) {


  }

  /**
   * @return the inventory of the currently selected unit.
   */
  public List<IEquipableItem> getItems() {
    return this.getSelectedUnit().getItems();
  }




  /**
   * Equips an item from the inventory to the currently selected unit.
   *
   * @param index
   *     the location of the item in the inventory.
   */
  public void equipItem(int index) {
    getSelectedUnit().equipItem(getSelectedUnit().selectItem(index));

  }

  /**
   * Uses the equipped item on a target
   *
   * @param x
   *     horizontal position of the target
   * @param y
   *     vertical position of the target
   */
  public void useItemOn(int x, int y) {
    this.getSelectedUnit().Combat(field.getCell(x,y).getUnit());
    this.getSelectedUnit().heal(field.getCell(x,y).getUnit());
  }

  /**
   * Selects an item from the selected unit's inventory.
   *
   * @param index
   *     the location of the item in the inventory.
   */
  public void selectItem(int index) {
    getSelectedUnit().selectItem(index);
  }


  /**
   * Gives the selected item to a target unit.
   *
   * @param x
   *     horizontal position of the target
   * @param y
   *     vertical position of the target
   */
  public void giveItemTo(int x, int y) {
    this.getSelectedUnit().trade(this.getSelectedUnit().getSelectedItem(),field.getCell(x,y).getUnit());

  }

  @Override
  public void update(Observable o, Object arg) {

  }
}
