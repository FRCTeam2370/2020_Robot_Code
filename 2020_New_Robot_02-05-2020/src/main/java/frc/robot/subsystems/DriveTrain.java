/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
}

  public static WPI_TalonSRX left1 = new WPI_TalonSRX(Constants.DriveMotorLeftFront);
  public static WPI_TalonSRX left2 = new WPI_TalonSRX(Constants.DriveMotorLeftBack);
  public static SpeedControllerGroup left = new SpeedControllerGroup(left1, left2);

  public static WPI_TalonSRX right1 = new WPI_TalonSRX(Constants.DriveMotorRightFront);
  public static WPI_TalonSRX right2 = new WPI_TalonSRX(Constants.DriveMotorRightBack);
  public static SpeedControllerGroup right = new SpeedControllerGroup(right1, right2);

  public static DifferentialDrive driveTrainMotors = new DifferentialDrive(left, right);
  public static AHRS ahrs = new AHRS();

  public static double getAngle(){
    double angle = ahrs.getAngle();
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

  /*public static double getPosition(){
    double position = left.getSensorCollection().getIntegratedSensorPosition();
  return position;
  }*/

  public static void arcadeDrive(double speed, double rotation){
    driveTrainMotors.arcadeDrive(speed, rotation);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler runs
  }
}
