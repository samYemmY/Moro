package io.samyemmy.github.tamagotchi;

public class TamagotchiSerializable
{
    float saturation;
    float happiness;
    float discipline;
    float body;
    float health;
    float energy;
    long timestampSaturation;
    long timestampHappiness;
    long timestampHealth;
    long timestampPoop;
    long timestampRandomAttention;
    boolean needsAttention;
    boolean punishableAttentionFlag;
    boolean isPunishable;
    boolean isSick;
    boolean hasActiveAction;
    boolean isAlive;


    TamagotchiSerializable(Tamagotchi tamagotchi)
    {
        this.saturation = tamagotchi.getSaturation();
        this.happiness = tamagotchi.getHappiness();
        this.discipline = tamagotchi.getDiscipline();
        this.body = tamagotchi.getBody();
        this.health = tamagotchi.getHealth();
        this.energy = tamagotchi.getEnergy();
        this.needsAttention = tamagotchi.needsAttention;
        this.timestampSaturation = tamagotchi.timestampSaturation;
        this.timestampHappiness = tamagotchi.timestampHappiness;
        this.timestampHealth = tamagotchi.timestampHealth;
        this.timestampPoop = tamagotchi.timestampPoop;
        this.timestampRandomAttention = tamagotchi.timestampRandomAttention;
        this.punishableAttentionFlag = tamagotchi.punishableAttentionFlag;
        this.isPunishable = tamagotchi.isPunishable;
        this.isSick = tamagotchi.isSick;
        this.hasActiveAction = tamagotchi.hasActiveAction;
        this.isAlive = tamagotchi.isAlive;
    }
}
