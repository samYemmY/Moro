package io.samyemmy.github.tamagotchi;

import io.samyemmy.github.GameScreen;
import io.samyemmy.github.MyGame;
import io.samyemmy.github.Utils;
import io.samyemmy.github.dialog.ActionDialog;

public class PlayAction implements Action
{
    ActionDialog dialog;

    public PlayAction(ActionDialog dialog)
    {
        this.dialog = dialog;
    }

    @Override
    public float execute(Tamagotchi tamagotchi)
    {
        if (tamagotchi.isSick)
        {
            MyGame.getInstance().getAndroid().longToast("Can't play when sick!");
            return 0;
        }
        else if (tamagotchi.getEnergy() < 1)
        {
            MyGame.getInstance().getAndroid().toast("Not enough energy!");
            return 0;
        }
        else if (Utils.oneIn(3))
        {
            tamagotchi.setAnimation("anim/tamagotchi/shaking_head", 0.05f);
            tamagotchi.setPunishable(30);
            dialog.hide();
            return 1.5f;
        }
        else
        {
            MyGame.getInstance().setScreen(new GameScreen());
            dialog.hide();
            return 0;
        }

    }

    @Override
    public void dispose(Tamagotchi tamagotchi)
    {
        tamagotchi.setAnimation("anim/tamagotchi/idle");
    }
}
