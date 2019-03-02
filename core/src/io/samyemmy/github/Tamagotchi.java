package io.samyemmy.github;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tamagotchi extends Sprite
{
    public Tamagotchi()
    {
        super(MyGame.atlas.createSprite("tama"));
        setScale(0.3f);
        setPosition(Gdx.graphics.getWidth() / 2f - this.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - this.getHeight() / 2f);
    }
}
