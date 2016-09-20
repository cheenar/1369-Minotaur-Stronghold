package org.usfirst.frc.team1369.robot.commands.auto;

/**
 * @author Squidmin
 * @notes - Ezekiel's gyro code
 * - Testing required to check if driving straight
 */

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1369.robot.Robot;
import org.usfirst.frc.team1369.robot.subsystems.DriveTrain;

public class AutoDriveStraight extends Command {

    private DriveTrain drive;
    private ADXRS450_Gyro gyro;
    private final double Kp = 0.03;

    public AutoDriveStraight() {
        drive = Robot.driveTrain;
        this.requires(drive);

        gyro = Robot.gyro;
    }

    @Override
    protected void initialize() {}

    @Override
    protected void execute() {
        double angle = gyro.getAngle(); // get current heading
        drive.driveCustom((-angle * (Kp * 0.003)) + (.15), (-angle * (Kp * 0.003)) + (-.15)); // drive towards heading 0
        Timer.delay(0.004);
        //System.out.println(gyro.getAngle());
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
        drive.driveCustom(0, 0);
    }

    @Override
    protected void interrupted() {
        drive.driveCustom(0, 0);
    }

}
