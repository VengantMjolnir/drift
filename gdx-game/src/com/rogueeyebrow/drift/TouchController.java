package com.rogueeyebrow.drift;

import com.badlogic.gdx.InputProcessor;
import java.util.*;

public class TouchController implements InputProcessor
{
	class TouchInfo
	{
		public float touchY = 0;
		public float touchX = 0;
		public boolean touched = false;
	}

	private Map<Integer, TouchInfo> touches = new HashMap<Integer, TouchInfo>();

	public void init() {
		for(int i = 0; i < 10; i++){
            touches.put(i, new TouchInfo());
        }
	}

	public TouchInfo getTouch(int index) {
		if ( index < 10 ) {
			return touches.get(index);
		}
		return null;
	}
	
	@Override
    public boolean keyDown(int keycode)
	{
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
	{
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character)
	{
        // TODO Auto-generated method stub
        return false;
    }

	@Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
        if (pointer < 10)
		{
            touches.get(pointer).touchX = screenX;
            touches.get(pointer).touchY = screenY;
            touches.get(pointer).touched = true;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
        if (pointer < 10)
		{
            touches.get(pointer).touchX = 0;
            touches.get(pointer).touchY = 0;
            touches.get(pointer).touched = false;
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
	{
        // TODO Auto-generated method stub
        return false;

	}

	@Override
    public boolean mouseMoved(int screenX, int screenY)
	{
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount)
	{
        // TODO Auto-generated method stub
        return false;
    }
}
