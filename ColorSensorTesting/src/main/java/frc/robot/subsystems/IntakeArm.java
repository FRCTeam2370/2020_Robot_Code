/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeArm extends SubsystemBase {
  /**
   * Creates a new IntakeArm.
   */
  public IntakeArm() {
    IntakeArmMotor.configForwardSoftLimitThreshold(-1000);
    IntakeArmMotor.configForwardSoftLimitEnable(true);
  }

  public static WPI_TalonSRX IntakeArmMotor = new WPI_TalonSRX(Constants.IntakeArmMotor); 

  @Override
  public void periodic() {
    if(IntakeArmMotor.getSensorCollection().isFwdLimitSwitchClosed()){
    IntakeArm.IntakeArmMotor.getSensorCollection().setQuadraturePosition(0, 10);
  }
  SmartDashboard.putBoolean("limit switch",IntakeArmMotor.getSensorCollection().isFwdLimitSwitchClosed());
  SmartDashboard.putNumber("ArmPosition",IntakeArmMotor.getSensorCollection().getQuadraturePosition());
    // This method will be called once per scheduler run
  }
}
