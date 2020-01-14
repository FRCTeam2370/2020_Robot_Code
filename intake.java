/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class intake extends SubsystemBase {
  /**
   * Creates the intake class.
   */
  public static WPI_TalonFX intakeMotor = new WPI_TalonFX(0);
//Adds the intake motor.
  public intake() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
