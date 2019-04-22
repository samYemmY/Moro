package io.samyemmy.github;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class BaseDrawableActor extends Actor
{
    private TextureRegionDrawable textureRegionDrawable;
    private Rectangle rectangle;

    protected TextureRegionDrawable getTextureRegionDrawable(){ return this.textureRegionDrawable; }

    public BaseDrawableActor()
    {
        this.rectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public BaseDrawableActor(String fileName)
    {
        this.textureRegionDrawable = (TextureRegionDrawable) MyGame.SKIN.getDrawable(fileName);
        this.rectangle = new Rectangle(getX(), getY(), textureRegionDrawable.getRegion().getRegionWidth(), textureRegionDrawable.getRegion().getRegionHeight());
        setSize(textureRegionDrawable.getRegion().getRegionWidth(), textureRegionDrawable.getRegion().getRegionHeight());
    }

    public BaseDrawableActor(String fileName, float width, float height)
    {
        this.textureRegionDrawable = (TextureRegionDrawable) MyGame.SKIN.getDrawable(fileName);
        this.rectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
        setSize(width, height);
    }

    public BaseDrawableActor(Pixmap pixmap)
    {
        this.textureRegionDrawable = new TextureRegionDrawable(new Texture(pixmap));
        this.rectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
        setSize(textureRegionDrawable.getRegion().getRegionWidth(), textureRegionDrawable.getRegion().getRegionHeight());
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
            textureRegionDrawable.draw(batch, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),getScaleX(), getScaleY(), getRotation());
        }
    }

    public void setTextureRegionDrawable(TextureRegionDrawable textureRegionDrawable, float width, float height)
    {
        this.textureRegionDrawable = textureRegionDrawable;
        setSize(width, height);
    }

}
