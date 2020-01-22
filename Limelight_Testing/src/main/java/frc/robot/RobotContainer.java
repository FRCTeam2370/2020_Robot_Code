/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.TurnWithLimelight;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private final DriveTrain m_driveTrain = new DriveTrain();

  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public static Joystick stick = new Joystick(0);
  private static double deadband = 0.1;
  public static JoystickButton A = new JoystickButton(stick, 1);
  
    public static double getLxAxis(){
    double raw = stick.getRawAxis(0);
    if(raw>0){
      return Math.abs(raw) < deadband ? 0.0 : (raw-deadband)/(1-deadband);
    } else{
      return Math.abs(raw) < deadband ? 0.0 : (raw+deadband)/(1-deadband);
    }
  }
    public static double getLyAxis(){
      double raw = stick.getRawAxis(1);
      if(raw>0){
        return Math.abs(raw) < deadband ? 0.0 : (raw-deadband)/(1-deadband);
      } else{
        return Math.abs(raw) < deadband ? 0.0 : (raw+deadband)/(1-deadband);
      }
    }  
    public static double getRightTrigger(){
      double raw = stick.getRawAxis(3);
      return Math.abs(raw) < deadband ? 0.0 : (raw-deadband)/(1-deadband);
    }

    public static boolean getAButton(){
      return A.get();
    }


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_driveTrain.setDefaultCommand(new DriveWithJoystick(m_driveTrain));
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    A.toggleWhenPressed(new TurnWithLimelight());
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
