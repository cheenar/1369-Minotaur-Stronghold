package org.usfirst.frc.team1369.robot.commands;

import org.usfirst.frc.team1369.robot.Robot;

public class AutoRoughTerrainBack extends Auto {

    @Override
    protected void initialize() {
        try {
            Robot.lift.setLiftAngle(.6);

            setMotors(0.3, 0.3);
            pause(1500);
            setMotors(0.55, 0.55);
            pause(1150);
            setMotors(1, 1);
            pause(1250);
            setMotors(-0.55, -0.55);
            pause(1150);
            setMotors(0,0);

            isCompleted = true;
            isCompleted = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void execute() {

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
