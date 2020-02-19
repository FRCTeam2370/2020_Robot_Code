/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class StartShooter extends CommandBase {
  /**
   * Creates a new StartShooter.
   */
  private double targetV;
  public StartShooter(Shooter s) {
    addRequirements(s);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Shooter.shooterMotor.configClosedloopRamp(2);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    targetV =  Shooter.speed * 2048 / 600;
    Shooter.shooterMotor.set(ControlMode.Velocity, -targetV);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if(interrupted){
    Shooter.shooterMotor.set(ControlMode.Velocity, 0);
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return -Shooter.getSpeed() > Shooter.speed-50 && -Shooter.getSpeed() < Shooter.speed+50;
  }
}
