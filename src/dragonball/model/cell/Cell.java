package dragonball.model.cell;

import dragonball.model.world.World;
import dragonball.model.world.WorldListener;

public abstract class Cell {
	
	private World world;
	private WorldListener worldListener;
	
	@Override
	public abstract String toString();
	public abstract void onStep();

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	public WorldListener getWorldListener() {
		return worldListener;
	}
	public void setWorldListener(WorldListener worldListener) {
		this.worldListener = worldListener;
	}
	
	
}
