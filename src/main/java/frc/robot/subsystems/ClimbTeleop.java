package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;

public class ClimbTeleop {

    private final XboxController controller;

    public ClimbTeleop(XboxController controller) {
        this.controller = controller;
    }

    public void run() {

        // a for Hold
        if (controller.getRawButtonPressed(XboxController.Button.kA.value)) {
            hold();
        }

        // y for Hook
        if (controller.getRawButtonPressed(XboxController.Button.kY.value)) {
            hook();
        }

        // x for Retract
        if (controller.getRawButtonPressed(XboxController.Button.kX.value)) {
            retract();
        }

        // b for Extend
        if (controller.getRawButtonPressed(XboxController.Button.kB.value)) {
            extend();
        }
    }

    private void hold() {
         
    }

    private void hook() {
        
    }

    private void retract() {

}

    private void extend() {

}
}
