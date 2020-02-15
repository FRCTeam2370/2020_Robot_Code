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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimeLight extends SubsystemBase {
  /**
   * Creates a new LimeLight.
   */
  public LimeLight() {

  }
  public static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  public static NetworkTableEntry tx = table.getEntry("tx");
  public static NetworkTableEntry ty = table.getEntry("ty");
  public NetworkTableEntry ta = table.getEntry("ta");
  public NetworkTableEntry LED = table.getEntry("ledMode");
  public boolean operatorAlign = true; 
  public static double offsetRatio = 15;
  public static double offsetInDegrees = 3.3;
  public SmartDashboard sDashboard;
  
  public static double limelightAngle = 25;
  public static double totalTangent;
  public static double startingAngle;
  public static double adjustedHeight = 64.5;  // 98 - 33.5
  public static double distanceToTarget(){
     return (adjustedHeight) / (totalTangent);
   }

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

  public void setLEDState(double value){
    LED.setNumber(value);
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
  public void sendToDashboard(){
    SmartDashboard.putString("IsThisConnected", NetworkTableInstance.getDefault().getTable("limelight").getSubTables().toString()); 
    SmartDashboard.putNumber("Limelight X", NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0.0));
    //SmartDashboard.putNumber("Limelight Y", getLimelightYOffset());
    //SmartDashboard.putNumber("Limelight A", getLimelightTargetArea());
  }

  @Override
  public void periodic() {
    startingAngle = getLimelightYOffset() + limelightAngle;
    totalTangent = Math.tan(Math.toRadians(startingAngle));
    
    sendToDashboard();
    // This method will be called once per scheduler run
  }
}
