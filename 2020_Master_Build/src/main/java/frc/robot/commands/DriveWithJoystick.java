/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class DriveWithJoystick extends CommandBase {
  /**
   * Creates a new DriveWithJoystick.
   */
  public DriveWithJoystick(DriveTrain drive) {
    addRequirements(drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("angle", DriveTrain.ahrs.getPitch());
    if(RobotState.isOperatorControl()){
      SmartDashboard.putNumber("angle", DriveTrain.getYaw());
      DriveTrain.left1.setNeutralMode(NeutralMode.Coast);
      DriveTrain.left2.setNeutralMode(NeutralMode.Coast);
      DriveTrain.right1.setNeutralMode(NeutralMode.Coast);
      DriveTrain.right2.setNeutralMode(NeutralMode.Coast);
      if(RobotContainer.getLxAxis() ==0 && RobotContainer.getLyAxis() ==0){
        DriveTrain.arcadeDrive(RobotContainer.getClimbLy()/2, RobotContainer.getClimbLx()/2);
    } else{
      DriveTrain.arcadeDrive(RobotContainer.getLyAxis(),RobotContainer.getLxAxis() * 0.7);
    }
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
