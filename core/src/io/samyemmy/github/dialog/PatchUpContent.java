package io.samyemmy.github.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.samyemmy.github.MyGame;
import io.samyemmy.github.action.RecoveryAction;
import io.samyemmy.github.Tamagotchi;
import io.samyemmy.github.ui.StatusBar;

public class PatchUpContent extends ChooseActionContent
{
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
        String name = actor.getName();
        if (name.equals("Patch Up"))
        {
            tamagotchi.setHealth(tamagotchi.getHealth() + 20);
            healthBar.setValue(tamagotchi.getHealth());
            if (tamagotchi.getHealth() >= 100)
            {
                MyGame.getInstance().getActionQueue().add(new RecoveryAction());
                dialog.hide();
            }
        }
        return false;
    }
}
