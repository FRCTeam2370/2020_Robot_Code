/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeArm extends SubsystemBase {
  /**
   * Creates a new IntakeArm.
   */
  public IntakeArm() {

  }

  public static WPI_TalonFX IntakeArmMotor = new WPI_TalonFX(Constants.IntakeArmMotor); 

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
