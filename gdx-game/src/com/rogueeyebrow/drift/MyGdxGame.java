package com.rogueeyebrow.drift;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.*;

public class MyGdxGame implements ApplicationListener, GestureListener
{
	private Texture texture;
	private SpriteBatch batch;
	private Sprite sprite;
	private GameController gameController = new GameController();
	private OrthographicCamera camera;
	private Color background = Color.BLACK;
	private float scaling = 0.5f;
	
	@Override
	public void create()
	{
		camera = new OrthographicCamera(1280, 720);
		
		texture = new Texture(Gdx.files.internal("data/Sample.png"));
		sprite = new Sprite(texture);

		sprite.setOrigin(0,0);

		sprite.setPosition(-sprite.getWidth()/2,-sprite.getHeight()/2);
		batch = new SpriteBatch();
		gameController.init();
		
		GestureDetector gd = new GestureDetector(this);
        Gdx.input.setInputProcessor(gd);
	}

	@Override
	public void render()
	{
	    Gdx.gl.glClearColor(background.r, background.g, background.b, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		sprite.draw(batch);
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
		camera.translate(-deltaX * scaling, deltaY * scaling);
		camera.update();
        return true;
    }

	@Override
	public boolean panStop(float p1, float p2, int p3, int p4)
	{
		return true;
	}

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
						 Vector2 pointer1, Vector2 pointer2) {
        return true;
    }
}
