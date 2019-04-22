package io.samyemmy.github;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;

public class BaseTabBar extends Table
{
    Screen screen;
    private TextButton.TextButtonStyle textButtonStyle;
    protected Array<TextButton> buttons;

    public BaseTabBar(Screen screen)
    {
        this.screen = screen;
        this.buttons = new Array<TextButton>();
        float height = 200;
        setBounds(0, Gdx.graphics.getHeight() - height, Gdx.graphics.getWidth(), height);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = Font.get(30);
        style.fontColor = Color.BLACK;
        this.textButtonStyle = style;
    }

    void addButton(final String text)
    {
        TextButton txtBtnStats = new TextButton(text, textButtonStyle);
        txtBtnStats.setName(text);
        txtBtnStats.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                BaseTabBar.this.onClick(text, actor);
            }
        });
        add(txtBtnStats).grow();
        buttons.add(txtBtnStats);
    }

    public TextButton getButton(String name)
    {
        for(TextButton button : buttons)
        {
            if (button.getName().equals(name))
            {
                return button;
            }
        }
        return null;
    }

    void onClick(String action, Actor actor) {}
}