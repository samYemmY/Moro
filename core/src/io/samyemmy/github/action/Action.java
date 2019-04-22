package io.samyemmy.github.action;

import io.samyemmy.github.Tamagotchi;

public interface Action
{
    boolean shouldExecute(Tamagotchi tamagotchi);
    void execute(Tamagotchi tamagotchi);
    void dispose(Tamagotchi tamagotchi);
    long getDuration();
}
