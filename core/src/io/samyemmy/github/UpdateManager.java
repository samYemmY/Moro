package io.samyemmy.github;

import com.badlogic.gdx.Gdx;

import java.io.File;

import io.samyemmy.github.dialog.StatsDialog;
import io.samyemmy.github.tamagotchi.Tamagotchi;

class UpdateManager
{
    private static final String TAG = "UpdateManager";
    private FileManager fileManager;
    private StatsDialog statsDialog;

    UpdateManager(File filesDir)
    {
        this.fileManager = new FileManager(filesDir);
    }

    UpdateManager(FileManager fileManager, StatsDialog statsDialog)
    {
        this.fileManager = fileManager;
        this.statsDialog = statsDialog;
    }

    void background()
    {
        Tamagotchi tamagotchi = new Tamagotchi(fileManager.deserialize());
        tamagotchi.update(false);
        fileManager.serialize(tamagotchi.getSerializable());
    }

    void foreground()
    {
        Gdx.app.debug(TAG, "foreground()");

        if (statsDialog == null)
        {
            Gdx.app.debug(TAG,"StatsDialog is null. Returning.");
            return;
        }

        Tamagotchi tamagotchi = MyGame.getInstance().getTamagotchi();
        tamagotchi.update(true);
        statsDialog.getStatusBarSaturation().setValue(tamagotchi.getSaturation());
        statsDialog.getStatusBarHappiness().setValue(tamagotchi.getHappiness());
        statsDialog.getStatusBarDiscipline().setValue(tamagotchi.getDiscipline());
        statsDialog.getStatusBarEnergy().setValue(tamagotchi.getEnergy());
    }
}
