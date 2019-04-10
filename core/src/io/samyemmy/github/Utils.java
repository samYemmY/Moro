package io.samyemmy.github;

import java.util.Random;

public class Utils
{
    public static int randomNumber()
    {
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(10) + 1;
    }

    public static boolean oneIn(int number)
    {
        Random random = new Random(System.currentTimeMillis());
        int i = random.nextInt(number);
        return i == 0;
    }
}
