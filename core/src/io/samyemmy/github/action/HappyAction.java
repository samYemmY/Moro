package io.samyemmy.github.action;

import java.util.Timer;
import java.util.TimerTask;

import io.samyemmy.github.MyGame;
import io.samyemmy.github.SoundHandler;
import io.samyemmy.github.Tamagotchi;


public class HappyAction implements Action
{
    private Timer timer;

    public HappyAction()
    {
        this.timer = new Timer();
    }

    @Override
    public boolean shouldExecute(Tamagotchi tamagotchi) {
        return true;
    }

    @Override
    public void execute(Tamagotchi tamagotchi)
    {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SoundHandler.getInstance().playSound("jump");
            }
        }, 0, 800);
        tamagotchi.setAnimation("anim/tamagotchi/happy");
    }

    @Override
    public void dispose(Tamagotchi tamagotchi)
    {
        timer.cancel();
        tamagotchi.setAnimation("anim/tamagotchi/idle");
    }

    @Override
    public long getDuration() {
        return 2300;
    }
}
