package org.usfirst.frc.team1369.robot.commands;

import org.usfirst.frc.team1369.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public abstract class Auto extends Command
{
	
	protected boolean isCompleted;
	
	protected void pause(int ms)
	{
		try
		{
			Thread.sleep(ms);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void setMotors(double speedA, double speedB)
    {
    	speedB+=0.07;
    	
    	RobotMap.MOTOR_FRONT_LEFT.set(-speedA);
    	RobotMap.MOTOR_BACK_LEFT.set(-speedA);
    	
    	RobotMap.MOTOR_FRONT_RIGHT.set(-speedB);
    	RobotMap.MOTOR_BACK_RIGHT.set(-speedB);
    }
	
	@Override
	protected void initialize()
	{
		isCompleted = false;
	}
	@Override
	protected boolean isFinished() 
	{
		return isCompleted;
	}

}
