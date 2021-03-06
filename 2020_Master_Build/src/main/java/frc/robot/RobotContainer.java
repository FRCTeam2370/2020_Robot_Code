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
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AlignAndShoot;
import frc.robot.commands.AutoDrivePID;
import frc.robot.commands.AutoShootLoadPos1;
import frc.robot.commands.AutoShootLoadPos3;
import frc.robot.commands.AutoShootReverse;
import frc.robot.commands.Climb;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.IntakeArmAuto;
import frc.robot.commands.MagazineDown;
import frc.robot.commands.MagazineUp;
import frc.robot.commands.MagazineUpShoot;
import frc.robot.commands.MoveIntakeArm;
import frc.robot.commands.PreShotRev;
import frc.robot.commands.ResetEncoders;
import frc.robot.commands.SetLEDs;
import frc.robot.commands.Shoot;
import frc.robot.commands.ShootAndIntake;
import frc.robot.commands.ToggleManualControl;
import frc.robot.commands.TurnToPowerCell;
import frc.robot.commands.TurnWithLimelight;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeArm;
import frc.robot.subsystems.LEDs;
import frc.robot.subsystems.LimeLight;
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
  public Command m_reset = new ResetEncoders(m_DriveTrain);
  private final LimeLight m_limelight = new LimeLight();
  private final Shooter m_Shooter = new Shooter();
  private final Magazine m_magazine = new Magazine();
  private final Indexer m_indexer = new Indexer();
  private final Intake m_intake = new Intake();
  private final IntakeArm m_intakearm = new IntakeArm();
  private final Climber m_climber = new Climber();
  private final LEDs m_leds = new LEDs();
  private AutoDrivePID autodrive = new AutoDrivePID(100000, m_DriveTrain);
  private AutoShootReverse ShootReverse = new AutoShootReverse(m_Shooter, m_magazine, m_indexer,m_limelight, m_DriveTrain, m_intake, m_intakearm);
  private AlignAndShoot ShootItALL = new AlignAndShoot(m_Shooter,m_magazine, m_indexer,m_limelight, m_intake, m_intakearm);
  private AutoShootLoadPos1 Position1 = new AutoShootLoadPos1(m_Shooter, m_magazine, m_indexer,m_limelight, m_DriveTrain, m_intake, m_intakearm);
  private AutoShootLoadPos3 Position3 = new AutoShootLoadPos3(m_Shooter, m_magazine, m_indexer,m_limelight, m_DriveTrain, m_intake, m_intakearm);

  public static SendableChooser<Command> autoChooser = new SendableChooser<>();
    public void addAutoOptions(){
    autoChooser.setDefaultOption("Turn, Shoot, and Reverse", ShootReverse);
    autoChooser.addOption("Go Straight", autodrive);
    autoChooser.addOption("Turn and Shoot", ShootItALL);
    autoChooser.addOption("Turn, Shoot, and Load (Position 1)", Position1);
    autoChooser.addOption("Turn, Shoot, and Load (Position 3)", Position3);
    SmartDashboard.putData("auto", autoChooser);
  }

  //private final DriveWithJoystick m_autoCommand = new DriveWithJoystick(m_DriveTrain);

  public static Joystick stick = new Joystick(0);
  public static Joystick climbStick = new Joystick(1);
  private static double deadband = 0.05;
  public static JoystickButton A = new JoystickButton(stick, 1);
  public static JoystickButton B = new JoystickButton(stick, 2);
  public static JoystickButton X = new JoystickButton(stick, 3);
  public static JoystickButton Y = new JoystickButton(stick, 4);
  public static JoystickButton LB = new JoystickButton(stick, 5);
  public static JoystickButton RB = new JoystickButton(stick, 6);
  public static JoystickButton Select = new JoystickButton(stick, 7);
  public static JoystickButton ClimbSelect = new JoystickButton(climbStick, 7);
  public static JoystickButton ClimbStart = new JoystickButton(climbStick, 8);
  public static JoystickButton ClimbA = new JoystickButton(climbStick, 1);
  public static JoystickButton ClimbB = new JoystickButton(climbStick, 2);
  public static JoystickButton ClimbX = new JoystickButton(climbStick, 3);
  public static JoystickButton ClimbY = new JoystickButton(climbStick, 4);
  public static JoystickButton ClimbRB = new JoystickButton(climbStick, 6);
  public static JoystickButton ClimbLB = new JoystickButton(climbStick, 5);
  public static int getPOV(){
   return stick.getPOV();
  }
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

    public static double getClimbLx(){
      double raw = climbStick.getRawAxis(0);
      if(raw>0){
        return Math.abs(raw) < deadband ? 0.0 : -(raw-deadband)/(1-deadband);
      } else{
        return Math.abs(raw) < deadband ? 0.0 : -(raw+deadband)/(1-deadband);
      }
    }

    public static double getClimbLy(){
      double raw = climbStick.getRawAxis(1);
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

    public static double getClimbRyAxis(){
      double raw = climbStick.getRawAxis(5);
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
    
    public static double getClimbRightTrigger(){
      double raw = climbStick.getRawAxis(3);
      return Math.abs(raw) < deadband ? 0.0 : (raw-deadband)/(1-deadband);
    }

    public static double getClimbLeftTrigger(){
      double raw = climbStick.getRawAxis(2);
      return Math.abs(raw) < deadband ? 0.0 : (raw-deadband)/(1-deadband);
    }

    public static double getClimbBothTrigger(){
      double left = getClimbLeftTrigger();
      double right = getClimbRightTrigger();
      return right-left;
    }

    public static double getBothTrigger(){
      double left = getLeftTrigger();
      double right = getRightTrigger();
      return right-left;
    }

    public static double getClimbyDriveyBoi(){
      boolean rawRB = ClimbRB.get();
      boolean rawLB = ClimbLB.get();      
      double output;
      if(rawRB){
        output = -0.45;
      } else if(rawLB){
        output = 0.45;
      } else{
        output = 0;
      }
      return output;
    }

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    addAutoOptions();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    m_DriveTrain.setDefaultCommand(new DriveWithJoystick(m_DriveTrain));
    m_intakearm.setDefaultCommand(new MoveIntakeArm(m_intakearm));
    //ENABLE CLIMBER PLEASE >:-|
    m_magazine.setDefaultCommand(new MagazineUp(m_magazine));
    m_climber.setDefaultCommand(new Climb(m_climber));
    m_leds.setDefaultCommand(new SetLEDs(m_leds));
    //m_climber.setDefaultCommand(new ClimberToNextSetpoint(m_climber));
    //m_Shooter.setDefaultCommand(new BaseSpeedAdjust(m_Shooter));
    //m_limelight.setDefaultCommand(new LimelightDistance(m_limelight));
    //B.whileHeld(new MagazineUpShoot(m_magazine));
    Y.whileHeld(new TurnToPowerCell(m_limelight));
    A.toggleWhenPressed(new PreShotRev(m_Shooter, m_magazine));
    X.whileHeld(new TurnWithLimelight(m_limelight));
    RB.whileHeld(new ShootAndIntake(m_Shooter,m_magazine,m_indexer,m_intake,m_intakearm, m_limelight));
    LB.toggleWhenPressed(new IntakeArmAuto(m_intake, m_intakearm));
    ClimbA.whileHeld(new MagazineDown(m_magazine));
    //ClimbB.whenPressed(new ClimberHang());
    ClimbX.whileHeld(new Shoot(m_Shooter));
    ClimbY.whileHeld(new MagazineUpShoot(m_magazine));
    ClimbSelect.whenPressed(new ToggleManualControl());

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoChooser.getSelected();
  }
}
