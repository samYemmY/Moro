package io.samyemmy.github;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import io.samyemmy.github.MyGame;
import io.samyemmy.github.TabBar;
import io.samyemmy.github.dialog.ActionDialog;
import io.samyemmy.github.dialog.BaseDialog;
import io.samyemmy.github.dialog.ChooseMealContent;
import io.samyemmy.github.dialog.StatsDialog;

public class MainScreen implements Screen
{
    private static final String TAG = "MainScreen";
    private MyGame game;
    private OrthographicCamera camera;
    private Stage stage;

    ActionDialog getActionDialog() {
        return actionDialog;
    }

    private ActionDialog actionDialog;

    public StatsDialog getStatsDialog() {
        return statsDialog;
    }

    private StatsDialog statsDialog;

    MainScreen(MyGame game)
    {
        this.game = game;
        this.actionDialog = new ActionDialog();
        this.statsDialog = new StatsDialog();
        
        OrthographicCamera cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera = cam;

        TabBar tabBar = new TabBar(this);

        Stage stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera));
        stage.addActor(game.getTamagotchi());
        stage.addActor(tabBar);
        stage.addActor(actionDialog);
        stage.addActor(statsDialog);
        Gdx.input.setInputProcessor(stage);
        this.stage = stage;
    }

    @Override
    public void show() {
        Gdx.app.debug(TAG, "show()");
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

    void toggleDialog(BaseDialog dialog)
    {
        dialog.toggle();
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
