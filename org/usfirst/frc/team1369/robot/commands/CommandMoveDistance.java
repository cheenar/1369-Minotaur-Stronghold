package org.usfirst.frc.team1369.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1369.robot.Robot;
import org.usfirst.frc.team1369.robot.RobotMap;

/**
 * Created by cheen on 2/20/2016.
 */
public class CommandMoveDistance extends Command
{

    public static boolean isExecuting = false;
    private boolean isDone;

    @Override
    protected void initialize()
    {
        isDone = false;
    }

    @Override
    protected void execute()
    {
        boolean b =  Robot.driveTrain.driveDistance(false, 0.5, 1);
        while(!b) { }
        isExecuting = false;
        isDone = true;
    }

    @Override
    protected boolean isFinished() {
        return isDone;
    }

    @Override
    protected void end()
    {
        isExecuting = false;
        isDone = true;
    }

    @Override
    protected void interrupted() {
        isExecuting = false;
        isDone = true;
    }
}
