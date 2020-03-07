/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeArm;
import frc.robot.subsystems.Shooter;

public class MoveIntakeArm extends CommandBase {
  /**
   * Creates a new MoveMagazineArm.
   */
  public MoveIntakeArm(IntakeArm ia) {
    addRequirements(ia);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Shooter.ManualToggle == "intake"){
    Intake.intakeMotor.set(ControlMode.PercentOutput, -RobotContainer.getLeftTrigger());
    IntakeArm.IntakeArmMotor.configForwardSoftLimitEnable(false);
    IntakeArm.IntakeArmMotor.configReverseSoftLimitEnable(false);
    IntakeArm.IntakeArmMotor.set(ControlMode.PercentOutput, RobotContainer.getClimbLy());
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
