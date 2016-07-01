package com.ilyamur.libgdx.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ilyamur.libgdx.Tools.B2WorldCreator;

public class Coin extends InteractiveTileObject {

    public Coin(World world, TiledMap tiledMap, Rectangle bounds) {
        super(world, tiledMap, bounds);
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        addStaticBody(world, bounds);
    }
}
