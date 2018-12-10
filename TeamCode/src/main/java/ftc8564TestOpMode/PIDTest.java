package ftc8564TestOpMode;

import ftc8564lib.*;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name="PIDTest", group="Autonomous")
public class PIDTest extends LinearOpMode {

    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this, true);

        waitForStart();

        robot.driveBase.drivePID(60, false);
    }

}
