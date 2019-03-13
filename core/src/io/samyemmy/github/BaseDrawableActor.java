package io.samyemmy.github;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class BaseDrawableActor extends Actor
{
    private static final String TAG = "BaseDrawableActor";
    private TextureRegion textureRegion;
    private Rectangle rectangle;

    public Rectangle getRectangle(){ return this.rectangle; }

    public BaseDrawableActor(Skin skin, String fileName, float width, float height)
    {
        setName(fileName);
        this.textureRegion = skin.getRegion(fileName);
        this.rectangle = new Rectangle((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
        setSize(width, height);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        rectangle.setPosition(x, y);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        rectangle.setSize(width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (isVisible())
        {
            batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),getScaleX(), getScaleY(), getRotation());
        }
    }

    public void setTextureRegion(TextureRegion textureRegion, float width, float height)
    {
        this.textureRegion = textureRegion;
        setSize(width, height);
    }

}
