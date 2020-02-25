/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ClimberToNextSetpoint extends CommandBase {
  /**
   * Creates a new ClimberToNextSetpoint.
   */
  private double limit = Climber.TopPoint;
  public ClimberToNextSetpoint(Climber c) {
    addRequirements(c);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Climber.WhatPoint == 0){
      Climber.setMotorsNoPID(.5);
      Climber.WhatPoint = 1;
    }
    if(Climber.WhatPoint == 1){
      Climber.setMotorsNoPID(.5);
      Climber.WhatPoint = 2;
    }
    if(Climber.WhatPoint == 2){
      Climber.setMotorsNoPID(-.5);
      Climber.WhatPoint = 1;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Climber.WhatPoint == 1){
      limit = Climber.TopPoint;
    } else if(Climber.WhatPoint == 2){
      limit = Climber.ClimbedPoint;
    }
    return Math.abs(limit - Climber.ReadEncoders()) < 1000;
  }
}
