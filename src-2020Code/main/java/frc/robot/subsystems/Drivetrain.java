/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.Drive;

public class Drivetrain extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
  private static WPI_TalonFX leftMotor = new WPI_TalonFX(0);
  private static WPI_TalonFX rightMotor = new WPI_TalonFX(1);
  public static DifferentialDrive driveTrain = new DifferentialDrive(leftMotor,rightMotor);

  public Drivetrain() {
  this.setDefaultCommand(new Drive(this));
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  

}