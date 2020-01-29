package dev.theavid.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.theavid.game.Player.Player;
import dev.theavid.game.World.WorldManager;

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
		
		float dt = Gdx.graphics.getDeltaTime();
		player.update(dt);
	}
	
	/**
	 * Disposes graphics.
	 */
	@Override
	public void dispose () {
		batch.dispose();
	}
}
