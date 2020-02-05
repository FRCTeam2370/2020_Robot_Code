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
public static WPI_TalonSRX ClimbMotor1 = new WPI_TalonSRX(Constants.ClimbMotor1);
public static WPI_TalonSRX ClimbMotor2 = new WPI_TalonSRX(Constants.ClimbMotor2);
private static int timeout = 30;
private static int slotIdx = 0;
public static double kF = 0;//.0522
public static double kP = 0;
public static double kI = 0;
public static double kD = 0;
public static double TopPoint = 4000;
public static double BottomPoint = 0;

public static double getDifference(){
  double raw1 = ClimbMotor1.getSensorCollection().getQuadraturePosition();
  double raw2 = ClimbMotor2.getSensorCollection().getQuadraturePosition();
  return raw1-raw2;
}

public static void ResetEncoders(){
  ClimbMotor1.getSensorCollection().setQuadraturePosition(0, timeout);
  ClimbMotor2.getSensorCollection().setQuadraturePosition(0, timeout);
}


public static void configPID(){
  //Configure Motor 1
  ClimbMotor1.configFactoryDefault();
  ClimbMotor1.setSensorPhase(true);
  ClimbMotor1.configNominalOutputForward(0, timeout);
  ClimbMotor1.configNominalOutputReverse(0, timeout);
  ClimbMotor1.configPeakOutputForward(1, timeout);
  ClimbMotor1.configPeakOutputReverse(-1, timeout);
  ClimbMotor1.config_kF(slotIdx, kF, timeout);
  ClimbMotor1.config_kP(slotIdx, kP, timeout);
  ClimbMotor1.config_kI(slotIdx, kI, timeout);
  ClimbMotor1.config_kD(slotIdx, kD, timeout);

  //Configure Motor 2
  ClimbMotor2.configFactoryDefault();
  ClimbMotor2.setSensorPhase(true);
  ClimbMotor2.configNominalOutputForward(0, timeout);
  ClimbMotor2.configNominalOutputReverse(0, timeout);
  ClimbMotor2.configPeakOutputForward(1, timeout);
  ClimbMotor2.configPeakOutputReverse(-1, timeout);
  ClimbMotor2.config_kF(slotIdx, kF, timeout);
  ClimbMotor2.config_kP(slotIdx, kP, timeout);
  ClimbMotor2.config_kI(slotIdx, kI, timeout);
  ClimbMotor2.config_kD(slotIdx, kD, timeout);
}

public static void setMotorsWithPID(double setpoint){
  configPID();
  ClimbMotor1.set(ControlMode.Position, setpoint);
  ClimbMotor2.set(ControlMode.Position, setpoint);
}

public static void setMotorsAutoAdjust(double speed,double error,double multiplier){
  ClimbMotor1.set(ControlMode.PercentOutput, speed + (error * multiplier));
  ClimbMotor2.set(ControlMode.PercentOutput, speed - (error * multiplier));
}

public static void gyroAdjust(){
  double angle = DriveTrain.getXRot();
  ClimbMotor1.set(ControlMode.PercentOutput, angle);
  ClimbMotor2.set(ControlMode.PercentOutput, -angle);
}



  public Climber() {
    ResetEncoders();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
