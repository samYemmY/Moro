package io.samyemmy.github.action;

import com.badlogic.gdx.Gdx;

import io.samyemmy.github.MyGame;
import io.samyemmy.github.SoundHandler;
import io.samyemmy.github.Tamagotchi;
import io.samyemmy.github.dialog.ActionDialog;
import io.samyemmy.github.dialog.PatchUpContent;

public class PatchUpAction implements Action
{
    ActionDialog dialog;

    public PatchUpAction(ActionDialog dialog)
    {
        this.dialog = dialog;
    }

    @Override
    public boolean shouldExecute(Tamagotchi tamagotchi) {
        if (tamagotchi.getHealth() < 100 || tamagotchi.isSick)
        {
            SoundHandler.getInstance().playSound("select");
            return true;
        }
        else
        {
            SoundHandler.getInstance().playSound("denied");
            MyGame.getInstance().getAndroid().toast("Moroidesu is healthy.");
            return false;
        }
    }

    @Override
    public void execute(Tamagotchi tamagotchi)
    {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                dialog.setActiveContent(new PatchUpContent(dialog));
            }
        });
    }

    @Override
    public void dispose(Tamagotchi tamagotchi) {

    }

    @Override
    public long getDuration() {
        return 0;
    }
}
