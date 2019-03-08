package ftc8564lib;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import hallib.HalUtil;

public class Hang {
    private DcMotor climb;
    private Servo lock;
    private LinearOpMode opMode;

    public Hang (LinearOpMode opMode){
        this.opMode = opMode;
        climb = opMode.hardwareMap.dcMotor.get("climb");
        lock = opMode.hardwareMap.servo.get("lock");
    }

    public void climb(double power){
        climb.setPower(power);
    }

    public void power(double power){
        climb.setPower(scalePower(power));
    }

    private double scalePower(double dVal) {
        return (Math.signum(dVal) * ((Math.pow(dVal, 2) * (.9)) + .1));
    }

    public void stop(){
        climb.setPower(0);
    }

    public void drop(){
        climb.setPower(1);
        HalUtil.sleep(500);
        lock.setPosition(1);
        HalUtil.sleep(700);
        climb(-1);
        HalUtil.sleep(2000);
        climb.setPower(0);
        HalUtil.sleep(500);
    }

}
