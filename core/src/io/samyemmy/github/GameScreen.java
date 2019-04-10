package io.samyemmy.github;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import io.samyemmy.github.tamagotchi.HappyAction;
import io.samyemmy.github.tamagotchi.SadAction;
import io.samyemmy.github.tamagotchi.Tamagotchi;

public class GameScreen implements Screen
{
    private static final String TAG = "GameScreen";
    private OrthographicCamera camera;
    private Stage stage;
    private GameScreenTabBar tabBar;
    private InputListener listener;
    private int gameScore;
    private FontActor faNextNumber;
    private FontActor faGivenNumber;
    private int givenNumber;
    private Tamagotchi tamagotchi;
    private Timer timer;
    private boolean active = false;

    public GameScreen()
    {
        OrthographicCamera cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera = cam;
        this.listener = new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return GameScreen.this.onClick(event.getListenerActor());
            }
        };
        this.timer = new Timer();
        this.tabBar = new GameScreenTabBar(this);

        float height = Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();

        Group buttonArea = new Group();
        buttonArea.setBounds(0,0, width, height / 4);

        BaseDrawableActor arrowUp = new BaseDrawableActor("ui/arrow-up", 250, 250);
        arrowUp.setName("up");
        arrowUp.addListener(listener);
        arrowUp.setPosition(width / 3f - arrowUp.getWidth() / 2, 200);
        buttonArea.addActor(arrowUp);

        BaseDrawableActor arrowDown = new BaseDrawableActor("ui/arrow-down", 250, 250);
        arrowDown.setName("down");
        arrowDown.addListener(listener);
        arrowDown.setPosition(width * 2/3f - arrowDown.getWidth() / 2, 200);

        FontActor faGivenNumber = new FontActor("-", 100);
        faGivenNumber.setPosition(width / 2f - faGivenNumber.getWidth() / 2f, 0.7f * height);
        this.faGivenNumber = faGivenNumber;

        FontActor nextNumber = new FontActor("?", 80);
        nextNumber.setPosition(0.75f * width - nextNumber.getWidth() / 2f, 0.4f * height + 170);
        this.faNextNumber = nextNumber;

        Tamagotchi tamagotchi = MyGame.getInstance().getTamagotchi();
        tamagotchi.setPosition(width / 4f - tamagotchi.getWidth() / 2f, 0.4f * height);
        this.tamagotchi = tamagotchi;

        Stage stage = new Stage(new StretchViewport(width, height, camera));
        stage.addActor(buttonArea);
        stage.addActor(tamagotchi);
        stage.addActor(arrowDown);
        stage.addActor(arrowUp);
        stage.addActor(faGivenNumber);
        stage.addActor(nextNumber);
        stage.addActor(tabBar);
        Gdx.input.setInputProcessor(stage);
        this.stage = stage;

        reset();
    }

    private boolean onClick(Actor actor)
    {
        if (active) return true;
        active = true;

        int random;
        do
        {
            random = Utils.randomNumber();
        } while (random == givenNumber);
        this.faNextNumber.setText(random + "");

        String name = actor.getName();
        if (name.equals("up"))
        {
            if (random > givenNumber)
            {
               win();
            }
            else
            {
               loose();
            }
        }
        else if (name.equals("down"))
        {
            if (random < givenNumber)
            {
                win();
            }
            else
            {
                loose();
            }
        }
        return false;
    }

    private void win()
    {
        tamagotchi.executeAction(new HappyAction());
        gameScore++;
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                reset();
            }
        }, 2);
    }

    private void loose()
    {
        tamagotchi.executeAction(new SadAction());
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                quit();
            }
        }, 2);
    }

    private void reset()
    {
        faNextNumber.setText("?");
        int givenNumber = Utils.randomNumber();
        this.givenNumber = givenNumber;
        faGivenNumber.setText(givenNumber + "");
        active = false;
    }

    public void quit()
    {
        float energyFactor = (gameScore+1) * 5;
        tamagotchi.setEnergy(tamagotchi.getEnergy() - energyFactor);
        float happinessFactor = gameScore * 10;
        tamagotchi.setHappiness(tamagotchi.getHappiness() + happinessFactor);
        MyGame.getInstance().setScreen(MyGame.getInstance().getMainScreen());
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
    public void hide()
    {
    }

    @Override
    public void dispose() {

    }
}
