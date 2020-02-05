/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimeLight extends SubsystemBase {
  /**
   * Creates a new LimeLight.
   */
  public LimeLight() {
//On the test bot, LimeLight is mounted 15.5 inches off the ground and 20 degrees from level.
//LimeLight targeting must be set to "Top".
  }
  private static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  private static NetworkTableEntry tx = table.getEntry("tx");
  private static NetworkTableEntry ty = table.getEntry("ty");
  private static NetworkTableEntry ta = table.getEntry("ta");
private boolean operatorAlign = true;           
  private static double offsetRatio = 15;
  public static double offsetInDegrees = 3.3;

  public static double limelightAngle = 20;
  public static double totalTangent;
  public static double startingAngle;
  public static double adjustedHeight = 82.5;  // 98 - 15.5
  public static double distanceToTarget(){
     return (adjustedHeight) / (totalTangent);
   }

  /* 
     RECALCULATE AFTER MOUNTING LIMELIGHT ON THE ACTUAL ROBOT!
     Gets the distance to the scoring ports with the following equation:
     d = (h2-h1) / tan(a1 + a2)
     where d is the distance to the ports, h1 is the height of the LimeLight, h2 is the height of the Upper Target,
     a1 is the LimeLight's mount angle, and a2 is the angle between the LimeLight and the target. The LimeLight must be fixed in place for this to work.
  */

  /*public static double getAdjustedDistance(){
    double raw = 0.485*distanceToTarget()+84.6;
    return raw;
  }*/

  public double getOffsetDegrees(){
    return offsetInDegrees;
  }
  public static double getLimelightXOffset() {
    double x = tx.getDouble(0.0);
    return x;
  }

  public static double getLimelightYOffset() {
    double y = ty.getDouble(0.0);
    return y;
  }

  public double getLimelightTargetArea() {
    double a = ta.getDouble(0.0);
    return a;
  }

  public boolean getOperatorAllign() {
    return operatorAlign;
  }

  public static double getRotation() {
    // Negetive is Left
    // Positive is Right
    return (getLimelightXOffset() - offsetInDegrees) / offsetRatio;
  }

  public void switchOperatorControl() {
    operatorAlign = !operatorAlign;
  }

  public void setCameraMode() {
    if (operatorAlign) {
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    } else {

      NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
    }
  }

  @Override
  public void periodic() {
    
    startingAngle = getLimelightYOffset() + limelightAngle;
    
    totalTangent = Math.tan(Math.toRadians(startingAngle));
    // This method will be called once per scheduler run
  }
}
