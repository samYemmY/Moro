package io.samyemmy.github.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;

import io.samyemmy.github.MyGame;
import io.samyemmy.github.action.CandyAction;
import io.samyemmy.github.action.MealAction;

public class ChooseMealContent extends ChooseActionContent
{
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
        String name = actor.getName();
        if (name.equals("Meal"))
        {
            MyGame.getInstance().getActionQueue().add(new MealAction());
            dialog.hide();
            return true;
        }
        else if (name.equals("Snack"))
        {
            MyGame.getInstance().getActionQueue().add(new CandyAction());
            dialog.hide();
            return true;
        }
        return false;
    }
}
