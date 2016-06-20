package com.ilyamur.libgdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ilyamur.libgdx.MarioBrosGame;
import com.ilyamur.libgdx.scenes.Hud;

public class PlayScreen extends ScreenAdapter {

    private final MarioBrosGame marioBrosGame;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final Hud hud;
    private final MapRenderer mapRenderer;

    public PlayScreen(MarioBrosGame marioBrosGame) {
        this.marioBrosGame = marioBrosGame;

        camera = new OrthographicCamera();
        viewport = new FitViewport(ppm(MarioBrosGame.V_WIDTH), ppm(MarioBrosGame.V_HEIGHT), camera);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

        hud = new Hud(marioBrosGame.getBatch());
        mapRenderer = new OrthogonalTiledMapRenderer(loadMap("level1.tmx"), ppm(1));
    }

    private float ppm(float value) {
        return value / MarioBrosGame.PPM;
    }

    private TiledMap loadMap(String fileName) {
        return (new TmxMapLoader()).load(fileName);
    }

    public void update(float delta) {
        camera.update();
        mapRenderer.setView(camera);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.render();

        marioBrosGame.getBatch().setProjectionMatrix(hud.getStage().getCamera().combined);
        hud.getStage().draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
