package org.usfirst.frc.team1369.robot.commands.auto;


import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1369.robot.Robot;
import org.usfirst.frc.team1369.robot.RobotMap;
import org.usfirst.frc.team1369.robot.subsystems.DriveTrain;

/**
 * Created by Squidmin on 17-Feb-16.
 */

public class AutoEncoderDrive extends Command {

    private boolean isDone;
    private DriveTrain driveTrain;

    public AutoEncoderDrive()
    {
        driveTrain = Robot.driveTrain;
        requires(driveTrain);
    }

    @Override
    protected void initialize() {
    	RobotMap.MOTOR_ENCODER_LEFT.reset();
        isDone = false;
    }

    @Override
    protected void execute() {

        //if(driveTrain.driveDistance(true, SmartDashboard.getNumber("encoder drive speed", 0.5), SmartDashboard.getNumber("encoder distance", 1)))
            //isDone = true;
    	SmartDashboard.putNumber("Encoder Rotations", RobotMap.MOTOR_ENCODER_LEFT.get());
    	RobotMap.log("Encoder Rotations", RobotMap.MOTOR_ENCODER_LEFT.getRate(), Double.class);

    }

    @Override
    protected boolean isFinished() {
        return isDone;
    }

    @Override
    protected void end()
    {
        driveTrain.stop();
    }

    @Override
    protected void interrupted() {
        driveTrain.stop();
    }

}
