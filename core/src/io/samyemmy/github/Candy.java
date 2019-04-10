package io.samyemmy.github;

import com.badlogic.gdx.Gdx;

public class Candy extends AnimatableActor
{
    private static final String TAG = "Candy";
    public Candy()
    {
        super("anim/food/candy", 0.3f, 150, 150);
    }

    @Override
    void onAnimationFinished() {
        super.onAnimationFinished();
        Gdx.app.debug(TAG, "onAnimationFinished()");
        remove();
    }
}
