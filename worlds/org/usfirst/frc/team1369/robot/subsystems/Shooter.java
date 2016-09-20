package org.usfirst.frc.team1369.robot.subsystems;

import org.usfirst.frc.team1369.robot.JoystickHelper;
import org.usfirst.frc.team1369.robot.Robot;
import org.usfirst.frc.team1369.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem
{

	private JoystickHelper joystick;
    private VictorSP leftMotor;
    private VictorSP rightMotor;

    private double outtakeSpeed;
    private final double lowGoalSpeed = 0.7;
    private final double highGoalSpeed = 1.0;

    public Shooter() {
        this.joystick = RobotMap.JOY_SHOOTER;
        this.leftMotor = RobotMap.MOTOR_LEFT_SHOOTER;
        this.rightMotor = RobotMap.MOTOR_RIGHT_SHOOTER;

        outtakeSpeed = lowGoalSpeed;
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void setSpeed(int isHigh)
    {
        if(isHigh == 3)
        {
            outtakeSpeed = highGoalSpeed;
        }
        if(isHigh == 5)
        {
            outtakeSpeed = lowGoalSpeed;
        }
    }

    public void outtake()
    {
        shootCustom(outtakeSpeed); //postive
    }

    public void intake()
    {
        shootCustom(-.8); //negative
    }

    public void stop()
    {
        shootCustom(0);
    }

    public void shoot()
    {
    	if(joystick.isPressed("Shoot") && RobotMap.isShooterReady)
    	{
    		RobotMap.isShooterReady = false;
    		Robot.shooterArm.activate();
    	}
    	
        if(joystick.isPressed("LowGoalOuttakeSpeed"))
        {
            setSpeed(5);
        }
        if(joystick.isPressed("HighGoalOuttakeSpeed"))
        {
            setSpeed(3);
        }

        if(joystick.isPressed("Outtake"))
        {
            outtake();
        }
        else if(RobotMap.JOY_LEFT.getJoystick().getTrigger() || RobotMap.JOY_RIGHT.getJoystick().getTrigger())
        {
            intake();
        }
        else
        {
            stop();
        }
    }
    public void shootCustom(double kek)
    {
        leftMotor.set(kek);
        rightMotor.set(-kek);
    }

}
