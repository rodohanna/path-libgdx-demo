package com.path.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Pathstroids extends ApplicationAdapter {
	private final static int WORLD_WIDTH = 10000;
	private final static int WORLD_HEIGHT = 10000;
	private final static int VIEWPORT_WIDTH = 640;
	private final static int VIEWPORT_HEIGHT = 480;
	private SpriteBatch batch;
	private Texture background;
	private SpaceShip ship;
	private OrthographicCamera camera;
	private Viewport viewport;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("asteroids/background1.png");
		background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		ship = new SpaceShip();
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, camera);
	}

	@Override
	public void render () {
		//update
		ship.update(Gdx.graphics.getDeltaTime(), camera);

		ScreenUtils.clear(0, 1, 0, 1);
		camera.position.set(ship.getPosition());
		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(background, -WORLD_WIDTH, -WORLD_HEIGHT, 0, 0, 2 * WORLD_WIDTH, 2 * WORLD_HEIGHT);
		ship.render(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}
}
