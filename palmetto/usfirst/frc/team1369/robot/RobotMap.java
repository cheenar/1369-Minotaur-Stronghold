package org.usfirst.frc.team1369.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.HashMap;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    public static boolean DEBUG_MODE = true;

    public static void log(String logName, Object logInput, Class type) {
        if (DEBUG_MODE) {
            System.out.println("[Team1369]" + logName + ": " + logInput);
            if (type.getName().equals(String.class.getName())) {
                SmartDashboard.putString(logName, (String) logInput);
            }
            if (type.getName().equals(Integer.class.getName()) || type.getName().equals(Double.class.getName())) {
                SmartDashboard.putNumber(logName, (Double)logInput);
            }
        }
    }

    /* Timer **/
    public static boolean timerDone = false;

    public static void setTimerDone(boolean kek) {
        timerDone = kek;
    }

    /* Motors */

    //Ports
    public static final int PORT_LEFT_MOTOR_FRONT = 0;
    public static final int PORT_LEFT_MOTOR_BACK = 2;
    public static final int PORT_RIGHT_MOTOR_FRONT = 1;
    public static final int PORT_RIGHT_MOTOR_BACK = 3;
    public static final int PORT_LEFT_MOTOR_SHOOTER = 4;
    public static final int PORT_RIGHT_MOTOR_SHOOTER = 5;
    public static final int PORT_MOTOR_LIFT = 6;
    public static final int PORT_MOTOR_HANG = 7;


    //Motor Controllers
    public static VictorSP MOTOR_LEFT_MOTOR_FRONT = new VictorSP(PORT_LEFT_MOTOR_FRONT);
    public static VictorSP MOTOR_LEFT_MOTOR_BACK = new VictorSP(PORT_LEFT_MOTOR_BACK);
    public static VictorSP MOTOR_RIGHT_MOTOR_FRONT = new VictorSP(PORT_RIGHT_MOTOR_FRONT);
    public static VictorSP MOTOR_RIGHT_MOTOR_BACK = new VictorSP(PORT_RIGHT_MOTOR_BACK);
    public static VictorSP MOTOR_LEFT_MOTOR_SHOOTER = new VictorSP(PORT_LEFT_MOTOR_SHOOTER);
    public static VictorSP MOTOR_RIGHT_MOTOR_SHOOTER = new VictorSP(PORT_RIGHT_MOTOR_SHOOTER);
    public static VictorSP MOTOR_LIFT = new VictorSP(PORT_MOTOR_LIFT);
    public static VictorSP MOTOR_HANG = new VictorSP(PORT_MOTOR_HANG);
    public static Encoder MOTOR_ENCODER_LEFT = new Encoder(0, 1, false, Encoder.EncodingType.k4X);

    //Motor Speeds
    public static double SHOOTER_SPEED = 0.2;
    public static double DRIVE_MOTOR_SPEED = 0.5;
    public static final double INTAKE_MOTOR_SLOW_SPEED = 0.5;

    public static DigitalInput LIMIT_LIFT = new DigitalInput(2);

    /* JOYSTICKS */


    //Ports
    public static final int PORT_JOYSTICK_LEFT = 0;
    public static final int PORT_JOYSTICK_RIGHT = 1;
    public static final int PORT_JOYSTICK_SHOOTER = 2;

    //Joysticks
    private static Joystick JOYSTICK_LEFT = new Joystick(PORT_JOYSTICK_LEFT);
    private static Joystick JOYSTICK_RIGHT = new Joystick(PORT_JOYSTICK_RIGHT);
    private static Joystick JOYSTICK_SHOOTER = new Joystick(PORT_JOYSTICK_SHOOTER);

    public static JoystickHelper JOY_LEFT = new JoystickHelper(JOYSTICK_LEFT, false, hashmap(new String[]{
            "CameraCenter",
            "D_Low", "D_Medium", "D_High"
    }, new Integer[]{
            2,
            4,5,6
    }));
    public static JoystickHelper JOY_RIGHT = new JoystickHelper(JOYSTICK_RIGHT, false, hashmap(new String[]{
            "CameraRight",
            "D_EHigher", "D_EEHigher", "D_Highest"
    }, new Integer[]{
            2,
            4,5,6
    }));
    public static JoystickHelper JOY_SHOOTER = new JoystickHelper(JOYSTICK_SHOOTER, true, hashmap(new String[]{
            "LowGoalOuttakeSpeed", "HighGoalOuttakeSpeed", "Outtake",
            "DEBUG_RESET_LIFT_POTENTIOMETER"
    }, new Integer[]{
            5, 3, 2,
            11
    }));

    /* PNEUMATICS */
    public static DoubleSolenoid SHOOTER_ARM = new DoubleSolenoid(0, 1); //Port 0 & 1
    public static DoubleSolenoid BIKE_BRAKE = new DoubleSolenoid(2, 3);

    /* POTENTIOMETER */
    //public static final int POTENTIOMETER_OFFEST = 0;
    //public static Potentiometer POTENTIOMETER = new AnalogPotentiometer(new AnalogInput(0), 360, POTENTIOMETER_OFFEST);  //AnalogInput, Factor to scale 0-1, Offset
    public static AnalogInput POTENTIOMETER = new AnalogInput(0);

    public static HashMap<String, Integer> hashmap(String[] keys, Integer[] ports) {
        HashMap<String, Integer> hash = new HashMap<String, Integer>();
        if (keys.length == ports.length) {
            for (int i = 0; i < keys.length; i++) {
                hash.put(keys[i], ports[i]);

            }
            return hash;
        } else {
            return hash;
        }
    }

}
