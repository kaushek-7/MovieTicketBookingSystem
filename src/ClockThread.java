import java.awt.Label;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockThread extends Thread {

    Label clockLabel;

    ClockThread(Label clockLabel) {
        this.clockLabel = clockLabel;
    }

    public void run() {

        while (true) {

            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
            String time = sdf.format(new Date());

            clockLabel.setText("Current Time: " + time);

            try {
                Thread.sleep(1000);
            }

            catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}