package io.samyemmy.github;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;

public class MainScreen implements Screen
{
    private MyGame game;
    private static int padding = 20;
    private OrthographicCamera camera;
    private Stage stage;
    private Tamagotchi sprTamagotchi;
    private ActionBar actionBar;
    private Launchpad launchpad;
    private Actor dropzone;

    public MainScreen(MyGame game)
    {
        this.game = game;
        sprTamagotchi = new Tamagotchi();
    }

    @Override
    public void show()
    {
        // Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Stage
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera));
        Gdx.input.setInputProcessor(stage);

        Launchpad launchpad = new Launchpad("", this);
        launchpad.setPosition(Gdx.graphics.getWidth() / 2 - launchpad.getWidth() / 2,Gdx.graphics.getHeight() / 2 - launchpad.getHeight() / 2);
        launchpad.setVisible(false);
        this.launchpad = launchpad;
        stage.addActor(launchpad);

        TabBar tabBar = new TabBar(this);
        stage.addActor(tabBar);

//        dropzone = new Actor();
//        dropzone.setBounds(0, viewHeight - viewHeight/6, viewWidth, viewHeight - 2* (viewHeight/6));
//        dropzone.debug();
//        stage.addActor(dropzone);

        // Action Bar
        actionBar = new ActionBar(dropzone);
        actionBar.debug();
        //stage.addActor(actionBar);
    }

    @Override
    public void render(float delta)
    {
        game.batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.begin();
        this.sprTamagotchi.draw(game.batch);
        game.batch.end();
        stage.draw();
        stage.act();
    }

    public void toggleLaunchPad()
    {
        this.launchpad.setVisible(!this.launchpad.isVisible());
    }

    @Override
    public void resize(int width, int height)
    {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
