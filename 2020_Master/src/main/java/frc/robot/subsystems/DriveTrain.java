/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.DriveWithJoystick;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    this.setDefaultCommand(new DriveWithJoystick(this));
}


  public static WPI_TalonSRX left = new WPI_TalonSRX(Constants.DriveMotorLeft);
  public static WPI_TalonSRX right = new WPI_TalonSRX(Constants.DriveMotorRight);
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
    // This method will be called once per scheduler run
  }
}
