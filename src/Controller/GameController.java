package Controller;

import model.Factories.Units.HeroFactory;
import model.Tactician.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.Hero;
import model.units.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
public class GameController implements PropertyChangeListener {
  private int numberOfPlayers;
  private int mapSize;
  private Field field;
  private ArrayList<Tactician> tacticians = new ArrayList<>();
  private int selectedTactician;
  private int round=0;
  private int maxRound;
  private Tactician tacticianPlaying;
  private IEquipableItem selectedItem;


  /**
   * Creates the controller for a new game.
   *
   * @param numberOfPlayers
   *     the number of players for this game
   * @param mapSize
   *     the dimensions of the map, for simplicity, all maps are squares
   */
  public GameController(int numberOfPlayers, int mapSize) {
    field = new Field();
    for (int i = 0; i<mapSize;i++){
      for (int j = 0; j<mapSize;j++){
        this.field.addCells(false, new Location(i, j));
      }
    }
    field.addObserver(this);
    int B = mapSize/numberOfPlayers;

    for(int k=0;k<=numberOfPlayers-1; k++){
      String name = "Player "+ k;
      Tactician tactician = new Tactician(name,field,this);
      tactician.setMap(field);
      //when a tactician is created, an hero is asignated, in
      //a position of the map
      Random random = new Random();
      int a =  random.nextInt(tacticians.size());
      HeroFactory heroFactory = new HeroFactory();
      tactician.changeFactory(heroFactory);
      tactician.addUnit(100,5,B*k,a);
      tacticians.add(tactician);
      field.addListener(tactician);
    }

  }

  /**
   * @return the list of all the tacticians participating in the game.
   */
  public List<Tactician> getTacticians() {
      return this.tacticians;
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
    return tacticianPlaying;
  }

  /**
   * @return the number of rounds since the start of the game.
   */
  public int getRoundNumber() {
    return this.round + 1;
  }

  /**
   * @return the maximum number of rounds a match can last
   */
  public int getMaxRounds() {
    return this.maxRound;
  }




public IEquipableItem getSelectedItem(){return this.selectedItem;}

public void setTacticianPlaying(Tactician tactician){this.tacticianPlaying = tactician;}




  /**
   * Finishes the current player's turn.
   */
  public void endTurn() {
    for(int i = 0; i<tacticianPlaying.getUnits().size();i++){
      if (!tacticianPlaying.getUnits().get(i).isHeroAlive()){
        int a = tacticians.indexOf(tacticianPlaying);
        int b = tacticians.size();
        tacticianPlaying.killTactician();
        this.removeTactician(tacticianPlaying.getName());
        if (a==b){this.setTacticianPlaying(tacticians.get(0));}
        else{this.setTacticianPlaying(tacticians.get(a));}
      }
    }
    if(tacticians.indexOf(tacticianPlaying)==tacticians.size()){
      this.setTacticianPlaying(tacticians.get(0));
    }
    else{
      int a = tacticians.indexOf(tacticianPlaying);
    this.setTacticianPlaying(tacticians.get(a+1));
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
     * Starts the game.
     * @param random
     *  the order for the tacticians to play
     */
  public void initRandomGame(Random random){
      maxRound = -1;
      ArrayList<Tactician> Tacticians2 = new ArrayList<>();
      while(this.tacticians.size()>0){
          int j = random.nextInt(tacticians.size());
          Tacticians2.add(tacticians.get(j));
          tacticians.remove(tacticians.get(j));
      }
      this.tacticians.clear();
      this.tacticians = Tacticians2;
      this.setTacticianPlaying(tacticians.get(0));

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
  public void selectUnitIn(int x, int y) { tacticianPlaying.setSelectedUnit(field.getCell(x,y));}/*arreglar esto, tiene que quedar guardado
  la unidad seleccionada*/


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
      this.getSelectedUnit().equipItem(this.getSelectedUnit().selectItem(index));
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
    this.getTurnOwner().setSelectedItem(this.getSelectedUnit().selectItem(index));
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
    this.getSelectedUnit().trade(this.getTurnOwner().getSelectedItem(),field.getCell(x,y).getUnit());
  }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
