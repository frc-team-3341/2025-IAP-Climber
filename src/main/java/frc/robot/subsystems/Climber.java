// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLimitSwitch;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.units.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.LimitSwitchConfig;
import com.revrobotics.spark.config.LimitSwitchConfig.Type;
import com.revrobotics.spark.config.SoftLimitConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

public class Climber extends SubsystemBase {
  /** Creates a new Climber. */
  SparkMax climbSparkMax;

  private SparkLimitSwitch revLimit;


  public SparkLimitSwitch forwardLimit;
  public SparkLimitSwitch reverseLimit;

  public RelativeEncoder encoder;

  public boolean override = false;

  public Climber() {

    climbSparkMax = new SparkMax(Constants.ClimberConstants.extPort, MotorType.kBrushless);
    
    forwardLimit = climbSparkMax.getForwardLimitSwitch(SparkLimitSwitch.Type.kNormallyClosed);
    forwardLimit.enableLimitSwitch(true);

    reverseLimit = climbSparkMax.getReverseLimitSwitch(SparkLimitSwitch.Type.kNormallyClosed);
    reverseLimit.enableLimitSwitch(true);

    encoder = climbSparkMax.getEncoder();

    SparkMax motorE;
        revLimit = motorE.getReverseLimitSwitch();


    encoder.setPositionConversionFactor(Constants.ClimberConstants.climberConversionFactor);
    encoder.setVelocityConversionFactor(Constants.ClimberConstants.velocityConversionFactor);
    


  }

  public boolean getOverride() {
    return override;
  }

  public void setOverride(boolean o) {
    override = o;
  }

  public void extendArmWithPower(double power) {
    climbSparkMax.set(power);
  }

  public void resetEncoder(){
    encoder.setPosition(0);
  }

  public double getEncoderInches(){
    //0.2 inches of lead which means that for every rotation of the lead screw, the hook moves up by 0.2 inches
    //3 motor rotations of the motor is 1 lead screw rotation
    //Accounted for in the conversion factor
    return Units.Inches.convertFrom(getArmPositionInMeters(), Units.Meters);
  }

  public double getArmPositionInMeters() {
    return encoder.getPosition();
  }

  public double getArmVelocityInMeters() {
    return encoder.getVelocity();
  }

  @Override
  public void periodic() {

    if (forwardLimit.isPressed()) {
      encoder.setPosition(0);
    }

    if (reverseLimit.isPressed()) {
      encoder.setPosition(Constants.ClimberConstants.maxExtensionLimit);
    }

    forwardLimit.enableLimitSwitch(!override);
    reverseLimit.enableLimitSwitch(!override);

    SmartDashboard.putBoolean("forward Limit", forwardLimit.isPressed());
    SmartDashboard.putBoolean("reverse Limit", reverseLimit.isPressed());
    SmartDashboard.putNumber("climber current", (int)(climbSparkMax.getOutputCurrent()));
    SmartDashboard.putNumber("climber position", encoder.getPosition());
  }
}