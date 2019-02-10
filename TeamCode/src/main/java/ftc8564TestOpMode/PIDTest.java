package ftc8564TestOpMode;

import ftc8564lib.*;
import hallib.HalUtil;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name="PIDTest", group="Autonomous")
@Disabled
public class PIDTest extends LinearOpMode {

    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this, true);

        waitForStart();

        robot.driveBase.spinPID(90);
        HalUtil.sleep(1000);
        robot.driveBase.spinPID(60);
        HalUtil.sleep(1000);
        robot.driveBase.spinPID(30);

    }

}
