package ftc8564lib;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Hang {
    DcMotor climb;

    LinearOpMode opMode;

    public Hang (LinearOpMode opMode){
        this.opMode = opMode;
        climb = opMode.hardwareMap.dcMotor.get("climb");
    }

    public void climb(double power){
        climb.setPower(power);
    }

    public void stop(){
        climb.setPower(0);
    }

}
