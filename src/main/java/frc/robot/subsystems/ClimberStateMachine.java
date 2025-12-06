package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.EnumMap;
import java.util.Map;


public class ClimberStateMachine extends SubsystemBase{
    public enum State{
        IDLE,
        EXTEND,
        RETRACT,
        HOLD,
        HOOK;
    }

    public State statey;

    private final Climber cl;


    public ClimberStateMachine(Climber cl){
        this.statey = State.IDLE;
        this.cl = cl;
    }

    public State getState(){
        return statey;
    }

    public void setState(State s){
        statey = s;
    }

    public Command tryState(State desiredstate){
        switch(desiredstate){
            case EXTEND:
                switch(statey){
                    case IDLE:
                    case RETRACT:
                    case HOLD:
                        return new InstantCommand(() -> {
                            cl.extendArmWithPower(1.0);
                            statey = State.EXTEND;
                        });
                }
                break;
            case RETRACT:
                switch(statey){
                    case IDLE:
                    case EXTEND:
                    case HOLD:
                    case HOOK:
                        return new InstantCommand(() -> {
                            cl.extendArmWithPower(-1.0);
                            statey = State.RETRACT;
                        });
                }
                break;
            case HOLD:
                switch(statey){
                    case EXTEND:
                    case RETRACT:
                        return new InstantCommand(() -> {
                            cl.extendArmWithPower(0.0);
                            statey = State.HOLD;
                        });
                }
                break;
            case HOOK:
                switch(statey){
                    case EXTEND:
                    case RETRACT:
                        return new InstantCommand(() -> {
                            cl.extendArmWithPower(0.0);
                            statey = State.HOOK;
                        });
                }
                break;
            case IDLE:
                switch(statey){
                    case EXTEND:
                    case RETRACT:
                        return new InstantCommand(() -> {
                            cl.extendArmWithPower(0.0);
                            statey = State.IDLE;
                        });
                }
                break;
        }
        return Commands.print("its joever 3: cuz "+desiredstate+" couldnt switch to up "+statey);

    }
    public void periodic(){
        switch (statey){
            case EXTEND:
                if (cl.forwardLimit.isPressed()){
                    this.tryState(State.HOLD);
                }
                break;
            case RETRACT:
                if (cl.reverseLimit.isPressed()){
                    this.tryState(State.HOLD);
                }
                break;
            default:
                break;
        }
    }

}