package com.chirag.mariobros.Sprites.TileObjects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;
import com.chirag.mariobros.MyGdxGame;
import com.chirag.mariobros.Scenes.Hud;
import com.chirag.mariobros.Screens.PlayScreen;
import com.chirag.mariobros.Sprites.Mario;

/**
 * Created by CHIRAG on 14-10-2016.
 */
public class Brick extends InteractiveTileObject{
    public Brick(PlayScreen screen, MapObject object){
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(MyGdxGame.BRICK_BIT);
    }

    @Override
    public void onHeadHit(Mario mario) {
        if(mario.isBig()) {
            setCategoryFilter(MyGdxGame.DESTROYED_BIT);
            getCell().setTile(null);
            Hud.addScore(200);
            MyGdxGame.manager.get("audio/sounds/breakblock.wav", Sound.class).play();
        }
        else{
            MyGdxGame.manager.get("audio/sounds/bump.wav", Sound.class).play();
        }
    }
}
