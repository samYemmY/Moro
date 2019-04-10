package io.samyemmy.github;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import io.samyemmy.github.tamagotchi.DieAction;
import io.samyemmy.github.tamagotchi.Tamagotchi;

public class MyGame extends Game
{
	private static MyGame INSTANCE;
	private static final String TAG = "MyGame";
	public static Skin SKIN;
	public static TextureAtlas ATLAS;
	private Tamagotchi tamagotchi;
	private FileManager fileManager;
	private Android android;
	private MainScreen mainScreen;

	private MyGame(){}

	public static MyGame getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new MyGame();
		}
		return INSTANCE;
	}

	public Android getAndroid() {
		return android;
	}
	public void setAndroid(Android android) {
		this.android = android;
	}
	public Tamagotchi getTamagotchi()
	{
		return tamagotchi;
	}
	void setTamagotchi(Tamagotchi tamagotchi)
	{
		this.tamagotchi = tamagotchi;
	}
	public MainScreen getMainScreen(){ return mainScreen; }

	@Override
	public void create ()
	{
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.app.debug(TAG, "create()");
		TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("atlas/myAtlas.atlas"));
		MyGame.SKIN = new Skin(Gdx.files.internal("mySkin.json"), textureAtlas);
		MyGame.ATLAS = textureAtlas;
		FileManager fileManager = new FileManager(Gdx.files.local("").file());
		this.fileManager = fileManager;
		setTamagotchi(new Tamagotchi(fileManager.deserialize()));
		if (getTamagotchi().isAlive)
		{
			MainScreen mainScreen = new MainScreen();
			this.setScreen(mainScreen);
			this.mainScreen = mainScreen;
			tamagotchi.update(true);
		}
		else
		{
			tamagotchi.executeAction(new DieAction());
		}
	}

	@Override
	public void resume()
	{
		super.resume();
		Gdx.app.debug(TAG, "resume()");
		setTamagotchi(new Tamagotchi(fileManager.deserialize()));
		if (!getTamagotchi().isAlive)
		{
			getTamagotchi().executeAction(new DieAction());
		}
	}

	@Override
	public void pause() {
		super.pause();
		Gdx.app.debug(TAG, "pause()");
		fileManager.serialize(getTamagotchi().getSerializable());
	}

	@Override
	public void render ()
	{
        super.render();
	}
	
	@Override
	public void dispose ()
	{
		Gdx.app.debug(TAG, "dispose()");
	}
}

