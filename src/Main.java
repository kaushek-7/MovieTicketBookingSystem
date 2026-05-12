import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Main extends Frame implements ActionListener {

    Label titleLabel, movieLabel, seatLabel, paymentLabel, clockLabel;

    Choice movieChoice, paymentChoice;

    TextField seatField;

    Button bookButton, cancelButton;

    TextArea outputArea;

    Main() {

        setTitle("Movie Ticket Booking System");

        setSize(500, 500);

        setLayout(new GridLayout(8, 2, 10, 10));

        titleLabel = new Label("MOVIE TICKET BOOKING SYSTEM");

        movieLabel = new Label("Select Movie:");

        seatLabel = new Label("Enter Seats:");

        paymentLabel = new Label("Payment Method:");

        clockLabel = new Label();

        movieChoice = new Choice();

        movieChoice.add("Avengers: Doomsday");
        movieChoice.add("Spider-Man: Brand New Day");
        movieChoice.add("Varanasi");
        movieChoice.add("The Odyssey");

        paymentChoice = new Choice();

        paymentChoice.add("UPI");
        paymentChoice.add("Credit Card");
        paymentChoice.add("Debit Card");
        paymentChoice.add("Cash");

        seatField = new TextField();

        bookButton = new Button("BOOK");

        cancelButton = new Button("CLEAR");

        outputArea = new TextArea();

        add(titleLabel);
        add(clockLabel);

        add(movieLabel);
        add(movieChoice);

        add(seatLabel);
        add(seatField);

        add(paymentLabel);
        add(paymentChoice);

        add(bookButton);
        add(cancelButton);

        add(new Label("Booking Status:"));
        add(new Label());

        add(outputArea);

        bookButton.addActionListener(this);

        cancelButton.addActionListener(this);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        ClockThread ct = new ClockThread(clockLabel);

        ct.start();

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == bookButton) {

            try {

                String movie = movieChoice.getSelectedItem();

                String payment = paymentChoice.getSelectedItem();

                int price = 0;

                if(movie.equals("Avengers: Doomsday")) {
                    price = 310;
                }

                else if(movie.equals("Spider-Man: Brand New Day")) {
                    price = 290;
                }

                else if(movie.equals("Varanasi")) {
                    price = 300;
                }

                else if(movie.equals("The Odyssey")) {
                    price = 220;
                }

                int seats = Integer.parseInt(seatField.getText());

                int total = seats * price;

                if (seats <= 0) {

                    throw new Exception("Invalid seat count");
                }

                Booking booking = new Booking(movie, seats);

                outputArea.setText("Ticket Booked Successfully\n\n");

                outputArea.append(booking.getDetails());

                outputArea.append("\nPayment Method: " + payment);

                outputArea.append("\nPay Amount: " + total);

                FileWriter fw = new FileWriter("bookings.txt", true);

                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(booking.getDetails());

                bw.write(" | Payment: " + payment);

                bw.write(" | Total: " + total);

                bw.newLine();

                bw.close();

                fw.close();
            }

            catch (NumberFormatException e) {

                outputArea.setText("Please enter valid number of seats");
            }

            catch (Exception e) {

                outputArea.setText(e.getMessage());
            }
        }

        if (ae.getSource() == cancelButton) {

            seatField.setText("");

            outputArea.setText("");
        }
    }

    public static void main(String[] args) {

        new Main();
    }
}