package org.usfirst.frc.team1369.robot;

import java.util.HashMap;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickHelper
{
	
	public JoystickHelper(Joystick joystick, HashMap<String, Integer> buttonMapping)
	{
		this.joystick = joystick;
		this.buttonMapping = buttonMapping;
	}
	
	public Joystick getJoystick()
	{
		return joystick;
	}
	
	public boolean isWithinDeadzone(double deadzone)
	{
		return getJoystick().getMagnitude() < deadzone;
	}
	
	public boolean isPressed(int button)
	{
		return getJoystick().getRawButton(button);
	}
	
	public boolean isPressed(String name)
	{
		return isPressed(buttonMapping.get(name));
	}
	
	public enum TophatPosition
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
	private HashMap<String, Integer> buttonMapping;
	

}
