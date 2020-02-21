/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeArm;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoShootLoadPos1 extends SequentialCommandGroup {
  /**
   * Creates a new AutoShootLoadPos1.
   */
  public AutoShootLoadPos1(Shooter s, Magazine m, Indexer i, DriveTrain d, Intake in, IntakeArm ia) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(/*new AlignAndShoot(s, m, i),*/new AutoTurnPID(180, d),/* new DoTheIntakeThingRealGood(in, ia),*/ new AutoDrivePID(20000, d)); 
  }
}
