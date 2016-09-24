package org.usfirst.frc.team1369.robot.subsystems;

import org.usfirst.frc.team1369.robot.JoystickHelper;
import org.usfirst.frc.team1369.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Hang 
{
	
	private DoubleSolenoid solenoid;
	private JoystickHelper joystick;
	private boolean isEnabled;
	private boolean shouldActivate;
	
	public Hang()
	{
		this.solenoid = RobotMap.SOLENOID_HANG;
		this.joystick = RobotMap.JOY_SHOOTER;
		this.isEnabled = false;
		this.shouldActivate = true;
		this.close();
	}
	
	public boolean isEnabled()
	{
		return isEnabled;
	}
	
	public void close()
	{
		this.solenoid.set(Value.kForward);
	}
	
	public void open()
	{
		this.solenoid.set(Value.kReverse);
	}
	
	public void hang()
	{
		if(this.joystick.isPressed("HangLeft")) //if start and select are pressed at the same time
		{
			if(shouldActivate)
			{
				shouldActivate = false;
				new Thread(new Runnable()
				{
					@Override
					public void run()
					{
						isEnabled = !isEnabled;
						if(isEnabled)
						{
							open();
						}
						else
						{
							close();
							if(!Lift.isSettingLiftAngle)
							{
								//Robot.lift.setLiftAngle(0.557);
							}
						}
						
						//wait for the user to stop pressing the button and resets the should activate
						try
						{
							Thread.sleep(1000);
						}
						catch(Exception e) 
						{
							e.printStackTrace();
						}
						finally
						{
							shouldActivate = true;
						}
					}
				}).start();
			}
		}
	}

}
