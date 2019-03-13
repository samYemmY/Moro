package io.samyemmy.github.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import io.samyemmy.github.BaseDrawableActor;
import io.samyemmy.github.Font;
import io.samyemmy.github.MyGame;

public abstract class BaseDialog extends Group
{
    private TextField titleField;

    public String getTitle() {
        return title;
    }

    private String title;

    public InputListener getInputListener() {
        return inputListener;
    }

    private InputListener inputListener;


    public BaseDialog(String title)
    {
        this.inputListener = new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return BaseDialog.this.onClick(event.getListenerActor());
            }
        };

        setWidth(Gdx.graphics.getWidth());
        setHeight(Gdx.graphics.getHeight()-0.1f*Gdx.graphics.getHeight());
        setPosition(0,0);
        setBackground("dialog");

        Table tabBarTable = new Table();
        tabBarTable.setName("tabBar");
        tabBarTable.setSize(getWidth(), getHeight() / 6);
        tabBarTable.setPosition(0, getHeight() - tabBarTable.getHeight());

        Table titleTable = new Table();
        titleTable.center();
        TextField titleTextField = new TextField(getTitle(), Font.getTextFieldStyle(60));
        titleTextField.setDisabled(true);
        titleTable.add(titleTextField).grow().padLeft(50);
        this.titleField = titleTextField;

        tabBarTable.add(titleTable).colspan(3).grow();

        setTitle(title);

        Table closeBtnTable = new Table();
        closeBtnTable.right();

        BaseDrawableActor btnClose = new BaseDrawableActor(MyGame.skinDefault, "x");
        btnClose.setSize(150, 150);
        btnClose.addListener(getInputListener());
        closeBtnTable.add(btnClose).padRight(30);

        tabBarTable.add(closeBtnTable).colspan(1).grow();

        addActor(tabBarTable);

    }

    public void setBackground(String fileName)
    {
        BaseDrawableActor background = new BaseDrawableActor(MyGame.skinDefault, fileName);
        background.setSize(getWidth(), getHeight());
        background.setName("background");
        addActor(background);
    }

    public void show()
    {
        setVisible(true);
    }

    public void hide()
    {
        setVisible(false);
    }

    public void toggle()
    {
        if (isVisible())
        {
           hide();
        }
        else
        {
            show();
        }
    }

    boolean onClick(Actor actor)
    {
        return false;
    }

    void setTitle(String title)
    {
        titleField.setText(title);
        this.title = title;
    }
}
