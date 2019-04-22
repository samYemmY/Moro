package io.samyemmy.github;

import com.badlogic.gdx.Gdx;

public class Candy extends AnimatableActor
{
    public Candy()
    {
        super("anim/food/candy", 0.3f, 150, 150);
    }

    @Override
    protected void onAnimationFinished() {
        super.onAnimationFinished();
        remove();
    }
}
