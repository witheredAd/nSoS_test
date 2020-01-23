package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class RobotMap {

    //Definition for the motor acuator parts.

    public static WPI_TalonSRX left_1;
    public static WPI_TalonSRX left_2;
    public static WPI_TalonSRX left_3;
    public static WPI_TalonSRX right_1;
    public static WPI_TalonSRX right_2;
    public static WPI_TalonSRX right_3;

    public static WPI_TalonSRX elevatorLeftMotor;
    public static WPI_TalonSRX elevatorRightMotor;

    public static VictorSP clawMotor;

    public static VictorSP climbingClawMotorLeft;
    public static VictorSP climbingClawMotorRight;


    //Definition for the pnumatics parts.

    public static Solenoid solenoidClaw;
    public static Solenoid solenoidHook;
    public static Solenoid elevatorGear;
    public static Solenoid climbingClawSolenoid;
    public static Solenoid lampLEDSolenoid;


    //Definition for sensor parts.

    public static Encoder encoderElevator;

    public static DigitalInput limitSwitchElevator;
    public static DigitalInput limitSwitchClimbingLeft;
    public static DigitalInput limitSwitchClimbingRight;

    public static PowerDistributionPanel pdp;




    public static void initRobotMap(){

        //Initialization for the motor acuator parts.

        left_1 = new WPI_TalonSRX(1);
        left_2 = new WPI_TalonSRX(2);
        left_3 = new WPI_TalonSRX(3);
        right_1 = new WPI_TalonSRX(10);
        right_2 = new WPI_TalonSRX(11);
        right_3 = new WPI_TalonSRX(12);

        elevatorLeftMotor = new WPI_TalonSRX(6);
        elevatorRightMotor = new WPI_TalonSRX(7);

        clawMotor = new VictorSP(1);
        
        climbingClawMotorLeft = new VictorSP(4);
        climbingClawMotorRight = new VictorSP(5);

        
        
        //Initialization for the pnumatics parts.

        solenoidClaw = new Solenoid(20, 4);       
        solenoidHook = new Solenoid(20, 5);

        elevatorGear = new Solenoid(20, 2);
        climbingClawSolenoid = new Solenoid(20, 1);
        lampLEDSolenoid = new Solenoid(20, 7);;

        //Initialization for sensor parts.

        encoderElevator = new Encoder(2, 3, false, EncodingType.k4X);


        limitSwitchElevator = new DigitalInput(1);
        limitSwitchClimbingLeft = new DigitalInput(5);
        limitSwitchClimbingRight = new DigitalInput(6);
    
        pdp = new PowerDistributionPanel(0);
    


        //ConfigreSmartDashboardDisplay();
    }

    public static void ConfigreSmartDashboardDisplay(){

        SmartDashboard.putBoolean("limitSwitchElevator", limitSwitchElevator.get());
        SmartDashboard.putBoolean("limitSwitchLeft", limitSwitchClimbingLeft.get());
        SmartDashboard.putBoolean("limitSwitchRight", limitSwitchClimbingRight.get());

        SmartDashboard.putData(pdp);
        SmartDashboard.putData("Encoder", encoderElevator);


    }

}
