package io.samyemmy.github.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import io.samyemmy.github.BaseDrawableActor;
import io.samyemmy.github.Font;
import io.samyemmy.github.MyGame;

public class ActionDialog extends BaseDialog
{
    private DialogContent activeContent;

    public DialogContent getChooseActionContent() {
        return chooseActionContent;
    }

    private DialogContent chooseActionContent;

    public DialogContent getChooseMealContent() {
        return chooseMealContent;
    }

    private DialogContent chooseMealContent;

    public ActionDialog()
    {
        super("Actions");
        this.chooseActionContent = new ChooseActionContent(this);
        this.chooseMealContent = new ChooseMealContent(this);
        setActiveContent(getChooseActionContent());
        setVisible(false);
    }

    @Override
    public void show()
    {
        setActiveContent(getChooseActionContent());
        setVisible(true);
    }

    void setActiveContent(DialogContent content)
    {
        if (activeContent != null)
        {
            removeActor((Actor) activeContent);
        }
        setTitle(content.getTitle());
        addActor((Actor) content);
        this.activeContent = content;
    }
}
