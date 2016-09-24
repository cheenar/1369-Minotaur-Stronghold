package org.usfirst.frc.team1369.robot.subsystems;

import org.usfirst.frc.team1369.robot.JoystickHelper;
import org.usfirst.frc.team1369.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem
{

	private VictorSP left_front;
	private VictorSP left_back;
	private VictorSP right_front;
	private VictorSP right_back;
	
	private JoystickHelper joy_left;
	private JoystickHelper joy_right;
	
	private double speed_const = 1.0;
	private final double deadzone = 0.3;
	
	public DriveTrain()
	{
		this.joy_left = RobotMap.JOY_LEFT;
		this.joy_right = RobotMap.JOY_RIGHT;
		
		this.left_back = RobotMap.MOTOR_BACK_LEFT;
		this.left_front = RobotMap.MOTOR_FRONT_LEFT;
		this.right_back = RobotMap.MOTOR_BACK_RIGHT;
		this.right_front = RobotMap.MOTOR_FRONT_RIGHT;
		
		this.right_back.setInverted(true);
		this.right_front.setInverted(true);
		
		this.speed_const = 0.7;
	}
	
	public void drive()
	{
		//setting the speed multiplier constant
		if(joy_left.isPressed("DSLow"))
		{
			speed_const = 0.4;
		}
		if(joy_left.isPressed("DSMed"))
		{
			speed_const = 0.5;
		}
		if(joy_left.isPressed("DSHigh"))
		{
			speed_const = 0.6;
		}
		if(joy_right.isPressed("DSHigher"))
		{
			speed_const = 0.7;
		}
		if(joy_right.isPressed("DSHigherer"))
		{
			speed_const = 0.8;
		}
		if(joy_right.isPressed("DSHighest"))
		{
			speed_const = 0.9;
		}
		
		SmartDashboard.putNumber("Drive Speed", speed_const);

		if(!joy_left.isWithinDeadzone(deadzone))
			setLeft(joy_left.getJoystick().getY() * speed_const);
		else
			setLeft(0);
		
		if(!joy_right.isWithinDeadzone(deadzone))
			setRight(joy_right.getJoystick().getY() * speed_const);
		else
			setRight(0);
	}
	
	public void setLeft(double a)
	{
		this.left_back.set(a);;
		this.left_front.set(a);
	}
	
	public void setRight(double a)
	{
		this.right_back.set(a);
		this.right_front.set(a);
	}
	
	public void set(double a, double b)
	{
		setLeft(a);
		setRight(b);
	}
	
	public void stop()
	{
		set(0, 0);
	}
	
	@Override
	protected void initDefaultCommand() 
	{
		
	}
	
	public boolean turningByRate(double angle, double speed)
	{
        boolean isCompleted = false;
        long lastSysTime = System.nanoTime();
        long currSysTime = -1;
        double angleMeasured = 0;

        //boolean angleWasNegative = false; //keeps track if the angle was negative

        if (angle < 0) 
        {
            set(speed, -speed);
            angle *= -1;
            //angleWasNegative = true;
        } 
        else 
        {
        	set(-speed, speed);
        }
        while (angleMeasured <= angle)
        {
            currSysTime = System.nanoTime();
            long elapsed = currSysTime - lastSysTime;
            double angleMoved = 0; //multiplys the rate by the elapsed time in secs
            if (RobotMap.GYRO != null) {
                double rate = Math.abs(RobotMap.GYRO.getRate());
                angleMoved = (rate * ((elapsed / 1000000000.0)));
            }
            angleMeasured += angleMoved;
            lastSysTime = currSysTime;
        }
        stop();
        isCompleted = true;
        return isCompleted;
    }

}
