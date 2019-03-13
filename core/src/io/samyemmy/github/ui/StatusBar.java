package io.samyemmy.github.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import io.samyemmy.github.BaseDrawableActor;
import io.samyemmy.github.Font;
import io.samyemmy.github.MyGame;

public class StatusBar extends Table
{
    private static final String TAG = "StatusBar";
    BaseDrawableActor bar;
    TextField textField;
    int width = (int) (Gdx.graphics.getWidth() * 0.75);
    int height = 100;
    float value = 100;

    public StatusBar(String title)
    {
        setPosition(0, 0);
        bar = new BaseDrawableActor(MyGame.skinDefault, "progressbar-100", width, height);
        bar.setName(title);
        textField = new TextField(title, getTextFieldStyle());
        add(textField).fillX().padBottom(20).row();
        add(bar);
    }

    public StatusBar(String title, float value)
    {
        this(title);
        setValue(value);
    }

    private TextField.TextFieldStyle getTextFieldStyle()
    {
        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = Font.get(30);
        style.fontColor = Color.BLACK;
        return style;
    }

    public void setValue(float value)
    {
        if (value <= 0)
        {
//            setBar("progressbar-0");
        }
        else if (value <= 16.6)
        {
//            setBar("progressbar-16,6");
        }
        else if (value <= 33.3)
        {
//            setBar("progressbar-33,3");
        }
        else if (value <= 50)
        {
            setBar("progressbar-50");
        }
        else if (value <= 66.6)
        {
//            setBar("progressbar-66,6");
        }
        else if (value <= 83.3)
        {
//            setBar("progressbar-83,3");
        }
        else
        {
//            setBar("progressbar-100");
        }
    }

    private void setBar(String filename)
    {
        bar.setTextureRegion(MyGame.skinDefault.getRegion(filename), width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Gdx.app.debug(TAG, "draw()");
    }
}