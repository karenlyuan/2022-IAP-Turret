// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turret extends SubsystemBase {

  private final WPI_TalonSRX motor;
  /** Creates a new Turret. */
  public Turret() {
    motor = new WPI_TalonSRX(Constants.DriveTrainPorts.LeftDriveTalonPort);
    motor.configFactoryDefault();
    motor.setInverted(false);
    motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
  }

  public void setAngle(double angle){
    motor.setSelectedSensorPosition(angle);
  }

  public double getAngle(){
    return motor.getSelectedSensorPosition() * 360 / (4096.0);
  }

  public void spin(double speed){
    motor.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Angle: ", getAngle());
  }
}
