package io.samyemmy.github;

import com.badlogic.gdx.Gdx;

import java.io.File;

import io.samyemmy.github.dialog.StatsDialog;
import io.samyemmy.github.tamagotchi.Tamagotchi;

class UpdateManager
{
    private static final String TAG = "UpdateManager";
    private FileManager fileManager;
    private MainScreen mainScreen;

    UpdateManager(File filesDir)
    {
        this.fileManager = new FileManager(filesDir);
    }
    UpdateManager(FileManager fileManager, MainScreen mainScreen)
    {
        this.fileManager = fileManager;
        this.mainScreen = mainScreen;
    }

    void background()
    {
        Gdx.app.debug(TAG, "background()");
        Tamagotchi tamagotchi = new Tamagotchi(fileManager.deserialize());
        tamagotchi.update();
        MyGame.getInstance().setTamagotchi(tamagotchi);
        fileManager.serialize(tamagotchi.getSerializable());
    }

    void foreground()
    {
        Gdx.app.debug(TAG, "foreground()");
        StatsDialog dialog = mainScreen.getStatsDialog();

        if (dialog == null)
        {
            Gdx.app.debug(TAG,"StatsDialog is null. Returning.");
            return;
        }

        Tamagotchi tamagotchi = MyGame.getInstance().getTamagotchi();
        tamagotchi.update();
        dialog.getStatusBarSaturation().setValue(tamagotchi.getSaturation());
        dialog.getStatusBarHappiness().setValue(tamagotchi.getHappiness());
        dialog.getStatusBarDiscipline().setValue(tamagotchi.getDiscipline());
        dialog.getStatusBarBody().setValue(tamagotchi.getBody());
    }
}
