package dev.theavid.game.World;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Enum containing all different blocks.
 */
public enum BlockID {
	AIR (0, "void"),
	DIRT (1, "dirt"),
	GRASS (2, "grass"),
	SAND (3, "sand"),
	RED_SAND (4, "red_sand"),
	STONE (5, "stone"),
	WATER (6, "water"),
	SNOW (7, "snow"),
	ICE (8, "ice"),
	COLD_SAND (9, "cold_sand"),
	COLD_RED_SAND (10, "cold_red_sand");
	
	private final int id;
	private final String texture_name;
	private Texture texture;
	
	BlockID(int id, String texture_name) {
		this.id = id;
		this.texture_name = texture_name;
	}
	
	/**
	 * Load texture into the asset manager.
	 * 
	 * @param manager {@link AssetManager} to load texture in.
	 */
	public void loadTexture(AssetManager manager) {
		manager.load("blocks/" + texture_name + ".png", Texture.class);
	}
	
	/**
	 * Set the block's texture.
	 * 
	 * @param manager {@link AssetManager} to get the texture from.
	 */
	public void assignTexture(AssetManager manager) {
		texture = manager.get("blocks/" + texture_name + ".png", Texture.class);
	}
	
	public int id() { return id; }
	public Texture texture() { return texture; }
}
