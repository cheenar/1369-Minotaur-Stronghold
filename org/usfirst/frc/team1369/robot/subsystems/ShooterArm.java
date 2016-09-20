package org.usfirst.frc.team1369.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1369.robot.Robot;
import org.usfirst.frc.team1369.robot.RobotMap;

/**
 * Created by Squidmin on 27-Jan-16.
 */
public class ShooterArm extends Subsystem {

	private Activate thread;
	private Value DEFAULT_POSITION = Value.kReverse;
	private Value EXTENDED_POSITION = Value.kForward;
	private final int DELAY = 1000;
	
    public ShooterArm()
    {
        RobotMap.SHOOTER_ARM.set(DEFAULT_POSITION);
    }

    @Override
    protected void initDefaultCommand() {

    }

    class Activate implements Runnable {
        @Override
        public void run() {
            try {
                RobotMap.SHOOTER_ARM.set(EXTENDED_POSITION);
                Thread.sleep(DELAY);
                RobotMap.SHOOTER_ARM.set(DEFAULT_POSITION);

                Robot.isShooterReady = true; //Reset the shooter check
            } 
            catch(Exception e) 
            {
            	e.printStackTrace();
            }  
            finally
            {
            }
        }
    }

    public void activate() {
    	thread = new Activate();
    	new Thread(thread).start();
    }

}


