package com.ilyamur.libgdx.Tools;

import static com.ilyamur.libgdx.MarioBrosGame.ppm;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class B2WorldCreator {

    private static final int GROUND_LAYER = 2;
    public static final int PIPES_LAYER = 3;
    public static final int BRICKS_LAYER = 5;
    public static final int COINS_LAYER = 4;

    private final World world;
    private final TiledMap tiledMap;

    public B2WorldCreator(World world, TiledMap tiledMap) {
        this.world = world;
        this.tiledMap = tiledMap;
    }

    public void addStaticBodiesAll() {
        addStaticBodies(GROUND_LAYER);
        addStaticBodies(PIPES_LAYER);
        addStaticBodies(BRICKS_LAYER);
        addStaticBodies(COINS_LAYER);
    }

    private void addStaticBodies(int groundLayer) {
        for (RectangleMapObject mapObject : getLayerObjects(tiledMap, groundLayer)) {
            addStaticBody(mapObject);
        }
    }

    private Array<RectangleMapObject> getLayerObjects(TiledMap map, int groundLayer) {
        return map.getLayers().get(groundLayer).getObjects().getByType(RectangleMapObject.class);
    }

    private void addStaticBody(RectangleMapObject mapObject) {
        Rectangle rect = mapObject.getRectangle();

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
}
