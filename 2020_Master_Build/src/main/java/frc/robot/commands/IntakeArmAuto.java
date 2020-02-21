/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeArm;

public class IntakeArmAuto extends CommandBase {
  /**
   * Creates a new IntakeArmAuto.
   */
  public IntakeArmAuto(Intake in, IntakeArm ia) {
    addRequirements(in,ia);
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
    IntakeArm.IntakeArmMotor.set(ControlMode.PercentOutput, IntakeArm.speed);
    Indexer.IndexerMotor.set(ControlMode.PercentOutput, Indexer.indexerSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Intake.intakeMotor.stopMotor();
    IntakeArm.IntakeArmMotor.set(ControlMode.PercentOutput, -IntakeArm.speed);
    Indexer.IndexerMotor.set(ControlMode.PercentOutput, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
