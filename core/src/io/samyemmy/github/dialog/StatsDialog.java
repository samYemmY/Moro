package io.samyemmy.github.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import io.samyemmy.github.ui.StatusBar;

public class StatsDialog extends BaseDialog
{
    private Table contentTable;

    public StatusBar getStatusBarHappiness() {
        return statusBarHappiness;
    }

    private StatusBar statusBarHappiness;

    public StatusBar getStatusBarSaturation() {
        return statusBarSaturation;
    }

    private StatusBar statusBarSaturation;

    public StatusBar getStatusBarDiscipline() {
        return statusBarDiscipline;
    }

    private StatusBar statusBarDiscipline;

    public StatusBar getStatusBarBody() {
        return statusBarBody;
    }

    private StatusBar statusBarBody;

    public StatsDialog()
    {
        super("Stats");
        contentTable = new Table();
        contentTable.setHeight(getHeight() * 0.6f);
        contentTable.setPosition(getWidth() / 2 - contentTable.getWidth() / 2, getHeight() / 2 - contentTable.getHeight() / 2);

        StatusBar statusBar = new StatusBar("Saturation");
        contentTable.add(statusBar).grow().row();
        this.statusBarSaturation = statusBar;

        statusBar = new StatusBar("Happiness");
        contentTable.add(statusBar).grow().row();
        this.statusBarHappiness = statusBar;

        statusBar = new StatusBar("Discipline");
        contentTable.add(statusBar).grow().row();
        this.statusBarDiscipline = statusBar;

        statusBar = new StatusBar("Body");
        contentTable.add(statusBar).grow();
        this.statusBarBody = statusBar;

        addActor(contentTable);
        setVisible(false);
    }
}
