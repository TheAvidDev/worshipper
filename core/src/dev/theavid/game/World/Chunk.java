package dev.theavid.game.World;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.theavid.game.Entities.TileEntity;
import dev.theavid.game.Util.OpenSimplexNoise;

/** 
 * A chunk that contains many blocks for more efficient rendering.
 */
public class Chunk {
	private Block[][] blocks;
	private ArrayList<TileEntity> tileEntities;
	private ChunkGenerator gen;
	
	
	public Chunk() {
		tileEntities = new ArrayList<TileEntity>();
		tileEntities.add(new TileEntity(5,4));
	}
	
	public void generate(int cX, int cY, OpenSimplexNoise elevationPreciseNoise, OpenSimplexNoise elevationGeneralNoise, OpenSimplexNoise temperatureGeneralNoise, OpenSimplexNoise precipitationGeneralNoise) {
		gen = new ChunkGenerator(cX, cY, elevationPreciseNoise, elevationGeneralNoise, temperatureGeneralNoise, precipitationGeneralNoise);
		Thread chunkGen = new Thread(gen);
		chunkGen.start();
	}
	
	public void update() {		
		if (gen == null)
			return;
		if (gen.isFinished()) {
			blocks = gen.getBlocks();
			gen = null;
		}
	}
	
	
	/**
	 * Draws the chunk.
	 * 
	 * @param batch {@link SpriteBatch} to draw on to.
	 * @param x X coordinate to draw chunk at.
	 * @param y Y coordinate to draw chunk at.
	 * @param offsetX X coordinate offset for sub-chunk movement.
	 * @param offsetY Y coordinate offset for sub-chunk movement.
	 */
	public void draw(SpriteBatch batch, int x, int y, float offsetX, float offsetY) {
		if (blocks == null) {
			new Block(BlockID.COLD_SAND).draw(batch,
					(x*WorldManager.CHUNK_SIZE)*WorldManager.BLOCK_SIZE,
					(y*WorldManager.CHUNK_SIZE)*WorldManager.BLOCK_SIZE,
					offsetX, offsetY);
			return;
		}
		
		for (int w = 0; w < WorldManager.CHUNK_SIZE; w ++) {
			for (int h = 0; h < WorldManager.CHUNK_SIZE; h ++) {
				blocks[w][h].draw(batch,
						(x*WorldManager.CHUNK_SIZE+w)*WorldManager.BLOCK_SIZE,
						(y*WorldManager.CHUNK_SIZE+h)*WorldManager.BLOCK_SIZE,
						offsetX, offsetY);
			}
		}

		for (int i = tileEntities.size()-1; i >= 0 ; i --) {
			if (tileEntities.get(i).shouldRemove()) {
				tileEntities.remove(i);
				continue;
			}
			tileEntities.get(i).draw(batch, x, y, offsetX, offsetY);
		}
	}
}
