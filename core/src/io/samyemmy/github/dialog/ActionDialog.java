package io.samyemmy.github.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import io.samyemmy.github.BaseDrawableActor;
import io.samyemmy.github.Font;
import io.samyemmy.github.FontActor;
import io.samyemmy.github.MyGame;

public class ActionDialog extends BaseDialog
{
    private ChooseActionContent activeContent;
    private ChooseActionContent chooseActionContent;
    private ChooseActionContent chooseMealContent;


    ChooseActionContent getChooseMealContent() {
        return chooseMealContent;
    }
    ChooseActionContent getChooseActionContent() {
    return chooseActionContent;
}

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

    public void setActiveContent(ChooseActionContent content)
    {
        if (activeContent != null)
        {
            removeActor(activeContent);
        }
        addActor(content);
        this.activeContent = content;
    }
}
