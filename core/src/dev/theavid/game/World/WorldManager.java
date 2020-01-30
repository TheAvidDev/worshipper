package dev.theavid.game.World;

import java.awt.Point;
import java.util.HashMap;
import java.util.Random;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.theavid.game.Player.PlayerManager;
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
	public static final int CHUNK_CACHE_SIZE = 64;
	
	private AssetManager manager;
	
	private HashMap<Point, Chunk> chunkCache;
	
	/**
	 * The random number generators responsible
	 * for world generation.
	 */
	private Random rand = new Random(/* seed */);
	private OpenSimplexNoise elevationPreciseNoise = new OpenSimplexNoise(rand.nextLong());
	private OpenSimplexNoise elevationGeneralNoise = new OpenSimplexNoise(rand.nextLong());
	private OpenSimplexNoise temperatureGeneralNoise = new OpenSimplexNoise(rand.nextLong());
	private OpenSimplexNoise precipitationGeneralNoise = new OpenSimplexNoise(rand.nextLong());

	public WorldManager() {
		chunkCache = new HashMap<Point, Chunk>();
	}
	
	
	/**
	 * Draws world onto the SpriteBatch.
	 * 
	 * @param batch {@link SpriteBatch} to draw onto.
	 * @param playerX Player x coordinate.
	 * @param playerY Player y coordinate.
	 */
	public void draw(SpriteBatch batch) {
		Point playerChunkCoords = PlayerManager.getPlayerChunk();
		
		for (int x = -2; x <= 2; x ++) {
			for (int y = -2; y <= 2; y ++) {
				Point chunkCoords = new Point(playerChunkCoords.x+x, playerChunkCoords.y+y);
				Chunk c = getChunk(chunkCoords);
				c.draw(batch, chunkCoords.x, chunkCoords.y, -PlayerManager.getPlayerX(), -PlayerManager.getPlayerY());
			}
		}
	}
	
	/**
	 * Updates everything having to do with chunks which
	 * currently includes updating the chunk cache.
	 */
	public void update() {
		cleanChunkCache();

		Point playerChunkCoords = PlayerManager.getPlayerChunk();
		
		for (int x = -3; x <= 3; x ++) {
			for (int y = -3; y <= 3; y ++) {
				Point chunkCoords = new Point(playerChunkCoords.x+x, playerChunkCoords.y+y);
				Chunk c = getChunk(chunkCoords);
				c.update();
			}
		}
	}
	
	/**
	 * Gets a chunk from the chunk cache or generates a new one
	 * if it isn't in the cache.
	 * 
	 * @param coords The relative {@link Point} of the chunk.
	 * @return This chunk.
	 */
	private Chunk getChunk(Point coords) {
		if (!chunkCache.containsKey(coords)) {
			Chunk c = new Chunk();
			c.generate(coords.x, coords.y, elevationPreciseNoise, elevationGeneralNoise, temperatureGeneralNoise, precipitationGeneralNoise);
			chunkCache.put(coords, c);
			return c;
		} else {
			return chunkCache.get(coords);
		}
	}
	
	/**
	 * Cleans the chunk cache by continuously finding the farthest chunk
	 * from the player and removing it from the cache until there are
	 * CHUNK_CACHE_SIZE chunk. Only cleans if there are currently more
	 * than CHUNK_CACHE_SIZE chunks.
	 */
	private void cleanChunkCache() {
		while (chunkCache.size() > CHUNK_CACHE_SIZE) {
			double furthestDistance = Double.MIN_VALUE;
			Point playerChunkCoords = PlayerManager.getPlayerChunk();
			Point furthestChunkCoords = playerChunkCoords;
			
			for (Point p : chunkCache.keySet()) {
				double distance = p.distance(playerChunkCoords);
				if (distance > furthestDistance) {
					furthestChunkCoords = p;
					furthestDistance = distance;
				}
			}
			
			chunkCache.remove(furthestChunkCoords);
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
