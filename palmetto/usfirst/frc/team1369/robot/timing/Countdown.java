package org.usfirst.frc.team1369.robot.timing;

import org.usfirst.frc.team1369.robot.RobotMap;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Squidmin on 19-Jan-16.
 */
public class Countdown {
    static int interval;
    static Timer timer;

    public static void main(int secsonds) {
        int secs = secsonds;
        int ms = 1;
        int something = 1000;
        timer = new Timer();
        interval = secs;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                setInterval();
            }
        }, ms, something);
    }

    private static int setInterval() {
        if (interval <= 0) {
            RobotMap.setTimerDone(true);
            timer.cancel();
        }
        return --interval;
    }
}
