/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TurnToPowerCellAuto extends PIDCommand {
  /**
   * Creates a new TurnWithLimelight.
   */
  public TurnToPowerCellAuto(LimeLight l) {
    super(  
        // The controller that the command will use
        new PIDController(0.06, 0.02, 0),
        // This should return the measurement
        () -> LimeLight.getBallXOffset(),
        // This should return the setpoint (can also be a constant)
        () -> 0,
        // This uses the output
        output -> {DriveTrain.arcadeDrive(-.55, MathUtil.clamp(output, -.4, .4));
          // Use the output here
        });
        getController().setTolerance(.25);
        addRequirements(l);
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  public void initialize() {
  }

  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
