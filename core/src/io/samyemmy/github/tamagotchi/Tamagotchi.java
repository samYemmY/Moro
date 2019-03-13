package io.samyemmy.github.tamagotchi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

import io.samyemmy.github.BaseDrawableActor;
import io.samyemmy.github.MyGame;

public class Tamagotchi extends BaseDrawableActor
{
    private static final String TAG = "Tamagotchi";
    private int minValue = 1;
    private int maxValue = 100;
    public float getSaturation() {
        return saturation;
    }
    void setSaturation(float sat)
    {
        if (sat > 100) sat = 100;
        this.saturation = sat;
    }
    private float saturation = maxValue;
    public float getHappiness() {
        return happiness;
    }
    private void setHappiness(float happiness) {
        this.happiness = happiness;
    }
    float happiness = maxValue;
    public float getDiscipline() {
        return discipline;
    }
    public void setDiscipline(float discipline) {
        this.discipline = discipline;
    }
    private float discipline = 50;
    public float getHealth() {
        return health;
    }
    public void setHealth(float health) {
        this.health = health;
    }
    private float health = maxValue;
    public float getBody() {
        return body;
    }
    public void setBody(float body) {
        this.body = body;
    }
    private float body = minValue;
    long getLastTimestamp() {
        return lastTimestamp;
    }
    private void setLastTimestamp(long lastTimestamp) {
        this.lastTimestamp = lastTimestamp;
    }
    private long lastTimestamp;

    private Tamagotchi()
    {
        super(MyGame.skinDefault, "tama", 300, 250);
        setPosition(Gdx.graphics.getWidth() / 2f - getWidth() / 2, Gdx.graphics.getHeight() / 2f - getHeight() / 2);
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

    private void diminish()
    {
        saturation -= 1;
        happiness -= 1;
    }

    private long getUpdateCycle()
    {
        int lifeSpanHours = 6;
        long hoursToMillis = 1000 * 60 * 60 * lifeSpanHours;
        return hoursToMillis / maxValue;
    }

    public void update()
    {
        Gdx.app.debug(TAG, "update()");
        long elapsedTime = TimeUtils.timeSinceMillis(getLastTimestamp());
        int diminishCounter =  (int) (elapsedTime / getUpdateCycle());
        for (int i=0; i<diminishCounter; i++)
        {
            diminish();
        }
        if (diminishCounter > 0)
        {
            Gdx.app.debug(TAG,"Updated Values.");
            setLastTimestamp(TimeUtils.millis());
        }
        Gdx.app.debug(TAG, toString());

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

    public TamagotchiSerializable getSerializable()
    {
        return new TamagotchiSerializable(this);
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
