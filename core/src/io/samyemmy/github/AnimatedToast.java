package io.samyemmy.github;

import com.badlogic.gdx.Gdx;

public class AnimatedToast extends AnimatableActor
{
    private static final String TAG = "AnimatedToast";

    public AnimatedToast(boolean isAnimated)
    {
        super("anim/food/toast", 0.3f, 100, 100);

        if (!isAnimated)
        {
            setAnimation("icons/toast", 1.5f);
        }
    }


    @Override
    void onAnimationFinished() {
        super.onAnimationFinished();
        Gdx.app.debug(TAG, "onAnimationFinished()");
        remove();
    }
}
