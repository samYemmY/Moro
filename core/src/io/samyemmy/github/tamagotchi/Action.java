package io.samyemmy.github.tamagotchi;

public interface Action
{
    float execute(Tamagotchi tamagotchi);
    void dispose(Tamagotchi tamagotchi);
}
