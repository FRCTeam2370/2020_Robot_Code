/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeArm;

public class DoTheIntakeThingRealGood extends CommandBase {
  /**
   * Creates a new DoTheIntakeThingRealGood.
   */
  public DoTheIntakeThingRealGood(Intake i, IntakeArm ia) {
    addRequirements(i, ia);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    IntakeArm.IntakeArmMotor.set(ControlMode.PercentOutput, -.2);
    //Intake.intakeMotor.set(ControlMode.PercentOutput, Intake.intakeSpeed);
    Timer.delay(.5);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    IntakeArm.IntakeArmMotor.set(ControlMode.PercentOutput, .05);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
