package dev.theavid.game.World;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** 
 * A single block.
 */
public class Block {
	BlockID id;
	
	/**
	 * Initialize a new block.
	 * 
	 * @param id {@link BlockID} of this block.
	 */
	public Block(BlockID id) {
		this.id = id;
	}
	
	/**
	 * Draws this block.
	 * 
	 * @param batch {@link SpriteBatch} to draw the block on.
	 * @param x X coordinate of the block.
	 * @param y Y coordinate of the block.
	 * @param offsetX X coordinate offset.
	 * @param offsetY Y coordinate offset.
	 */
	public void draw(SpriteBatch batch, int x, int y, float offsetX, float offsetY) {
		batch.draw(id.texture(), x + offsetX, y + offsetY);
	}
}
