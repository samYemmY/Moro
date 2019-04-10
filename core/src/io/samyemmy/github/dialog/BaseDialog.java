package io.samyemmy.github.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import io.samyemmy.github.BaseDrawableActor;
import io.samyemmy.github.FontActor;

public abstract class BaseDialog extends Group
{
    private FontActor faTitle;
    Table titleTable;

    BaseDialog(String title)
    {
        setWidth(Gdx.graphics.getWidth());
        setHeight(Gdx.graphics.getHeight());
        setPosition(0,0);

        Pixmap pixmap = new Pixmap((int) getWidth(),(int) getHeight(), Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        addActor(new BaseDrawableActor(pixmap));
    }

    public void show()
    {
        setVisible(true);
    }

    public void hide()
    {
        setVisible(false);
    }

    public void toggle()
    {
        if (isVisible())
        {
           hide();
        }
        else
        {
            show();
        }
    }
}
