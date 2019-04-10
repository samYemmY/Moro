package io.samyemmy.github.tamagotchi;

import com.badlogic.gdx.utils.Timer;

import io.samyemmy.github.MyGame;
import io.samyemmy.github.dialog.ActionDialog;

public class DisciplineAction implements Action
{
    ActionDialog dialog;

    public DisciplineAction(ActionDialog dialog)
    {
        this.dialog = dialog;
    }

    @Override
    public float execute(Tamagotchi tamagotchi)
    {
        if (tamagotchi.isSick)
        {
            tamagotchi.setAnimation("anim/tamagotchi/sad");
            tamagotchi.executeAction(new DieAction());
            return 0;
        }
        if (tamagotchi.isPunishable)
        {
            tamagotchi.isPunishable = false;
            tamagotchi.setAnimation("anim/tamagotchi/sad");
            MyGame.getInstance().getAndroid().longToast("Your Tamagotchi learned from its mistakes!");
            tamagotchi.setDiscipline(tamagotchi.getDiscipline() + 5);
            dialog.hide();
        }
        else
        {
            MyGame.getInstance().getAndroid().toast("You can't do that right now!");
            return 0;
        }
        return 2f;
    }

    @Override
    public void dispose(Tamagotchi tamagotchi)
    {
        tamagotchi.setAnimation("anim/tamagotchi/idle");
    }
}
