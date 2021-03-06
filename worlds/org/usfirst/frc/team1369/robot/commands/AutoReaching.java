package org.usfirst.frc.team1369.robot.commands;

import org.usfirst.frc.team1369.robot.Robot;

public class AutoReaching extends Auto
{
	
	@Override
	protected void execute() 
	{
		try
		{
			Robot.lift.down(-0.35);
			pause(375);
			Robot.lift.stop();
			setMotors(0.3, 0.3);
			pause(1500);
			setMotors(0.55, 0.55);
			pause(550);
			setMotors(0, 0);
			isCompleted = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	protected void end()
	{
		this.isCompleted = true;
		Robot.driveTrain.stop();
		Robot.lift.stop();
	}

	@Override
	protected void interrupted() 
	{
		this.isCompleted = true;
		Robot.driveTrain.stop();
		Robot.lift.stop();
	}

}
