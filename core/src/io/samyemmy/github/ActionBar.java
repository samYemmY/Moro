package io.samyemmy.github;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ActionBar extends Table
{
    Image[] meals;
    Image[] drugs;
    Button[] messages;
    Actor dropzone;

    public ActionBar(Actor dropzone)
    {
//        this.dropzone = dropzone;
//
//        TextureManager manager = new TextureManager();
//
//        // Meals
//        Image imgCandy = manager.createImage("candy.png");
//        new MyDragAndDrop(imgCandy, dropzone, manager.createImage("candy.png"), manager.createImage("candy_green.png"));
//        Image imgCarrot = manager.createImage("karotte.png");
//        new MyDragAndDrop(imgCarrot, dropzone, manager.createImage("karotte.png"), manager.createImage("karotte_grün.png"));
//        this.meals = new Image[]{imgCandy, imgCarrot};
//
//        // Drugs
//        Image imgSyringe = manager.createImage("spritze.png");
//        new MyDragAndDrop(imgSyringe, dropzone, manager.createImage("spritze.png"), manager.createImage("spritze_grün.png"));
//        drugs = new Image[]{imgSyringe};
//
//        // Messages
//        TextureRegionDrawable txdRight = manager.createTextureRegionDrawable("right.png");
//        Button btnRight = new Button(txdRight);
//        TextureRegionDrawable txdWrong = manager.createTextureRegionDrawable("wrong.png");
//        Button btnWrong = new Button(txdWrong);
//        messages = new Button[]{btnRight, btnWrong};
    }
}
