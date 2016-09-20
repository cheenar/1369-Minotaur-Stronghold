package org.usfirst.frc.team1369.robot;

import java.util.HashMap;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

public class RobotMap 
{
	
	/** Sensors **/
	public static ADXRS450_Gyro GYRO = new ADXRS450_Gyro();
	public static boolean isShooterReady = true;
	
	/** Motors **/
	//Ports
    public static final int PORT_LEFT_MOTOR_FRONT = 0;
    public static final int PORT_LEFT_MOTOR_BACK = 2;
    public static final int PORT_RIGHT_MOTOR_FRONT = 1;
    public static final int PORT_RIGHT_MOTOR_BACK = 3;
    public static final int PORT_LEFT_MOTOR_SHOOTER = 4;
    public static final int PORT_RIGHT_MOTOR_SHOOTER = 5;
    public static final int PORT_MOTOR_LIFT = 6;


    //Motor Controllers
    public static VictorSP MOTOR_FRONT_LEFT = new VictorSP(PORT_LEFT_MOTOR_FRONT);
    public static VictorSP MOTOR_BACK_LEFT = new VictorSP(PORT_LEFT_MOTOR_BACK);
    public static VictorSP MOTOR_FRONT_RIGHT = new VictorSP(PORT_RIGHT_MOTOR_FRONT);
    public static VictorSP MOTOR_BACK_RIGHT = new VictorSP(PORT_RIGHT_MOTOR_BACK);
    public static VictorSP MOTOR_LEFT_SHOOTER = new VictorSP(PORT_LEFT_MOTOR_SHOOTER);
    public static VictorSP MOTOR_RIGHT_SHOOTER = new VictorSP(PORT_RIGHT_MOTOR_SHOOTER);
    public static VictorSP MOTOR_LIFT = new VictorSP(PORT_MOTOR_LIFT);
    
    /** Joysticks **/
    //Ports
    public static final int PORT_JOYSTICK_LEFT = 0;
    public static final int PORT_JOYSTICK_RIGHT = 1;
    public static final int PORT_JOYSTICK_SHOOTER = 2;

    //Joysticks
    private static Joystick JOYSTICK_LEFT = new Joystick(PORT_JOYSTICK_LEFT);
    private static Joystick JOYSTICK_RIGHT = new Joystick(PORT_JOYSTICK_RIGHT);
    private static Joystick JOYSTICK_SHOOTER = new Joystick(PORT_JOYSTICK_SHOOTER);
    
    @SuppressWarnings("serial")
	public static JoystickHelper JOY_LEFT = new JoystickHelper(JOYSTICK_LEFT, new HashMap<String, Integer>(){{
    	put("DSLow", 4);
    	put("DSMed", 3);
    	put("DSHigh", 5);
    	put("CameraCenter", 2);
    }});
    
    @SuppressWarnings("serial")
    public static JoystickHelper JOY_RIGHT = new JoystickHelper(JOYSTICK_RIGHT, new HashMap<String, Integer>(){{
    	put("DSHigher", 4);
    	put("DSHigherer", 3);
    	put("DSHighest", 5);
    	put("CameraRight", 2);
    }});
    
    @SuppressWarnings("serial")
    public static JoystickHelper JOY_SHOOTER = new JoystickHelper(JOYSTICK_SHOOTER, new HashMap<String, Integer>(){{
    	put("LowGoalOuttakeSpeed", 1);
    	put("HighGoalOuttakeSpeed", 4);
    	put("Outtake", 2);
    	put("Shoot", 8);
    	put("HangLeft", 9);
    }});
    
    /**Solenoids**/
    public static DoubleSolenoid SOLENOID_SHOOTER_ARM = new DoubleSolenoid(0,1);
    public static DoubleSolenoid SOLENOID_HANG = new DoubleSolenoid(2,3);
    public static DoubleSolenoid SOLENOID_BLOCKER = new DoubleSolenoid(4,5);
    
    /** Potentiometer **/
    public static Potentiometer POTENTIOMETER = new AnalogPotentiometer(new AnalogInput(0));
    
	
}
