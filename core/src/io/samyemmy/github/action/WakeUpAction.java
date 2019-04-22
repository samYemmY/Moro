package io.samyemmy.github.action;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import io.samyemmy.github.MyGame;
import io.samyemmy.github.Tamagotchi;
import io.samyemmy.github.screen.MainScreen;

public class WakeUpAction implements Action
{
    private MainScreen mainScreen;
    private TextButton buttonSleep;

    public WakeUpAction()
    {
        this.mainScreen = MyGame.getInstance().getMainScreen();
        this.buttonSleep = mainScreen.getMainScreenTabBar().getButton("Sleep");
    }

    @Override
    public boolean shouldExecute(Tamagotchi tamagotchi) {
        return tamagotchi.isSleeping;
    }

    @Override
    public void execute(Tamagotchi tamagotchi)
    {
        tamagotchi.setAnimation("anim/tamagotchi/idle");
        tamagotchi.isSleeping = false;
        mainScreen.hideSleep();
        buttonSleep.setText("Sleep");
    }

    @Override
    public void dispose(Tamagotchi tamagotchi) {

    }

    @Override
    public long getDuration() {
        return 0;
    }
}
