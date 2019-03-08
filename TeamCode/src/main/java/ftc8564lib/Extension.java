package ftc8564lib;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Extension {
    private DcMotor extension;
    private LinearOpMode opMode;

    public Extension(LinearOpMode opMode){
        this.opMode = opMode;
        extension = opMode.hardwareMap.dcMotor.get("extension");
    }

    public void power(double power){
        extension.setPower(scalePower(power));
    }

    private double scalePower(double dVal) {
        return (Math.signum(dVal) * ((Math.pow(dVal, 2) * (.9)) + .1));
    }
}
