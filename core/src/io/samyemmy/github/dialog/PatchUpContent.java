package io.samyemmy.github.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;

import io.samyemmy.github.AnimatedToast;
import io.samyemmy.github.Candy;
import io.samyemmy.github.MyGame;
import io.samyemmy.github.Utils;
import io.samyemmy.github.tamagotchi.Tamagotchi;
import io.samyemmy.github.ui.StatusBar;

public class PatchUpContent extends ChooseActionContent
{
    private static final String TAG = "PatchUpContent";
    private ActionDialog dialog;
    private Tamagotchi tamagotchi;
    private StatusBar healthBar;

    public PatchUpContent(ActionDialog dialog)
    {
        super();
        this.dialog = dialog;
        this.tamagotchi = MyGame.getInstance().getTamagotchi();
        this.healthBar = new StatusBar("Health");
        healthBar.setValue(tamagotchi.getHealth());
        add(healthBar).row();
        addButton("Patch Up","icons/syringe");
    }

    @Override
    boolean onClick(Actor actor)
    {
        Gdx.app.debug(TAG, "onClick()");
        String name = actor.getName();
        if (name.equals("Patch Up"))
        {
            tamagotchi.setHealth(tamagotchi.getHealth() + 20);
            if(tamagotchi.getHealth() > 61)
            {
                MyGame.getInstance().getMainScreen().hideSick();
            }
            healthBar.setValue(tamagotchi.getHealth());
        }
        return false;
    }
}
