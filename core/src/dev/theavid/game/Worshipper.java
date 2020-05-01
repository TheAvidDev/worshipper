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
		float dt = Gdx.graphics.getDeltaTime();
		worldManager.update();
		playerManager.update(dt);
		
		batch.begin();
		worldManager.draw(batch);
		playerManager.draw(batch);
		batch.end();
		
		System.out.println("FPS: " + Gdx.graphics.getFramesPerSecond());
	}
	
	/**
	 * Disposes graphics.
	 */
	@Override
	public void dispose () {
		batch.dispose();
	}
}
