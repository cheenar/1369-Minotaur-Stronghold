package org.usfirst.frc.team1369.robot.commands.auto;


import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1369.robot.Robot;
import org.usfirst.frc.team1369.robot.subsystems.DriveTrain;

/**
 * Created by Squidmin on 17-Feb-16.
 */
public class AutoGyroTurn extends Command {

    private boolean isDone;
    private DriveTrain driveTrain;

    public AutoGyroTurn()
    {
        driveTrain = Robot.driveTrain;
        requires(driveTrain);
    }

    @Override
    protected void initialize()
    {
        isDone = false;
    }

    @Override
    protected void execute() {
        double angle = SmartDashboard.getNumber("turnAngle", 180.0);
        double speed = SmartDashboard.getNumber("turnSpeed", .9);

        driveTrain.turningByRate(angle, speed);
        isDone = true;
    }

    @Override
    protected boolean isFinished() {
        return isDone;
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {

    }
}
