package io.samyemmy.github.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import io.samyemmy.github.Font;
import io.samyemmy.github.MyGame;
import io.samyemmy.github.TabBar;
import io.samyemmy.github.ui.StatusBar;

public class StatsDialog extends BaseDialog
{
    private static final String TAG = "StatsDialog";
    private Table contentTable;
    private StatusBar statusBarHappiness;
    private StatusBar statusBarSaturation;
    private StatusBar statusBarBody;

    public StatusBar getStatusBarHappiness() {
        return statusBarHappiness;
    }
    public StatusBar getStatusBarSaturation() {
        return statusBarSaturation;
    }
    public StatusBar getStatusBarDiscipline() {
        return statusBarDiscipline;
    }
    private StatusBar statusBarDiscipline;
    public StatusBar getStatusBarBody() {
        return statusBarBody;
    }

    public StatsDialog()
    {
        super("Stats");
        Gdx.app.debug(TAG, "StatsDialog()");
        this.statusBarSaturation = new StatusBar("Saturation");
        this.statusBarHappiness = new StatusBar("Happiness");
        this.statusBarDiscipline = new StatusBar("Discipline");
        this.statusBarBody = new StatusBar("Body");

        Table contentTable = new Table();
        contentTable.add(this.statusBarSaturation).grow().row();
        contentTable.add(this.statusBarHappiness).grow().row();
        contentTable.add(this.statusBarDiscipline).grow().row();
        contentTable.add(this.statusBarBody).grow();
        contentTable.setSize(getWidth(), getHeight() * 0.8f);
        contentTable.setY(getY() + 60);
        addActor(contentTable);

        setVisible(false);
    }
}
