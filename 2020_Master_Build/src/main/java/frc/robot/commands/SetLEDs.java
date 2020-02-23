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
import frc.robot.RobotContainer;
import frc.robot.subsystems.LEDs;
import frc.robot.subsystems.LimeLight;

public class SetLEDs extends CommandBase {
  /**
   * Creates a new SetLEDs.
   */
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
    //boolean climbing = RobotContainer.getClimbRyAxis() != 0 || RobotContainer.getClimbBothTrigger() !=0;
    //boolean firing = RobotContainer.RB.get();
    //boolean targeting = LimeLight.operatorAlign;
    //int getHighestSlot = 0;
	/*if(climbing){
      LEDs.setAll(227, 157, 18);
    } else if(Timer.getMatchTime()<15 && RobotState.isOperatorControl()){
      LEDs.setAll(255, 0, 0);
      Timer.delay(0.5);
      LEDs.setAll(255, 255, 255);
    }
    else if (Timer.getMatchTime()<30 && RobotState.isOperatorControl()){
      LEDs.setAll(255, 0, 0);
    }
    else if (firing){
      LEDs.setAll(0, 0, 255);
      LEDs.SweepAll(0, 255, 0, 1);
    }
    else if (targeting){
      LEDs.setAll(255, 0, 255);
    }
    else {*/
      //if(RobotContainer.ClimbA.get()){
      LEDs.setAll(0, 0, 255);
      //}else{
      //LEDs.SweepAll(0, 0, 255,5);
      //}
      //LEDs.SetsegmentFromTop(getHighestSlot, 0, 255, 0);
    //}
    LEDs.ledLights.setData(LEDs.m_ledBuffer);
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
