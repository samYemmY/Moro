package io.samyemmy.github;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

public class Launchpad extends Window
{
    private int maxLaunchBtns = 8;
    private Array<Button> launchButtons = new Array<Button>(true, maxLaunchBtns, Button.class);
    private Screen gameScreen;
    private Button btnCloseWindow;
    private Button btnFood;
    private Button btnController;
    private Button btnCross;
    private Button btnToilet;
    private Button btnMessage;
    private Button btnClock;


    public Launchpad(String title, MainScreen gameScreen)
    {
        super(title, MyGame.skinDefault);
        this.setWidth(Gdx.graphics.getWidth()*5/6f);
        this.setHeight(Gdx.graphics.getHeight()*5/6f);
        this.gameScreen = gameScreen;
        debug();

        MyButton button = new MyButton(MyGame.skinDefault, "x");
        addCloseButton(button);

//        this.btnFood = new Button(manager.createTextureRegionDrawable("food.png"));
//        addLaunchButton(btnFood);
//        this.btnController = new Button(manager.createTextureRegionDrawable("controller.png"));
//        addLaunchButton(btnController);
//        this.btnCross = new Button(manager.createTextureRegionDrawable("cross.png"));
//        addLaunchButton(btnCross);
//        this.btnToilet = new Button(manager.createTextureRegionDrawable("klo.png"));
//        addLaunchButton(btnToilet);
//        this.btnMessage = new Button(manager.createTextureRegionDrawable("message.png"));
//        addLaunchButton(btnMessage);
//        this.btnClock = new Button(manager.createTextureRegionDrawable("clock.png"));
//        addLaunchButton(btnClock);

        this.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Launchpad.this.onClick(event, actor);
            }
        });

        init();
    }

    private void onClick(ChangeListener.ChangeEvent even, Actor actor)
    {
        if (actor == this.btnCloseWindow)
        {
            this.setVisible(false);
        }
    }

    public void addLaunchButton(Button button)
    {
        this.launchButtons.add(button);
    }

    public void addCloseButton(Button button)
    {
        this.btnCloseWindow = button;
        this.btnCloseWindow.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Launchpad.this.setVisible(false);
            }
        });
    }

    public void init()
    {
        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = Font.get(20);
        style.fontColor = Color.BLACK;
        TextField text = new TextField("Actions", style);
        text.setPosition(20, getHeight() - 20);
        addActor(text);

        Table launchBtnTable = new Table();
        launchBtnTable.debug();
        launchBtnTable.setHeight(this.getHeight()*5/6);
        launchBtnTable.setWidth(this.getWidth());
        for (int i=0; i<launchButtons.items.length; i++)
        {
            Button btn = this.launchButtons.items[i];
            launchBtnTable.add(btn).pad(40);
            if (i % 2 == 1) launchBtnTable.row();
        }
        launchBtnTable.moveBy(0, 200);
        this.addActor(launchBtnTable);
    }
}
