/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class ResetEncoders extends CommandBase {
  /**
   * Creates a new ResetEncoders.
   */
  public ResetEncoders() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    DriveTrain.left.getSensorCollection().setIntegratedSensorPosition(0, 30);
    DriveTrain.left.setInverted(false);
    DriveTrain.right.setInverted(false);
    DriveTrain.left.configPeakOutputForward(.4);
    DriveTrain.left.configPeakOutputReverse(-.4);
    DriveTrain.right.configPeakOutputForward(.4);
    DriveTrain.right.configPeakOutputReverse(-.4);
    DriveTrain.ahrs.reset();
    DriveTrain.left.setNeutralMode(NeutralMode.Brake);
    DriveTrain.right.setNeutralMode(NeutralMode.Brake);
   }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
