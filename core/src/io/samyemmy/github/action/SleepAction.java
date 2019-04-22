package io.samyemmy.github.action;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import io.samyemmy.github.MyGame;
import io.samyemmy.github.Tamagotchi;
import io.samyemmy.github.screen.MainScreen;

public class SleepAction implements Action
{
    private MainScreen mainScreen;
    private TextButton buttonSleep;
    private static final String TAG = "SleepAction";

    public SleepAction()
    {
        this.mainScreen = MyGame.getInstance().getMainScreen();
        this.buttonSleep = mainScreen.getMainScreenTabBar().getButton("Sleep");
    }

    @Override
    public boolean shouldExecute(Tamagotchi tamagotchi)
    {
        if (tamagotchi.isSick)
        {
            MyGame.getInstance().getAndroid().toast("Can't sleep when sick!");
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
        tamagotchi.setAnimation("anim/tamagotchi/sleeping");
        tamagotchi.isSleeping = true;
        mainScreen.showSleep();
        buttonSleep.setText("Wake Up");
    }

    @Override
    public void dispose(Tamagotchi tamagotchi) {

    }

    @Override
    public long getDuration() {
        return 0;
    }
}
