package frc.robot.subsystems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.simulation.DriverStationSim;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExampleSubsystemTest {
  // For reading output from System.out.println
  private final PrintStream standardOut = System.out;
  private ByteArrayOutputStream outputStreamCaptor;

  private ExampleSubsystem exampleSubsystem;

  @BeforeEach
  void setup() {
    // Initialize WPILib internals
    HAL.initialize(500, 0);

    // Tell WPILib that the robot is "enabled" so commands can run
    DriverStationSim.setEnabled(true);
    DriverStationSim.notifyNewData();

    exampleSubsystem = new ExampleSubsystem();
    outputStreamCaptor = new ByteArrayOutputStream(0);
    System.setOut(new PrintStream(outputStreamCaptor, true));
  }

  @AfterEach
  void teardown() {
    CommandScheduler.getInstance().cancelAll();
    System.setOut(standardOut);
  }

  @Test
  void helloCommand() {
    var helloCommand = exampleSubsystem.hello();
    helloCommand.schedule();
    assertTrue(helloCommand.isScheduled(), "Hello should be scheduled");

    CommandScheduler.getInstance().run();
    assertEquals("Hello, world!", outputStreamCaptor.toString().trim());
    assertFalse(helloCommand.isScheduled(), "Hello should run exactly once");
  }
}
