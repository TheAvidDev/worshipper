package com.theaviddev.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
	private float x;
	private float y;
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
		batch.draw(texture, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
	}
	
	public void update() {
		float mult = 5;
		if(Gdx.input.isKeyPressed(Keys.W)) {
		     y += 1f * mult;
		}
		if(Gdx.input.isKeyPressed(Keys.A)) {
		     x -= 1f * mult;
		}
		if(Gdx.input.isKeyPressed(Keys.S)) {
		     y -= 1f * mult;
		}
		if(Gdx.input.isKeyPressed(Keys.D)) {
		     x += 1f * mult;
		}
	}
	
	public float getX() { return x; }
	public float getY() { return y; }
}
