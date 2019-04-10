package io.samyemmy.github.tamagotchi;

import io.samyemmy.github.AnimatedToast;
import io.samyemmy.github.MyGame;
import io.samyemmy.github.Utils;

public class MealAction implements Action
{
    @Override
    public float execute(Tamagotchi tamagotchi)
    {
        if (tamagotchi.getSaturation() > 99 || tamagotchi.isSick)
        {
            MyGame.getInstance().getAndroid().toast("Your Tamagotchi is not hungry.");
            return 0;
        }
        else
        {
            if (Utils.oneIn(5))
            {
                tamagotchi.setAnimation("anim/tamagotchi/shaking_head");
                tamagotchi.setPunishable(30);
                MyGame.getInstance().getMainScreen().spawnFood(new AnimatedToast(false));
            }
            else
            {
                tamagotchi.setAnimation("anim/tamagotchi/eating");
                MyGame.getInstance().getMainScreen().spawnFood(new AnimatedToast(true));
                tamagotchi.setSaturation(tamagotchi.getSaturation() + 40);
            }
            return 1.5f;
        }
    }

    @Override
    public void dispose(Tamagotchi tamagotchi)
    {
        tamagotchi.setAnimation("anim/tamagotchi/idle");
    }
}