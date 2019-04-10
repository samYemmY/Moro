package io.samyemmy.github.tamagotchi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import io.samyemmy.github.AnimatableActor;
import io.samyemmy.github.MyGame;
import io.samyemmy.github.Utils;

public class Tamagotchi extends AnimatableActor
{
    private static final String TAG = "Tamagotchi";
    private static final int saturationLifespan = 3; // hours
    private static final int happinessLifespan = 2; // hours
    private static final int healthRegenerationDuration = 1; // hour
    private static final int poopCycle = 30; // minutes
    private static final int randomAttentionCycle = 10; // minutes
    private float saturation = 100;
    private float happiness = 100;
    private float discipline = 0;
    private float health = 100;
    private float energy = 100;
    private float body = 0;
    long timestampSaturation;
    long timestampHappiness;
    long timestampHealth;
    long timestampPoop;
    long timestampRandomAttention;
    boolean punishableAttentionFlag;
    boolean needsAttention = false;
    public boolean isPunishable = false;
    public boolean isSick = false;
    public boolean hasActiveAction = false;
    public boolean isAlive = true;
    public float getSaturation() {
        return saturation;
    }
    public float getHappiness() {
        return happiness;
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
    public void setEnergy(float energy) { this.energy = energy; }
    public float getEnergy() {
        return energy;
    }
    public float getBody() {
        return body;
    }
    public void setBody(float body) {
        this.body = body;
    }

    public void setHealth(float health)
    {
        if (health > 100)
        {
            health = 100;
        }

        if (health > 61)
        {
            isSick = false;
            executeAction(new IdleAction());
        }
        else if (health > 0)
        {
            isSick = true;
        }
        else
        {
            executeAction(new DieAction());
        }

        this.health = health;
    }


    public void setPunishable(int seconds)
    {
        isPunishable = true;
        new Timer().scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                Tamagotchi.this.isPunishable = false;
            }
        }, seconds);
    }

    void setSaturation(float sat)
    {
        if (sat > 100)
        {
            sat = 100;
        }

        if (sat < 20)
        {
            needsAttention = true;
        }

        this.saturation = sat;
    }

    public void setHappiness(float happiness)
    {
        if (happiness > 100)
        {
            happiness = 100;
        }

        if (happiness < 20)
        {
            needsAttention = true;
        }

        this.happiness = happiness;
    }

    private Tamagotchi()
    {
        super("anim/tamagotchi/idle", 0.1f, 300, 300);
        center();
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
        setBody(serializable.body);
        setEnergy(serializable.energy);
        setHealth(serializable.health);
        this.timestampSaturation = serializable.timestampSaturation;
        this.timestampHappiness = serializable.timestampHappiness;
        this.timestampHealth = serializable.timestampHealth;
        this.timestampPoop = serializable.timestampPoop;
        this.timestampRandomAttention = serializable.timestampRandomAttention;
        this.punishableAttentionFlag = serializable.punishableAttentionFlag;
        this.needsAttention = serializable.needsAttention;
        this.isPunishable = serializable.isPunishable;
        this.isSick = serializable.isSick;
        this.hasActiveAction = serializable.hasActiveAction;
        this.isAlive = serializable.isAlive;
    }

    public Tamagotchi(long timeStamp)
    {
        this();
        this.timestampSaturation = timeStamp;
        this.timestampHealth = timeStamp;
        this.timestampHappiness = timeStamp;
        this.timestampPoop = timeStamp;
        this.timestampRandomAttention = timeStamp;
    }

    public void center()
    {
        setPosition(Gdx.graphics.getWidth() / 2f - getWidth() / 2, Gdx.graphics.getHeight() / 2f - getHeight() / 2);
    }

    private long getUpdateCountForStatus(int hours, long timestamp)
    {
        long elapsedTime = TimeUtils.timeSinceMillis(timestamp);
        long hoursToMillis = 1000 * 60 * 60 * hours;
        long ratio = hoursToMillis / 100;
        return elapsedTime / ratio;
    }

    private long getUpdateCount(int minutes, long timestamp)
    {
        long elapsedTime = TimeUtils.timeSinceMillis(timestamp);
        long minutesToMillis = 1000 * 60 * minutes;
        return elapsedTime / minutesToMillis;
    }

    public void executeAction(final Action action)
    {
        if (hasActiveAction)
        {
            new Timer().scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    executeAction(action);
                }
            }, 2);
        }
        else
        {
            hasActiveAction = true;
            final float duration = action.execute(this);
                new Timer().scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        Tamagotchi.this.hasActiveAction = false;
                        if (duration > 0)
                        {
                            action.dispose(Tamagotchi.this);
                        }
                    }
                }, duration);
        }
    }


    public void update(boolean isForeground)
    {
        Gdx.app.debug(TAG, "update()");

        if (!isAlive && isForeground)
        {
            executeAction(new DieAction());
            return;
        }

        long systemtime = System.currentTimeMillis();

        if (isForeground)
        {
            if (punishableAttentionFlag)
            {
                long updateCount = getUpdateCount(1, timestampRandomAttention);
                if(updateCount < 1)
                {
                    Gdx.app.debug(TAG,"You made it in Time! 30 seconds left to punish");
                    setPunishable(30);
                }
                else
                {
                    Gdx.app.debug(TAG,"You missed the Time-Window!");
                }
                punishableAttentionFlag = false;
            }
        }

        int saturationCounter =  (int) getUpdateCountForStatus(saturationLifespan, timestampSaturation);
        if (saturationCounter > 0)
        {
            for (int i=0; i<saturationCounter; i++)
            {
                setSaturation(getSaturation() - 1);
            }
            timestampSaturation = systemtime;
        }

        int happinessCounter = (int) getUpdateCountForStatus(happinessLifespan, timestampHappiness);
        if (happinessCounter > 0)
        {
            for (int i=0; i<happinessCounter; i++)
            {
                setHappiness(getHappiness() - 1);
            }
            timestampHappiness = systemtime;
        }

        int healthCounter = (int) getUpdateCountForStatus(healthRegenerationDuration, timestampHealth);
        if (healthCounter > 0)
        {
            for (int i=0; i<healthCounter; i++)
            {
                setHealth(getHealth() + 1);
            }
            timestampHealth = systemtime;
        }

        int poopCounter = (int) getUpdateCount(poopCycle, timestampPoop);
        if (poopCounter > 0)
        {
            // poop()
        }

        int randomAttentionCounter = (int) getUpdateCount(randomAttentionCycle, timestampRandomAttention);
        if (randomAttentionCounter > 0)
        {
            if (Utils.oneIn(5))
            {
                if (isForeground)
                {
                    needsAttention = true;
                    setPunishable(30);
                }
                else
                {
                    punishableAttentionFlag = true;
                }
            }
            else if (Utils.oneIn(10))
            {
                executeAction(new SickAction());
            }
            timestampRandomAttention = systemtime;
        }

        if (needsAttention && !isSick)
        {
            MyGame.getInstance().getMainScreen().showAttention();
        }

        if (isSick)
        {
            executeAction(new SickAction());
            setHealth(getHealth() - 1);
        }
    }

    public TamagotchiSerializable getSerializable()
    {
        return new TamagotchiSerializable(this);
    }

    private boolean onClick()
    {
        executeAction(new ClickAction());
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
