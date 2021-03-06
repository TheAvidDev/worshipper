package dev.theavid.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
	private float x;
	private float y;
	private float vx;
	private float vy;
	private float rotation;
	
	private Texture texture;
	
	/**
	 * Create a player at a location.
	 * 
	 * @param x X coordinate of spawn location.
	 * @param y Y coordinate of spawn location.
	 */
	public Player(float x, float y) {
		this.x = x;
		this.y = y;
		vx = 0;
		vy = 0;
	}
	
	/**
	 * Loads the player texture.
	 * 
	 * @param manager {@link AssetManager} to load the texture
	 * in to.
	 */
	public void loadTexture(AssetManager manager) {
		manager.load("player/player.png", Texture.class);
	}
	
	/**
	 * Assigns the player texture to the player.
	 * 
	 * @param manager {@link AssetManager} which contains
	 * the loaded texture.
	 */
	public void assignTexture(AssetManager manager) {
		texture = manager.get("player/player.png", Texture.class);
	}
	
	/**
	 * Draws the player on the screen.
	 * @param batch {@link SpriteBatch} to draw on.
	 */
	public void draw(SpriteBatch batch) {
		batch.draw(texture, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 8, 8, 16, 16, 1, 1, rotation, 0, 0, 16, 16, false, false);
	}
	
	/**
	 * Updates the player position and direction.
	 * 
	 * @param dt Time since last tick.
	 */
	public void update(float dt) {
		updatePosition(dt);
		updateRotation();
	}
	
	/**
	 * Updates the player position
	 * 
	 * @param dt Time since last tick.
	 */
	private void updatePosition(float dt) {
		float mult = 150 * dt;
		if(Gdx.input.isKeyPressed(Keys.W)) {
		     vy += 1f * mult;
		}
		if(Gdx.input.isKeyPressed(Keys.A)) {
		     vx -= 1f * mult;
		}
		if(Gdx.input.isKeyPressed(Keys.S)) {
		     vy -= 1f * mult;
		}
		if(Gdx.input.isKeyPressed(Keys.D)) {
		     vx += 1f * mult;
		}
		x += vx;
		y += vy;
	}
	
	/**
	 * Updates the player rotation.
	 */
	private void updateRotation() {		
		if (vx != 0 || vy != 0) {
			rotation = (float) (Math.atan2(vy, vx) / Math.PI * 180 - 90);
		} else if (vx == 0 && vy != 0) {
			rotation = vy > 0 ? 0 : 180;
		} else if (vy == 0 && vx != 0) {
			rotation = vx > 0 ? -90 : 90;
		}
		
		if (Math.abs(vx) < 0.1 && Math.abs(vx) < 0.1) {
			vx = 0;
			vy = 0;
		}
		
		vx *= 0.5;
		vy *= 0.5;
	}
	
	public float getX() { return x; }
	public float getY() { return y; }
}
