package io.samyemmy.github.action;

import io.samyemmy.github.MyGame;
import io.samyemmy.github.SoundHandler;
import io.samyemmy.github.Tamagotchi;
import io.samyemmy.github.dialog.ActionDialog;

public class DisciplineAction implements Action
{
    private ActionDialog dialog;

    public DisciplineAction(ActionDialog dialog)
    {
        this.dialog = dialog;
    }

    @Override
    public boolean shouldExecute(Tamagotchi tamagotchi) {
        if (tamagotchi.isSick)
        {
            MyGame.getInstance().getActionQueue().add(new DieAction());
            return false;
        }
        else if (tamagotchi.isPunishable)
        {
            SoundHandler.getInstance().playSound("select");
            return true;
        }
        else
        {
            SoundHandler.getInstance().playSound("denied");
            MyGame.getInstance().getAndroid().toast("You can't do that right now!");
            return false;
        }
    }

    @Override
    public void execute(Tamagotchi tamagotchi)
    {
        tamagotchi.isPunishable = false;
        SoundHandler.getInstance().playSound("discipline");
        tamagotchi.setAnimation("anim/tamagotchi/sad");
        MyGame.getInstance().getAndroid().longToast("Moroidesu learned from its mistakes!");
        tamagotchi.setDiscipline(tamagotchi.getDiscipline() + 5);
        dialog.hide();
    }

    @Override
    public void dispose(Tamagotchi tamagotchi)
    {
        tamagotchi.setAnimation("anim/tamagotchi/idle");
    }

    @Override
    public long getDuration() {
        return 2000;
    }
}
