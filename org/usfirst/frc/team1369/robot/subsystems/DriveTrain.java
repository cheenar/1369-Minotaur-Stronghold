package org.usfirst.frc.team1369.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1369.robot.JoystickHelper;
import org.usfirst.frc.team1369.robot.Robot;
import org.usfirst.frc.team1369.robot.RobotMap;
import org.usfirst.frc.team1369.robot.commands.CommandMoveDistance;

public class DriveTrain extends Subsystem {

    //    private RobotDrive driveTrain;
    private JoystickHelper leftStick;
    private JoystickHelper rightStick;

    private VictorSP frontLeft;
    private VictorSP frontRight;
    private VictorSP backLeft;
    private VictorSP backRight;
    private double DEADZONE = .4;
    private ADXRS450_Gyro gyro;
    private Encoder encoder;

    private double DRIVE_SPEED_L = 0.5;
    private double DRIVE_SPEED_R = 0.5;
    private double DRIVE_SPEED_CONSTANT = 1;

    private enum Speeds
    {
        LOW(0.4),
        MEDIUM(0.5),
        HIGH(0.6),
        HIGHER(0.7),
        EHIGHER(0.8),
        EEHIGHER(0.9),
        HIGHEST(1.0);

        public double getSpeed()
        {
            return this.speed;
        }

        Speeds(double speed)
        {
            this.speed = speed;
        }
        private double speed;
    }

    public DriveTrain() {
        //  this.driveTrain = RobotMap.ROBOT_DRIVE;
        this.leftStick = RobotMap.JOY_LEFT;
        this.rightStick = RobotMap.JOY_RIGHT;

        //MOTORS ARE INVERTERED
        RobotMap.MOTOR_RIGHT_MOTOR_BACK.setInverted(true);
        RobotMap.MOTOR_RIGHT_MOTOR_FRONT.setInverted(true);

        encoder = RobotMap.MOTOR_ENCODER_LEFT;
        encoder.setMaxPeriod(1);
        encoder.setMinRate(10);
        encoder.setDistancePerPulse(10);
        encoder.setSamplesToAverage(10);

        this.frontRight = RobotMap.MOTOR_RIGHT_MOTOR_FRONT;
        this.frontLeft = RobotMap.MOTOR_LEFT_MOTOR_FRONT;
        this.backLeft = RobotMap.MOTOR_LEFT_MOTOR_BACK;
        this.backRight = RobotMap.MOTOR_RIGHT_MOTOR_BACK;

        this.gyro = Robot.gyro;
    }

    @Override
    protected void initDefaultCommand() {

    }

    private void stickDrive(JoystickHelper joy, VictorSP one, VictorSP two, double DS)
    {
        if (joy.isWithinDeadzone(DEADZONE)) {
            one.set(0);
            two.set(0);
        } else {
            if (joy.getJoystick().getY() < 0) {

                one.set(-DS);
                two.set(-DS);
            } else {
                one.set(DS);
                two.set(DS);
            }
        }
    }

    /**
     * drive: will move the robot with tank drive
     * args: none
     */
    public void drive() {
        //motors are inverted in the DriveTrain.java subsystem file.

        if(leftStick.isPressed("D_Low"))
            DRIVE_SPEED_CONSTANT = Speeds.LOW.getSpeed();
        if(leftStick.isPressed("D_Medium"))
            DRIVE_SPEED_CONSTANT = Speeds.MEDIUM.getSpeed();
        if(leftStick.isPressed("D_High"))
            DRIVE_SPEED_CONSTANT = Speeds.HIGH.getSpeed();

        if(rightStick.isPressed("D_EHigher"))
            DRIVE_SPEED_CONSTANT = Speeds.EHIGHER.getSpeed();
        if(rightStick.isPressed("D_EEHigher"))
            DRIVE_SPEED_CONSTANT = Speeds.EEHIGHER.getSpeed();
        if(rightStick.isPressed("D_Highest"))
            DRIVE_SPEED_CONSTANT = Speeds.HIGHEST.getSpeed();

        DRIVE_SPEED_L = DRIVE_SPEED_CONSTANT * (Robot.magnititude(leftStick.getJoystick().getY()));
        DRIVE_SPEED_R = DRIVE_SPEED_CONSTANT * (Robot.magnititude(rightStick.getJoystick().getY()));

        stickDrive(leftStick, frontLeft, backLeft, DRIVE_SPEED_L);
        stickDrive(rightStick, frontRight, backRight, DRIVE_SPEED_R);

        SmartDashboard.putNumber("Drive Speed Left", DRIVE_SPEED_L);
        SmartDashboard.putNumber("Drive Speed Right", DRIVE_SPEED_R);
        SmartDashboard.putNumber("Encoder (dist)", encoder.getDistance());
        SmartDashboard.putNumber("Encoder (raw)", encoder.get());

    }

    public void driveCustom(double x, double y) {
        frontLeft.set(x);
        backLeft.set(x);
        backRight.set(y);
        frontRight.set(y);
    }

    public boolean driveDistance(boolean shouldReset, double speed, double dist) {
        if (shouldReset) {
            encoder.reset();
        }

        if (dist < 0) {
            driveCustom(-speed, -speed);
        } else {
            driveCustom(speed, speed);
        }

        while (encoder.getDistance() <= dist) {
            if (CommandMoveDistance.isExecuting == false) {
                break;
            }
        }
        stop();
        return true;
    }

    public void stop() {
        driveCustom(0, 0);
    }

    public double getAngle(double angle)
    {
        return ((175/90) * angle) - 175;
    }

    public boolean turningByRate(double angle, double speed) {
        boolean isCompleted = false;
        long lastSysTime = System.nanoTime();
        long currSysTime = -1;
        double angleMeasured = 0;

        boolean angleWasNegative = false; //keeps track if the angle was negative

        if (angle < 0) {
            driveCustom(speed, -speed);
            angle *= -1;
            angleWasNegative = true;
        } else {
            driveCustom(-speed, speed);
        }
        while (angleMeasured <= angle) {
            currSysTime = System.nanoTime();
            long elapsed = currSysTime - lastSysTime;
            double angleMoved = 0; //multiplys the rate by the elapsed time in secs
            if (Robot.gyro != null) {
                double rate = Robot.magnititude(gyro.getRate());
                angleMoved = (rate * ((elapsed / 1000000000.0)));
            }
            angleMeasured += angleMoved;
            lastSysTime = currSysTime;
        }
        RobotMap.log("Gyro Turn Moved", angle + "degrees", String.class);
        stop();
        isCompleted = true;
        return isCompleted;
    }

}
