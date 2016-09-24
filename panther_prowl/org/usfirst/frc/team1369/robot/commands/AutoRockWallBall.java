package org.usfirst.frc.team1369.robot.commands;

import org.usfirst.frc.team1369.robot.Robot;

/**
 * Created by Squidmin on 12-Mar-16.
 */
public class AutoRockWallBall extends Auto {
    @Override
    protected void execute() {
        try {
            Robot.lift.setLiftAngle(.6);
            setMotors(0.3, 0.3);
            pause(1500);
            setMotors(0.55, 0.55);
            pause(550);
            setMotors(1, 1);
            pause(1250);
            setMotors(0, 0);

            Robot.shooter.outtake();
            pause(1000);
            Robot.shooterArm.activate();
            pause(3000);
            Robot.shooter.stop();

            isCompleted = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void end() {
        this.isCompleted = true;
        Robot.driveTrain.stop();
        Robot.lift.stop();
    }

    @Override
    protected void interrupted() {
        this.isCompleted = true;
        Robot.driveTrain.stop();
        Robot.lift.stop();
    }
}
