/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Magazine extends SubsystemBase {
  /**
   * Creates a new Magazine.
   */
  public Magazine() {

  }

  public static double magazineSpeed = -.75;

  public static WPI_TalonSRX magazineMotor = new WPI_TalonSRX(Constants.MagazineMotor);

  public static AnalogInput MagazineTopSensor = new AnalogInput(0);

  public static boolean IsBallOnTop(){
    double raw = MagazineTopSensor.getValue();
    boolean status;
    if(raw > 800){
      status = true;
    } else{
      status = false;
    }
    return status;
  }

  @Override
  public void periodic() {
    //SmartDashboard.putNumber("Magazine Top Ball",MagazineTopSensor.getValue());
    // This method will be called once per scheduler run
  }
}
