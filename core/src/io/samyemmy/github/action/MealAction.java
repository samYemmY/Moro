package io.samyemmy.github.action;

import io.samyemmy.github.AnimatedToast;
import io.samyemmy.github.MyGame;
import io.samyemmy.github.SoundHandler;
import io.samyemmy.github.Tamagotchi;
import io.samyemmy.github.Utils;

public class MealAction implements Action
{
    @Override
    public boolean shouldExecute(Tamagotchi tamagotchi)
    {
        if (tamagotchi.isSick)
        {
            SoundHandler.getInstance().playSound("denied");
            MyGame.getInstance().getAndroid().toast("Moroidesu is too sick to eat.");
            return false;
        }
        else if (tamagotchi.getSaturation() > 99)
        {
            SoundHandler.getInstance().playSound("denied");
            MyGame.getInstance().getAndroid().toast("Moroidesu is not hungry.");
            return false;
        }
        else if (tamagotchi.isSleeping)
        {
            MyGame.getInstance().getActionQueue().add(new WakeUpAction());
            MyGame.getInstance().getActionQueue().add(new MealAction());
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
        if (Utils.oneIn(5))
        {
            SoundHandler.getInstance().playSound("shake_head");
            tamagotchi.setAnimation("anim/tamagotchi/shaking_head", 0.05f);
            tamagotchi.setPunishable(30);
            MyGame.getInstance().getMainScreen().spawnFood(new AnimatedToast(false));
        }
        else
        {
            SoundHandler.getInstance().playSound("select");
            tamagotchi.setAnimation("anim/tamagotchi/eating");
            MyGame.getInstance().getMainScreen().spawnFood(new AnimatedToast(true));
            tamagotchi.setSaturation(tamagotchi.getSaturation() + 40);
        }
    }

    @Override
    public void dispose(Tamagotchi tamagotchi)
    {
        tamagotchi.setAnimation("anim/tamagotchi/idle");
        MyGame.getInstance().getMainScreen().getUpdateManager().foreground();
    }

    @Override
    public long getDuration() {
        return 1500;
    }
}