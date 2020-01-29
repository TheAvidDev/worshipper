package dev.theavid.game.Entities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileEntity {
	private float x;
	private float y;
	private Texture texture;
	private boolean remove;
	
	public TileEntity(int x, int y) {
		this.x = x;
		this.y = y;
		AssetManager m = new AssetManager();
		m.load("entities/tiles/box.png", Texture.class);
		m.finishLoading();
		texture = m.get("entities/tiles/box.png", Texture.class);
	}
	
	public boolean shouldRemove() {
		return remove;
	}
	
	public void draw(SpriteBatch batch, float playerX, float playerY) {
		batch.draw(texture, x - playerX, y - playerY);
	}
	
	public float getX() { return x; }
	public float getY() { return y; }
}
