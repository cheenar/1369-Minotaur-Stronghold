package org.usfirst.frc.team1369.robot.subsystems;

import org.usfirst.frc.team1369.robot.JoystickHelper;
import org.usfirst.frc.team1369.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lift extends Subsystem {

    private VictorSP motor;
    private JoystickHelper joystick;
    private boolean shouldOverride;
    private double speed_const;
    private Potentiometer pot;

    public static boolean isSettingLiftAngle;

    public Lift() {
        this.motor = RobotMap.MOTOR_LIFT;
        this.pot = RobotMap.POTENTIOMETER;
        this.shouldOverride = true;
        isSettingLiftAngle = false;
        this.speed_const = 0.5;
        this.joystick = RobotMap.JOY_SHOOTER;
    }

    public void stop() {
        motor.set(0);
    }

    public void up(double speed) {
        motor.set(-speed);
    }

    public void down(double speed) {
        motor.set(speed);
    }

    public void setLiftAngle(double raw) {
        isSettingLiftAngle = true;

        try {
            while (pot.get() > raw - .003 || pot.get() < raw + 0.003) {
                //top = 0.470
                //bot = 0.757
                if (raw < pot.get()) {
                    //desired position requires up ward movement
                    motor.set(0.5);
                    while (pot.get() > raw) {
                        //let the lift go up
                    }
                    motor.set(0);
                } else {
                    //desired position requires down ward movement
                    motor.set(-0.5);
                    while (pot.get() < raw) {
                        //let the lift go down
                    }
                    motor.set(0);
                }
                isSettingLiftAngle = false;
                return;

            }
            motor.set(0);
            isSettingLiftAngle = false;
            return;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            motor.set(0);
            isSettingLiftAngle = false;
        }
    }

    public boolean isWithinDeadzone(double deadzone) {
        SmartDashboard.putNumber("Raw Lift Speed", joystick.getJoystick().getY());
        if (joystick.getJoystick().getY() < -deadzone || joystick.getJoystick().getY() > deadzone) {
            return false;
        } else {
            return true;
        }
    }

    public void lift() {
        SmartDashboard.putNumber("Lift Speed_Const", speed_const);
        SmartDashboard.putNumber("Absolute Encoder Value", pot.get());


        if (!isSettingLiftAngle) {
            //SETTING LIFT ANGLE
           /* if (joystick.getJoystick().getPOV() == TophatPosition.WEST.getValue()) {
                this.setLiftAngle(0.525);
            }
            if (joystick.getJoystick().getPOV() == TophatPosition.NORTH.getValue()) {
                this.setLiftAngle(0.473);
            }
            if (joystick.getJoystick().getPOV() == TophatPosition.EAST.getValue()) {
                this.setLiftAngle(0.719);
            }
            if (joystick.getJoystick().getPOV() == TophatPosition.SOUTH.getValue()) {
                this.setLiftAngle(0.745);
            }*/


            if (joystick.isPressed(10) && shouldOverride) {
                this.shouldOverride = false;
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            if (speed_const == 0.5) {
                                speed_const = 1.0;
                            } else {
                                speed_const = 0.5;
                            }
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            shouldOverride = true;
                        }
                    }
                }).start();
            }

            if (isWithinDeadzone(0.15)) {
                stop();
            } else {
                motor.set(joystick.getJoystick().getY() * speed_const); //now a hundred percent power will only be thirty percent
            }
        }
    }

    @Override
    protected void initDefaultCommand() {

    }

}
