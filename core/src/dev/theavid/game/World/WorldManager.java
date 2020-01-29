package dev.theavid.game.World;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.theavid.game.Util.OpenSimplexNoise;

/**
 * Manages world loading, unloading, rendering,
 * updating, and everything else related to the in-game
 * world.
 */
public class WorldManager {
	public static final int BLOCK_SIZE = 16;
	public static final int CHUNK_SIZE = 32;
	public static final int WORLD_SIZE = 128;
	
	private AssetManager manager;
	
	/**
	 * The random number generators responsible
	 * for world generation.
	 */
	private Random rand = new Random(/* seed */);
	private OpenSimplexNoise elevationPreciseNoise = new OpenSimplexNoise(rand.nextLong());
	private OpenSimplexNoise elevationGeneralNoise = new OpenSimplexNoise(rand.nextLong());
	private OpenSimplexNoise temperatureGeneralNoise = new OpenSimplexNoise(rand.nextLong());
	private OpenSimplexNoise precipitationGeneralNoise = new OpenSimplexNoise(rand.nextLong());

	/**
	 * Draws world onto the SpriteBatch.
	 * 
	 * @param batch {@link SpriteBatch} to draw onto.
	 * @param playerX Player x coordinate.
	 * @param playerY Player y coordinate.
	 */
	public void draw(SpriteBatch batch, float playerX, float playerY) {
		int cX = (int) Math.floor((playerX + Gdx.graphics.getWidth() / 2) / CHUNK_SIZE / BLOCK_SIZE);
		int cY = (int) Math.floor((playerY + Gdx.graphics.getHeight() / 2) / CHUNK_SIZE / BLOCK_SIZE);
		
		for (int x = -2; x <= 2; x ++) {
			for (int y = -1; y <= 1; y ++) {
				Chunk c = new Chunk();
				c.generate(cX+x, cY+y, elevationPreciseNoise, elevationGeneralNoise, temperatureGeneralNoise, precipitationGeneralNoise);
				c.draw(batch, cX+x, cY+y, -playerX, -playerY);
			}
		}
	}
	
	/**
	 * Loads all block textures.
	 */
	public void loadBlocks() {
		manager = new AssetManager();
		for (BlockID id : BlockID.values()) {
			id.loadTexture(manager);
		}
		manager.finishLoading();
		
		
		for (BlockID id : BlockID.values()) {
			id.assignTexture(manager);
		}
	}
}
