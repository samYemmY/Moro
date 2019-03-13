package io.samyemmy.github.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import io.samyemmy.github.BaseDrawableActor;
import io.samyemmy.github.Font;
import io.samyemmy.github.MyGame;

public class StatusBar extends BaseDrawableActor
{
    private static final String TAG = "StatusBar";
    private float value = 100;
    private BitmapFont font = Font.get(30);

    public StatusBar(String title)
    {
        super(MyGame.skinDefault, "progressbar-100", Gdx.graphics.getWidth() * 0.75f, 100);
        setName(title);
        font.setColor(Color.BLACK);
    }

    public StatusBar(String title, float value)
    {
        this(title);
        setValue(value);
    }

    public void setValue(float value)
    {
        if (value <= 0)
        {
            setBar("progressbar-0");
        }
        else if (value <= 16.6)
        {
            setBar("progressbar-16,6");
        }
        else if (value <= 33.3)
        {
            setBar("progressbar-33,3");
        }
        else if (value <= 50)
        {
            setBar("progressbar-50");
        }
        else if (value <= 66.6)
        {
            setBar("progressbar-66,6");
        }
        else if (value <= 83.3)
        {
            setBar("progressbar-83,3");
        }
        else
        {
            setBar("progressbar-100");
        }
        this.value = value;
    }

    public float getValue()
    {
        return value;
    }

    private void setBar(String filename)
    {
        setTextureRegion(MyGame.skinDefault.getRegion(filename), getWidth(), getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        int offsetFont = 150;
        font.draw(batch, getName(), getX(), getY() + offsetFont);
    }
}