package io.samyemmy.github;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import io.samyemmy.github.tamagotchi.Tamagotchi;
import io.samyemmy.github.tamagotchi.TamagotchiSerializable;

public class FileManager
{
    private static final String TAG = "FileManager";
    private Gson gson;
    private String fileName = "Tamagotchi.json";
    private File file;

    public FileManager(File filesDir)
    {
        gson = new Gson();
        File file = new File(filesDir.getAbsolutePath() + "/" + fileName);
        this.file = file;
        if (!this.file.exists())
        {
            try
            {
                this.file.createNewFile();
                Gdx.app.debug(TAG,"Created new File: " + file);
                Tamagotchi tamagotchi = new Tamagotchi(System.currentTimeMillis());
                serialize(tamagotchi.getSerializable());
            }
            catch (IOException e) { e.printStackTrace(); }
        }
    }

    TamagotchiSerializable deserialize()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            TamagotchiSerializable tamagotchiSerializable = gson.fromJson(reader, TamagotchiSerializable.class);
            reader.close();
            return tamagotchiSerializable;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    Tamagotchi deserializeT()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            Tamagotchi tamagotchi = gson.fromJson(reader, Tamagotchi.class);
            reader.close();
            return tamagotchi;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }


    void serialize(TamagotchiSerializable tamagotchiSerializable)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String json = new Gson().toJson(tamagotchiSerializable);
            writer.write(json);
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    void serialize(Tamagotchi tamagotchi)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String json = new Gson().toJson(tamagotchi);
            writer.write(json);
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
