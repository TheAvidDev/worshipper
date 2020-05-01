package dev.theavid.game.Entities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.theavid.game.World.WorldManager;

public class TileEntity {
	private float x;
	private float y;
	private Texture texture;
	private boolean remove;
	
	/**
	 * Creates a new tile entity at the given, relative coordinates in the chunk.
	 * 
	 * @param x Relative X coordinate within the chunk.
	 * @param y Relative Y coordinate within the chunk.
	 */
	public TileEntity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Loads the tile entity's texture.
	 * 
	 * @param manager {@link AssetManager} to load the texture
	 * in to.
	 */
	public void loadTexture(AssetManager manager) {
		manager.load("entities/tiles/box.png", Texture.class);
	}
	
	/**
	 * Assigns the tile entity's texture to the tile entity.
	 * 
	 * @param manager {@link AssetManager} to load the texture
	 * in to.
	 */
	public void assignTexture(AssetManager manager) {
		texture = manager.get("entities/tiles/box.png", Texture.class);
	}
	
	/**
	 * Whether this tile entity should be removed.
	 * 
	 * @return the tile entity's removal state.
	 */
	public boolean shouldRemove() {
		return remove;
	}
	
	/**
	 * Draw the tile entity on the screen.
	 * 
	 * @param batch {@link SpriteBatch} to draw on to.
	 * @param cX Relative X coordinate of chunk.
	 * @param cY Relative Y coordinate of chunk.
	 * @param playerX X coordinate of player.
	 * @param playerY Y coordinate of player.
	 */
	public void draw(SpriteBatch batch, int cX, int cY, float playerX, float playerY) {
		batch.draw(texture, cX*WorldManager.CHUNK_SIZE*WorldManager.BLOCK_SIZE+getX()*WorldManager.BLOCK_SIZE+playerX, cY*WorldManager.CHUNK_SIZE*WorldManager.BLOCK_SIZE+getY()*WorldManager.BLOCK_SIZE+playerY);
	}
	
	public float getX() { return x; }
	public float getY() { return y; }
}
