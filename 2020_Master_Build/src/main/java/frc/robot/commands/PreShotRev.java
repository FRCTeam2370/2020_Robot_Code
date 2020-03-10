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
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;

public class PreShotRev extends CommandBase {
  /**
   * Creates a new PreShotRev.
   */
  public PreShotRev(Shooter s, Magazine m) {
    addRequirements(s,m);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Shooter.shooterMotor.set(ControlMode.Velocity, -2000 * 2048 / 600);
    if(!Magazine.IsBallOnTop()){
      Indexer.IndexerMotor.set(ControlMode.PercentOutput, Indexer.indexerSpeed);
      Magazine.magazineMotor.set(ControlMode.PercentOutput, Magazine.magazineSpeed);
    } else{
      Indexer.IndexerMotor.set(ControlMode.PercentOutput, 0);
      Magazine.magazineMotor.set(ControlMode.PercentOutput, 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if(!interrupted){
      Shooter.shooterMotor.set(ControlMode.Velocity, 0);
    }
    Magazine.magazineMotor.set(ControlMode.PercentOutput, 0);
    Indexer.IndexerMotor.set(ControlMode.PercentOutput, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
