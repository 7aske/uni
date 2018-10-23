package zadatak7;

import java.util.Scanner;

public class Zadatak7 {
    public static void main(String[] args) {
        new Zadatak7();
    }

    private Zadatak7() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter first point \"x, y\":");
        String text1 = input.next();
        Coord coord1 = new Coord(Double.parseDouble(text1.split(",")[0]), Double.parseDouble(text1.split(",")[1]));

        System.out.print("Enter second point \"x, y\":");
        String text2 = input.next();
        Coord coord2 = new Coord(Double.parseDouble(text2.split(",")[0]), Double.parseDouble(text2.split(",")[1]));

        System.out.printf("Distance between points is: %.2f", coord1.distanceTo(coord2));
    }
}

class Coord {
    public double x;
    public double y;
    private final double RADIUS = 6371.01;
    public Coord(double x, double y) {
        this.x = Math.toRadians(x);
        this.y = Math.toRadians(y);
    }
    public double distanceTo(Coord coord) {
        return this.RADIUS * Math.acos(Math.sin(this.x) * Math.sin(coord.x) + Math.cos(this.x) * Math.cos(coord.x) * Math.cos(this.y - coord.y));
    }


}
