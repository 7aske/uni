package base;

public class Product {
    public static final int SIZE_NOT_APPLICABLE = -1;
    private String code;
    private int color;
    private int size;
    private double price;
    private String currency;

    public Product(String code, int color, int size, double price, String currency) {
        this.code = code;
        this.color = color;
        this.size = size;
        this.price = price;
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public int getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    private String getSizeFor() {
        switch (this.getSize()) {
            case 1:
                return "XS";
            case 2:
                return "S";
            case 3:
                return "M";
            case 4:
                return "L";
            case 5:
                return "XL";
            case 6:
                return "XXL";
            default:
                return "Invalid Size";
        }
    }

    private String getColorFor() {
        switch (this.getColor()) {
            case 1:
                return "blue";
            case 2:
                return "red";
            case 3:
                return "yellow";
            default:
                return "no color";
        }
    }

    @Override
    public String toString() {
        if (this.getSize() != Product.SIZE_NOT_APPLICABLE) {
            return String.format("{\"code\": \"%s\", \"color\": \"%s\", \"size\": \"%s\", \"price\": %.2f, \"currency\": \"%s\"}", code, getColorFor(), getSizeFor(), price, currency);
        } else {
            return String.format("{\"code\": \"%s\", \"color\": \"%s\", \"price\": %.2f, \"currency\": \"%s\"}", code, getColorFor(), getSizeFor(), price, currency);
        }

    }
}
