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
import frc.robot.commands.AllShoot;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.LoadBall;
import frc.robot.commands.RunIntake;
import frc.robot.commands.RunIntakeArm;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeArm;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_DriveTrain = new DriveTrain();
  private final Shooter m_Shooter = new Shooter();
  private final Magazine m_magazine = new Magazine();
  private final Indexer m_indexer = new Indexer();
  private final Intake m_intake = new Intake();
  private final IntakeArm m_intakearm = new IntakeArm();

  private final DriveWithJoystick m_autoCommand = new DriveWithJoystick(m_DriveTrain);

  public static Joystick stick = new Joystick(0);
  private static double deadband = 0.01;
  public static JoystickButton A = new JoystickButton(stick, 1);
  public static JoystickButton B = new JoystickButton(stick, 2);
  public static JoystickButton X = new JoystickButton(stick, 3);
  public static JoystickButton Y = new JoystickButton(stick, 4);
  public static JoystickButton LB = new JoystickButton(stick, 5);
  public static JoystickButton RB = new JoystickButton(stick, 6);

  public static double getLxAxis(){
    double raw = stick.getRawAxis(0);
    if(raw>0){
      return Math.abs(raw) < deadband ? 0.0 : -(raw-deadband)/(1-deadband);
    } else{
      return Math.abs(raw) < deadband ? 0.0 : -(raw+deadband)/(1-deadband);
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
    public static double getRyAxis(){
      double raw = stick.getRawAxis(5);
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

    public static double getLeftTrigger(){
      double raw = stick.getRawAxis(2);
      return Math.abs(raw) < deadband ? 0.0 : (raw-deadband)/(1-deadband);
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
    m_DriveTrain.setDefaultCommand(new DriveWithJoystick(m_DriveTrain));
    m_intakearm.setDefaultCommand(new RunIntakeArm(m_intakearm));    
    B.whenPressed(new LoadBall(m_indexer,m_magazine));
    RB.whileHeld(new AllShoot(m_Shooter,m_magazine,m_indexer));
    LB.whileHeld(new RunIntake(m_intake));
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
