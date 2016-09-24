package org.usfirst.frc.team1369.robot.commands;

import org.usfirst.frc.team1369.robot.Robot;
import org.usfirst.frc.team1369.robot.subsystems.DriveTrain;

public class AutoJoke extends Auto {

	private DriveTrain driveTrain;
	
	public AutoJoke()
	{
		this.driveTrain = Robot.driveTrain;
		this.requires(driveTrain);
	}
	
	@Override
	protected void execute() 
	{
		/*AutoRoughTerrain auto = new AutoRoughTerrain();
		auto.initialize();
		auto.start();
		while(!auto.isCompleted)
		{
			
		}
		pause(1000);
		while(!driveTrain.turningByRate(350, 0.5))
		{
			
		}*/
		//Robot.lift.setLiftAngle(0.557);
		this.isCompleted = true;
	}

	@Override
	protected void end() 
	{
		this.driveTrain.stop();
		this.isCompleted = true;
	}

	@Override
	protected void interrupted() 
	{
		this.driveTrain.stop();
		this.isCompleted = true;
	}

}
