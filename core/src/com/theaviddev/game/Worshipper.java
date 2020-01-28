package com.theaviddev.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.theaviddev.game.Player.Player;
import com.theaviddev.game.World.WorldManager;

/**
 * Main game class.
 */
public class Worshipper extends ApplicationAdapter {
	private SpriteBatch batch;
	private WorldManager worldManager;
	private Player player;
	
	/**
	 * Initializes required objects.
	 */
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		worldManager = new WorldManager();
		worldManager.loadBlocks();
		
		player = new Player(0, 0);
		player.loadTexture();
		player.assignTexture();
	}
	
	
	/**
	 * Renders all screen elements.
	 */
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		worldManager.draw(batch, player.getX(), player.getY());
		player.draw(batch);
		batch.end();
		
		player.update();
	}
	
	/**
	 * Disposes graphics.
	 */
	@Override
	public void dispose () {
		batch.dispose();
	}
}
