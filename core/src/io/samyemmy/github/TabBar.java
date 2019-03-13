package io.samyemmy.github;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class TabBar extends Table
{
    MainScreen screen;

    public TabBar(MainScreen screen)
    {
        this.screen = screen;
        float height = 0.1f * Gdx.graphics.getHeight();
        setBounds(0, Gdx.graphics.getHeight() - height, Gdx.graphics.getWidth(), height);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = Font.get(30);
        style.fontColor = Color.BLACK;
        style.downFontColor = Color.GRAY;

        TextButton txtBtnStats = new TextButton("Stats", style);
        txtBtnStats.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                TabBar.this.onClick("Stats");
            }
        });
        add(txtBtnStats).grow();

        TextButton txtBtnActions = new TextButton("Actions", style);
        txtBtnActions.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                TabBar.this.onClick("Actions");
            }
        });
        add(txtBtnActions).grow();

        TextButton txtBtnLight = new TextButton("Light", style);
        txtBtnLight.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                TabBar.this.onClick("Light");
            }
        });
        add(txtBtnLight).grow();
    }

    private void onClick(String action)
    {
        if (action.equals("Actions"))
        {
            screen.getStatsDialog().hide();
            screen.toggleDialog(screen.getActionDialog());
        }
        else if (action.equals("Stats"))
        {
            screen.getActionDialog().hide();
            screen.toggleDialog(screen.getStatsDialog());
        }
    }
}
