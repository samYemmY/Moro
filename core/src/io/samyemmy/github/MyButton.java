package io.samyemmy.github;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MyButton extends Button
{
    public MyButton(Skin skin, String styleName)
    {
        super(skin, styleName);
        ButtonStyle style = skin.get(styleName, ButtonStyle.class);
        setStyle(style);
        getBackground().setMinWidth(200);
        getBackground().setMinHeight(200);
    }

    public MyButton(Skin skin, String styleName, int size)
    {
        super(skin, styleName);
        ButtonStyle style = skin.get(styleName, ButtonStyle.class);
        setStyle(style);
        getBackground().setMinWidth(size);
        getBackground().setMinHeight(size);
    }
}
