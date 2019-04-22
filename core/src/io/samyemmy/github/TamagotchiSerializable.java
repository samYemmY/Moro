package io.samyemmy.github;

public class TamagotchiSerializable
{
    float saturation;
    float happiness;
    float discipline;
    float health;
    float energy;
    long timestampSaturation;
    long timestampHappiness;
    long timestampHealth;
    long timestampPoop;
    long timestampRandomAttention;
    long timestampSleep;
    long timestampSick;
    boolean needsAttention;
    boolean punishableAttentionFlag;
    boolean isPunishable;
    boolean isSick;
    boolean isAlive;
    boolean isSleeping;


    TamagotchiSerializable(Tamagotchi tamagotchi)
    {
        this.saturation = tamagotchi.getSaturation();
        this.happiness = tamagotchi.getHappiness();
        this.discipline = tamagotchi.getDiscipline();
        this.health = tamagotchi.getHealth();
        this.energy = tamagotchi.getEnergy();
        this.needsAttention = tamagotchi.needsAttention;
        this.timestampSaturation = tamagotchi.timestampSaturation;
        this.timestampHappiness = tamagotchi.timestampHappiness;
        this.timestampHealth = tamagotchi.timestampHealth;
        this.timestampPoop = tamagotchi.timestampPoop;
        this.timestampRandomAttention = tamagotchi.timestampRandomAttention;
        this.timestampSleep = tamagotchi.timestampSleep;
        this.timestampSick = tamagotchi.timestampSick;
        this.punishableAttentionFlag = tamagotchi.punishableAttentionFlag;
        this.isPunishable = tamagotchi.isPunishable;
        this.isSick = tamagotchi.isSick;
        this.isAlive = tamagotchi.isAlive;
        this.isSleeping = tamagotchi.isSleeping;
    }
}
