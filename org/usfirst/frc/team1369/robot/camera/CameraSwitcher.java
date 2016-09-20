package org.usfirst.frc.team1369.robot.camera;

import com.ni.vision.NIVision;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1369.robot.RobotMap;

public class CameraSwitcher {

    private final int camCenter;
    private final int camRight;
    private int curCam;
    private NIVision.Image frame;
    private CameraServer server;

    private String camera;


    public CameraSwitcher() {
        // Get camera ids by supplying camera name ex 'cam0', found on roborio web interface
        camCenter = NIVision.IMAQdxOpenCamera("cam1", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        camRight = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        curCam = camCenter;
        camera = "center";
        // Img that will contain camera img
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        // Server that we'll give the img to
        server = CameraServer.getInstance();
        server.setQuality(50);
        server.setSize(300);

    }

    public void init() {
        changeCam(camRight);
        camera = "right";
    }

    public void run() {
        try
        {
            if(RobotMap.JOY_LEFT.getJoystick() != null && RobotMap.JOY_RIGHT.getJoystick() != null)
            {
                if (RobotMap.JOY_LEFT.isPressed("CameraCenter")) {
                    changeCam(camCenter);
                    camera = "center";
                } else if (RobotMap.JOY_RIGHT.isPressed("CameraRight")) {
                    changeCam(camRight);
                    camera = "right";
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        SmartDashboard.putString("camera", camera);
        try
        {
            updateCam();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Stop aka close camera stream
     */
    public void end() {
        NIVision.IMAQdxStopAcquisition(curCam);
    }

    /**
     * Change the camera to get imgs from to a different one
     *
     * @param newId for camera
     */
    public void changeCam(int newId) {
        try
        {
            try { NIVision.IMAQdxStopAcquisition(curCam); } catch(Exception e) { }
            NIVision.IMAQdxConfigureGrab(newId);
            NIVision.IMAQdxStartAcquisition(newId);
            curCam = newId;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Get the img from current camera and give it to the server
     */
    public void updateCam() {
        try
        {
            System.out.println("Updating camerea");
            NIVision.IMAQdxGrab(curCam, frame, 1);
            //Rotation

            NIVision.imaqFlip(frame, frame, NIVision.FlipAxis.HORIZONTAL_AXIS);

            if (camera.equalsIgnoreCase("center"))
            {
                NIVision.imaqFlip(frame, frame, NIVision.FlipAxis.HORIZONTAL_AXIS);
            }
            else
            {
                NIVision.imaqFlip(frame, frame, NIVision.FlipAxis.VERTICAL_AXIS);
            }

            server.setImage(frame);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}