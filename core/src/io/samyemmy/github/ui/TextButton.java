package io.samyemmy.github.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import io.samyemmy.github.BaseDrawableActor;
import io.samyemmy.github.Font;
import io.samyemmy.github.MyGame;

public class TextButton extends Table
{
    private TextField textField;
    private BaseDrawableActor image;

    public TextButton(String text, String fileName, InputListener listener)
    {
        addListener(listener);
        setName(fileName);

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = Font.get(30);
        style.fontColor = Color.BLACK;

        BaseDrawableActor image = new BaseDrawableActor(MyGame.skinDefault, fileName, 150, 150);
        add(image).row();
        TextField textField = new TextField(text, style);
        add(textField);
    }
}
