package io.samyemmy.github.action;

import com.badlogic.gdx.Gdx;

import io.samyemmy.github.MyGame;
import io.samyemmy.github.SoundHandler;
import io.samyemmy.github.Tamagotchi;
import io.samyemmy.github.screen.EndScreen;

public class DieAction implements Action
{
    @Override
    public boolean shouldExecute(Tamagotchi tamagotchi) {
        return true;
    }

    @Override
    public void execute(Tamagotchi tamagotchi)
    {
        tamagotchi.center();
        tamagotchi.setAnimation("anim/tamagotchi/dead");
        tamagotchi.isAlive = false;
        SoundHandler.getInstance().playMusic("hypnotic");
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                MyGame.getInstance().setScreen(new EndScreen());
            }
        });
    }

    @Override
    public void dispose(Tamagotchi tamagotchi)
    {

    }

    @Override
    public long getDuration() {
        return 0;
    }
}
