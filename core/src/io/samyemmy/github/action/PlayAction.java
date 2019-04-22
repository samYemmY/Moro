package io.samyemmy.github.action;

import com.badlogic.gdx.Gdx;

import io.samyemmy.github.MyGame;
import io.samyemmy.github.SoundHandler;
import io.samyemmy.github.Tamagotchi;
import io.samyemmy.github.Utils;
import io.samyemmy.github.dialog.ActionDialog;
import io.samyemmy.github.screen.GameScreen;

public class PlayAction implements Action
{
    private ActionDialog dialog;

    public PlayAction(ActionDialog dialog)
    {
        this.dialog = dialog;
    }

    @Override
    public boolean shouldExecute(Tamagotchi tamagotchi)
    {
        if (tamagotchi.isSick)
        {
            SoundHandler.getInstance().playSound("denied");
            MyGame.getInstance().getAndroid().longToast("Can't play when sick!");
            return false;
        }
        else if (tamagotchi.getEnergy() < 1)
        {
            SoundHandler.getInstance().playSound("denied");
            MyGame.getInstance().getAndroid().toast("Not enough energy!");
            return false;
        }
        else if (tamagotchi.isSleeping)
        {
            MyGame.getInstance().getActionQueue().add(new WakeUpAction());
            MyGame.getInstance().getActionQueue().add(new PlayAction(dialog));
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void execute(Tamagotchi tamagotchi)
    {

        if (Utils.oneIn(3))
        {
            SoundHandler.getInstance().playSound("shake_head");
            tamagotchi.setAnimation("anim/tamagotchi/shaking_head", 0.05f);
            tamagotchi.setPunishable(20);
            dialog.hide();
        }
        else
        {
            SoundHandler.getInstance().playSound("select");
            SoundHandler.getInstance().playSound("start_game");
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    MyGame.getInstance().setScreen(new GameScreen());
                }
            });
            dialog.hide();
        }
    }

    @Override
    public void dispose(Tamagotchi tamagotchi)
    {
        tamagotchi.setAnimation("anim/tamagotchi/idle");
    }

    @Override
    public long getDuration() {
        return 1500;
    }
}
