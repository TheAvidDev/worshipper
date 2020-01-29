package dev.theavid.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.theavid.game.Player.PlayerManager;
import dev.theavid.game.World.WorldManager;

/**
 * Main game class.
 */
public class Worshipper extends ApplicationAdapter {
	private SpriteBatch batch;
	private static WorldManager worldManager;
	private PlayerManager playerManager;
	
	/**
	 * Initializes required objects.
	 */
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		worldManager = new WorldManager();
		worldManager.loadBlocks();
		
		playerManager = new PlayerManager();
		playerManager.createMainPlayer(0, 0);
		playerManager.loadTextures();
	}
	
	
	/**
	 * Renders all screen elements.
	 */
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		worldManager.draw(batch);
		playerManager.draw(batch);
		batch.end();
		
		float dt = Gdx.graphics.getDeltaTime();
		worldManager.update();
		playerManager.update(dt);
	}
	
	/**
	 * Disposes graphics.
	 */
	@Override
	public void dispose () {
		batch.dispose();
	}
}
