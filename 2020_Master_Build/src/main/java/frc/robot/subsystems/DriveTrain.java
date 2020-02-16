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

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    left1.configOpenloopRamp(.5);
    left2.configOpenloopRamp(.5);
    right1.configOpenloopRamp(.5);
    right2.configOpenloopRamp(.5);
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
    if(RobotState.isAutonomous()){
      left1.setNeutralMode(NeutralMode.Brake);
      left2.setNeutralMode(NeutralMode.Brake);
      right1.setNeutralMode(NeutralMode.Brake);
      right2.setNeutralMode(NeutralMode.Brake);
      left1.configPeakOutputForward(.3);
      left1.configPeakOutputReverse(-.3);
      left2.configPeakOutputForward(.3);
      left2.configPeakOutputReverse(-.3);
      right1.configPeakOutputForward(.3);
      right1.configPeakOutputReverse(-.3);
      right2.configPeakOutputForward(.3);
      right2.configPeakOutputReverse(-.3);
      SmartDashboard.putString("Test", "Testing 123");
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
