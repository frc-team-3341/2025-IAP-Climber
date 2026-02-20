// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

// public static final String ClimberConstants = null;



public static class ClimberConstants {
  public static final int extPort = 20;
  public static final double climberConversionFactor = (1/3.0) * Units.inchesToMeters(0.2);
  public static final double velocityConversionFactor = (1/3.0)/60.0 * Units.inchesToMeters(0.2);
  public static final double maxExtensionLimit = Units.inchesToMeters(26.55);

  public static final double maxExtensionVelocity = 0.1;

  public static final double climbkP = 0.01;
  public static final double climbkI = 0.0;
  public static final double climbkD = 0.0;

  }


}
