package org.usfirst.frc.team1369.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1369.robot.Robot;
import org.usfirst.frc.team1369.robot.RobotMap;
import org.usfirst.frc.team1369.robot.subsystems.Hang;
import org.usfirst.frc.team1369.robot.subsystems.Lift;

/**
 * Created by Squidmin on 19-Feb-16.
 */
public class CommandLift extends Command
{

    private Lift lift;
    private Hang hang;
    private boolean isComplete;

    public CommandLift()
    {
        this.isComplete = false;

        this.lift = Robot.lift;
        this.hang = Robot.hang;

        this.requires(lift);
        this.requires(hang);
    }

    @Override
    protected void initialize()
    {
        //RobotMap.LIFT_SPEED = 0.60;
        lift.INITIAL_SHOOTER_ANGLE = (RobotMap.POTENTIOMETER.getAverageVoltage() * 10000);
    }

    @Override
    protected void execute()
    {
        new Thread(new Runnable(){
            @Override
            public void run()
            {
                try
                {
                    hang.up(0.6);

                    //arm
                    Thread.sleep(1000);

                    lift.liftUpwards();
                    Thread.sleep(1950);
                    lift.forceStop();

                    Thread.sleep(4575);
                    hang.stop();

                    Robot.isLifting = false;
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected boolean isFinished()
    {
        return isComplete;
    }

    @Override
    protected void end()
    {
        lift.forceStop();
        hang.stop();
    }

    @Override
    protected void interrupted()
    {
        lift.forceStop();
        hang.stop();
    }
}
