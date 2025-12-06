
package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

import frc.robot.commands.*;

import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj.PowerDistribution.*;

public class RobotContainer {

 

  // ---------------------- END OF CONFIG SECTION --------------------------

  // Xbox + an additional one for PC use
  private final Joystick drivingXbox = new Joystick(0);
  private final Joystick simulationJoy = new Joystick(1);
  private final static Joystick mechanismJoy = new Joystick(2);

  // Chooser for testing teleop commands
  

  // Define axises for using joystick
  private final int translationAxis = XboxController.Axis.kLeftY.value; // Axis ID: 1
  private final int strafeAxis = XboxController.Axis.kLeftX.value; // Axis ID: 0
  private final int rotationAxis = XboxController.Axis.kRightX.value; // Axis ID: 4

 
  // Empty Climber object
  private Climber climber;
  private ClimberStateMachine climberstate;

  // Field centric toggle - true for field centric, false for robot centric
  private boolean fieldCentricToggle = true;


  public RobotContainer() {
    
    // Construct all other things
  }

//    moveBackIntoAmp = new MoveBackIntoAmp(swerve);
//    JoystickButton moveButton = new JoystickButton(drivingXbox, XboxController.Button.kY.value);

    JoystickButton backupSimpleButton = new JoystickButton(drivingXbox, XboxController.Button.kY.value);

    JoystickButton ninetyDegreeRotationButton = new JoystickButton(drivingXbox, XboxController.Button.kB.value);

    JoystickButton resetNavXButton = new JoystickButton(drivingXbox, XboxController.Button.kLeftBumper.value);

  public void configureClimber() {
    climber = new Climber(); // Climber CAN ID was inactive, causing a timeout
    climberstate = new ClimberStateMachine(climber);
    JoystickButton climberControl = new JoystickButton(mechanismJoy, 17);
    //Throttle switching the power hasn't been updated yet. Should test code before implementing
  
  // need to do button mapping for the controller
  
  
  }
}
