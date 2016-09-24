
package org.usfirst.frc.team1369.robot;

import org.usfirst.frc.team1369.robot.camera.CameraSwitcher;
import org.usfirst.frc.team1369.robot.subsystems.*;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1369.robot.commands.*;

public class Robot extends IterativeRobot
{

    private Command autonomousCommand;
    private SendableChooser chooser;
    
    private CameraSwitcher cameraSwitcher;
    
    public static DriveTrain driveTrain;
    public static Hang hang;
    public static Lift lift;
    public static Shooter shooter;
    public static ShooterArm shooterArm;
    public static Blocker blocker;
    
    public void robotInit() 
    {
    	//Initialize subsystems
    	driveTrain = new DriveTrain();
    	hang = new Hang();
    	lift = new Lift();
    	shooter = new Shooter();
    	shooterArm = new ShooterArm();
    	blocker = new Blocker();
    	
        chooser = new SendableChooser();
        chooser.addDefault("Do Nothing", null);
        chooser.addObject("Reaching", new AutoReaching());
        chooser.addObject("Rough Terrain", new AutoRoughTerrain());
        chooser.addObject("les bourgeois", new AutoJoke());
        chooser.addObject("Rough Terrain + Drop Ball + Go Back", new AutoRoughDropBall());
        chooser.addObject("Rough Terrain + Drop Ball", new AutoRoughBall());
        chooser.addObject("Reach backwafds", new AutoReachingBackwards());
        chooser.addObject("RockWall Cross", new AutoRockWall());
        
        
        
        SmartDashboard.putData("Autonomous Selection", chooser);
    }
	
    public void disabledInit()
    {

    }
	
	public void disabledPeriodic() 
	{
		Scheduler.getInstance().run();
	}

    public void autonomousInit() 
    {
        if(chooser.getSelected() != null)
        {
        	autonomousCommand = (Command) chooser.getSelected();
        	autonomousCommand.start();
        }
    }

    public void autonomousPeriodic() 
    {
        Scheduler.getInstance().run();
    }

    public void teleopInit() 
    {
        if (autonomousCommand != null) 
        	autonomousCommand.cancel();
        
        try
        {

        	if(cameraSwitcher == null && RobotMap.JOY_LEFT.getJoystick() != null && RobotMap.JOY_RIGHT != null)
            	cameraSwitcher = new CameraSwitcher();
            
            if(cameraSwitcher != null)
            	cameraSwitcher.init();
        }
        catch(Exception e)
        {
        	System.out.println("---Camera---");
        	System.out.println("[1369] Failed to init camera, PLUG IN THE CAMERA");
        	System.out.println("---Camera---");
        	
        	e.printStackTrace();
        }

        Lift.target = RobotMap.POTENTIOMETER.getVoltage();
    }
    
    public void teleopPeriodic() 
    {
        Scheduler.getInstance().run();
        
        try
        {
        	if(cameraSwitcher != null)
        		cameraSwitcher.run();
        }
        catch(Exception e)
        {
        	System.out.println("[1369] Camera Problem");
        	e.printStackTrace();
        }
        
        try
        {
        	driveTrain.drive();
            lift.lift();
            shooter.shoot();
            hang.hang();
            blocker.block();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }
    
    public void testPeriodic() 
    {
        LiveWindow.run();
    }
}
