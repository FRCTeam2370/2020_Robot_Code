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
public class AutoTurnPID extends PIDCommand {
  /**
   * Creates a new AutoTurnPID.
   */
  //170 degrees is 180 degree turn :-/
  public AutoTurnPID(double setpoint, DriveTrain drive) {
    super(
        // The controller that the command will use
        new PIDController(0.01, 0.008, 0),
        // This should return the measurement
        () -> -DriveTrain.getYaw(),
        // This should return the setpoint (can also be a constant)
        () -> -setpoint,
        // This uses the output
        output -> {DriveTrain.arcadeDrive(0, output);
          // Use the output here
        SmartDashboard.putNumber("Angle", DriveTrain.getYaw());
        });
        getController().setTolerance(setpoint * .05);
        DriveTrain.ahrs.reset();
        // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
