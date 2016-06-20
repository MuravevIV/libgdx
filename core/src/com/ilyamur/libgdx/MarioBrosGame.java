package com.ilyamur.libgdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ilyamur.libgdx.screens.PlayScreen;

public class MarioBrosGame extends Game {

    public static final float V_WIDTH = 400;
    public static final float V_HEIGHT = 208;
    public static final float PPM = 100;

    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new PlayScreen(this));
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
