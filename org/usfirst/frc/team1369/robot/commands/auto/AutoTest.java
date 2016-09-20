package org.usfirst.frc.team1369.robot.commands.auto;

/**
 * 
 * @author Ezekiel
 * @notes
 * 		- This is going to be a test for the gyro/encoder setup
 *
 */

import org.usfirst.frc.team1369.robot.Robot;
import org.usfirst.frc.team1369.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.command.Command;

public class AutoTest extends Command
{

	protected DriveTrain drive;
	
	public AutoTest()
	{
		drive = Robot.driveTrain;
		this.requires(drive);

	}
	
	protected void drive(double dist, int timeout)
	{
		
	}
	
	protected void drive(double dist)
	{
		
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute()
	{

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	
	
}
