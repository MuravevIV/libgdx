package com.ilyamur.libgdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ilyamur.libgdx.screens.PlayScreen;

public class MarioBros extends com.badlogic.gdx.Game {

    public static final float V_WIDTH = 400;
    public static final float V_HEIGHT = 208;
    public static final float PPM = 100;

    private SpriteBatch batch;

    @Override
    public void create() {
        setBatch(new SpriteBatch());
        setScreen(new PlayScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    private void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }
}
