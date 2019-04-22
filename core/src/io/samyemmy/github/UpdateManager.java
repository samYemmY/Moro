package io.samyemmy.github;

import com.badlogic.gdx.Gdx;

import java.io.File;

import io.samyemmy.github.dialog.StatsDialog;

public class UpdateManager
{
    private FileManager fileManager;
    private StatsDialog statsDialog;

    public UpdateManager(File filesDir)
    {
        this.fileManager = new FileManager(filesDir);
    }

    public UpdateManager(FileManager fileManager, StatsDialog statsDialog)
    {
        this.fileManager = fileManager;
        this.statsDialog = statsDialog;
    }

    public boolean background()
    {
        Tamagotchi tamagotchi = new Tamagotchi(fileManager.deserialize(), true);
        tamagotchi.updateBackground();
        fileManager.serialize(tamagotchi.getSerializable());
        return tamagotchi.needsAttention;
    }

    public void foreground()
    {
        if (statsDialog == null)
        {
            return;
        }

        Tamagotchi tamagotchi = MyGame.getInstance().getTamagotchi();
        tamagotchi.update();
        statsDialog.getStatusBarSaturation().setValue(tamagotchi.getSaturation());
        statsDialog.getStatusBarHappiness().setValue(tamagotchi.getHappiness());
        statsDialog.getStatusBarDiscipline().setValue(tamagotchi.getDiscipline());
        statsDialog.getStatusBarEnergy().setValue(tamagotchi.getEnergy());
    }
}
