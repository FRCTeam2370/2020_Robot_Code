/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoDrivePID extends PIDCommand {
  /**
   * Creates a new AutoDrivePID.
   */
  public AutoDrivePID(double setpoint, DriveTrain drive) {
    super(
        // The controller that the command will use
        new PIDController(0.000027, 0.000000, 0),
        // This should return the measurement
        () ->-DriveTrain.getPosition(),
        // This should return the setpoint (can also be a constant)
        () -> setpoint,
        // This uses the output
        output -> {DriveTrain.arcadeDrive(-output, 0);
          // Use the output here
        SmartDashboard.putNumber("encoderValue", -DriveTrain.getPosition());
        });
        getController().setTolerance(2000);
        // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.

  }

  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
