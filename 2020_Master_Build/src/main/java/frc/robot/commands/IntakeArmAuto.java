/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeArm;

public class IntakeArmAuto extends CommandBase {
  /**
   * Creates a new IntakeArmAuto.
   */
  public IntakeArmAuto() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Intake.intakeMotor.set(ControlMode.PercentOutput, Intake.intakeSpeed);
    if(IntakeArm.IntakeArmMotor.getSensorCollection().getQuadraturePosition() > 5000){
    IntakeArm.IntakeArmMotor.set(ControlMode.PercentOutput, -0.2);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Intake.intakeMotor.stopMotor();
    IntakeArm.IntakeArmMotor.set(ControlMode.PercentOutput, 0.2);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
