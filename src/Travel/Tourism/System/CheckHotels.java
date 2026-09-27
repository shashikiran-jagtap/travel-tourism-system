package Travel.Tourism.System;

import javax.swing.*;
import java.awt.*;

public class CheckHotels extends JFrame implements Runnable {

    JLabel[] hotelLabels = new JLabel[10];
    JLabel caption;
    Thread th;

    public CheckHotels() {

        setBounds(580, 220, 900, 700);
        setTitle("Check Hotels");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(220, 250, 250));


        caption = new JLabel();
        caption.setBounds(50, 550, 800, 70);
        caption.setForeground(Color.WHITE);
        caption.setFont(new Font("Tahoma", Font.BOLD, 40));
        add(caption);


        for (int i = 0; i < 10; i++) {

            int hotelNumber = i + 1;

            String imagePath =
                    "icons/hotel" +
                            hotelNumber +
                            ".jpg";

            java.net.URL imageUrl =
                    ClassLoader.getSystemResource(imagePath);

            if (imageUrl != null) {

                ImageIcon originalIcon = new ImageIcon(imageUrl);

                Image scaledImage =
                        originalIcon.getImage().getScaledInstance(
                                900,
                                700,
                                Image.SCALE_SMOOTH
                        );

                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                hotelLabels[i] = new JLabel(scaledIcon);
                hotelLabels[i].setBounds(0, 0, 900, 700);

                add(hotelLabels[i]);


                hotelLabels[i].setVisible(i == 0);

            } else {

                System.out.println(
                        "Image not found: " + imagePath
                );


                hotelLabels[i] = new JLabel();
                hotelLabels[i].setBounds(0, 0, 900, 700);
                hotelLabels[i].setVisible(false);
                add(hotelLabels[i]);
            }
        }


        getContentPane().setComponentZOrder(caption, 0);


        th = new Thread(this);
        th.start();
    }

    @Override
    public void run() {

        String[] hotelNames = {
                "JW Marriott Hotel",
                "Mandarin Oriental Hotel",
                "Four Seasons Hotel",
                "Radisson Hotel",
                "Classio Hotel",
                "The Bay Club Hotel",
                "Breeze Blows Hotel",
                "Quick Stop Hotel",
                "Happy Mornings Motel",
                "Moss View Hotel"
        };

        try {

            for (int i = 0; i < hotelLabels.length; i++) {

                final int index = i;


                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {


                        for (JLabel label : hotelLabels) {
                            label.setVisible(false);
                        }


                        hotelLabels[index].setVisible(true);


                        caption.setText(hotelNames[index]);


                        caption.setVisible(true);
                    }
                });

                Thread.sleep(2800);
            }


            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    dispose();
                }
            });

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                CheckHotels frame = new CheckHotels();
                frame.setVisible(true);
            }
        });
    }
}