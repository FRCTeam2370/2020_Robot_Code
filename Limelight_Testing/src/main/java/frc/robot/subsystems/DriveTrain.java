/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;



import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
}
  public static PIDController controller = new PIDController(0.06, 0.05, 0);
  public static WPI_TalonSRX left = new WPI_TalonSRX(Constants.DriveMotorLeft);
  public static WPI_TalonSRX right = new WPI_TalonSRX(Constants.DriveMotorRight);
  public static WPI_TalonSRX left2 = new WPI_TalonSRX(Constants.DriveMotorLeft2);
  public static WPI_TalonSRX right2 = new WPI_TalonSRX(Constants.DriveMotorRight2);
  public static DifferentialDrive driveTrainMotors = new DifferentialDrive(left, right);
  public static AHRS ahrs;

  public static double getAngle(){
    double angle = ahrs.getAngle();
    return angle;
  }

  public static void arcadeDrive(double speed, double rotation){
    driveTrainMotors.arcadeDrive(speed, rotation);
  }

  


  @Override
  public void periodic() {
    
    right2.follow(right);
    left2.follow(left);
    // This method will be called once per scheduler run
  }
}
