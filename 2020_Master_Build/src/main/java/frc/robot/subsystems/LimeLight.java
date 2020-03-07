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
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class LimeLight extends SubsystemBase {
  /**
   * Creates a new LimeLight.
   */
  public LimeLight() {

  }
  public static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  public static NetworkTable BallTable = NetworkTableInstance.getDefault().getTable("limelight-ballcam");
  public static NetworkTableEntry Balltx = BallTable.getEntry("tx");
  public static NetworkTableEntry Ballty = BallTable.getEntry("ty");
  public static NetworkTableEntry tx = table.getEntry("tx");
  public static NetworkTableEntry ty = table.getEntry("ty");
  public NetworkTableEntry ta = table.getEntry("ta");
  public static NetworkTableEntry LED = table.getEntry("ledMode");
  public static NetworkTableEntry Camera = table.getEntry("camMode");
  public static boolean operatorAlign = false; 
  public static double offsetRatio = 15;
  public static double offsetInDegrees = 3.3;
  public SmartDashboard sDashboard;
  
  public static double limelightAngle = 25;
  public static double totalTangent;
  public static double startingAngle;
  public static double adjustedHeight = 64.5;  // 98 - 33.5
  public double distanceToTarget(){
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

  public static double getBallXOffset() {
    double x = Balltx.getDouble(0.0);
    return x;
  }

 public static double getBallYOffset() {
    double y =  ty.getDouble(0.0);
    return y;
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
    if (operatorAlign == false) {
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("snapshot").setNumber(0);
    } else {  
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(1);
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("snapshot").setNumber(1);
    }
  }
  public void sendToDashboard(){
    SmartDashboard.putString("IsThisConnected", NetworkTableInstance.getDefault().getTable("limelight").getSubTables().toString()); 
    SmartDashboard.putNumber("Limelight X", NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0.0));
    SmartDashboard.putNumber("Limelight Y", getLimelightYOffset());
    SmartDashboard.putNumber("Limelight Ball X", getBallXOffset());
    //SmartDashboard.putBoolean("Operator Align", operatorAlign);
    //SmartDashboard.putNumber("Limelight A", getLimelightTargetArea());
  }

  @Override
  public void periodic() {

    startingAngle = getLimelightYOffset() + limelightAngle;
    totalTangent = Math.tan(Math.toRadians(startingAngle));
    
    if(RobotState.isAutonomous() && !RobotState.isDisabled() || RobotContainer.RB.get() || RobotContainer.X.get() || RobotContainer.A.get()){
      operatorAlign = true;
      
    } else{
      operatorAlign = false;
    }

    setCameraMode();
    sendToDashboard();
    // This method will be called once per scheduler run
  }
}
