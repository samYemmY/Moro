package io.samyemmy.github;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FontActor extends Actor
{
    private String text;
    BitmapFont font;

    public FontActor(String text, int size)
    {
        this.text = text;
        BitmapFont font = Font.get(size);
        GlyphLayout layout = new GlyphLayout(font, text);
        setSize(layout.width, layout.height);
        this.font = font;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        font.draw(batch, getText(), getX(), getY());
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}
