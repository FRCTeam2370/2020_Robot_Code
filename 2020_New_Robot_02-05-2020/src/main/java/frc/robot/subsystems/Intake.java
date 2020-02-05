/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  public Intake() {

  }
  public static double intakeSpeed = .2;
  public static double intakeUpDownSpeed = 0.1;
  public static WPI_TalonSRX intakeMotor = new WPI_TalonSRX(Constants.IntakeMotor);
  public static WPI_TalonSRX intakeUpAndDown = new WPI_TalonSRX(Constants.IntakeArmMotor);

  public static void Intakein(){
    intakeMotor.set(ControlMode.PercentOutput, intakeSpeed);
  }
  public static void IntakeOut(){
    intakeMotor.set(ControlMode.PercentOutput, -intakeSpeed);
  }
  public static void StopIntake(){
    intakeMotor.stopMotor();
  }

  public static void IntakeUp() {
    intakeUpAndDown.set(ControlMode.PercentOutput, intakeUpDownSpeed);
  }

  public static void IntakeDown() {
    intakeUpAndDown.set(ControlMode.PercentOutput, -intakeUpDownSpeed);
  }

  public static void IntakeHang() {
    intakeUpAndDown.stopMotor();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
