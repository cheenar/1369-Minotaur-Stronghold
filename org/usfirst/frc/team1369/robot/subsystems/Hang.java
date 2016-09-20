package org.usfirst.frc.team1369.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1369.robot.JoystickHelper;
import org.usfirst.frc.team1369.robot.Robot;
import org.usfirst.frc.team1369.robot.RobotMap;

/**
 * Created by Squidmin on 15-Feb-16.
 */
public class Hang extends Subsystem {

    private VictorSP motor;
    private JoystickHelper joy;
    private double SPEED_CONST;

    public Hang() {
        motor = RobotMap.MOTOR_HANG;
        motor.setInverted(true);
        joy = RobotMap.JOY_SHOOTER;
        SPEED_CONST = 0.35;
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void up() {
        motor.set(1.0);
    }

    public void up(double s) {
        motor.set(s);
    }

    public void down() {
        motor.set(-0.35);
    }

    public void stop() {
        motor.set(0);
    }

    public void hang()
    {
        if (joy.getTophatDirection() == JoystickHelper.TophatPosition.NORTH.getValue())
        {
            up();
        }
        else if (joy.getTophatDirection() == JoystickHelper.TophatPosition.SOUTH.getValue())
        {
            down();
        }
        else
        {
            stop();
        }
    }

    private boolean shouldToggleBrake = true;

}
