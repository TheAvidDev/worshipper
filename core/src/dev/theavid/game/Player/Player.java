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
	private AssetManager manager;
	
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
	
	public void loadTexture() {
		manager = new AssetManager();
		manager.load("player/player.png", Texture.class);
		manager.finishLoading();
	}
	
	public void assignTexture() {
		texture = manager.get("player/player.png", Texture.class);
	}
	
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
		updateDirection();
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
	 * Updates the player direction.
	 */
	private void updateDirection() {		
		if (vx != 0 && vy != 0) {
			rotation = (float) (Math.atan2(vy, vx) / Math.PI * 180 - 90);
		}
		
		vx *= 0.5;
		vy *= 0.5;
	}
	
	public float getX() { return x; }
	public float getY() { return y; }
}
