package io.samyemmy.github;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;

public class SoundHandler
{
    private static SoundHandler INSTANCE;
    private HashMap<String, Sound> sounds;
    private HashMap<String, Music> musics;

    public static SoundHandler getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new SoundHandler();
        }
        return INSTANCE;
    }

    private SoundHandler()
    {
        sounds = new HashMap<String, Sound>();
        sounds.put("click", Gdx.audio.newSound(Gdx.files.internal("audio/click.wav")));
        sounds.put("denied", Gdx.audio.newSound(Gdx.files.internal("audio/denied.wav")));
        sounds.put("discipline", Gdx.audio.newSound(Gdx.files.internal("audio/discipline.wav")));
        sounds.put("win", Gdx.audio.newSound(Gdx.files.internal("audio/win.wav")));
        sounds.put("loose", Gdx.audio.newSound(Gdx.files.internal("audio/loose.wav")));
        sounds.put("jump", Gdx.audio.newSound(Gdx.files.internal("audio/jump.wav")));
        sounds.put("select", Gdx.audio.newSound(Gdx.files.internal("audio/select.wav")));
        sounds.put("shake_head", Gdx.audio.newSound(Gdx.files.internal("audio/shake_head.wav")));
        sounds.put("start_game", Gdx.audio.newSound(Gdx.files.internal("audio/start_game.wav")));

        musics = new HashMap<String, Music>();
        musics.put("hypnotic", Gdx.audio.newMusic(Gdx.files.internal("audio/hypnotic.mp3")));
    }

    public void playSound(String name)
    {
        Sound sound = sounds.get(name);
        sound.play(1);
    }

    public void playMusic(String name){
        Music music = musics.get(name);
        music.setVolume(0.5f);
        music.setLooping(true);
        music.play();
    }

    public void stopMusic(String name)
    {
        Music music = musics.get(name);
        music.stop();
    }
}
