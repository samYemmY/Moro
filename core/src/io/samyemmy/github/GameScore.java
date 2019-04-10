package io.samyemmy.github;

public class GameScore extends FontActor
{
    private int value;

    GameScore(int value)
    {
        super("Score: 0", 30);
        this.value = value;
    }

    @Override
    public String getText() {
        return "Score: " + value;
    }

    int getValue() {
        return this.value;
    }

    void increment()
    {
        value++;
    }
}
