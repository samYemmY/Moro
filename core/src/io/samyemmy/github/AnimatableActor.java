package io.samyemmy.github;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimatableActor extends BaseDrawableActor {
    private Animation<TextureRegion> animation;
    private float stateTime;

    public AnimatableActor(){}

    public AnimatableActor(String animationName, float frameDuration, int width, int height)
    {
        setSize(width, height);
        stateTime = 0f;
        animation = new Animation<TextureRegion>(frameDuration, MyGame.ATLAS.findRegions(animationName));
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public void setAnimation(String animationName, float frameDuration)
    {
        Animation<TextureRegion> animation = new Animation<TextureRegion>(frameDuration, MyGame.ATLAS.findRegions(animationName));
        animation.setPlayMode(Animation.PlayMode.LOOP);
        this.animation = animation;
    }

    public void setAnimation(String animationName)
    {
        setAnimation(animationName, 0.1f);
    }

    public void act(float deltaTime)
    {
        stateTime += deltaTime;
        if (animation.isAnimationFinished(stateTime))
        {
            onAnimationFinished();
        }
    }

    protected void onAnimationFinished(){}

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(animation.getKeyFrame(stateTime), getX(), getY(), getWidth(), getHeight());
    }
}
