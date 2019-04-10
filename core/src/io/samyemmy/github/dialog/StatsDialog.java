package io.samyemmy.github.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import io.samyemmy.github.ui.StatusBar;

public class StatsDialog extends BaseDialog
{
    private static final String TAG = "StatsDialog";
    private StatusBar statusBarHappiness;
    private StatusBar statusBarSaturation;
    private StatusBar statusBarEnergy;
    private StatusBar statusBarDiscipline;

    public StatusBar getStatusBarHappiness() {
        return statusBarHappiness;
    }
    public StatusBar getStatusBarSaturation() {
        return statusBarSaturation;
    }
    public StatusBar getStatusBarDiscipline() {
        return statusBarDiscipline;
    }
    public StatusBar getStatusBarEnergy() {
        return statusBarEnergy;
    }

    public StatsDialog()
    {
        super("Stats");
        Gdx.app.debug(TAG, "StatsDialog()");
        this.statusBarSaturation = new StatusBar("Saturation");
        this.statusBarHappiness = new StatusBar("Happiness");
        this.statusBarEnergy = new StatusBar("Energy");
        this.statusBarDiscipline = new StatusBar("Discipline");

        Table contentTable = new Table();
        contentTable.add(this.statusBarSaturation).grow().row();
        contentTable.add(this.statusBarHappiness).grow().row();
        contentTable.add(this.statusBarEnergy).grow().row();
        contentTable.add(this.statusBarDiscipline).grow();
        contentTable.setSize(getWidth(), getHeight() * 0.75f);
        contentTable.setPosition(getWidth() / 2 - contentTable.getWidth() / 2, getHeight() / 2 - contentTable.getHeight() / 2);
        addActor(contentTable);

        setVisible(false);
    }
}
