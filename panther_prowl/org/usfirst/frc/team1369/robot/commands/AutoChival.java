package org.usfirst.frc.team1369.robot.commands;

import org.usfirst.frc.team1369.robot.subsystems.Lift;
import org.usfirst.frc.team1369.robot.Robot;
import org.usfirst.frc.team1369.robot.subsystems.DriveTrain;

public class AutoChival extends Auto {

	private DriveTrain driveTrain;
	private Lift lift;
	
	public AutoChival()
	{
		this.lift = Robot.lift;
		this.driveTrain = Robot.driveTrain;
		
		this.requires(lift);
		this.requires(driveTrain);
	}
	
	@Override
	protected void execute() 
	{
		
	}

	@Override
	protected void end() 
	{
		//this.lift = 
	}

	@Override
	protected void interrupted() 
	{
		
	}

}
