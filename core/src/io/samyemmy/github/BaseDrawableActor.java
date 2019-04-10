package io.samyemmy.github;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BaseDrawableActor extends Actor
{
    private static final String TAG = "BaseDrawableActor";
    private TextureRegion textureRegion;
    private Rectangle rectangle;

    public Rectangle getRectangle(){ return this.rectangle; }

    public BaseDrawableActor()
    {
        this.rectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public BaseDrawableActor(String fileName)
    {
        this.textureRegion = MyGame.SKIN.getRegion(fileName);
        this.rectangle = new Rectangle(getX(), getY(), textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
        setSize(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
    }

    public BaseDrawableActor(String fileName, float width, float height)
    {
        this.textureRegion = MyGame.SKIN.getRegion(fileName);
        this.rectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
        setSize(width, height);
    }

    public BaseDrawableActor(Pixmap pixmap)
    {
        this.textureRegion = new TextureRegion(new Texture(pixmap));
        this.rectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
        setSize(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
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
