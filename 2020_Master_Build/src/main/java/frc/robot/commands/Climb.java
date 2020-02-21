/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;

public class Climb extends CommandBase {
  /**
   * Creates a new Climb.
   */
  private int inverted;
  public Climb(Climber c) {
    addRequirements(c);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Climber.ClimbMotorRight.setInverted(true);
    Climber.ClimbMotorLeft.setInverted(false);
    Climber.ClimbMotorLeft.setNeutralMode(NeutralMode.Brake);
    Climber.ClimbMotorRight.setNeutralMode(NeutralMode.Brake);
    DriveTrain.ahrs.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(/*Timer.getMatchTime() < 35 &&*/ RobotState.isOperatorControl()){
    SmartDashboard.putNumber("Roll", DriveTrain.getRoll());
    SmartDashboard.putNumber("RYAxis", RobotContainer.getClimbRyAxis());
    if(RobotContainer.ClimbStart.get()){
      inverted = -1;
    } else{
      inverted = 1;
    }
    if(RobotContainer.getClimbRyAxis() == 0){
    Climber.ClimbMotorLeft.set(ControlMode.PercentOutput, RobotContainer.getClimbLeftTrigger() * inverted);
    Climber.ClimbMotorRight.set(ControlMode.PercentOutput, RobotContainer.getClimbRightTrigger() * inverted);
    } else{
    Climber.setMotorsNoPID(RobotContainer.getClimbRyAxis());
    }
  }
}


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Climber.ClimbMotorLeft.stopMotor();
    Climber.ClimbMotorRight.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
