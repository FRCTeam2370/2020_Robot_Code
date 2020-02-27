/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.RobotContainer;
import frc.robot.subsystems.LEDs;
import frc.robot.subsystems.LimeLight;

public class SetLEDs extends CommandBase {
  /**
   * Creates a new SetLEDs.
   */
  private boolean Flash; 
  public SetLEDs(LEDs l) {
    addRequirements(l);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Math.IEEEremainder(Timer.getMatchTime(), 2) == 0){
      Flash = false;
    } else{
      Flash = true;
    }
    boolean climbing = RobotContainer.getClimbRyAxis() != 0 || RobotContainer.getClimbBothTrigger() !=0;
    boolean firing = LEDs.Shooting;
    boolean targeting = LimeLight.operatorAlign;
    //int getHighestSlot = 0;
	if(climbing){
      LEDs.setAll(150, 90, 0);
    } else if(Timer.getMatchTime()<15 && RobotState.isOperatorControl()){
      if(Flash){
      LEDs.setAll(0, 100, 0);
      } else{
        LEDs.setAll(100, 100, 100);
      }
    } else if (Timer.getMatchTime()<30 && RobotState.isOperatorControl()){
      LEDs.setAll(200, 0, 0);
    } else if (firing){
      LEDs.setAll(0, 0, 100);
    } else if (targeting){
      LEDs.setAll(255, 0, 255);
    } else{
      LEDs.setAll(0, 100, 0);
    }

    //else {*/
      //if(RobotContainer.ClimbA.get())
      //}else{
      //LEDs.SweepAll(0, 0, 255,5);
      //}
      //LEDs.SetsegmentFromTop(getHighestSlot, 0, 255, 0);
    //}
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    LEDs.setAll(0, 0, 0);
    LEDs.activate();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
