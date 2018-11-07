package com.rogueeyebrow.drift;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

public class GameController{
	private int gameTick;
	private BitmapFont font;

	public void init() {
		gameTick = 0;
		font = new BitmapFont();
        font.setColor(Color.RED);
	}
	
	public void tick(){
		gameTick += 1;
	}

	public void render(SpriteBatch batch){
		String message = "tick: " + gameTick;
		font.drawMultiLine(batch, message, 50, 50);
	}
}
