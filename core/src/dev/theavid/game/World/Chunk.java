package dev.theavid.game.World;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

/** 
 * A chunk that contains many blocks for more efficient rendering.
 */
public class Chunk {
	private Block[][] blocks;
	
	/** 
	 * Normal / Green biome generation.
	 * 
	 * @param precise_elevation The precise elevation of the block.
	 * @param x X coordinate of the block.
	 * @param y Y coordinate of the block.
	 */
	private void normalBiome(double precise_elevation, int w, int h) {
		if (precise_elevation < 0) {
			blocks[w][h] = new Block(BlockID.WATER);
		} else if (precise_elevation < 0.1) {
			blocks[w][h] = new Block(BlockID.SAND);
		} else if (precise_elevation < 0.15) {
			blocks[w][h] = new Block(BlockID.RED_SAND);
		} else if (precise_elevation < 0.2) {
			blocks[w][h] = new Block(BlockID.DIRT);
		} else if (precise_elevation < 0.45) {
			blocks[w][h] = new Block(BlockID.GRASS);
		} else if (precise_elevation < 0.75){
			blocks[w][h] = new Block(BlockID.STONE);
		} else {
			blocks[w][h] = new Block(BlockID.SNOW);
		}
	}
	
	/** 
	 * Cold biome generation.
	 * 
	 * @param precise_elevation The precise elevation of the block.
	 * @param x X coordinate of the block.
	 * @param y Y coordinate of the block.
	 */
	private void coldBiome(double precise_elevation, int w, int h) {
		if (precise_elevation < 0) {
			blocks[w][h] = new Block(BlockID.ICE);
		} else if (precise_elevation < 0.1) {
			blocks[w][h] = new Block(BlockID.COLD_SAND);
		} else if (precise_elevation < 0.15) {
			blocks[w][h] = new Block(BlockID.COLD_RED_SAND);
		} else {
			blocks[w][h] = new Block(BlockID.SNOW);
		}
	}
	
	/** 
	 * Desert biome generation.
	 * 
	 * @param precise_elevation The precise elevation of the block.
	 * @param x X coordinate of the block.
	 * @param y Y coordinate of the block.
	 */
	private void desertBiome(double precise_elevation, int x, int y) {
		if (precise_elevation < 0) {
			blocks[x][y] = new Block(BlockID.SAND);
		} else if (precise_elevation < 0.1) {
			blocks[x][y] = new Block(BlockID.RED_SAND);
		} else if (precise_elevation < 0.15) {
			blocks[x][y] = new Block(BlockID.SAND);
		} else {
			blocks[x][y] = new Block(BlockID.RED_SAND);
		}
	}
	
	/**
	 * Generates the blocks in a chunk based off of {@link OpenSimplexNoise}.
	 * 
	 * @param cX X coordinate of the chunk.
	 * @param cY Y coordinate of the chunk.
	 * @param elevationPreciseNoise Noise for general elevation generation.
	 * @param elevationGeneralNoise Noise for precise elevation generation.
	 * @param temperatureGeneralNoise Noise for temperature generation.
	 * @param precipitationGeneralNoise Noise for precipitation generation.
	 */
	public void generate(int cX, int cY, OpenSimplexNoise elevationPreciseNoise, OpenSimplexNoise elevationGeneralNoise, OpenSimplexNoise temperatureGeneralNoise, OpenSimplexNoise precipitationGeneralNoise) {
		blocks = new Block[WorldManager.CHUNK_SIZE][WorldManager.CHUNK_SIZE];
		for (int bX = 0; bX < WorldManager.CHUNK_SIZE; bX ++) {
			for (int bY = 0; bY < WorldManager.CHUNK_SIZE; bY ++) {
				double size = 1000.0;
				double elevationPrecise = elevationPreciseNoise.eval((cX*WorldManager.CHUNK_SIZE+bX)*WorldManager.BLOCK_SIZE / size, (cY*WorldManager.CHUNK_SIZE+bY)*WorldManager.BLOCK_SIZE / size, 0.5);
				size = 2500.0;
				double elevationGeneral = elevationGeneralNoise.eval((cX*WorldManager.CHUNK_SIZE+bX)*WorldManager.BLOCK_SIZE / size, (cY*WorldManager.CHUNK_SIZE+bY)*WorldManager.BLOCK_SIZE / size, 0.5);
				double temperatureGeneral = temperatureGeneralNoise.eval((cX*WorldManager.CHUNK_SIZE+bX)*WorldManager.BLOCK_SIZE / size, (cY*WorldManager.CHUNK_SIZE+bY)*WorldManager.BLOCK_SIZE / size, 0.5);
				double precipitationGeneral = precipitationGeneralNoise.eval((cX*WorldManager.CHUNK_SIZE+bX)*WorldManager.BLOCK_SIZE / size, (cY*WorldManager.CHUNK_SIZE+bY)*WorldManager.BLOCK_SIZE / size, 0.5);
				
				if (temperatureGeneral < 0) { //Cold
					if (precipitationGeneral < 0) { //Dry
						if (elevationGeneral < 0) { //Low
							coldBiome(elevationPrecise, bX, bY);
						} else { //High
							coldBiome(elevationPrecise, bX, bY);
						}
					} else { //Rainy
						if (elevationGeneral < 0) { //Low
							coldBiome(elevationPrecise, bX, bY);
						} else { //Low
							coldBiome(elevationPrecise, bX, bY);
						}
					}
				} else { //Warm
					
					if (precipitationGeneral < 0) { //Dry
						if (elevationGeneral < 0) { //Low
							normalBiome(elevationPrecise, bX, bY);
							//desertBiome(elevationPrecise, w, h);
						} else { //High
							normalBiome(elevationPrecise, bX, bY);
							//desertBiome(elevationPrecise, w, h);
						}
					} else { //Rainy
						if (elevationGeneral < 0) { //Low
							normalBiome(elevationPrecise, bX, bY);
						} else { //High
							normalBiome(elevationPrecise, bX, bY);
						}
					}
				}
			}
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
		for (int w = 0; w < WorldManager.CHUNK_SIZE; w ++) {
			for (int h = 0; h < WorldManager.CHUNK_SIZE; h ++) {
				blocks[w][h].draw(batch,
						(x*WorldManager.CHUNK_SIZE+w)*WorldManager.BLOCK_SIZE,
						(y*WorldManager.CHUNK_SIZE+h)*WorldManager.BLOCK_SIZE,
						offsetX, offsetY);
			}
		}
	}
	
	ShapeRenderer sr = new ShapeRenderer();
	
}
