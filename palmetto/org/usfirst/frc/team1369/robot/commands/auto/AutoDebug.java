package org.usfirst.frc.team1369.robot.commands.auto;

/** @author Ezekiel
 * @notes
 * 		- Debugging output for autonomous
 */

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1369.robot.Robot;
import org.usfirst.frc.team1369.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class AutoDebug extends Command
{

	@Override
	protected void initialize()
	{
		
	}

	@Override
	protected void execute() 
	{
		/*System.out.println("---Gyro---");
		System.out.println("Gyro Angle: :" + Robot.gyro.getAngle());
		System.out.println("Gyro Rate: " + Robot.gyro.getRate());
		System.out.println();
		System.out.println("---Potentiometer---");

		*/System.out.println("Pot Angle: " + ((RobotMap.POTENTIOMETER.getAverageVoltage() * 10000)));



		SmartDashboard.putNumber("Pot Volt: ", (RobotMap.POTENTIOMETER.getVoltage()));
		SmartDashboard.putNumber("Pot Accum Count", (RobotMap.POTENTIOMETER.getAccumulatorCount()));
		SmartDashboard.putNumber("Pot Accum Val", RobotMap.POTENTIOMETER.getAccumulatorValue());
		SmartDashboard.putNumber("Pot Vollt Aver", RobotMap.POTENTIOMETER.getAverageVoltage());
		SmartDashboard.putNumber("Pot Vollt Aver multpliy", (RobotMap.POTENTIOMETER.getAverageVoltage() * 10000));

		SmartDashboard.putNumber("Gyro", Robot.gyro.getRate());

		System.out.println();
	}

	@Override
	protected boolean isFinished() 
	{
		return false;
	}

	@Override
	protected void end() 
	{
		
	}

	@Override
	protected void interrupted() 
	{
		
	}

}
