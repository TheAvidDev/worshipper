package com.theaviddev.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.theaviddev.game.World.WorldManager;

/**
 * Main game class.
 */
public class Worshipper extends ApplicationAdapter {
	private SpriteBatch batch;
	private WorldManager worldManager;
	
	private float x = 0;
	private float y = 0;
	
	/**
	 * Initializes required objects.
	 */
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		worldManager = new WorldManager();
		worldManager.loadBlocks();
	}
	
	/**
	 * Renders all screen elements.
	 */
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		worldManager.draw(batch, x, y);
		batch.end();
		
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
	
	/**
	 * Disposes graphics.
	 */
	@Override
	public void dispose () {
		batch.dispose();
	}
}
