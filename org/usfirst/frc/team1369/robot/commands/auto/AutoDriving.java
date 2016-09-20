package org.usfirst.frc.team1369.robot.commands.auto;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1369.robot.Robot;
import org.usfirst.frc.team1369.robot.subsystems.DriveTrain;

/**
 * Created by Squidmin on 15-Feb-16.
 */
public class AutoDriving extends Command {

    private boolean isDone;
    private DriveTrain driveTrain;
    private ADXRS450_Gyro gyro;
    private final double Kp = 0.03;
    private final double Kpi = 1;

    public AutoDriving() {
        driveTrain = Robot.driveTrain;
        this.requires(driveTrain);
        gyro = Robot.gyro;
    }

    @Override
    protected void initialize() {
        isDone = false;
    }

    @Override
    protected void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                double angle = gyro.getAngle(); // get current heading
     //s           driveTrain.driveCustom((-angle * (Kp * 0.003)) + (.15), (-angle * (Kp * 0.003)) + (-.15)); // drive towards heading 0
                Timer.delay(0.004);
                sleep(2000);
                driveTrain.stop();
                sleep(250);
                boolean hasTurned = driveTrain.turningByRate(-60
                        , 0.5);
                while (hasTurned == false) {
                    if (hasTurned) break;
                }
                driveTrain.driveDistance(true, 0.5, 226.898);
                driveTrain.driveDistance(true, 0.5, 97.802);
                driveTrain.stop();
            }
        }).start();
    }

    public void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected boolean isFinished() {
        return isDone;
    }

    @Override
    protected void end() {
        Robot.driveTrain.stop();
    }

    @Override
    protected void interrupted() {
        Robot.driveTrain.stop();
    }
}
