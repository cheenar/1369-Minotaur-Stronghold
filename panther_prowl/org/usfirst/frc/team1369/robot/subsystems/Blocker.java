package org.usfirst.frc.team1369.robot.subsystems;

import org.usfirst.frc.team1369.robot.JoystickHelper;
import org.usfirst.frc.team1369.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Blocker extends Subsystem
{

	private DoubleSolenoid sol;
	private JoystickHelper joy;
	private boolean isEnabled;
	private boolean shouldActivate;
	
	public Blocker()
	{
		this.sol = RobotMap.SOLENOID_BLOCKER;
		this.joy = RobotMap.JOY_LEFT;
		this.shouldActivate = false;
		this.isEnabled = false;
		close();
	}
	
	public void close()
	{
		this.sol.set(Value.kForward);
	}
	
	public void open()
	{
		this.sol.set(Value.kReverse);
	}
	
	public void block()
	{
		if(this.joy.isPressed("")) //if start and select are pressed at the same time
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
	
	@Override
	protected void initDefaultCommand() {
		
	}

}
