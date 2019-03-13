package io.samyemmy.github.tamagotchi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import io.samyemmy.github.BaseDrawableActor;
import io.samyemmy.github.MyGame;
import io.samyemmy.github.Poop;

public class Tamagotchi extends BaseDrawableActor
{
    private static final String TAG = "Tamagotchi";
    private int saturationLifespan = 6; // hours
    private int happinessLifespan = 12; // hours
    private int poopCycle = 5; // minutes
    private int minValue = 1;
    private int maxValue = 100;
    private float saturation = 19;
    private float happiness = maxValue;
    private float discipline = minValue;
    private float health = maxValue;
    private float body = minValue;
    private long lastTimestamp;
    private boolean needsAttention = false;

    public float getSaturation() {
        return saturation;
    }
    void setSaturation(float sat)
    {
        if (sat > 100) sat = 100;
        else if (sat < 20) needsAttention = true;
        this.saturation = sat;
    }
    public float getHappiness() {
        return happiness;
    }
    private void setHappiness(float happiness) {
        if (happiness < 20) needsAttention = true;
        this.happiness = happiness;
    }
    public float getDiscipline() {
        return discipline;
    }
    public void setDiscipline(float discipline) {
        this.discipline = discipline;
    }
    public float getHealth() {
        return health;
    }
    public void setHealth(float health) {
        this.health = health;
    }
    public float getBody() {
        return body;
    }
    public void setBody(float body) {
        this.body = body;
    }
    long getLastTimestamp() {
        return lastTimestamp;
    }
    private void setLastTimestamp(long lastTimestamp) {
        this.lastTimestamp = lastTimestamp;
    }

    private Tamagotchi()
    {
        super(MyGame.skinDefault, "tama", 300, 250);
        setPosition(Gdx.graphics.getWidth() / 2f - getWidth() / 2, Gdx.graphics.getHeight() / 2f - getHeight() / 2);
        InputListener listener = new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return Tamagotchi.this.onClick();
            }
        };
        addListener(listener);
    }

    public Tamagotchi(TamagotchiSerializable serializable)
    {
        this();
        setSaturation(serializable.saturation);
        setHappiness(serializable.happiness);
        setDiscipline(serializable.discipline);
        setLastTimestamp(serializable.lastTimestamp);
    }

    public Tamagotchi(long timeStamp)
    {
        this();
        setLastTimestamp(timeStamp);
    }

    private long getUpdateCountForStatus(int hours)
    {
        long elapsedTime = TimeUtils.timeSinceMillis(getLastTimestamp());
        long hoursToMillis = 1000 * 60 * 60 * hours;
        long ratio = hoursToMillis / maxValue;
        return elapsedTime / ratio;
    }

    private long getUpdateCount(int minutes)
    {
        long elapsedTime = TimeUtils.timeSinceMillis(getLastTimestamp());
        long minutesToMillis = 1000 * 60 * minutes;
        return elapsedTime / minutesToMillis;
    }

    public void update()
    {
        Gdx.app.debug(TAG, "update()");

        int saturationCounter =  (int) getUpdateCountForStatus(saturationLifespan);
        for (int i=0; i<saturationCounter; i++)
        {
            setSaturation(getSaturation() - 1);
        }

        int happinessCounter = (int) getUpdateCountForStatus(happinessLifespan);
        for (int i=0; i<happinessCounter; i++)
        {
            setHappiness(getHappiness() - 1);
        }

        int poopCounter = (int) getUpdateCount(poopCycle);
        for (int i=0; i<poopCounter; i++)
        {
            poop();
        }

        if (needsAttention)
        {
            showAttention();
        }

        if (happinessCounter > 0 || saturationCounter > 0 || poopCounter > 0)
        {
            Gdx.app.debug(TAG,"Updated Values.");
            setLastTimestamp(TimeUtils.millis());
            Gdx.app.debug(TAG, toString());
        }
    }

    private void showAttention()
    {
        MyGame.getInstance().getMainScreen().showAttention();
    }

    private void hideAttention()
    {
        MyGame.getInstance().getMainScreen().hideAttention();
    }

    public void giveSnack()
    {
        Gdx.app.debug(TAG, "giveSnack()");
        setHealth(getHealth() - 20);
        setSaturation(getSaturation() + 20);
        setHappiness(getHappiness() + 30);
        Gdx.app.debug(TAG,toString());
    }

    public void giveMeal()
    {
        Gdx.app.debug(TAG, "giveMeal()");
        if (getSaturation() > 99)
        {
            MyGame.getInstance().getAndroid().toast("Your Tamagotchi is not hungry.");
            return;
        }
        setSaturation(getSaturation() + 20);
        Gdx.app.debug(TAG,toString());
    }

    public void poop()
    {
        Poop poop = new Poop();
        int maxY = (int) (Gdx.graphics.getHeight() - MyGame.getInstance().getMainScreen().getTabBar().getHeight() - poop.getHeight());
        int maxX = (int) (Gdx.graphics.getWidth() - poop.getWidth());
        int x,y;
        Random rand = new Random();
        do {
            x = rand.nextInt(maxX);
            y = rand.nextInt(maxY);
        } while (getRectangle().contains(x,y));
        poop.setPosition(x, y);
        MyGame.getInstance().getMainScreen().getStage().addActor(poop);
    }

    public TamagotchiSerializable getSerializable()
    {
        return new TamagotchiSerializable(this);
    }

    private boolean onClick()
    {
        Gdx.app.debug(TAG, "onClick()");
        if (needsAttention)
        {
            hideAttention();
            needsAttention = false;
        }
        return false;
    }

    @Override
    public String toString()
    {
        return "\nSaturation: " + getSaturation()
                + "\nHappiness: " + getHappiness()
                + "\nDiscipline: " + getDiscipline()
                + "\nHealth: " + getHealth();
    }
}
