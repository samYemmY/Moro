package io.samyemmy.github;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import javax.swing.event.ChangeEvent;
import javax.xml.soap.Text;

public class TabBar extends Table
{
    TextButton txtBtnStats;
    TextButton txtBtnActions;
    TextButton txtBtnLight;
    MainScreen screen;

    public TabBar(final MainScreen screen)
    {
        super();
        this.screen = screen;
        setSkin(MyGame.skinDefault);
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
                TabBar.this.onClickStats();
            }
        });
        add(txtBtnStats).grow();

        TextButton txtBtnActions = new TextButton("Actions", style);
        txtBtnActions.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                TabBar.this.onClickActions();
            }
        });
        add(txtBtnActions).grow();

        TextButton txtBtnLight = new TextButton("Light", style);
        txtBtnLight.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                TabBar.this.onClickLight();
            }
        });
        add(txtBtnLight).grow();

        debug();
    }

    private void onClickStats()
    {

    }

    private void onClickActions()
    {
        this.screen.toggleLaunchPad();
    }

    private void onClickLight()
    {

    }
}
