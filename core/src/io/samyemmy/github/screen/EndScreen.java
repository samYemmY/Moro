package io.samyemmy.github.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import io.samyemmy.github.FontActor;
import io.samyemmy.github.MyGame;
import io.samyemmy.github.Tamagotchi;

public class EndScreen implements Screen
{
    private OrthographicCamera camera;
    private Stage stage;
    private FontActor title;
    private Tamagotchi tamagotchi;

    public void setTamagotchi(Tamagotchi newTamagotchi)
    {
        tamagotchi = newTamagotchi;
        stage.addActor(tamagotchi);
    }

    public EndScreen()
    {
        OrthographicCamera cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera = cam;

        title = new FontActor("You Died!", 40);
        title.setPosition(Gdx.graphics.getWidth() / 2f - title.getWidth() / 2, Gdx.graphics.getHeight() / 2f - title.getHeight() / 2 + 200);

        this.stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera));
        stage.addActor(title);
        setTamagotchi(MyGame.getInstance().getTamagotchi());

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show()
    {
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        stage.draw();
        stage.act();
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
