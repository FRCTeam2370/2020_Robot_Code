/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Shooter;

public class BaseSpeedAdjust extends CommandBase {
  /**
   * Creates a new BaseSpeedUp.
   */
  public boolean isSet = false;
  public BaseSpeedAdjust(Shooter s) {
    addRequirements(s);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotContainer.getPOV == 0 && !isSet){
      Shooter.BaseSpeed+=5;
      isSet = true;
    }
    if(RobotContainer.getPOV == 180 && !isSet){
      Shooter.BaseSpeed-=5;
      isSet = true;
    }
    if(RobotContainer.getPOV == -1){
      isSet = false;
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
