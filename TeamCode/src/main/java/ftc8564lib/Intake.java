package ftc8564lib;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import hallib.HalUtil;

public class Intake {
    private CRServo intake;
    private LinearOpMode opMode;

    public Intake(LinearOpMode opMode){
        this.opMode = opMode;
        intake = opMode.hardwareMap.crservo.get("intake");
    }

    public void power(double power) {
        intake.setPower(power);
    }

    public void in(){
        intake.setPower(1);
    }

    public void out(){
        intake.setPower(-1);
    }

    public double getPower(){
        return intake.getPower();
    }

    public void dropMarker(){
        power(-1);
        HalUtil.sleep(2000);
        power(0);
    }
}
