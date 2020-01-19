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
//import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.IntakeDeploy;
import frc.robot.commands.IntakeUp;
//import frc.robot.commands.Shoot;
//import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final Shooter m_Shooter = new Shooter();

  //private final Shoot m_autoCommand = new Shoot(m_Shooter);

  private final Intake m_intake = new Intake();

  private static Joystick stick = new Joystick(0);
  private static double deadband = 0.1;

  public static JoystickButton A = new JoystickButton(stick, 1);
  public static JoystickButton X = new JoystickButton(stick, 3);
  public static JoystickButton Y = new JoystickButton(stick, 4);

  

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

    public static boolean getXButton(){
      return X.get();
    }

    public static boolean getYButton(){
      return Y.get();
    }


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
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
    //A.toggleWhenPressed(new DriveWithLimelight());
    X.whenReleased(new IntakeUp());
    X.whileHeld(new IntakeDeploy());
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
 // public Command getAutonomousCommand() {
    // An Shoot will run in autonomous
    //return m_autoCommand;
  }

