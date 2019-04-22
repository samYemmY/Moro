package io.samyemmy.github;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import io.samyemmy.github.action.ClickAction;
import io.samyemmy.github.action.DieAction;
import io.samyemmy.github.action.SickAction;
import io.samyemmy.github.action.SleepAction;
import io.samyemmy.github.action.WakeUpAction;

public class Tamagotchi extends AnimatableActor
{
    private static final int saturationLifespan = 1; // hours
    private static final int happinessLifespan = 1; // hours
    private static final int healthRegenerationDuration = 1; // hour
    private static final int energyRegenerationDuration = 1; // hour
    private static final int sickHealthLifespan = 1; // minutes
    private float saturation = 20;
    private float happiness = 40;
    private float discipline = 0;
    private float health = 100;
    private float energy = 20;
    long timestampSaturation;
    long timestampHappiness;
    long timestampHealth;
    long timestampPoop;
    long timestampRandomAttention;
    long timestampSleep;
    long timestampSick;
    boolean punishableAttentionFlag;
    public boolean needsAttention = false;
    public boolean isPunishable = false;
    public boolean isSick = false;
    public boolean isAlive = true;
    public boolean isSleeping = false;

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
    public float getEnergy() {
        return energy;
    }

    public void setHealth(float health)
    {
        if (health > 100)
        {
            health = 100;
        }
        else if (health <= 20)
        {
            needsAttention = true;
            if (health <= 0)
            {
                isAlive = false;
            }
            isSick = true;
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

    public void setSaturation(float sat)
    {
        if (sat > 100)
        {
            sat = 100;
        }

        if (sat <= 20)
        {
            needsAttention = true;
            if (sat <= 0)
            {
                isAlive = false;
            }
        }

        this.saturation = sat;
    }

    public void setHappiness(float happiness)
    {
        if (happiness > 100)
        {
            happiness = 100;
        }

        if (happiness <= 20)
        {
            needsAttention = true;
            if (happiness <= 0)
            {
                isAlive = false;
            }
        }

        this.happiness = happiness;
    }

    public void setEnergy(float energy)
    {
        if (energy > 100)
        {
            energy = 100;
        }
        if (energy < 0)
        {
            energy = 0;
        }
        this.energy = energy;
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
        setEnergy(serializable.energy);
        setHealth(serializable.health);
        this.timestampSaturation = serializable.timestampSaturation;
        this.timestampHappiness = serializable.timestampHappiness;
        this.timestampHealth = serializable.timestampHealth;
        this.timestampPoop = serializable.timestampPoop;
        this.timestampRandomAttention = serializable.timestampRandomAttention;
        this.timestampSleep = serializable.timestampSleep;
        this.timestampSick = serializable.timestampSick;
        this.punishableAttentionFlag = serializable.punishableAttentionFlag;
        this.needsAttention = serializable.needsAttention;
        this.isPunishable = serializable.isPunishable;
        this.isSick = serializable.isSick;
        this.isAlive = serializable.isAlive;
        this.isSleeping = serializable.isSleeping;
    }

    public Tamagotchi(TamagotchiSerializable serializable, boolean isBackground)
    {
        super();
        setSaturation(serializable.saturation);
        setHappiness(serializable.happiness);
        setDiscipline(serializable.discipline);
        setEnergy(serializable.energy);
        setHealth(serializable.health);
        this.timestampSaturation = serializable.timestampSaturation;
        this.timestampHappiness = serializable.timestampHappiness;
        this.timestampHealth = serializable.timestampHealth;
        this.timestampPoop = serializable.timestampPoop;
        this.timestampRandomAttention = serializable.timestampRandomAttention;
        this.timestampSleep = serializable.timestampSleep;
        this.timestampSick = serializable.timestampSick;
        this.punishableAttentionFlag = serializable.punishableAttentionFlag;
        this.needsAttention = serializable.needsAttention;
        this.isPunishable = serializable.isPunishable;
        this.isSick = serializable.isSick;
        this.isAlive = serializable.isAlive;
        this.isSleeping = serializable.isSleeping;
    }

    public Tamagotchi(long timeStamp)
    {
        this();
        this.timestampSaturation = timeStamp;
        this.timestampHealth = timeStamp;
        this.timestampHappiness = timeStamp;
        this.timestampPoop = timeStamp;
        this.timestampRandomAttention = timeStamp;
        this.timestampSleep = timeStamp;
        this.timestampSick = timeStamp;
    }

    public void center()
    {
        setPosition(Gdx.graphics.getWidth() / 2f - getWidth() / 2, Gdx.graphics.getHeight() / 2f - getHeight() / 2);
    }

    // get update count for going from 100 to 0 in x hours
    private long getUpdateCountForStatus(int hours, long timestamp)
    {
        long elapsedTime = TimeUtils.timeSinceMillis(timestamp);
        long hoursToMillis = 1000 * 60 * 60 * hours;
        long ratio = hoursToMillis / 100;
        return elapsedTime / ratio;
    }

    // 1 update every x minutes
    private long getUpdateCount(int minutes, long timestamp)
    {
        long elapsedTime = TimeUtils.timeSinceMillis(timestamp);
        long minutesToMillis = 1000 * 60 * minutes;
        return elapsedTime / minutesToMillis;
    }

    @Override
    public void setAnimation(String animationName, float frameDuration) {
        super.setAnimation(animationName, frameDuration);
    }

    public void update()
    {
        needsAttention = false;
        ActionQueue actionQueue = MyGame.getInstance().getActionQueue();

        if (!isAlive)
        {
            actionQueue.add(new DieAction());
            return;
        }

        if (!actionQueue.isEmpty())
        {
            return;
        }

        if (isSick)
        {
            actionQueue.add(new WakeUpAction());
            actionQueue.add(new SickAction());
            checkSick();
        }
        else
        {
            checkHealth();
            timestampSick = System.currentTimeMillis();
        }

        if (isSleeping)
        {
            checkEnergy();
            actionQueue.add(new SleepAction());
        }
        else
        {
            timestampSleep = System.currentTimeMillis();
        }

        checkSaturation();
        checkHappiness();
    }

    public void updateBackground()
    {
        if (!isAlive)
        {
            return;
        }

        if (Utils.oneIn(20))
        {
            isSick = true;
        }

        if (isSleeping)
        {
           checkEnergy();
        }
        else
        {
            timestampSleep = System.currentTimeMillis();
        }

        checkHappiness();
        checkSaturation();

        if (isSick)
        {
            needsAttention = true;
            checkSick();
        }
        else
        {
            checkHealth();
        }
    }

    private void checkEnergy()
    {
        int energyCounter =  (int) getUpdateCountForStatus(energyRegenerationDuration, timestampSleep);
        if (energyCounter > 0)
        {
            for (int i=0; i<energyCounter; i++)
            {
                setEnergy(getEnergy() + 1);
            }
            timestampSleep = System.currentTimeMillis();
        }
    }

    private void checkSaturation()
    {
        int saturationCounter =  (int) getUpdateCountForStatus(saturationLifespan, timestampSaturation);
        if (saturationCounter > 0)
        {
            for (int i=0; i<saturationCounter; i++)
            {
                setSaturation(getSaturation() - 1);
            }
            timestampSaturation = System.currentTimeMillis();
        }
    }

    private void checkHappiness()
    {
        int happinessCounter = (int) getUpdateCountForStatus(happinessLifespan, timestampHappiness);
        if (happinessCounter > 0)
        {
            for (int i=0; i<happinessCounter; i++)
            {
                setHappiness(getHappiness() - 1);
            }
            timestampHappiness = System.currentTimeMillis();
        }
    }

    private void checkHealth()
    {
        int healthCounter = (int) getUpdateCountForStatus(healthRegenerationDuration, timestampHealth);
        if (healthCounter > 0)
        {
            for (int i=0; i<healthCounter; i++)
            {
                setHealth(getHealth() + 1);
            }
            timestampHealth = System.currentTimeMillis();
        }
    }

    private void checkSick()
    {
        int sickCounter = (int) getUpdateCount(sickHealthLifespan, timestampSick);
        if (sickCounter > 0)
        {
            for (int i=0; i<sickCounter; i++)
            {
                setHealth(getHealth() - 5);
            }
            timestampSick = System.currentTimeMillis();
        }
    }

    public TamagotchiSerializable getSerializable()
    {
        return new TamagotchiSerializable(this);
    }

    private boolean onClick()
    {
        MyGame.getInstance().getActionQueue().add(new ClickAction());
        return false;
    }

    @Override
    public String toString()
    {
        return "\nSaturation: " + getSaturation()
                + "\nHappiness: " + getHappiness()
                + "\nEnergy: " + getEnergy()
                + "\nDiscipline: " + getDiscipline()
                + "\nHealth: " + getHealth();
    }
}
