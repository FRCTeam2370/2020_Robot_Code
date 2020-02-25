/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  public Shooter() {
    configPID();
    
  }
  private static LimeLight m_limelight = new LimeLight();
  public static WPI_TalonFX shooterMotor = new WPI_TalonFX(Constants.shooterMotor1);
  //public static WPI_TalonFX shooterMotor2 = new WPI_TalonFX(Constants.shooterMotor2);
  public static AnalogInput ShooterSensor = new AnalogInput(3);
  public static String ManualToggle = "none";
  private static int timeout = 30;
  private static int slotIdx = 0;
  public static double kF = .0522;//.0522
  public static double kP = 0.01;
  public static double kI = 0.000005;//000005;
  public static double kD = 0.15;
  public static double speed = 2485;
  //don't touch these (\/) unless you know what you are doing
  public static double StartingBaseSpeed = 1450;
  public static double BaseSpeed = 1450;
  public static double scaling = 1.28;
  private boolean isSet;

  public static double getSpeed(){
    double raw = (shooterMotor.getSensorCollection().getIntegratedSensorVelocity() /2048)*600;
    return raw;
  }
  
  public static void configPID(){
    //Configure Motor 1
    shooterMotor.configFactoryDefault();
    shooterMotor.setSensorPhase(true);
    shooterMotor.configNominalOutputForward(0, timeout);
    shooterMotor.configNominalOutputReverse(0, timeout);
    shooterMotor.configPeakOutputForward(1, timeout);
    shooterMotor.configPeakOutputReverse(-1, timeout);
    shooterMotor.config_kF(slotIdx, kF, timeout);
    shooterMotor.config_kP(slotIdx, kP, timeout);
    shooterMotor.config_kI(slotIdx, kI, timeout);
    shooterMotor.config_kD(slotIdx, kD, timeout);

    //Configure Motor 2
    /*shooterMotor2.configFactoryDefault();
    shooterMotor2.setSensorPhase(true);
    shooterMotor.configNominalOutputForward(0, timeout);
    shooterMotor2.configNominalOutputReverse(0, timeout);
    shooterMotor.configPeakOutputForward(1, timeout);
    shooterMotor.configPeakOutputReverse(-1, timeout);
    shooterMotor2.config_kF(slotIdx, kF, timeout);
    shooterMotor2.config_kP(slotIdx, kP, timeout);
    shooterMotor2.config_kI(slotIdx, kI, timeout);
    shooterMotor2.config_kD(slotIdx, kD, timeout);*/
  }



  @Override
  public void periodic() {
    //if(!ManualToggle){
    speed =Math.min(BaseSpeed * Math.pow(m_limelight.distanceToTarget(), 0.0525) + 0.5 * Math.pow(m_limelight.distanceToTarget(), scaling), 6100); // 6303 + (-95.8 * m_limelight.distanceToTarget()) + (0.8 * Math.pow(m_limelight.distanceToTarget(), 2)) + (-0.0028 * Math.pow(m_limelight.distanceToTarget(), 3)) + (0.00000354 * Math.pow(m_limelight.distanceToTarget(), 4));
    //==} else{
    //speed = 3000 * RobotContainer.getRightTrigger();
    //}
    if(RobotContainer.getPOV() == 0 && !isSet){
      Shooter.BaseSpeed+=5;
      isSet = true;
    }
    if(RobotContainer.getPOV() == 180 && !isSet){
      Shooter.BaseSpeed-=5;
      isSet = true;
    }
    if(RobotContainer.getPOV() == -1){
      isSet = false;
    }
  
    SmartDashboard.putString("ManualMode", ManualToggle);
    SmartDashboard.putNumber("Speed", speed);
    SmartDashboard.putNumber("Original Base Speed", StartingBaseSpeed);
    SmartDashboard.putNumber("Current Base Speed", BaseSpeed);
    SmartDashboard.putNumber("ActualSpeed", getSpeed());
    SmartDashboard.putNumber("sensorValue", ShooterSensor.getValue());
    SmartDashboard.putNumber("POV", RobotContainer.getPOV());
    // This method will be called once per scheduler run
  }
}
