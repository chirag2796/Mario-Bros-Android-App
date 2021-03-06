package com.chirag.mariobros.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.chirag.mariobros.MyGdxGame;
import com.chirag.mariobros.Screens.PlayScreen;
import com.chirag.mariobros.Sprites.TileObjects.Brick;
import com.chirag.mariobros.Sprites.TileObjects.Coin;
import com.chirag.mariobros.Sprites.Enemies.Goomba;

/**
 * Created by CHIRAG on 14-10-2016.
 */
public class B2WorldCreator {

    private Array<Goomba> goombas;

    public B2WorldCreator(PlayScreen screen){
        World world = screen.getWorld();
        TiledMap map = screen.getMap();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //ground
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2)/ MyGdxGame.PPM, (rect.getY() + rect.getHeight() / 2)/ MyGdxGame.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2/ MyGdxGame.PPM, rect.getHeight()/2/ MyGdxGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //pipes
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2)/ MyGdxGame.PPM, (rect.getY() + rect.getHeight() / 2)/ MyGdxGame.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2/ MyGdxGame.PPM, rect.getHeight()/2/ MyGdxGame.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = MyGdxGame.OBJECT_BIT;
            body.createFixture(fdef);
        }

        //bricks
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            new Brick(screen, object);
        }

        //coins
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            new Coin(screen, object);
        }

        //create all goombas
        goombas = new Array<Goomba>();
        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            goombas.add(new Goomba(screen, rect.getX() / MyGdxGame.PPM, rect.getY() / MyGdxGame.PPM));
        }
    }

    public Array<Goomba> getGoombas() {
        return goombas;
    }
}
