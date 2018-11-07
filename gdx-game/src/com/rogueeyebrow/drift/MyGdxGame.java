package com.rogueeyebrow.drift;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.maps.tiled.renderers.*;

public class MyGdxGame implements ApplicationListener, GestureListener
{
	private SpriteBatch batch;
	private Sprite sprite;
	private GameController gameController = new GameController();
	private OrthographicCamera camera;
	private Color background = Color.BLACK;
	private float currentZoom = 1f;
	private TiledMapRenderer tiledMapRenderer;
	private TiledMap tiledMap;
	
	@Override
	public void create()
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		camera.update();
		
		batch = new SpriteBatch();
		gameController.init();
		tiledMap = new TmxMapLoader().load("data/main_map.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
		int mapWidth = tiledMap.getProperties().get("width",Integer.class)/2;
		int mapHeight = tiledMap.getProperties().get("height",Integer.class)/2;
	
		
		
		GestureDetector gd = new GestureDetector(this);
        Gdx.input.setInputProcessor(gd);
	}

	@Override
	public void render()
	{
	    Gdx.gl.glClearColor(background.r, background.g, background.b, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		gameController.render(batch);
		
		batch.end();
	}

	@Override
	public void dispose()
	{
		batch.dispose();
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}

	@Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
		gameController.tick();
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
		camera.translate(-deltaX * currentZoom, deltaY * currentZoom);
		camera.update();
        return false;
    }

	@Override
	public boolean panStop(float p1, float p2, int p3, int p4)
	{
		currentZoom = camera.zoom;
		return false;
	}

    @Override
    public boolean zoom(float initialDistance, float distance) {
		camera.zoom = (initialDistance /distance) * currentZoom;
		camera.update();
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
						 Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
