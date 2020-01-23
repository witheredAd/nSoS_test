/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import frc.utils.ScheduledTask;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;

import frc.utils.Algorithm;
import frc.utils.Constants;
/**
 * Easy TankDrive using 2020-nSoS
 */
public class Drive extends ScheduledTask{

    @Override
    public void update(){

    }

    public WPI_TalonSRX left_1 = RobotMap.left_1;
    public WPI_TalonSRX left_2 = RobotMap.left_2;
    public WPI_TalonSRX left_3 = RobotMap.left_3;
    public WPI_TalonSRX right_1 = RobotMap.right_1;
    public WPI_TalonSRX right_2 = RobotMap.right_2;
    public WPI_TalonSRX right_3 = RobotMap.right_3;
  
    public SpeedControllerGroup leftGroup = new SpeedControllerGroup(left_1, left_2, left_3);
    public SpeedControllerGroup rightGroup = new SpeedControllerGroup(right_1, right_2, right_3);
    public DifferentialDrive differentialDrive = new DifferentialDrive(leftGroup, rightGroup);
  
    public Encoder encoderElevator = RobotMap.encoderElevator;
  
    private static final Drive instance = new Drive();

    public Drive(){
        super(20);
     
      TalonSRX_Init();
  
      left_2.follow(left_1);
      left_3.follow(left_1);
      right_2.follow(right_1);
      right_3.follow(right_1);
      
      differentialDrive.setSafetyEnabled(false);
      differentialDrive.setExpiration(0.1);
      differentialDrive.setMaxOutput(1.0);
    }

    public static Drive getInstance(){
        return instance;
    }
  
  
    //Config some talonSRX parameters.
    public void TalonSRX_Init(){
  
        ConfigFactoryDefault();
        ConfigTalonStartUpSettings();
        SetEncoder();
        ConfigPIDParameter();
    }
  
  
    public void StopRobot_TankDrive(){
      differentialDrive.tankDrive(0, 0);
    }
  
    public void Stop_BrakeMode(){

        left_1.setNeutralMode(NeutralMode.Brake);
        left_2.setNeutralMode(NeutralMode.Brake);
        left_3.setNeutralMode(NeutralMode.Brake);
    
        right_1.setNeutralMode(NeutralMode.Brake);
        right_2.setNeutralMode(NeutralMode.Brake);
        right_3.setNeutralMode(NeutralMode.Brake);
    
    }
    
    
      public void TankDrive_PercentOutput(double inputLeft, double inputRight){
    
        // inputLeft = Robot.oi.logitechF310.getRawAxis(5);
        // inputRight = Robot.oi.logitechF310.getRawAxis(1);
    
        double leftPower = Algorithm.ConfigJoystickInput_PercentOutput(inputLeft);
        double rightPower = Algorithm.ConfigJoystickInput_PercentOutput(inputRight);
        double speedRatio = 1;
    
        if(encoderElevator.get() > 450){
          speedRatio = 0.4;
        }else if(encoderElevator.get() > 400){
          speedRatio = 0.55;
        }else if(encoderElevator.get() > 350){
          speedRatio = 0.7;
        }else if(encoderElevator.get() > 300){
          speedRatio = 0.85;
        }else{
          speedRatio = 1;
        }
    
        differentialDrive.tankDrive(leftPower*speedRatio, rightPower*speedRatio);
    
      }
    
      public void TankDrive_Velocity(double leftPower, double rightPower){
    
        double leftSpeed = Algorithm.VelocityCalculator(Algorithm.ConfigJoystickInput_Velocity(leftPower));
        double rightSpeed = -Algorithm.VelocityCalculator(Algorithm.ConfigJoystickInput_Velocity(rightPower)); 
    
        left_1.set(ControlMode.Velocity, leftSpeed);
        right_1.set(ControlMode.Velocity, rightSpeed);
        left_2.set(ControlMode.Velocity, leftSpeed);
        right_2.set(ControlMode.Velocity, rightSpeed);
        left_3.set(ControlMode.Velocity, leftSpeed);
        right_3.set(ControlMode.Velocity, rightSpeed);
    
    }
    
      public void TankDrive_Original(double inputLeft, double inputRight){
        differentialDrive.tankDrive(inputLeft, inputRight);
      }
      
    
      public void ArcadeDrive_Original(double inputForward, double inputTurning){
    
        differentialDrive.arcadeDrive(Robot.oi.logitechF310.getRawAxis(1), Robot.oi.logitechF310.getRawAxis(4));
        
      }
    
      public void SetEncoder(){
    
        left_2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.kTimeoutMs);
        left_2.setSensorPhase(true);
        left_2.setSelectedSensorPosition(0);
    
        right_2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.kTimeoutMs);
        right_2.setSensorPhase(true);
        right_2.setSelectedSensorPosition(0);
    
      }
    
      public void ResetEncoder(){
    
        left_2.setSelectedSensorPosition(0);
        right_2.setSelectedSensorPosition(0);
    
      }
    
      public void ConfigFactoryDefault(){
    
        left_1.configFactoryDefault();
        left_2.configFactoryDefault();
        left_3.configFactoryDefault();
    
        right_1.configFactoryDefault();
        right_2.configFactoryDefault();
        right_3.configFactoryDefault();
    
      }
    
      public void ConfigTalonStartUpSettings(){
    
        left_1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0/*pidIdx*/, Constants.kTimeoutMs);
        left_1.setSensorPhase(true);
        left_1.configNominalOutputForward(0/*minPercentOut*/, 30/*timeoutMs*/);
        left_1.configNominalOutputForward(0, Constants.kTimeoutMs);
        left_1.configPeakCurrentDuration(1, Constants.kTimeoutMs);
        left_1.configPeakOutputReverse(-1, Constants.kTimeoutMs);
    
        right_1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0/*pidIdx*/, Constants.kTimeoutMs);
        right_1.setSensorPhase(true);
        right_1.configNominalOutputForward(0/*minPercentOut*/, 30/*timeoutMs*/);
        right_1.configNominalOutputForward(0, Constants.kTimeoutMs);
        right_1.configPeakCurrentDuration(1, Constants.kTimeoutMs);
        right_1.configPeakOutputReverse(-1, Constants.kTimeoutMs);
    
        left_2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0/*pidIdx*/, Constants.kTimeoutMs);
        left_2.setSensorPhase(true);
        left_2.configNominalOutputForward(0/*minPercentOut*/, 30/*timeoutMs*/);
        left_2.configNominalOutputForward(0, Constants.kTimeoutMs);
        left_2.configPeakCurrentDuration(1, Constants.kTimeoutMs);
        left_2.configPeakOutputReverse(-1, Constants.kTimeoutMs);
        
        right_2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0/*pidIdx*/, Constants.kTimeoutMs);
        right_2.setSensorPhase(true);
        right_2.configNominalOutputForward(0/*minPercentOut*/, 30/*timeoutMs*/);
        right_2.configNominalOutputForward(0, Constants.kTimeoutMs);
        right_2.configPeakCurrentDuration(1, Constants.kTimeoutMs);
        right_2.configPeakOutputReverse(-1, Constants.kTimeoutMs);
    
        left_3.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0/*pidIdx*/, Constants.kTimeoutMs);
        left_3.setSensorPhase(true);
        left_3.configNominalOutputForward(0/*minPercentOut*/, 30/*timeoutMs*/);
        left_3.configNominalOutputForward(0, Constants.kTimeoutMs);
        left_3.configPeakCurrentDuration(1, Constants.kTimeoutMs);
        left_3.configPeakOutputReverse(-1, Constants.kTimeoutMs);
    
        right_3.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0/*pidIdx*/, Constants.kTimeoutMs);
        right_3.setSensorPhase(true);
        right_3.configNominalOutputForward(0/*minPercentOut*/, 30/*timeoutMs*/);
        right_3.configNominalOutputForward(0, Constants.kTimeoutMs);
        right_3.configPeakCurrentDuration(1, Constants.kTimeoutMs);
        right_3.configPeakOutputReverse(-1, Constants.kTimeoutMs);
    
      }
    
      public void ConfigPIDParameter(){
    
        left_1.config_kF(Constants.driveBase_kSlotID, Constants.driveBase_kF, Constants.kTimeoutMs);
        left_1.config_kP(Constants.driveBase_kSlotID, Constants.driveBase_kP, Constants.kTimeoutMs);
        left_1.config_kI(Constants.driveBase_kSlotID, Constants.driveBase_kI, Constants.kTimeoutMs);
        left_1.config_kD(Constants.driveBase_kSlotID, Constants.driveBase_kD, Constants.kTimeoutMs);
        left_1.config_IntegralZone(Constants.driveBase_kSlotID, Constants.driveBase_kIzone, Constants.kTimeoutMs);
    
        right_1.config_kF(Constants.driveBase_kSlotID, Constants.driveBase_kF, Constants.kTimeoutMs);
        right_1.config_kP(Constants.driveBase_kSlotID, Constants.driveBase_kP, Constants.kTimeoutMs);
        right_1.config_kI(Constants.driveBase_kSlotID, Constants.driveBase_kI, Constants.kTimeoutMs);
        right_1.config_kD(Constants.driveBase_kSlotID, Constants.driveBase_kD, Constants.kTimeoutMs);        
        right_1.config_IntegralZone(Constants.driveBase_kSlotID, Constants.driveBase_kIzone, Constants.kTimeoutMs);
    
        left_2.config_kF(Constants.driveBase_kSlotID, Constants.driveBase_kF, Constants.kTimeoutMs);
        left_2.config_kP(Constants.driveBase_kSlotID, Constants.driveBase_kP, Constants.kTimeoutMs);
        left_2.config_kI(Constants.driveBase_kSlotID, Constants.driveBase_kI, Constants.kTimeoutMs);
        left_2.config_kD(Constants.driveBase_kSlotID, Constants.driveBase_kD, Constants.kTimeoutMs);
        left_2.config_IntegralZone(Constants.driveBase_kSlotID, Constants.driveBase_kIzone, Constants.kTimeoutMs);
    
        right_2.config_kF(Constants.driveBase_kSlotID, Constants.driveBase_kF, Constants.kTimeoutMs);
        right_2.config_kP(Constants.driveBase_kSlotID, Constants.driveBase_kP, Constants.kTimeoutMs);
        right_2.config_kI(Constants.driveBase_kSlotID, Constants.driveBase_kI, Constants.kTimeoutMs);
        right_2.config_kD(Constants.driveBase_kSlotID, Constants.driveBase_kD, Constants.kTimeoutMs);        
        right_2.config_IntegralZone(Constants.driveBase_kSlotID, Constants.driveBase_kIzone, Constants.kTimeoutMs);
    
        left_3.config_kF(Constants.driveBase_kSlotID, Constants.driveBase_kF, Constants.kTimeoutMs);
        left_3.config_kP(Constants.driveBase_kSlotID, Constants.driveBase_kP, Constants.kTimeoutMs);
        left_3.config_kI(Constants.driveBase_kSlotID, Constants.driveBase_kI, Constants.kTimeoutMs);
        left_3.config_kD(Constants.driveBase_kSlotID, Constants.driveBase_kD, Constants.kTimeoutMs);
        left_3.config_IntegralZone(Constants.driveBase_kSlotID, Constants.driveBase_kIzone, Constants.kTimeoutMs);
    
        right_3.config_kF(Constants.driveBase_kSlotID, Constants.driveBase_kF, Constants.kTimeoutMs);
        right_3.config_kP(Constants.driveBase_kSlotID, Constants.driveBase_kP, Constants.kTimeoutMs);
        right_3.config_kI(Constants.driveBase_kSlotID, Constants.driveBase_kI, Constants.kTimeoutMs);
        right_3.config_kD(Constants.driveBase_kSlotID, Constants.driveBase_kD, Constants.kTimeoutMs);        
        right_3.config_IntegralZone(Constants.driveBase_kSlotID, Constants.driveBase_kIzone, Constants.kTimeoutMs);
    
      }
    
    
    
    
    }
    