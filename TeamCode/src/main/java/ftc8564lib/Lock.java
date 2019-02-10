package ftc8564lib;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import hallib.HalUtil;

public class Lock {
    public Servo lock;
    public Servo hatch;
    private LinearOpMode opMode;

    public Lock (LinearOpMode opMode){
        this.opMode = opMode;
        lock = opMode.hardwareMap.servo.get("lock");
        hatch = opMode.hardwareMap.servo.get("hatch");
    }

    public void open(){
        lock.setPosition(.525);
    }

    public void close(){
        lock.setPosition(.4);
    }

}
