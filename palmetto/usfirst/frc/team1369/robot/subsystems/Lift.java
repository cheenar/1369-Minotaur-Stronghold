package org.usfirst.frc.team1369.robot.subsystems;

import org.usfirst.frc.team1369.robot.JoystickHelper;
import org.usfirst.frc.team1369.robot.Robot;
import org.usfirst.frc.team1369.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lift extends Subsystem
{

	private JoystickHelper joy;
	private VictorSP motor;
	//private Potentiometer angle;
	private final double DEADZONE = 0.3;

    private double liftSpeed;

	public double INITIAL_SHOOTER_ANGLE;

	public Lift()
	{
		motor = RobotMap.MOTOR_LIFT;
		//angle = RobotMap.POTENTIOMETER;
		joy = RobotMap.JOY_SHOOTER;

        liftSpeed = 0.7;

		//REVERSING MOTOR
		motor.setInverted(false);
		motor.setSafetyEnabled(true);

		INITIAL_SHOOTER_ANGLE = (RobotMap.POTENTIOMETER.getAverageVoltage() * 10000);
	}

	boolean isActive = true;
	double pidStop = -1;

	//Neutral signal needs to be sent so that way the lift brake will engage
	public void lift()
	{
		//manually setting the lift angle checking voltage
		if(joy.isPressed("DEBUG_RESET_LIFT_POTENTIOMETER"))
		{
			INITIAL_SHOOTER_ANGLE = (RobotMap.POTENTIOMETER.getAverageVoltage() * 10000);
		}

        //set the lift speed
        //liftSpeed = joy.getThrottle() * -1;
        ///liftSpeed = ((7/20) * liftSpeed) + (13/20);
        SmartDashboard.putNumber("Lift Speed", liftSpeed);

		SmartDashboard.putNumber("Pot_3:", RobotMap.POTENTIOMETER.getAverageVoltage()); //use voltage kun

        SmartDashboard.putNumber("Throttle", joy.getThrottle());
        
        
		double joyValue = joy.getJoystick().getY();
		if(joy.isWithinDeadzone(DEADZONE))
		{
            if(joy.isPressed(4)) {
				pidStop = RobotMap.POTENTIOMETER.getVoltage();
			}
			if(joy.isPressed(7))
			{
				double error = RobotMap.POTENTIOMETER.getVoltage() - pidStop;
				SmartDashboard.putNumber("Error val", error);
				if(Math.abs(error) > 0.1) motor.set(error * 1.9);
			}
		}
		else
		{
			if(joyValue < -DEADZONE)
			{
				liftSpeed = 0.25;
				liftUpwards();
			}
			if(joyValue > DEADZONE)
			{
				liftSpeed = 0.5;
				liftDownwards();
			}

			//checking for the potentiometer
			/*double angle = (RobotMap.POTENTIOMETER.getAverageVoltage() * 10000);

			if(Robot.magnititude(INITIAL_SHOOTER_ANGLE - angle) >= 10)
			{
				if(joyValue < -DEADZONE)
				{
					liftUpwards();
				}
			}
			else
			{
				if(joy.isPressed(12)) //simulates limit switch for now
				{
					INITIAL_SHOOTER_ANGLE = (RobotMap.POTENTIOMETER.getAverageVoltage() * 10000);
					if(joyValue > DEADZONE)
					{
						liftDownwards();
					}
				}
				else
				{
					if(joyValue < -DEADZONE)
					{
						liftUpwards();
					}
					if(joyValue > DEADZONE)
					{
						liftDownwards();
					}
				}
			}*/
		}

	}

	public void forceStop()
	{
		motor.set(0);
	}

	//lift goes down
	public void liftDownwards()
	{
		motor.set(liftSpeed);
	}

	//lift goes up
	public void liftUpwards()
	{
		motor.set(-liftSpeed);
	}

	@Override
	protected void initDefaultCommand() 
	{
		
	}

}
