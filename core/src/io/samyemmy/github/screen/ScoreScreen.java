package io.samyemmy.github.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import io.samyemmy.github.FontActor;
import io.samyemmy.github.MyGame;

public class ScoreScreen implements Screen
{
    private OrthographicCamera camera;
    private Stage stage;
    private FontActor title;

    public ScoreScreen(int score)
    {
        OrthographicCamera cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera = cam;

        title = new FontActor("Your Score: " + score, 40);
        title.setPosition(Gdx.graphics.getWidth() / 2f - title.getWidth() / 2, Gdx.graphics.getHeight() / 2f - title.getHeight() / 2 + 50);

        this.stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera));
        stage.addActor(title);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show()
    {
        new Timer().scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        MyGame.getInstance().setScreen(MyGame.getInstance().getMainScreen());
                    }
                });
            }
        }, 2);
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
