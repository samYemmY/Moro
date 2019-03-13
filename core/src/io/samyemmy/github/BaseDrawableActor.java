package io.samyemmy.github;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class BaseDrawableActor extends Actor
{
    private static final String TAG = "BaseDrawableActor";
    private TextureRegion textureRegion;

    public BaseDrawableActor(Skin skin, String fileName)
    {
        textureRegion = skin.getRegion(fileName);
        setName(fileName);
        setSize(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
    }

    public BaseDrawableActor(Skin skin, String fileName, int width, int height)
    {
        textureRegion = skin.getRegion(fileName);
        setName(fileName);
        setSize(width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (getName().equals("Discipline"))
        {
            Gdx.app.debug(TAG, "draw()");
        }
        if (isVisible())
        {
            batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),getScaleX(), getScaleY(), getRotation());
        }
    }

    public void setTextureRegion(TextureRegion textureRegion, int width, int height)
    {
        this.textureRegion = textureRegion;
        setSize(width, height);
    }

}
