package ftc8564TestOpMode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import ftc8564lib.GoldAlign;
import ftc8564lib.Robot;
import hallib.HalUtil;


@Autonomous(name="OPENCV", group="Autonomous")
public class OPENCV extends LinearOpMode {

    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this, true);

        waitForStart();
        robot.hang.climb(1);
        HalUtil.sleep(3000);
        robot.hang.stop();
        robot.driveBase.spinPID(180);
        //HalUtil.sleep(100);
        robot.driveBase.spinPID(20);
        robot.goldAlign.enable();
        HalUtil.sleep(1000);
        if (robot.goldAlign.getAligned()) {
            robot.driveBase.drivePID(-36, false);
        }
        else {
            robot.driveBase.spinPID(30);
            HalUtil.sleep(1000);
            if (robot.goldAlign.getAligned()) {
                robot.driveBase.drivePID(-36, false);
            }
            else {
                robot.driveBase.spinPID(-65);
                HalUtil.sleep(1000);
                telemetry.addData("IsAligned" , robot.goldAlign.detector.getAligned());
                if (robot.goldAlign.getAligned()) {
                    telemetry.addData("IsAligned", robot.goldAlign.detector.getAligned());
                    robot.driveBase.drivePID(-36, false);

                }
            }
        }
        robot.goldAlign.stop();
    }

}
