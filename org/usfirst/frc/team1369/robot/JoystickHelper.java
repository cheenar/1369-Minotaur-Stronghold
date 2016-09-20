package org.usfirst.frc.team1369.robot;

import edu.wpi.first.wpilibj.Joystick;

import java.util.HashMap;

/**
 * Created by cheen on 2/20/2016.
 */
public class JoystickHelper
{

    public static enum TophatPosition
    {
        NORTH(0),
        SOUTH(180),
        EAST(90),
        WEST(270);


        TophatPosition(int i)
        {
            this.pos = i;
        }

        private int pos;

        public int getValue()
        {
            return this.pos;
        }
    }

    private Joystick joystick;
    private boolean isLogitechExtreme3D;
    private HashMap<String, Integer> mappings;

    public JoystickHelper(Joystick joystick, boolean isLogitechExtreme3D, HashMap<String, Integer> mappings)
    {
        this.joystick = joystick;
        this.isLogitechExtreme3D = isLogitechExtreme3D;
        this.mappings = mappings;
    }

    public int getTophatDirection()
    {
        return getJoystick().getPOV();
    }

    public int getPort(String name)
    {
        if(mappings.containsKey(name))
        {
            return mappings.get(name);
        }
        else
        {
            System.out.println("Invalid key input: " + joystick.getName());
            return 99;
        }
    }

    public boolean isPressed(int port)
    {
        return joystick.getRawButton(port);
    }

    public boolean isPressed(String name)
    {
        return isPressed(getPort(name));
    }

    public Joystick getJoystick()
    {
        return this.joystick;
    }

    public boolean isJoystickYDown()
    {
        return (getJoystick().getY() < 0);
    }

    public boolean isLogitechExtreme3D()
    {
        return this.isLogitechExtreme3D;
    }

    public boolean isWithinDeadzone(double DEADZONE)
    {
        if(joystick.getMagnitude() < DEADZONE)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public double getThrottle()
    {
        if(isLogitechExtreme3D)
        {
            return getJoystick().getThrottle();
        }
        else
        {
            return getJoystick().getZ();
        }
    }

}
