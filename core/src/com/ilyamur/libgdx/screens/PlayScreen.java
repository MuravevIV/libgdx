package com.ilyamur.libgdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ilyamur.libgdx.MarioBrosGame;
import com.ilyamur.libgdx.scenes.Hud;
import com.ilyamur.libgdx.sprites.Mario;

public class PlayScreen extends ScreenAdapter {

    private static final int GROUND_LAYER = 2;
    public static final int PIPES_LAYER = 3;
    public static final int BRICKS_LAYER = 5;
    public static final int COINS_LAYER = 4;

    private final MarioBrosGame marioBrosGame;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final Hud hud;
    private final MapRenderer mapRenderer;

    private final World world;
    private final Box2DDebugRenderer debugRenderer;
    private final Mario mario;

    public PlayScreen(MarioBrosGame marioBrosGame) {
        this.marioBrosGame = marioBrosGame;

        camera = new OrthographicCamera();
        viewport = new FitViewport(ppm(MarioBrosGame.V_WIDTH), ppm(MarioBrosGame.V_HEIGHT), camera);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

        hud = new Hud(marioBrosGame.getBatch());
        TiledMap map = loadMap("level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, ppm(1));

        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();

        addStaticBodies(map, GROUND_LAYER);
        addStaticBodies(map, PIPES_LAYER);
        addStaticBodies(map, BRICKS_LAYER);
        addStaticBodies(map, COINS_LAYER);

        mario = new Mario(this.world);
    }

    private void addStaticBodies(TiledMap map, int groundLayer) {
        for (RectangleMapObject object : getLayerObjects(map, groundLayer)) {
            addStaticBody(object);
        }
    }

    private void addStaticBody(RectangleMapObject object) {
        Rectangle rect = object.getRectangle();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(ppm(rect.getX() + rect.getWidth() / 2), ppm(rect.getY() + rect.getHeight() / 2));
        Body body = world.createBody(bodyDef);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(ppm(rect.getWidth() / 2), ppm(rect.getHeight() / 2));
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        body.createFixture(fixtureDef);
    }

    private Array<RectangleMapObject> getLayerObjects(TiledMap map, int groundLayer) {
        return map.getLayers().get(groundLayer).getObjects().getByType(RectangleMapObject.class);
    }

    private float ppm(float value) {
        return value / MarioBrosGame.PPM;
    }

    private TiledMap loadMap(String fileName) {
        return (new TmxMapLoader()).load(fileName);
    }

    public void update(float delta) {
        handleInput(delta);

        world.step(1 / 60f, 6, 2);

        camera.position.x = mario.body.getPosition().x;

        camera.update();
        mapRenderer.setView(camera);
    }

    private void handleInput(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            mario.body.applyLinearImpulse(new Vector2(0, 4f), mario.body.getWorldCenter(), true);
        }
        float xVelocity = mario.body.getLinearVelocity().x;
        handleXMovement(Input.Keys.RIGHT, xVelocity <= 2, 0.1f);
        handleXMovement(Input.Keys.LEFT, xVelocity >= -2, -0.1f);
    }

    private void handleXMovement(int button, boolean limit, float xVelocityInc) {
        if (Gdx.input.isKeyPressed(button) && limit) {
            mario.body.applyLinearImpulse(new Vector2(xVelocityInc, 0), mario.body.getWorldCenter(), true);
        }
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.render();

        debugRenderer.render(world, camera.combined);

        marioBrosGame.getBatch().setProjectionMatrix(hud.getStage().getCamera().combined);
        hud.getStage().draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
