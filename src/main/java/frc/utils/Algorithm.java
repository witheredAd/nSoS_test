/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.utils;

import frc.utils.Constants;

/**
 * Add your docs here.
 */
public class Algorithm {

    public Algorithm(){

    }

    //Convert the encoder read to metre.
    public static double EncoderToMs(double encoderVal, double wheelRadius){

        return Math.abs(encoderVal) / .1  / 4096 * .0254 * wheelRadius * Math.PI;
        
    }
    
    public static double ConfigJoystickInput_Velocity(double input){
    
        //0.1 is the therehold of the Joystick.
        if(Math.abs(input) > 0.1){
          return input*input*input*Constants.maxRPM;
        }else if(Math.abs(input) > 1.001){
          return Constants.maxRPM;
        }else{
          return 0;
        }
    
    }

    public static double ConfigJoystickInput_PercentOutput(double input){
    
        //0.1 is the therehold of the Joystick.
        if(Math.abs(input) > 0.1){
          return input*input*input;
        }else if(Math.abs(input) > 1.001){
          return 1;
        }else{
          return 0;
        }

       
    }

    public static double SquareAv(double inputA, double inpputB){
      double outputRaw = inputA*inputA + inpputB*inpputB;
      return Math.sqrt(outputRaw);
    }

        
    public static double VelocityCalculator(double input){
    
      //  double output = input * Constants.kSensorUnitsPerRotation * Constants.MaxRPM / 1000 /*60ms pre unit*/ ;
          double output = input * Constants.maxRPM;
          return output;
      
      }



}
