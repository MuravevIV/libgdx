package com.ilyamur.libgdx.sprites;

import static com.ilyamur.libgdx.MarioBrosGame.ppm;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class InteractiveTileObject {

    private final World world;
    private final TiledMap tiledMap;
    private final Rectangle bounds;

    private TiledMapTile tiledMapTile;

    public InteractiveTileObject(World world,
                                 TiledMap tiledMap,
                                 Rectangle bounds) {
        this.world = world;
        this.tiledMap = tiledMap;
        this.bounds = bounds;
    }

    protected void addStaticBody(World world, Rectangle rect) {
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
