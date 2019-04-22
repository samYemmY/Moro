package io.samyemmy.github.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import io.samyemmy.github.BaseDrawableActor;
import io.samyemmy.github.FontActor;
import io.samyemmy.github.MyGame;

public class StatusBar extends Table
{
    private static final String TAG = "StatusBar";
    private float value = 100;
    private FontActor fontActor;
    private BaseDrawableActor statusBar;

    public StatusBar(String title)
    {
        setWidth(Gdx.graphics.getWidth() * 0.8f);
        setHeight(150);
        FontActor fontActor = new FontActor(title, 40);
        add(fontActor).padBottom(100).row();
        this.fontActor = fontActor;
        BaseDrawableActor statusBar = new BaseDrawableActor("ui/progressbar-100");
        statusBar.setSize(800, 80);
        add(statusBar);
        this.statusBar = statusBar;
    }

    public void setValue(float value)
    {
        if (value == 100)
        {
            setBar("ui/progressbar-100");
        }
        else if (value >= 90)
        {
            setBar("ui/progressbar-90");
        }
        else if (value >= 80)
        {
            setBar("ui/progressbar-80");
        }
        else if (value >= 70)
        {
            setBar("ui/progressbar-70");
        }
        else if (value >= 60)
        {
            setBar("ui/progressbar-60");
        }
        else if (value >= 50)
        {
            setBar("ui/progressbar-50");
        }
        else if (value >= 40)
        {
            setBar("ui/progressbar-40");
        }
        else if (value >= 30)
        {
            setBar("ui/progressbar-30");
        }
        else if (value >= 20)
        {
            setBar("ui/progressbar-20");
        }
        else if (value > 0)
        {
            setBar("ui/progressbar-10");
        }
        else if (value == 0)
        {
            setBar("ui/progressbar-0");
        }
        this.value = value;
    }

    private void setBar(String filename)
    {
        statusBar.setTextureRegionDrawable((TextureRegionDrawable) MyGame.SKIN.getDrawable(filename), statusBar.getWidth(), statusBar.getHeight());
    }
}