package io.samyemmy.github.tamagotchi;

import io.samyemmy.github.MyGame;
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
    public float execute(Tamagotchi tamagotchi)
    {
        if (tamagotchi.isSick)
        {
            dialog.setActiveContent(new PatchUpContent(dialog));
        }
        else
        {
            MyGame.getInstance().getAndroid().toast("Your Tamagotchi is healthy.");
        }
        return 0;
    }

    @Override
    public void dispose(Tamagotchi tamagotchi) {

    }
}
