package io.samyemmy.github.dialog;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import io.samyemmy.github.ActionQueue;
import io.samyemmy.github.BaseDrawableActor;
import io.samyemmy.github.MyGame;
import io.samyemmy.github.SoundHandler;
import io.samyemmy.github.action.DieAction;
import io.samyemmy.github.action.DisciplineAction;
import io.samyemmy.github.action.PatchUpAction;
import io.samyemmy.github.action.PlayAction;
import io.samyemmy.github.Tamagotchi;
import io.samyemmy.github.ui.ImageTextButton;

public class ChooseActionContent extends Table
{
    private int buttons = 0;
    private InputListener listener;
    private ActionDialog dialog;

    ChooseActionContent()
    {
        setFillParent(true);
        this.listener = new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return ChooseActionContent.this.onClick(event.getListenerActor());
            }
        };
    }

    ChooseActionContent(ActionDialog dialog)
    {
        this();
        this.dialog = dialog;
        addButton("Feed","icons/food");
        addButton("First Aid","icons/heart");
        addButton("Play","icons/controller");
        addButton("Discipline","icons/discipline");
    }

    void addButton(String label, String fileName)
    {
        BaseDrawableActor image = new BaseDrawableActor(fileName, 220, 220);
        ImageTextButton imageTextButton = new ImageTextButton(label, 30, image);
        imageTextButton.addListener(listener);
        add(imageTextButton).pad(100);
        if (buttons % 2 == 1)
        {
            row();
        }
        buttons++;
    }

    boolean onClick(Actor actor) {
        ActionQueue queue = MyGame.getInstance().getActionQueue();
        String actorName = actor.getName();
        if (actorName.equals("background"))
        {
            return true;
        }
        if (actorName.equals("Feed"))
        {
            SoundHandler.getInstance().playSound("click");
            dialog.setActiveContent(dialog.getChooseMealContent());
        }
        else if (actorName.equals("Play"))
        {
            queue.add(new PlayAction(dialog));
        }
        else if (actorName.equals("First Aid"))
        {
            queue.add(new PatchUpAction(dialog));
        }
        else if (actorName.equals("Discipline"))
        {
            queue.add(new DisciplineAction(dialog));
        }
        return false;
    }
}
