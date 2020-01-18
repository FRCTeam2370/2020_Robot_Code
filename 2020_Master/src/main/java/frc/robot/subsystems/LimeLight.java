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

  }
  private static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  private static NetworkTableEntry tx = table.getEntry("tx");
  private static NetworkTableEntry ty = table.getEntry("ty");
  private static NetworkTableEntry ta = table.getEntry("ta");
  private boolean operatorAlign = true; 
  private static double offsetRatio = 15;
  public static double offsetInDegrees = 3.3;

  public double getOffsetDegrees(){
    return offsetInDegrees;
  }
  public double getLimelightXOffset() {
    double x = tx.getDouble(0.0);
    return x;
  }

  public double getLimelightYOffset() {
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

  public double getRotation() {
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
    // This method will be called once per scheduler run
  }
}
