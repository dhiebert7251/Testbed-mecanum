// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class Auto_DriveForward extends CommandBase {
  /** Creates a new Auto_DriveForward. */
  private final Drivetrain m_drive;
  private final double m_distance;
  private final double m_speed;

  /**
   * Creates a new Auto_DriveForward.
   *
   * @param meters The number of meters the robot will drive
   * @param speed The speed at which the robot will drive
   * @param drive The drive subsystem on which this command will run
   */
  public Auto_DriveForward(double meters, double speed, Drivetrain drive) {
    m_distance = meters;
    m_speed = speed;
    m_drive = drive;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive.resetEncoders();
    m_drive.drive(m_speed, 0,0, m_drive.getFieldRelative());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.drive(m_speed, 0,0, m_drive.getFieldRelative());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.drive(0, 0, 0, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double avDistance = (m_drive.
                            getCurrentWheelDistances().frontLeftMeters +
                          m_drive.
                            getCurrentWheelDistances().frontRightMeters)/2;

    return Math.abs(avDistance) >= m_distance;
  }
}
