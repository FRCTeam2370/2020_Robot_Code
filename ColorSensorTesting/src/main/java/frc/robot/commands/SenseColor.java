/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SenseColor extends CommandBase {
  private final I2C.Port I2CPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_ColorSensor = new ColorSensorV3(I2CPort);
  private final ColorMatch m_ColorMatcher = new ColorMatch();
  
  private final Color blue = ColorMatch.makeColor(0, 1, 1);
  private final Color green = ColorMatch.makeColor(0, 1, 0);
  private final Color red = ColorMatch.makeColor(1, 0, 0);
  private final Color yellow = ColorMatch.makeColor(1, 1, 0);

  /**
   * Creates a new SenseColor.
   */
  public SenseColor() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_ColorMatcher.addColorMatch(blue);
    m_ColorMatcher.addColorMatch(green);
    m_ColorMatcher.addColorMatch(red);
    m_ColorMatcher.addColorMatch(yellow); 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Color SensedColor = m_ColorSensor.getColor();
    String colorName;
    ColorMatchResult match = m_ColorMatcher.matchClosestColor(SensedColor);

    if (match.color == blue) {
      colorName = "Blue";
    } else if (match.color == red) {
      colorName = "Red";
    } else if (match.color == green) {
      colorName = "Green";
    } else if (match.color == yellow) {
      colorName = "Yellow";
    } else {
      colorName = "Correct Color not Detected";
    }

    //For debugging:
    /*SmartDashboard.putNumber("R", SensedColor.red);
    SmartDashboard.putNumber("G", SensedColor.green);
    SmartDashboard.putNumber("B", SensedColor.blue);
    */
    SmartDashboard.putNumber("Match Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorName);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
