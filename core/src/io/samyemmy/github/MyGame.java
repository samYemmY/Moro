package io.samyemmy.github;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import io.samyemmy.github.screen.MainScreen;

public class MyGame extends Game
{
	private static MyGame INSTANCE;
	public static String CHANNEL_ID = "420";
	public static Skin SKIN;
	public static TextureAtlas ATLAS;
	private Tamagotchi tamagotchi;
	private FileManager fileManager;
	private Android android;
	private MainScreen mainScreen;
	private ActionQueue actionQueue;

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
	public ActionQueue getActionQueue(){ return actionQueue; }

	@Override
	public void create ()
	{
//		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		SoundHandler.getInstance();
		TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("atlas/myAtlas.atlas"));
		MyGame.SKIN = new Skin(Gdx.files.internal("mySkin.json"), textureAtlas);
		MyGame.ATLAS = textureAtlas;
		FileManager fileManager = new FileManager(Gdx.files.local("").file());
		this.fileManager = fileManager;
		setTamagotchi(new Tamagotchi(fileManager.deserialize()));
		actionQueue = new ActionQueue(getTamagotchi());
		MainScreen mainScreen = new MainScreen(getTamagotchi());
		this.mainScreen = mainScreen;
		this.setScreen(mainScreen);
		tamagotchi.update();
	}

	@Override
	public void resume()
	{
		super.resume();
		SoundHandler.getInstance();
		setTamagotchi(new Tamagotchi(fileManager.deserialize()));
		getMainScreen().setTamagotchi(getTamagotchi());
		actionQueue = new ActionQueue(getTamagotchi());
		MyGame.getInstance().getMainScreen().getUpdateManager().foreground();
	}

	@Override
	public void pause()
	{
		super.pause();
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

	}
}

