package io.samyemmy.github;

import java.util.LinkedList;
import java.util.Queue;

import io.samyemmy.github.action.Action;

public class ActionQueue
{
    private Queue<Action> queue;
    private Thread thread;
    private Runnable run;
    private Tamagotchi tamagotchi;
    private boolean isActive = false;
    private boolean hasJob = false;

    public ActionQueue(Tamagotchi tamagotchi)
    {
        this.queue = new LinkedList<Action>();
        run = new Runnable() {
            @Override
            public void run() {
                isActive = true;
                poll();
            }
        };
        this.tamagotchi = tamagotchi;
        thread = new Thread(run);
        start();
    }

    private void poll()
    {
        while (isActive)
        {
            Action next = queue.poll();
            if (next == null || !next.shouldExecute(tamagotchi))
                continue;

            hasJob = true;
            next.execute(tamagotchi);
            try
            {
                if (next.getDuration() > 0)
                {
                    Thread.sleep(next.getDuration());
                }
                next.dispose(tamagotchi);
                hasJob = false;
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public boolean isEmpty()
    {
        return !hasJob;
    }

    public void add(Action action)
    {
        queue.add(action);
    }

    public void stop()
    {
        isActive = false;
    }

    public void start()
    {
        thread.start();
    }
}
