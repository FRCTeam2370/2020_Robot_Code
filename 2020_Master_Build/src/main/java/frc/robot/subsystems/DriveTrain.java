/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    left1.configFactoryDefault();
    left2.configFactoryDefault();
    right1.configFactoryDefault();
    right2.configFactoryDefault();
    left1.configOpenloopRamp(0.5);
    left2.configOpenloopRamp(0.5);
    right1.configOpenloopRamp(0.5);
    right2.configOpenloopRamp(0.5);
  }

  public static WPI_TalonFX left1 = new WPI_TalonFX(Constants.DriveMotorLeftFront);
  public static WPI_TalonFX left2 = new WPI_TalonFX(Constants.DriveMotorLeftBack);
  public static SpeedControllerGroup left = new SpeedControllerGroup(left1, left2);

  public static WPI_TalonFX right1 = new WPI_TalonFX(Constants.DriveMotorRightFront);
  public static WPI_TalonFX right2 = new WPI_TalonFX(Constants.DriveMotorRightBack);
  public static SpeedControllerGroup right = new SpeedControllerGroup(right1, right2);

  public static DifferentialDrive driveTrainMotors = new DifferentialDrive(left, right);
  public static AHRS ahrs = new AHRS();

  public static double getYaw(){
    double angle = ahrs.getYaw();
    return angle;
  }
  public static double getRoll(){
    double angle = ahrs.getRoll();
    return angle;
  }
  public static double getPitch(){
    double angle  = ahrs.getPitch();
    return angle;
  }

  public static double getRate(){
    double rate = ahrs.getRate();
    return rate;
  }
  public static double getXRot(){
    double rot = ahrs.getPitch();
    return rot;
  }

  public static double getPosition(){
    double position = left1.getSensorCollection().getIntegratedSensorPosition();
  return position;
  }

  public static void arcadeDrive(double speed, double rotation){
    driveTrainMotors.arcadeDrive(speed, rotation);
  }

  @Override
  public void periodic() {
    //SmartDashboard.putNumber("LYAxis", RobotContainer.getLyAxis());
    //SmartDashboard.putNumber("LXAxis", RobotContainer.getLxAxis());
    
    if(Timer.getMatchTime() == 40 || Timer.getMatchTime() == 30 || Timer.getMatchTime() == 10 || Timer.getMatchTime() == 5){
      RobotContainer.stick.setRumble(RumbleType.kLeftRumble, 1);
      RobotContainer.stick.setRumble(RumbleType.kRightRumble, 1);
      RobotContainer.climbStick.setRumble(RumbleType.kLeftRumble, 1);
      RobotContainer.climbStick.setRumble(RumbleType.kRightRumble, 1);
    } else{
      RobotContainer.stick.setRumble(RumbleType.kLeftRumble, 0);
      RobotContainer.stick.setRumble(RumbleType.kRightRumble, 0);
      RobotContainer.climbStick.setRumble(RumbleType.kLeftRumble, 0);
      RobotContainer.climbStick.setRumble(RumbleType.kRightRumble, 0);
    }

    if(RobotState.isAutonomous()){
      left1.setNeutralMode(NeutralMode.Brake);
      left2.setNeutralMode(NeutralMode.Brake);
      right1.setNeutralMode(NeutralMode.Brake);
      right2.setNeutralMode(NeutralMode.Brake);
      left1.configPeakOutputForward(.25);
      left1.configPeakOutputReverse(-.25);
      left2.configPeakOutputForward(.25);
      left2.configPeakOutputReverse(-.25);
      right1.configPeakOutputForward(.25);
      right1.configPeakOutputReverse(-.25);
      right2.configPeakOutputForward(.25);
      right2.configPeakOutputReverse(-.25);
    } else{
      left1.configPeakOutputForward(1);
      left1.configPeakOutputReverse(-1);
      left2.configPeakOutputForward(1);
      left2.configPeakOutputReverse(-1);
      right1.configPeakOutputForward(1);
      right1.configPeakOutputReverse(-1);
      right2.configPeakOutputForward(1);
      right2.configPeakOutputReverse(-1);
      left1.setNeutralMode(NeutralMode.Coast);
      left2.setNeutralMode(NeutralMode.Coast);
      right1.setNeutralMode(NeutralMode.Coast);
      right2.setNeutralMode(NeutralMode.Coast);
    }
    
    // This method will be called once per scheduler runs
  }
}
