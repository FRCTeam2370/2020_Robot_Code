/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class Shooter extends PIDSubsystem {
  /**
   * Creates a new Shooter.
   */
  public WPI_TalonFX ShooterMotor = new WPI_TalonFX(10);
  
  public Shooter() {
    super(
        // The PIDController used by the subsystem
        new PIDController(0, 0, 0));
  }

  @Override
  public void useOutput(double output, double setpoint) {
    ShooterMotor.pidWrite(output+setpoint); 
     // Use the output here
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return ShooterMotor.getSensorCollection().getIntegratedSensorVelocity();
  }
}
