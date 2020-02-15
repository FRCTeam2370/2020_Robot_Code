/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  /**
   * Creates a new Climber.
   */
public static WPI_TalonSRX ClimbMotorLeft = new WPI_TalonSRX(Constants.ClimbMotorLeft);
public static WPI_TalonSRX ClimbMotorRight = new WPI_TalonSRX(Constants.ClimbMotorRight);
private static int timeout = 30;
private static int slotIdx = 0;
public static double kF = 0;//.0522
public static double kP = 0;
public static double kI = 0;
public static double kD = 0;
public static double TopPoint = 4000;
public static double BottomPoint = 0;
public static boolean climbing = false;

public static double getDifference(){
  double raw1 = ClimbMotorLeft.getSensorCollection().getQuadraturePosition();
  double raw2 = ClimbMotorRight.getSensorCollection().getQuadraturePosition();
  return raw1-raw2;
}

public static void ResetEncoders(){
  //ClimbMotorLeft.getSensorCollection().setQuadraturePosition(0, timeout);
  ClimbMotorRight.getSensorCollection().setQuadraturePosition(0, timeout);
}


public static void configPID(){
  //Configure Motor 1
  /*ClimbMotorLeft.configFactoryDefault();
  ClimbMotorLeft.setSensorPhase(true);
  ClimbMotorLeft.configNominalOutputForward(0, timeout);
  ClimbMotorLeft.configNominalOutputReverse(0, timeout);
  ClimbMotorLeft.configPeakOutputForward(1, timeout);
  ClimbMotorLeft.configPeakOutputReverse(-1, timeout);
  ClimbMotorLeft.config_kF(slotIdx, kF, timeout);
  ClimbMotorLeft.config_kP(slotIdx, kP, timeout);
  ClimbMotorLeft.config_kI(slotIdx, kI, timeout);
  ClimbMotorLeft.config_kD(slotIdx, kD, timeout);
*/
  //Configure Motor 2
  ClimbMotorRight.configFactoryDefault();
  ClimbMotorRight.setSensorPhase(true);
  ClimbMotorRight.configNominalOutputForward(0, timeout);
  ClimbMotorRight.configNominalOutputReverse(0, timeout);
  ClimbMotorRight.configPeakOutputForward(1, timeout);
  ClimbMotorRight.configPeakOutputReverse(-1, timeout);
  ClimbMotorRight.config_kF(slotIdx, kF, timeout);
  ClimbMotorRight.config_kP(slotIdx, kP, timeout);
  ClimbMotorRight.config_kI(slotIdx, kI, timeout);
  ClimbMotorRight.config_kD(slotIdx, kD, timeout);
}

public static void setMotorsWithPID(double setpoint){
  configPID();
  //ClimbMotorLeft.set(ControlMode.Position, setpoint);
  ClimbMotorRight.set(ControlMode.Position, setpoint);
}

public static void setMotorsNoPID(double speed){
  ClimbMotorLeft.set(ControlMode.PercentOutput, speed);
  ClimbMotorRight.set(ControlMode.PercentOutput, speed);
}

public static void setMotorsAutoAdjust(double speed,double error,double multiplier){
  //ClimbMotorLeft.set(ControlMode.PercentOutput, speed + (error * multiplier));
  ClimbMotorRight.set(ControlMode.PercentOutput, speed - (error * multiplier));
}

public static void gyroAdjust(){
  double angle = DriveTrain.getXRot();
  //ClimbMotorLeft.set(ControlMode.PercentOutput, angle);
  ClimbMotorRight.set(ControlMode.PercentOutput, -angle);
}



  public Climber() {
    ResetEncoders();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
