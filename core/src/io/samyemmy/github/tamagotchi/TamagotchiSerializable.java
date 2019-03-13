package io.samyemmy.github.tamagotchi;

public class TamagotchiSerializable
{
    float saturation;
    float happiness;
    float discipline;
    float body;
    long lastTimestamp;

    TamagotchiSerializable(Tamagotchi tamagotchi)
    {
        this.saturation = tamagotchi.getSaturation();
        this.happiness = tamagotchi.getHappiness();
        this.discipline = tamagotchi.getDiscipline();
        this.body = tamagotchi.getBody();
        this.lastTimestamp = tamagotchi.getLastTimestamp();
    }
}
