package io.samyemmy.github.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;

import io.samyemmy.github.AnimatedToast;
import io.samyemmy.github.BaseDrawableActor;
import io.samyemmy.github.Candy;
import io.samyemmy.github.MyGame;
import io.samyemmy.github.Utils;
import io.samyemmy.github.tamagotchi.CandyAction;
import io.samyemmy.github.tamagotchi.MealAction;
import io.samyemmy.github.tamagotchi.Tamagotchi;

public class ChooseMealContent extends ChooseActionContent
{
    private static final String TAG = "ChooseMealContent";
    private ActionDialog dialog;

    ChooseMealContent(ActionDialog dialog)
    {
        super();
        this.dialog = dialog;
        addButton("Meal","icons/toast");
        addButton("Snack","icons/candy");
    }

    @Override
    boolean onClick(Actor actor)
    {
        Gdx.app.debug(TAG, "onClick()");
        Tamagotchi tamagotchi = MyGame.getInstance().getTamagotchi();
        String name = actor.getName();
        if (name.equals("Meal"))
        {
            tamagotchi.executeAction(new MealAction());
            dialog.hide();
            return true;
        }
        else if (name.equals("Snack"))
        {
            tamagotchi.executeAction(new CandyAction());
            dialog.hide();
            return true;
        }
        return false;
    }
}
