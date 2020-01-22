/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static int shooterMotorLeft = 0;
    public static int shooterMotorRight = 3;
    public static int DriveMotorLeft = 16;
    public static int DriveMotorRight = 20;
    public static int DriveMotorLeft2 = 22;
    public static int DriveMotorRight2 = 11;
    
    public static int IntakeMotor = 4;
    public static int WheelSpinnerMotor = 5;
    public static int MagazineSensor1 = 0;
    public static int MagazineSensor2 = 1;
    public static int MagazineSensor3 = 2;
    public static int MagazineSensor4 = 3;
    public static int MagazineSensor5 = 4;
    public static int MagazineMotor = 6;
    public static double driveTrainP = 0.0005;
    public static double driveTrainI = 0;
    public static double driveTrainD = 0;
}
