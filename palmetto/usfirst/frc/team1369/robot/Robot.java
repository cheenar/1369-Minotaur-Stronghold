package org.usfirst.frc.team1369.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1369.robot.camera.CameraSwitcher;
import org.usfirst.frc.team1369.robot.commands.CommandLift;
import org.usfirst.frc.team1369.robot.commands.CommandMoveDistance;
import org.usfirst.frc.team1369.robot.commands.auto.*;
import org.usfirst.frc.team1369.robot.commands.debug.DebugLift;
import org.usfirst.frc.team1369.robot.subsystems.*;

//test

/*
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	/* Variables */

    //Shooter
    public static boolean isShooterReady = true;

    //Subsystems
    public static DriveTrain driveTrain;
    public static Shooter shooter;
    public static ShooterArm shooterArm;
    public static Lift lift;
    public static Hang hang;

    //Commands
    private CommandMoveDistance commandMoveDistance;

    //OI (Unused)
    public static OI oi;

    //Autonomous
    private Command autonomousCommand;
    private SendableChooser chooser;

    public static ADXRS450_Gyro gyro;

    public static boolean isLifting;

    //Camera
    /**
     * CAMERA CODE
     **/
    private CameraSwitcher cc;

    /*
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        oi = new OI();

        isLifting = false;

        //Gyro Initialization
        gyro = new ADXRS450_Gyro(); //VERY IMPORTANT, THIS HAS TO BE INITIALIZED FIRST, BEFORE ANY OF THE SUBSYSTEMS

        //Subsystem Initialization
        driveTrain = new DriveTrain();
        shooter = new Shooter();
        shooterArm = new ShooterArm();
        lift = new Lift();
        hang = new Hang();

        //Command Initialization
        commandMoveDistance = new CommandMoveDistance();

        //Chooser Initialization
        chooser = new SendableChooser();
        chooser.addDefault("Do Nothing", null);
        chooser.addObject("Debug", new AutoDebug());
        chooser.addObject("DriveStraight", new AutoDriveStraight());
        chooser.addObject("DriveTest", new AutoTest());
        chooser.addObject("Driving", new AutoDriving());
        chooser.addObject("EncoderDrive", new AutoEncoderDrive());
        chooser.addObject("GyroTurn", new AutoGyroTurn());

        if(RobotMap.DEBUG_MODE)
        {
            chooser.addObject("Debug Lift Test", new DebugLift());

        }

        SmartDashboard.putData("Auto Chooser", chooser);

        SmartDashboard.putString("", "Someone save me from the nothing I've become");
        SmartDashboard.putNumber("turnAngle", 180.0);
        SmartDashboard.putNumber("turnSpeed", .9);
        SmartDashboard.putNumber("encoder drive speed", 0.5);
        SmartDashboard.putNumber("encoder distance", 1);
        SmartDashboard.putNumber("Drive Stick Deadzone", 0.4);
    }

    //used for getting the absolute value of a double (in)
    public static double magnititude(double in) {
        double out = in;
        if (out < 0) {
            out *= -1;
        }
        return out;
    }

    /*
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    public void disabledInit() {
        /** CAMERA CODE **/
        if (cc != null) {
            cc.end();
        }
        if (commandMoveDistance != null) {
            if (commandMoveDistance.isRunning()) {
                commandMoveDistance.cancel();
                commandMoveDistance.isExecuting = false;
            }
        }
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();

    }

    /*
     * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
     * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
     * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
     * below the Gyro
     *
     * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
     * or additional comparisons to the switch structure below with additional strings & commands.
     */
    public void autonomousInit() {
        try {
            if (chooser.getSelected() != null) {
                autonomousCommand = (Command) chooser.getSelected();
                autonomousCommand.start();
            } else {
                System.out.println("No Autonomous Mode Selected");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
        /** CAMERA CODE **/
        if (cc == null && RobotMap.JOY_LEFT.getJoystick() != null && RobotMap.JOY_RIGHT.getJoystick() != null) {
            cc = new CameraSwitcher();
        }
        if (cc != null) {
            cc.init();
        }
        RobotMap.MOTOR_ENCODER_LEFT.reset();
    }


    /*
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();


        //camera code
        /** CAMERA CODE **/
        try {
            cc.run();
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {

            //if(!isLifting) {
            //Joystick: Left & Right
            //DriveTrain Move
            //RobotMap.DRIVE_MOTOR_SPEED = (0.4 * RobotMap.JOY_RIGHT.getThrottle()) + .6;
            //SmartDashboard.putNumber("Drive Speed", ((0.4 * RobotMap.JOY_RIGHT.getThrottle()) + .6));

            driveTrain.drive();

            /**RobotMap.MOTOR_RIGHT_MOTOR_FRONT.set(RobotMap.JOY_RIGHT.getJoystick().getY());
            RobotMap.MOTOR_RIGHT_MOTOR_BACK.set(RobotMap.JOY_RIGHT.getJoystick().getY());


            RobotMap.MOTOR_LEFT_MOTOR_FRONT.set(RobotMap.JOY_LEFT.getJoystick().getY());
            RobotMap.MOTOR_LEFT_MOTOR_BACK.set(RobotMap.JOY_LEFT.getJoystick().getY());
**/
            //Joystick: Shooter
            lift.lift();
            hang.hang();
            shooter.shoot();

            if (RobotMap.JOY_SHOOTER.getJoystick().getTrigger() && isShooterReady) {
                shooterArm.activate();
                isShooterReady = false;
            }

                /*if (RobotMap.JOY_SHOOTER.isPressed("CommandLift"))
                    {
                    new CommandLift().start();
                    isLifting = true;
                }*/

            /*if (RobotMap.JOY_SHOOTER.getThrottle() < .6) {
                RobotMap.SHOOTER_SPEED = ((((-RobotMap.JOY_SHOOTER.getThrottle()) + 1.0) / 2.0));
            } else {
                RobotMap.SHOOTER_SPEED = .2;
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    /*
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        //   LiveWindow.run();
        System.out.println(gyro.getAngle());

    }

}
