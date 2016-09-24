package org.usfirst.frc.team1369.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import org.usfirst.frc.team1369.robot.JoystickHelper;
import org.usfirst.frc.team1369.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lift extends Subsystem
{

    private VictorSP motor;
    private JoystickHelper joystick;
    private boolean shouldOverride;
    private double speed_const;
    private AnalogInput pot;

    public static boolean isSettingLiftAngle;

    public Lift()
    {
        this.motor = RobotMap.MOTOR_LIFT;
        this.pot = RobotMap.POTENTIOMETER;
        this.shouldOverride = true;
        isSettingLiftAngle = false;
        this.speed_const = 0.5;
        this.joystick = RobotMap.JOY_SHOOTER;

        /** WARNING **/
        //so I put the smartdashboard code here for inserting the gains because this will help with tuning purposes
        //if there is a compilation error, simply delete this part and put it where it is indicated below
        SmartDashboard.putNumber("pI", pI);
        SmartDashboard.putNumber("kI", kI);
        SmartDashboard.putNumber("kD", kD);
    }


    boolean hasBeenStopped = false;

    @Deprecated
    public void stop()
    {
        if (!hasBeenStopped)
        {
            motor.set(0);
            hasBeenStopped = true;
            previousTime = System.nanoTime();
            integral = 0;
        }
        if (hasBeenStopped)
        {
            double tChange = System.nanoTime() - previousTime;
            tChange = tChange / 1e9;
            double error = RobotMap.POTENTIOMETER.getVoltage() - target;
            SmartDashboard.putNumber("Error val", error);

            double p = error * 1.0000000000001;///p gain
            integral += (error * tChange * 0.0300000000001); //i gain

            double sum = p + integral;

            if (Math.abs(error) > 0.1) motor.set(sum);

            previousTime = System.nanoTime();
        }
    }

    public void up(double speed)
    {
        motor.set(-speed);
    }

    public void down(double speed)
    {
        motor.set(speed);
    }

    @Deprecated
    public void setLiftAngle(double raw)
    {
        //this is un-needed, this needs to be rewritten for the analog inupt~kun
    }

    public boolean isWithinDeadzone(double deadzone)
    {
        SmartDashboard.putNumber("Raw Lift Speed", joystick.getJoystick().getY());
        return !(joystick.getJoystick().getY() < -deadzone || joystick.getJoystick().getY() > deadzone);
    }

    /**
     * Variables for PID control
     * Against convention, will be moved later...
     */
    public static double target = -1;
    private double previousTime = 0;
    private double integral = 0;
    private double previousError = 0;
    private double pI = ((joystick.getJoystick().getY() < -0.15) ? 0.9 : 1.35);
    private double kI = 0.05;
    private double kD = 0.03;

    public void lift()
    {
        SmartDashboard.putNumber("Lift Speed_Const", speed_const);

        //if the lift is within the deadzone, nothing should happen
        if (isWithinDeadzone(0.15))
        {
        }
        else
        {
            //increases the target by the inverse of the joystick Y value
            target += -(joystick.getJoystick().getY() / 30);
            SmartDashboard.putNumber("PID Stop Value: ", target);
        }

        //during the loop, keeps setting the proper angle

        //tChange = change in time
        double tChange = System.nanoTime() - previousTime;
        tChange = tChange / 1e9; //converts to seconds

        double error = RobotMap.POTENTIOMETER.getVoltage() - target;
        SmartDashboard.putNumber("Error val", error);

        //gain values

        //display on the smartDashboard

        //if there is an error, delete the code in the constructor and put it here, otherwise use this
        double pGain = SmartDashboard.getNumber("pI");
        double iGain = SmartDashboard.getNumber("kI");
        double dGain = SmartDashboard.getNumber("kD");

        double p = error;
        integral += (error * tChange);
        double d = (error - previousError) / tChange;

        double sum = (p * pGain) + (integral * iGain) + (d * dGain);

        if (Math.abs(error) > 0.1) motor.set(sum); //filtering?

        previousTime = System.nanoTime();
        previousError = error;
    }

    @Override
    protected void initDefaultCommand()
    {

    }

}
