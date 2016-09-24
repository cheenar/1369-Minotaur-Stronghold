package org.usfirst.frc.team1369.robot.subsystems;

import org.usfirst.frc.team1369.robot.Robot;
import org.usfirst.frc.team1369.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterArm extends Subsystem {

	private Activate thread;
	private Value DEFAULT_POSITION = Value.kReverse;
	private Value EXTENDED_POSITION = Value.kForward;
	private final int DELAY = 1000;
	
    public ShooterArm()
    {
    	this.resetArm();
    }
    
    public void resetArm()
    {
    	RobotMap.SOLENOID_SHOOTER_ARM.set(DEFAULT_POSITION);
    }

    public void shoot()
    {
    	RobotMap.SOLENOID_SHOOTER_ARM.set(this.EXTENDED_POSITION);
    }
    
    @Override
    protected void initDefaultCommand() {

    }

    class Activate implements Runnable {
        @Override
        public void run() {
            try 
            {
        //    	Robot.shooter.setSpeed(3);  Removed at request of the driveteam 
      //      	Robot.shooter.outtake();
    //        	Thread.sleep(1250);
            	RobotMap.SOLENOID_SHOOTER_ARM.set(EXTENDED_POSITION);
                Thread.sleep(DELAY);
                RobotMap.SOLENOID_SHOOTER_ARM.set(DEFAULT_POSITION);
                Thread.sleep(1000);
                Robot.shooter.stop();
            } 
            catch(Exception e) 
            {
            	e.printStackTrace();
            }  
            finally
            {
            	RobotMap.isShooterReady = true;
            }
        }
    }

    public void activate() {
    	thread = new Activate();
    	new Thread(thread).start();
    }

}