/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
  
public class Magazine extends SubsystemBase {
  /**
   * Creates a new Magazine.
   */
  public Magazine() {

  }

  public static double speed = 0.3;
  public static WPI_TalonSRX MagazineMotor = new WPI_TalonSRX(Constants.MagazineMotor);
  public static AnalogInput sensor1 = new AnalogInput(Constants.MagazineSensor1);
  public static AnalogInput sensor2 = new AnalogInput(Constants.MagazineSensor2);
  public static AnalogInput sensor3 = new AnalogInput(Constants.MagazineSensor3);
  public static AnalogInput sensor4 = new AnalogInput(Constants.MagazineSensor4);
  public static AnalogInput sensor5 = new AnalogInput(Constants.MagazineSensor5);
  public static boolean slot1;
  public static boolean slot2;
  public static boolean slot3;
  public static boolean slot4;
  public static boolean slot5;
  public static double minValue = 1.8;

  public static int getHighestSlot() {
    if (slot5) {
      return 5;
    } else if (slot4) {
      return 4;
    } else if (slot3) {
      return 3;
    } else if (slot2) {
      return 2;
    } else if (slot1) {
      return 1;
    } else{
      return 0;
    }
  }

  public static void RunMagazine(){
    MagazineMotor.set(ControlMode.PercentOutput, speed);
  }

  public static void stopMagazine(){
    MagazineMotor.stopMotor();
  }

  public static void addBall(int slot){
    while(getHighestSlot()!=slot){
      RunMagazine();
    }
  }



  @Override
  public void periodic() {
    slot1 = sensor1.getAverageVoltage() > minValue;
    slot2 = sensor2.getAverageVoltage() > minValue;
    slot3 = sensor3.getAverageVoltage() > minValue;
    slot4 = sensor4.getAverageVoltage() > minValue;
    slot5 = sensor5.getAverageVoltage() > minValue;
    // This method will be called once per scheduler run
  }
}
