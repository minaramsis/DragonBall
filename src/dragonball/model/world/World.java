package dragonball.model.world;

import java.util.ArrayList;
import java.util.Random;

import dragonball.model.cell.Cell;
import dragonball.model.cell.CellListener;
import dragonball.model.cell.Collectible;
import dragonball.model.cell.CollectibleCell;
import dragonball.model.cell.EmptyCell;
import dragonball.model.cell.FoeCell;
import dragonball.model.character.fighter.NonPlayableFighter;

public class World implements CellListener{
	public static final int MAP_SIZE = 10;
	public static final int NUM_WEAK_FOES = 15;
	public static final int NUM_MIN_SENZU_BEANS = 3;
	public static final int NUM_MAX_SENZU_BEANS = 5;
	public static final int NUM_DRAGON_BALLS = 1;

	private Cell[][] map;
	private int playerRow;
	private int playerColumn;
	
	public WorldListener worldListener;

	public World() {
		map = new Cell[MAP_SIZE][MAP_SIZE];
	}

	public Cell[][] getMap() {
		return map;
	}

	public int getPlayerRow() {
		return playerRow;
	}

	public int getPlayerColumn() {
		return playerColumn;
	}

	// a helper method to get a random foe
	private NonPlayableFighter getRandomFoe(ArrayList<NonPlayableFighter> foes) {
		int i = new Random().nextInt(foes.size());
		return foes.get(i);
	}

	public void generateMap(ArrayList<NonPlayableFighter> weakFoes, ArrayList<NonPlayableFighter> strongFoes) {
		// place the boss in position 0,0
		map[0][0] = new FoeCell(getRandomFoe(strongFoes));
		map[0][0].setCellListener(this);

		// place an empty cell in place of the player in position 9,9
		playerRow = playerColumn = MAP_SIZE - 1;
		map[playerRow][playerColumn] = new EmptyCell();
		map[playerRow][playerColumn].setCellListener(this);

		// place weak foes
		for (int i = NUM_WEAK_FOES; i > 0;) {
			// generate a random row and column
			int row = new Random().nextInt(MAP_SIZE);
			int column = new Random().nextInt(MAP_SIZE);

			// only place the foe if the cell is free
			if (map[row][column] == null) {
				map[row][column] = new FoeCell(getRandomFoe(weakFoes));
				map[row][column] .setCellListener(this);
				i--;
			}
		}

		// place senzu beans (random between 3 and 5)
		int numSenzuBeans = NUM_MIN_SENZU_BEANS + new Random().nextInt(NUM_MAX_SENZU_BEANS - NUM_MIN_SENZU_BEANS + 1);
		for (int i = numSenzuBeans; i > 0;) {
			// generate a random row and column
			int row = new Random().nextInt(MAP_SIZE);
			int column = new Random().nextInt(MAP_SIZE);

			// only place the senzu bean if the cell is free
			if (map[row][column] == null) {
				map[row][column] = new CollectibleCell(Collectible.SENZU_BEAN);
				map[row][column].setCellListener(this);
				i--;
			}
		}

		for (int i = NUM_DRAGON_BALLS; i > 0;) {
			// generate a random row and column
			int row = new Random().nextInt(MAP_SIZE);
			int column = new Random().nextInt(MAP_SIZE);

			// only place the dragon ball if the cell is free
			if (map[row][column] == null) {
				map[row][column] = new CollectibleCell(Collectible.DRAGON_BALL);
				map[row][column].setCellListener(this);
				i--;
			}
		}

		// place empty cells in remaining free cells
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == null) {
					map[i][j] = new EmptyCell();
					map[i][j].setCellListener(this);
				}
			}
		}
	}

	@Override
	public String toString() {
		String toString = "";

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (i == playerRow && j == playerColumn) {
					toString += "[x]"; // place an [x] in place of the player
				} else {
					toString += map[i][j];
				}
			}
			toString += "\n";
		}

		return toString.substring(0, toString.length() - 1);
	}

	public WorldListener getWorldListener() {
		return worldListener;
	}

	public void setWorldListener(WorldListener worldListener) {
		this.worldListener = worldListener;
	}

	@Override
	public void onFoeEncountered(NonPlayableFighter foe) {
		worldListener.onFoeEncountered(foe);
	}

	@Override
	public void onCollectibleFound(Collectible collectible) {
		worldListener.onCollectibleFound(collectible);
		map[playerRow][playerColumn] = new EmptyCell();
		map[playerRow][playerColumn].setCellListener(this);
	}
	
	public void resetPlayerPosition(){
		this.playerRow = 9;
		this.playerColumn = 9;
	}
	
	public void moveUp(){
		playerRow--;
		if(playerRow < 0){
			playerRow = 0;
		}
		map[playerRow][playerColumn].onStep();
	}
	
	public void moveDown(){
		playerRow++;
		if(playerRow > 9){
			playerRow = 9;
		}
		map[playerRow][playerColumn].onStep();
	}
	
	public void moveRight(){
		playerColumn++;
		if(playerColumn > 9){
			playerColumn = 9;
		}
		map[playerRow][playerColumn].onStep();
	}
	
	public void moveLeft(){
		playerColumn--;
		if(playerColumn < 0){
			playerColumn = 0;
		}
		map[playerRow][playerColumn].onStep();
	}
}
