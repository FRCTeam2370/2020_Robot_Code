/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDs extends SubsystemBase {
  /**
   * Creates a new LEDs.
   */
   public LEDs() {
    ledLights.setLength(m_ledBuffer.getLength());
     ledLights.start();
   ledLights.setData(m_ledBuffer);
  }

  public static AddressableLEDBuffer m_ledBuffer = new AddressableLEDBuffer(60);
  public static AddressableLED ledLights = new AddressableLED(12);

  public static int segments = 5;


  public static int getLength(){
    int raw = m_ledBuffer.getLength();
    return raw;
  }

  public static int getSideLength(){
    int raw = getLength();
    return raw/2;
  }

  public static int segmentLength(){
    int raw = getSideLength();
    return raw/segments;
  }

  public static void setBothSides(int index, int r, int g, int b){
    m_ledBuffer.setRGB(index, r, g, b);
    m_ledBuffer.setRGB(getLength()+1-index, r, g, b);
  }


  public static void setAll(int Red,int Green,int Blue){
    for(var i = 0; i<m_ledBuffer.getLength()/2; i++){
      setBothSides(i, Red, Green, Blue);
    }
  }

  public static void SweepAll(int Red,int Green,int Blue,double Delay){
    for(var i = 0; i<m_ledBuffer.getLength()/2; i++){
      setBothSides(i, Red, Green, Blue);
    }
    Timer.delay(Delay);
  }

  public static void Setsegment(int SegmentNumber, int Red, int Green, int Blue){
    for(var i = (SegmentNumber*segmentLength())-segmentLength(); i<SegmentNumber*segmentLength(); i++){
      setBothSides(i, Red, Green, Blue);
    }
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
