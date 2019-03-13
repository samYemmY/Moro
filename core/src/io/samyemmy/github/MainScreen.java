package io.samyemmy.github;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import io.samyemmy.github.MyGame;
import io.samyemmy.github.TabBar;
import io.samyemmy.github.dialog.ActionDialog;
import io.samyemmy.github.dialog.BaseDialog;
import io.samyemmy.github.dialog.ChooseMealContent;
import io.samyemmy.github.dialog.StatsDialog;
import io.samyemmy.github.tamagotchi.Tamagotchi;

public class MainScreen implements Screen
{
    private static final String TAG = "MainScreen";
    private OrthographicCamera camera;
    private Stage stage;
    private ActionDialog actionDialog;
    private StatsDialog statsDialog;
    private UpdateManager updateManager;
    private TabBar tabBar;
    private BaseDrawableActor attentionSymbol;


    ActionDialog getActionDialog() {
        return actionDialog;
    }

    StatsDialog getStatsDialog()
    {
        updateManager.foreground();
        return statsDialog;
    }

    public TabBar getTabBar()
    {
        return this.tabBar;
    }

    public Stage getStage(){ return this.stage; }

    MainScreen()
    {
        this.actionDialog = new ActionDialog();
        this.statsDialog = new StatsDialog();
        this.updateManager = new UpdateManager(new FileManager(Gdx.files.local("").file()), this.statsDialog);
        
        OrthographicCamera cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera = cam;

        TabBar tabBar = new TabBar(this);
        this.tabBar = tabBar;

        this.attentionSymbol = new BaseDrawableActor(MyGame.skinDefault, "attention", 50, 160);
        Tamagotchi tamagotchi = MyGame.getInstance().getTamagotchi();
        float y = tamagotchi.getY() + tamagotchi.getHeight();
        float x = tamagotchi.getX() + tamagotchi.getWidth();
        attentionSymbol.setPosition(x, y);
        attentionSymbol.setVisible(false);

        Stage stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera));
        stage.addActor(MyGame.getInstance().getTamagotchi());
        stage.addActor(tabBar);
        stage.addActor(actionDialog);
        stage.addActor(statsDialog);
        stage.addActor(attentionSymbol);
        Gdx.input.setInputProcessor(stage);
        this.stage = stage;
        new Timer().scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                updateManager.foreground();
            }
        },0, 20);
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

    public void showAttention(){ this.attentionSymbol.setVisible(true); }
    public void hideAttention(){ this.attentionSymbol.setVisible(false); }


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
