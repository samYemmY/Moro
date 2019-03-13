package io.samyemmy.github;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;


import io.samyemmy.github.dialog.ChooseActionContent;

public class Poop extends BaseDrawableActor {
    private static final String TAG = "Poop";

    public Poop()
    {
        super(MyGame.skinDefault, "poop", 100, 100);
        InputListener listener = new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return Poop.this.onClick();
            }
        };
        addListener(listener);
    }

    private boolean onClick()
    {
        Gdx.app.debug(TAG, "onClick()");
        remove();
        return false;
    }
}
