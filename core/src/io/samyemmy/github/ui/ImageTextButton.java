package io.samyemmy.github.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import io.samyemmy.github.BaseDrawableActor;
import io.samyemmy.github.FontActor;

public class ImageTextButton extends Table
{
    public ImageTextButton(String label, int textSize, BaseDrawableActor image)
    {
        center();
        setName(label);
        add(image).row();
        FontActor labelActor = new FontActor(label, textSize);
        add(labelActor).top();
    }
}
