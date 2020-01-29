package dev.theavid.game.Player;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.theavid.game.World.WorldManager;

public class PlayerManager {
	private static Player mainPlayer;
	AssetManager manager;
	
	/**
	 * Creates the main, human controlled player at a position.
	 * 
	 * @param x X coordinate of main player.
	 * @param y Y coordinate of main player.
	 */
	public void createMainPlayer(float x, float y) {
		mainPlayer = new Player(x, y);
	}
	
	/**
	 * Draws the player on to the screen.
	 * 
	 * @param batch {@link SpriteBatch} to draw on.
	 */
	public void draw(SpriteBatch batch) {
		mainPlayer.draw(batch);
	}
	
	/**
	 * Updates all players.
	 * 
	 * @param dt Time since last frame.
	 */
	public void update(float dt) {
		mainPlayer.update(dt);
	}
	
	/**
	 * Loads all player textures.
	 */
	public void loadTextures() {
		manager = new AssetManager();
		mainPlayer.loadTexture(manager);
		manager.finishLoading();
		
		mainPlayer.assignTexture(manager);
	}
	
	/**
	 * Gets player x coordinate.
	 * 
	 * @return Precise x coordinate in world.
	 */
	public static float getPlayerX() {
		return mainPlayer.getX();
	}
	
	/**
	 * Gets player y coordinate.
	 * 
	 * @return Precise y coordinate in world.
	 */
	public static float getPlayerY() {
		return mainPlayer.getY();
	}
	
	/**
	 * Gets the chunk the player is in.
	 * 
	 * @return {@link Point} with the relative coordinates
	 * of the chunk.
	 */
	public static Point getPlayerChunk() {
		int cX = (int) Math.floor((getPlayerX() + Gdx.graphics.getWidth() / 2) / WorldManager.CHUNK_SIZE / WorldManager.BLOCK_SIZE);
		int cY = (int) Math.floor((getPlayerY() + Gdx.graphics.getHeight() / 2) / WorldManager.CHUNK_SIZE / WorldManager.BLOCK_SIZE);
		return new Point(cX, cY);
	}
}
