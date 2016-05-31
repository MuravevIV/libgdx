package com.ilyamur.libgdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ilyamur.libgdx.MarioBros;
import com.ilyamur.libgdx.scenes.Hud;

public class PlayScreen extends ScreenAdapter {

    private final MarioBros game;
    private final OrthographicCamera gameCamera;
    private final Viewport gamePort;
    private final Hud hud;
    private final OrthogonalTiledMapRenderer renderer;

    public PlayScreen(MarioBros game) {
        this.game = game;

        gameCamera = new OrthographicCamera();
        gamePort = new FitViewport(ppm(MarioBros.V_WIDTH), ppm(MarioBros.V_HEIGHT), gameCamera);
        gameCamera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        hud = new Hud(game.getBatch());
        renderer = new OrthogonalTiledMapRenderer(loadMap("level1.tmx"), ppm(1));
    }

    private float ppm(float value) {
        return value / MarioBros.PPM;
    }

    private TiledMap loadMap(String fileName) {
        return (new TmxMapLoader()).load(fileName);
    }

    public void update(float delta) {
        gameCamera.update();
        renderer.setView(gameCamera);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        game.getBatch().setProjectionMatrix(hud.getStage().getCamera().combined);
        hud.getStage().draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }
}
