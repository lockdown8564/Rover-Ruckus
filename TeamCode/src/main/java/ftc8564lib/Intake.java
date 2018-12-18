package ftc8564lib;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import hallib.HalUtil;

public class Intake {
    private DcMotor intake;
    private LinearOpMode opMode;

    public Intake(LinearOpMode opMode){
        this.opMode = opMode;
        intake = opMode.hardwareMap.dcMotor.get("intake");
        intake.setDirection(DcMotor.Direction.REVERSE);
    }

    public void power(double power) {
        intake.setPower(power);
    }

    public void dropMarker(){
        power(-1);
        HalUtil.sleep(2000);
        power(0);
    }
}
