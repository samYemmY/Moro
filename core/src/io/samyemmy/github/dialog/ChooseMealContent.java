package io.samyemmy.github.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import io.samyemmy.github.MyGame;
import io.samyemmy.github.ui.TextButton;

public class ChooseMealContent extends Table implements DialogContent
{
    private static final String TAG = "ChooseMealContent";
    private ActionDialog dialog;
    private InputListener listener;

    ChooseMealContent(ActionDialog dialog)
    {
        this.dialog = dialog;
        this.listener = new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return ChooseMealContent.this.onClick(event.getListenerActor());
            }
        };

        setPosition(dialog.getWidth() / 2 - getWidth() / 2,dialog.getHeight() / 2 - getHeight() / 2);
        add(new TextButton("Meal", "carrot", listener)).pad(100);
        add(new TextButton("Snack", "candy", listener)).pad(100);
    }

    private boolean onClick(Actor actor)
    {
        Gdx.app.debug(TAG, "onClick()");
        String name = actor.getName();
        boolean willClose = false;
        if (name.equals("carrot"))
        {
            MyGame.getInstance().getTamagotchi().giveMeal();
            dialog.hide();
            return true;
        }
        else if (name.equals("candy"))
        {
            MyGame.getInstance().getTamagotchi().giveSnack();
            dialog.hide();
            return true;
        }
        return false;
    }

    @Override
    public String getTitle() {
        return "Choose Meal";
    }
}
