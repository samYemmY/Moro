package io.samyemmy.github.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import io.samyemmy.github.AnimatableActor;
import io.samyemmy.github.BaseDrawableActor;
import io.samyemmy.github.FileManager;
import io.samyemmy.github.MainScreenTabBar;
import io.samyemmy.github.Tamagotchi;
import io.samyemmy.github.UpdateManager;
import io.samyemmy.github.dialog.ActionDialog;
import io.samyemmy.github.dialog.BaseDialog;
import io.samyemmy.github.dialog.StatsDialog;

public class MainScreen implements Screen
{
    private OrthographicCamera camera;
    private Stage stage;
    private ActionDialog actionDialog;
    private StatsDialog statsDialog;
    private UpdateManager updateManager;
    private MainScreenTabBar mainScreenTabBar;
    private BaseDrawableActor sickSymbol;
    private AnimatableActor sleepSymbol;
    private Tamagotchi tamagotchi;
    private Group gameLayer;


    public ActionDialog getActionDialog() {
        return actionDialog;
    }

    public StatsDialog getStatsDialog()
    {
        return statsDialog;
    }

    public UpdateManager getUpdateManager()
    {
        return updateManager;
    }

    public MainScreenTabBar getMainScreenTabBar()
    {
        return mainScreenTabBar;
    }

    public void setTamagotchi(Tamagotchi newTamagotchi)
    {
        gameLayer.removeActor(this.tamagotchi);
        gameLayer.addActor(newTamagotchi);
        this.tamagotchi = newTamagotchi;
    }

    public MainScreen(Tamagotchi tamagotchi)
    {
        this.actionDialog = new ActionDialog();
        this.statsDialog = new StatsDialog();
        this.updateManager = new UpdateManager(new FileManager(Gdx.files.local("").file()), this.statsDialog);
        
        OrthographicCamera cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera = cam;

        this.mainScreenTabBar = new MainScreenTabBar(this);
        this.gameLayer = new Group();
        gameLayer.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - mainScreenTabBar.getHeight());
        setTamagotchi(tamagotchi);
        float y = tamagotchi.getY() + tamagotchi.getHeight() - 50;
        float x = tamagotchi.getX() + tamagotchi.getWidth() - 50;
        this.sickSymbol = new BaseDrawableActor("icons/skull", 100, 100);
        sickSymbol.setVisible(false);
        sickSymbol.setPosition(x, y);
        gameLayer.addActor(sickSymbol);
        this.sleepSymbol = new AnimatableActor("anim/zzz", 0.2f, 200, 200);
        sleepSymbol.setPosition(tamagotchi.getX() + tamagotchi.getWidth() - 100, tamagotchi.getY() + tamagotchi.getHeight() - 100);
        sleepSymbol.setVisible(false);
        gameLayer.addActor(sleepSymbol);

        this.stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera));

        new Timer().scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                updateManager.foreground();
            }
        },0, 20);

    }

    @Override
    public void show()
    {
        gameLayer.addActor(tamagotchi);
        stage.addActor(gameLayer);
        stage.addActor(actionDialog);
        stage.addActor(statsDialog);
        stage.addActor(mainScreenTabBar);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        stage.draw();
        stage.act();
    }

    public void spawnFood(BaseDrawableActor actor)
    {
        actor.setPosition(tamagotchi.getX() + tamagotchi.getWidth() / 2 - actor.getWidth() / 2, tamagotchi.getY() - 50);
        gameLayer.addActor(actor);
    }

    public void toggleDialog(BaseDialog dialog)
    {
        dialog.toggle();
    }

    public void showSick()
    {
        this.sickSymbol.setVisible(true);
    }

    public void hideSick(){ this.sickSymbol.setVisible(false); }

    public void showSleep(){ this.sleepSymbol.setVisible(true); }

    public void hideSleep(){ this.sleepSymbol.setVisible(false); }

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
