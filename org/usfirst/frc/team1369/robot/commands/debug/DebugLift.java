package org.usfirst.frc.team1369.robot.commands.debug;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1369.robot.JoystickHelper;
import org.usfirst.frc.team1369.robot.Robot;
import org.usfirst.frc.team1369.robot.RobotMap;
import org.usfirst.frc.team1369.robot.subsystems.Lift;

/**
 * Created by Squidmin on 22-Feb-16.
 */
public class DebugLift extends Command
{

    private Lift lift;
    private JoystickHelper joyShooter;
    private double TOP_HEIGHT_VOLTAGE;

    private final double VOLT_SCALING = 100000;

    public DebugLift()
    {
        this.lift = Robot.lift;
        this.joyShooter = RobotMap.JOY_SHOOTER;
        this.requires(lift);
    }

    @Override
    protected void initialize() {
        this.lift.forceStop();
        if(RobotMap.LIMIT_LIFT.get())
        {
            TOP_HEIGHT_VOLTAGE = (RobotMap.POTENTIOMETER.getAverageVoltage() * VOLT_SCALING);
        }
    }

    @Override
    protected void execute()
    {
        while (true)
        {
            if(RobotMap.LIMIT_LIFT.get())
            {
                TOP_HEIGHT_VOLTAGE = (RobotMap.POTENTIOMETER.getAverageVoltage() * VOLT_SCALING);
            }

            RobotMap.log("Potetiometer Reading", ((RobotMap.POTENTIOMETER.getAverageVoltage() * VOLT_SCALING)), Double.class);

            if(joyShooter.isPressed(9))
            {
                RobotMap.log("Potentiometer Record", (RobotMap.POTENTIOMETER.getAverageVoltage() * VOLT_SCALING), Double.class);
                RobotMap.log("Potentiometer Difference", TOP_HEIGHT_VOLTAGE - (RobotMap.POTENTIOMETER.getAverageVoltage() * VOLT_SCALING), Double.class);
                RobotMap.log("Potentiometer Absolute", Robot.magnititude(TOP_HEIGHT_VOLTAGE - (RobotMap.POTENTIOMETER.getAverageVoltage() * VOLT_SCALING)), Double.class);
            }

            if(joyShooter.isWithinDeadzone(.3))
            {
                lift.forceStop();
            }
            else
            {
                if(joyShooter.isJoystickYDown())
                {
                    lift.liftUpwards();
                }
                else
                {
                    lift.liftDownwards();
                }
            }
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        this.lift.forceStop();
    }

    @Override
    protected void interrupted() {
        this.lift.forceStop();
    }
}
