/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeArm extends SubsystemBase {
  /**
   * Creates a new IntakeArm.
   */
  public IntakeArm() {
    IntakeArmMotor.configFactoryDefault();
    IntakeArmMotor.setInverted(false);
    IntakeArmMotor.configForwardSoftLimitThreshold(3000);
    IntakeArmMotor.configForwardSoftLimitEnable(true);
    IntakeArmMotor.configReverseSoftLimitThreshold(2000);
    IntakeArmMotor.configReverseSoftLimitEnable(true);
    IntakeArmMotor.setSensorPhase(true);
    IntakeArmMotor.setNeutralMode(NeutralMode.Coast);
    IntakeArmMotor.configSupplyCurrentLimit(Arm);
  }
  public static DigitalInput LimitSwitch = new DigitalInput(0);
  public static SupplyCurrentLimitConfiguration Arm = new SupplyCurrentLimitConfiguration(true, 10, 0, 0);
  public static WPI_TalonSRX IntakeArmMotor = new WPI_TalonSRX(Constants.IntakeArmMotor); 
  public static double speed = .65;

  @Override
  public void periodic() {
    //if(IntakeArmMotor.getSensorCollection().getQuadraturePosition() < 0){
    //  IntakeArmMotor.getSensorCollection().setQuadraturePosition(IntakeArmMotor.getSensorCollection().getQuadraturePosition() * -1, 10);
    //}
    if(!LimitSwitch.get()){
    IntakeArm.IntakeArmMotor.getSensorCollection().setQuadraturePosition(0, 10);
  }
  SmartDashboard.putBoolean("limit switch",!LimitSwitch.get());
  //SmartDashboard.putNumber("ArmPosition",IntakeArmMotor.getSensorCollection().getQuadraturePosition());
  //SmartDashboard.putNumber("Arm Current", IntakeArmMotor.getSupplyCurrent());
  //SmartDashboard.putNumber("Pan Sensor",Indexer.PanSensor.getValue());
  // This method will be called once per scheduler run
  }
}
