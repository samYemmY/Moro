package io.samyemmy.github.action;

import io.samyemmy.github.Candy;
import io.samyemmy.github.MyGame;
import io.samyemmy.github.SoundHandler;
import io.samyemmy.github.Tamagotchi;

public class CandyAction implements Action
{
    @Override
    public boolean shouldExecute(Tamagotchi tamagotchi) {
        if (tamagotchi.isSick)
        {
            SoundHandler.getInstance().playSound("denied");
            MyGame.getInstance().getAndroid().toast("Moroidesu is too sick to eat.");
            return false;
        }
        else if (tamagotchi.isSleeping)
        {
            MyGame.getInstance().getActionQueue().add(new WakeUpAction());
            MyGame.getInstance().getActionQueue().add(new CandyAction());
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
        SoundHandler.getInstance().playSound("select");
        tamagotchi.setAnimation("anim/tamagotchi/eating");
        MyGame.getInstance().getMainScreen().spawnFood(new Candy());
        tamagotchi.setHealth(tamagotchi.getHealth() - 41);
        tamagotchi.setSaturation(tamagotchi.getSaturation() + 20);
        tamagotchi.setHappiness(tamagotchi.getHappiness() + 30);
    }

    @Override
    public void dispose(Tamagotchi tamagotchi)
    {
        tamagotchi.setAnimation("anim/tamagotchi/idle");
        MyGame.getInstance().getActionQueue().add(new HappyAction());
        if (tamagotchi.isSick)
        {
            MyGame.getInstance().getActionQueue().add(new SickAction());
        }
        MyGame.getInstance().getMainScreen().getUpdateManager().foreground();
    }

    @Override
    public long getDuration() {
        return 1500;
    }
}
