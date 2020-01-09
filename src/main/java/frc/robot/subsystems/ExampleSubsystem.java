/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.ExampleCommand;

public class ExampleSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  public static WPI_TalonSRX leftMotor = new WPI_TalonSRX(0);
  public static WPI_TalonSRX rightMotor = new WPI_TalonSRX(1);
  public static DifferentialDrive driveTrain = new DifferentialDrive(leftMotor,rightMotor);

  public ExampleSubsystem() {
  this.setDefaultCommand(new ExampleCommand(this));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  

}