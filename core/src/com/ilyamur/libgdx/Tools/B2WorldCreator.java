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
import com.ilyamur.libgdx.sprites.Brick;
import com.ilyamur.libgdx.sprites.Coin;

public class B2WorldCreator {

    private static final int GROUND_LAYER = 2;
    public static final int PIPES_LAYER = 3;
    public static final int BRICKS_LAYER = 5;
    public static final int COINS_LAYER = 4;

    private final World world;
    private final TiledMap map;

    public B2WorldCreator(World world, TiledMap map) {
        this.world = world;
        this.map = map;
    }

    public void addStaticBodiesAll() {
        for (RectangleMapObject mapObject3 : getLayerObjects(map, GROUND_LAYER)) {
            Rectangle rect3 = mapObject3.getRectangle();
            BodyDef bodyDef3 = new BodyDef();
            bodyDef3.type = BodyDef.BodyType.StaticBody;
            bodyDef3.position.set(ppm(rect3.getX() + rect3.getWidth() / 2), ppm(rect3.getY() + rect3.getHeight() / 2));
            Body body3 = world.createBody(bodyDef3);

            PolygonShape polygonShape3 = new PolygonShape();
            polygonShape3.setAsBox(ppm(rect3.getWidth() / 2), ppm(rect3.getHeight() / 2));
            FixtureDef fixtureDef3 = new FixtureDef();
            fixtureDef3.shape = polygonShape3;
            body3.createFixture(fixtureDef3);
        }
        for (RectangleMapObject mapObject2 : getLayerObjects(map, PIPES_LAYER)) {
            Rectangle rect2 = mapObject2.getRectangle();
            BodyDef bodyDef2 = new BodyDef();
            bodyDef2.type = BodyDef.BodyType.StaticBody;
            bodyDef2.position.set(ppm(rect2.getX() + rect2.getWidth() / 2), ppm(rect2.getY() + rect2.getHeight() / 2));
            Body body2 = world.createBody(bodyDef2);

            PolygonShape polygonShape2 = new PolygonShape();
            polygonShape2.setAsBox(ppm(rect2.getWidth() / 2), ppm(rect2.getHeight() / 2));
            FixtureDef fixtureDef2 = new FixtureDef();
            fixtureDef2.shape = polygonShape2;
            body2.createFixture(fixtureDef2);
        }
        for (RectangleMapObject mapObject : getLayerObjects(map, BRICKS_LAYER)) {
            new Brick(world, map, mapObject.getRectangle());
        }
        for (RectangleMapObject mapObject : getLayerObjects(map, COINS_LAYER)) {
            new Coin(world, map, mapObject.getRectangle());
        }
    }

    private Array<RectangleMapObject> getLayerObjects(TiledMap map, int groundLayer) {
        return map.getLayers().get(groundLayer).getObjects().getByType(RectangleMapObject.class);
    }

}
