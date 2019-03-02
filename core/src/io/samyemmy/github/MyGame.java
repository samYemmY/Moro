package io.samyemmy.github;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.TimeUtils;

import javax.naming.BinaryRefAddr;

public class MyGame extends Game {
    public SpriteBatch batch;
	public static TextureAtlas atlas;
	public static Skin skinDefault;

	@Override
	public void create () {
        batch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal("atlas/myAtlas.atlas"));
		skinDefault = new Skin(Gdx.files.internal("mySkin.json"), atlas);
        this.setScreen(new MainScreen(this));
	}

	@Override
	public void render () {
        super.render();
	}
	
	@Override
	public void dispose () {

	}
}

