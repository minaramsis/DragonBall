package dragonball.model.cell;

import dragonball.model.world.World;

public abstract class Cell {
	
	private World world;
	
	@Override
	public abstract String toString();
	public abstract void onStep();

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
	
}
