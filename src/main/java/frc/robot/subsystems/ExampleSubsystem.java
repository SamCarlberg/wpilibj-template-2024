package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class ExampleSubsystem implements Subsystem {
  public Command hello() {
    return runOnce(() -> System.out.println("Hello, world!"));
  }
}
