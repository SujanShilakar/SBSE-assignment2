package guioptimiser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Util {
  public static void main(String[] args) {
    // Example image paths
    String image1Path = "C:\\Users\\sujan\\OneDrive\\Desktop\\SBSE\\Assignment2\\Code\\screenshots\\image1.png";
    String image2Path = "C:\\Users\\sujan\\OneDrive\\Desktop\\SBSE\\Assignment2\\Code\\screenshots\\image2.png";
    String image3Path = "C:\\Users\\sujan\\OneDrive\\Desktop\\SBSE\\Assignment2\\Code\\screenshots\\image3.png";
    String image4Path = "C:\\Users\\sujan\\OneDrive\\Desktop\\SBSE\\Assignment2\\Code\\screenshots\\image4.png";
    String image5Path = "C:\\Users\\sujan\\OneDrive\\Desktop\\SBSE\\Assignment2\\Code\\screenshots\\image5.png";

    // Calculate charge consumption for each image
    double chargeConsumption1 = calculateChargeConsumptionForImage(image1Path);
    double chargeConsumption2 = calculateChargeConsumptionForImage(image2Path);
    double chargeConsumption3 = calculateChargeConsumptionForImage(image3Path);
    double chargeConsumption4 = calculateChargeConsumptionForImage(image4Path);
    double chargeConsumption5 = calculateChargeConsumptionForImage(image5Path);

    // Print results
    printResults(image1Path, chargeConsumption1);
    printResults(image2Path, chargeConsumption2);
    printResults(image3Path, chargeConsumption3);
    printResults(image4Path, chargeConsumption4);
    printResults(image5Path, chargeConsumption5);
  }

  public static double calculateChargeConsumptionForImage(String imagePath) {
    try {
      // Load the image
      BufferedImage image = ImageIO.read(new File(imagePath));

      // Get the image dimensions
      int width = image.getWidth();
      int height = image.getHeight();
      // int totalPixels = width * height;

      // Iterate through the pixels and calculate the charge consumption
      double totalChargeConsumption = 0.0;
      for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
          int pixel = image.getRGB(x, y);
          int red = (pixel >> 16) & 0xFF;
          int green = (pixel >> 8) & 0xFF;
          int blue = (pixel) & 0xFF;
          totalChargeConsumption += calculateChargeConsumptionPerPixel(red, green, blue);
        }
      }

      return totalChargeConsumption;
    } catch (IOException e) {
      e.printStackTrace();
      return 0.0;
    }
  }

  public static double calculateChargeConsumptionPerPixel(int red, int green, int blue) {
    // Charge consumption rates for Nexus 6 (in mA)
    double redConsumption = 1.20e-01;
    double greenConsumption = 1.40e-01;
    double blueConsumption = 2.40e-01;

    // Calculate the charge consumption for the given RGB values
    double chargeConsumption = (red * redConsumption + green * greenConsumption + blue * blueConsumption) / 255.0;

    return chargeConsumption;
  }

  private static void printResults(String imagePath, double chargeConsumption) {
    try {
      // Load the image
      BufferedImage image = ImageIO.read(new File(imagePath));

      // Get the image dimensions
      int width = image.getWidth();
      int height = image.getHeight();
      int totalPixels = width * height;

      System.out.println("Image: " + imagePath);
      System.out.println("Number of pixels: " + totalPixels);
      System.out.println("Charge consumption: " + chargeConsumption + " mA");
      System.out.println();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
