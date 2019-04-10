package io.samyemmy.github;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class BaseTabBar extends Table
{
    Screen screen;
    private TextButton.TextButtonStyle textButtonStyle;
    private TextButton textButtonStats;
    private TextButton textButtonActions;
    private TextButton textButtonLight;

    public BaseTabBar(Screen screen)
    {
        this.screen = screen;
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
        txtBtnStats.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                BaseTabBar.this.onClick(text);
            }
        });
        add(txtBtnStats).grow();
    }

    void onClick(String action) {}
}