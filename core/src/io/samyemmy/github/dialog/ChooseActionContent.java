package io.samyemmy.github.dialog;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import io.samyemmy.github.BaseDrawableActor;
import io.samyemmy.github.GameScreen;
import io.samyemmy.github.MyGame;
import io.samyemmy.github.Utils;
import io.samyemmy.github.tamagotchi.DisciplineAction;
import io.samyemmy.github.tamagotchi.PatchUpAction;
import io.samyemmy.github.tamagotchi.PlayAction;
import io.samyemmy.github.tamagotchi.Tamagotchi;
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
        final Tamagotchi tamagotchi = MyGame.getInstance().getTamagotchi();
        String actorName = actor.getName();
        if (actorName.equals("background"))
        {
            return true;
        }
        if (actorName.equals("Feed"))
        {
            dialog.setActiveContent(dialog.getChooseMealContent());
        }
        else if (actorName.equals("Play"))
        {
            tamagotchi.executeAction(new PlayAction(dialog));
        }
        else if (actorName.equals("First Aid"))
        {
            tamagotchi.executeAction(new PatchUpAction(dialog));
        }
        else if (actorName.equals("Discipline"))
        {
            tamagotchi.executeAction(new DisciplineAction(dialog));
    }
        return false;
    }
}
