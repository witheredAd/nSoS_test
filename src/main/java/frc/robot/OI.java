/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.Joystick;

/**
 * Add your docs here.
 */
public class OI {

    public Joystick logitechF310;
    public Joystick BETOP;

    public JoystickButton yellowButton;
    public JoystickButton redButton;
    public JoystickButton greenButton;
    public JoystickButton blueButton;
    public JoystickButton frontLeftButton;
    public JoystickButton frontRightButton;
    public JoystickButton middleLeftButton;
    public JoystickButton middleRightButton;
    public JoystickButton middleAxisLeftButton;
    public JoystickButton middleAxisRightButton;

    public JoystickButton yellowButtonBETOP;
    public JoystickButton redButtonBETOP;
    public JoystickButton greenButtonBETOP;
    public JoystickButton blueButtonBETOP;
    public JoystickButton frontLeftButtonBETOP;
    public JoystickButton frontRightButtonBETOP;
    public JoystickButton throttleButtonLeftBETOP;
    public JoystickButton throttleButtonRightBETOP;
    public JoystickButton middleLeftButtonBETOP;
    public JoystickButton middleRightButtonBETOP;
    public JoystickButton middleAxisLeftButtonBETOP;
    public JoystickButton middleAxisRightButtonBETOP;

    public OI(){


        //The folling code is for the Joystick definition

        logitechF310 = new Joystick(0);
        BETOP = new Joystick(1);


        //These are the initialization of the logitech F310 gamepad.(The black one)
        yellowButton = new JoystickButton(logitechF310, 4);
        redButton = new JoystickButton(logitechF310, 2);
        greenButton = new JoystickButton(logitechF310, 1);
        blueButton = new JoystickButton(logitechF310, 3);

        frontLeftButton = new JoystickButton(logitechF310, 5);
        frontRightButton = new JoystickButton(logitechF310, 6);
        middleLeftButton = new JoystickButton(logitechF310, 7);
        middleRightButton = new JoystickButton(logitechF310, 8);
        // middleAxisLeftButton = new JoystickButton(logitechF310, 9);
        // middleAxisRightButton = new JoystickButton(logitechF310, 10);


        //The are the initialization of the BETOP gamepad, aka the white one.

    //    middleLeftButton.whileHeld(new OpenLED());






        //The following code is for the JoystickButton commands



    //    ConfigCommandDisplay();
    }

    public void ConfigCommandDisplay(){

        

    }

    public Joystick getJoystick1(){

        return logitechF310;

    }

    public Joystick getJoystick2(){
        
        return BETOP;

    }





    



}

