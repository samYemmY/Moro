package io.samyemmy.github;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class Font
{
    public static BitmapFont get(int size)
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/PressStart2P-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = Color.BLACK;
        BitmapFont font = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
        return font;
    }

    public static TextField.TextFieldStyle getTextFieldStyle(int size)
    {
        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = Font.get(size);
        style.fontColor = Color.BLACK;
        return style;
    }
}
