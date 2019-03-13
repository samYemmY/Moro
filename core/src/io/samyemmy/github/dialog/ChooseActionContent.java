package io.samyemmy.github.dialog;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

import io.samyemmy.github.BaseDrawableActor;
import io.samyemmy.github.MyGame;

public class ChooseActionContent extends Table implements DialogContent
{
    private int maxBtns = 8;
    private Array<BaseDrawableActor> launchButtons = new Array<BaseDrawableActor>(true, maxBtns, BaseDrawableActor.class);
    private InputListener listener;
    private ActionDialog dialog;

    ChooseActionContent(ActionDialog dialog)
    {
        this.dialog = dialog;
        this.listener = new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return ChooseActionContent.this.onClick(event.getListenerActor());
            }
        };

        setHeight(getHeight()*5/6);
        setPosition(dialog.getWidth() / 2 - getWidth() / 2,dialog.getHeight() / 2 - getHeight() / 2 - 320);

        addButton("food");
        addButton("cross");
        addButton("toilet");
        addButton("message");

        for (int i=0; i<launchButtons.items.length; i++)
        {
            BaseDrawableActor actor = this.launchButtons.items[i];
            add(actor).pad(100);
            if (i % 2 == 1) row();
        }
    }

    private void addButton(String fileName)
    {
        BaseDrawableActor button = new BaseDrawableActor(MyGame.skinDefault, fileName, 150, 150);
        button.addListener(listener);
        this.launchButtons.add(button);
    }

    private boolean onClick(Actor actor) {
        String actorName = actor.getName();
        if (actorName.equals("background"))
        {
            return true;
        }
        if (actorName.equals("food"))
        {
            dialog.setActiveContent(dialog.getChooseMealContent());
        }
        else if (actorName.equals("cross"))
        {

        }
        else if (actorName.equals("toilet"))
        {

        }
        else if (actorName.equals("message"))
        {

        }
        else if (actorName.equals("clock"))
        {

        }
        return false;
    }

    @Override
    public String getTitle()
    {
        return "Choose Action";
    }
}
