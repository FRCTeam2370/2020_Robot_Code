/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Indexer extends SubsystemBase {
  /**
   * Creates a new Indexer.
   */
  public static WPI_TalonSRX IndexerMotor = new WPI_TalonSRX(Constants.IndexerMotor);
  public static SupplyCurrentLimitConfiguration IndexerLimit = new SupplyCurrentLimitConfiguration(true, 3, 0.1, 0);
   public Indexer() {
    IndexerMotor.configSupplyCurrentLimit(IndexerLimit);
  }

  public static double indexerSpeed = .8;

  

  public static AnalogInput PanSensor = new AnalogInput(1);

  public static boolean IsBallInPan(){
    double raw = PanSensor.getValue();
    boolean status;
    if(raw > 1200){
      status = true;
    } else{
      status = false;
    }
    return status;
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Indexer Current", IndexerMotor.getSupplyCurrent());
    // This method will be called once per scheduler run
  }
}
