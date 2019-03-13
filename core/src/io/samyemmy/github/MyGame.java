package io.samyemmy.github;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;

import io.samyemmy.github.tamagotchi.Tamagotchi;

public class MyGame extends Game
{
	private static MyGame instance;
	private static final String TAG = "MyGame";
	public static Skin skinDefault;
	private Tamagotchi tamagotchi;
	private FileManager fileManager;
	private Android android;
	private MainScreen mainScreen;

	private MyGame(Android android)
	{
		setAndroid(android);
	}

	public static MyGame getInstance(Android android)
	{
		if (instance == null)
		{
			instance = new MyGame(android);
		}
		return instance;
	}

	public static MyGame getInstance()
	{
		if (MyGame.instance == null)
		{
			Gdx.app.debug(TAG, "getInstance()");
			Gdx.app.debug(TAG,"First call getInstance(Android android) to init Game.");
			return null;
		}
		return MyGame.instance;
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
		MyGame.skinDefault = new Skin(Gdx.files.internal("mySkin.json"), new TextureAtlas(Gdx.files.internal("atlas/myAtlas.atlas")));
		FileManager fileManager = new FileManager(Gdx.files.local("").file());
		setTamagotchi(new Tamagotchi(fileManager.deserialize()));
		MainScreen mainScreen = new MainScreen();
		this.fileManager = fileManager;
		this.setScreen(mainScreen);
		this.mainScreen = mainScreen;
	}

	@Override
	public void resume()
	{
		super.resume();
		Gdx.app.debug(TAG, "resume()");
		setTamagotchi(new Tamagotchi(fileManager.deserialize()));
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

