package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.littletonrobotics.junction.Logger;

import java.util.EnumMap;
import java.util.Map;


public class ClimberStateMachine extends SubsystemBase{
    public enum State{
        IDLE,
        EXTEND,
        RETRACT,
        HOLD,
        HOOK,
        L1,
        L2;
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
                            Logger.recordOutput("State Event", statey.toString() + " -> EXTEND");
                            statey = State.EXTEND;
                            System.out.println("gurt");
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
                            Logger.recordOutput("State Event", statey.toString() + " -> RETRACT");
                            statey = State.RETRACT;
                        });
                }
                break;
            case HOLD:
                switch(statey){
                    case EXTEND:
                    case RETRACT:
                    case IDLE:
                    case L1:
                    case L2:
                        return new InstantCommand(() -> {
                            cl.extendArmWithPower(0.0);
                            Logger.recordOutput("State Event", statey.toString() + " -> HOLD");
                            statey = State.HOLD;
                        });
                }
                break;
            case HOOK:
                switch(statey){
                    case EXTEND:
                    case RETRACT:
                    case L1:
                    case L2:
                        return new InstantCommand(() -> {
                            cl.extendArmWithPower(0.0);
                            Logger.recordOutput("State Event", statey.toString() + " -> HOOK");
                            statey = State.HOOK;
                        });
                }
                break;
            case IDLE:
                switch(statey){
                    case EXTEND:
                    case RETRACT:
                    case HOLD:
                    case L1:
                    case L2:
                        return new InstantCommand(() -> {
                            cl.extendArmWithPower(0.0);
                            Logger.recordOutput("State Event", statey.toString() + " -> IDLE");
                            statey = State.IDLE;
                        });
                }
            case L1:
                switch(statey){
                    case HOLD:
                    case IDLE:
                    return new InstantCommand(() -> {
                        int pow = 0;
                        statey = State.L1;
                        if (cl.getArmPositionInMeters() > cl.l1+0.05){
                            tryState(State.RETRACT);
                        }
                        else if (cl.getArmPositionInMeters() < cl.l1-0.05){
                            tryState(State.EXTEND);
                        }
                        else{
                            tryState(State.HOLD);
                        }
                    });
                }
            case L2:
                switch(statey){
                    case HOLD:
                    case IDLE:
                    return new InstantCommand(() -> {
                        int pow = 0;
                        statey = State.L2;
                        if (cl.getArmPositionInMeters() > cl.l2+0.05){
                            tryState(State.RETRACT);
                        }
                        else if (cl.getArmPositionInMeters() < cl.l2-0.05){
                            tryState(State.EXTEND);
                        }
                        else{
                            tryState(State.HOLD);
                        }
                    });
                }
                break;
        }
        Logger.recordOutput("State Event", statey.toString() + " X->X" + desiredstate.toString());
        return Commands.print("its joever 3: cuz "+desiredstate+" couldnt switch to up "+statey);
        

    }
    public void periodic(){
        Logger.recordOutput("State", statey.toString());
        Logger.recordOutput("Motor Speed", cl.getSpeed());
        Logger.recordOutput("Postion", cl.getArmPositionInMeters());
        Logger.recordOutput("Current", cl.getCurrent());
    //     switch (statey){
    //         case EXTEND:
    //             if (cl.forwardLimit.isPressed()){
    //                 this.tryState(State.HOLD);
    //             }
    //             break;
    //         case RETRACT:
    //             if (cl.reverseLimit.isPressed()){
    //                 this.tryState(State.HOLD);
    //             }
    //             break;
    //         default:
    //             break;
    //     }
    
    }

}