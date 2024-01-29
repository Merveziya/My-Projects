public class Route
{
    static Route[] busRoutesAndDistances = {
            new Route("Istanbul", "Kocaeli", 100),
            new Route("Istanbul", "Ankara", 500),
            new Route("Istanbul", "Eskisehir", 300),
            new Route("Istanbul", "Konya", 600),
            new Route("Kocaeli", "Istanbul", 100),
            new Route("Kocaeli", "Ankara", 400),
            new Route("Kocaeli", "Eskisehir", 200),
            new Route("Kocaeli", "Konya", 500),
            new Route("Ankara", "Istanbul", 500),
            new Route("Ankara", "Kocaeli", 400),
            new Route("Eskisehir", "Istanbul", 300),
            new Route("Eskisehir", "Kocaeli", 200),
            new Route("Eskisehir", "Konya", 300),
            new Route("Konya", "Istanbul", 600),
            new Route("Konya", "Kocaeli", 500),
            new Route("Konya", "Eskisehir", 300)
    };

    static Route[] trainRoutesAndDistances = {
            new Route("Istanbul", "Kocaeli", 75),
            new Route("Istanbul", "Bilecik", 225),
            new Route("Istanbul", "Ankara", 375),
            new Route("Istanbul", "Eskisehir", 300),
            new Route("Istanbul", "Konya", 450),
            new Route("Kocaeli", "Istanbul", 75),
            new Route("Kocaeli", "Bilecik", 75),
            new Route("Kocaeli", "Ankara", 300),
            new Route("Kocaeli", "Eskisehir", 150),
            new Route("Kocaeli", "Konya", 350),
            new Route("Bilecik", "Istanbul", 225),
            new Route("Bilecik", "Kocaeli", 75),
            new Route("Bilecik", "Ankara", 225),
            new Route("Bilecik", "Eskisehir", 75),
            new Route("Bilecik", "Konya", 300),
            new Route("Ankara", "Istanbul", 375),
            new Route("Ankara", "Kocaeli", 300),
            new Route("Ankara", "Bilecik", 225),
            new Route("Eskisehir", "Istanbul", 300),
            new Route("Eskisehir", "Kocaeli", 150),
            new Route("Eskisehir", "Bilecik", 75),
            new Route("Eskisehir", "Ankara", 150),
            new Route("Eskisehir", "Konya", 225),
            new Route("Konya", "Istanbul", 450),
            new Route("Konya", "Kocaeli", 350),
            new Route("Konya", "Bilecik", 300),
            new Route("Konya", "Eskisehir", 225)
    };

    static Route[] planeRoutesAndDistances = {
            new Route("Istanbul", "Ankra", 250),
            new Route("Istanbul", "Konya", 300),
            new Route("Ankara", "Istanbul", 250),
            new Route("Konya", "Istanbul", 300),
    };

    static String[][] busRoutesAndPrices = {
            {"Istanbul", "Kocaeli", "50 TL"},
            {"Istanbul", "Ankara", "300 TL"},
            {"Istanbul", "Eskisehir", "150 TL"},
            {"Istanbul", "Konya", "300 TL"},
            {"Kocaeli", "Istanbul", "50 TL"},
            {"Kocaeli", "Ankara", "400 TL"},
            {"Kocaeli", "Eskisehir", "100 TL"},
            {"Kocaeli", "Konya", "250 TL"},
            {"Ankara", "Istanbul", "300 TL"} ,
            {"Ankara", "Kocaeli", "400 TL"},
            {"Eskisehir", "Istanbul", "150 TL"},
            {"Eskisehir", "Kocaeli", "100 TL"},
            {"Eskisehir", "Konya", "150 TL"},
            {"Konya", "Istanbul", "300 TL"},
            {"Konya", "Kocaeli", "250 TL"},
            {"Konya", "Eskisehir", "150 TL"}
    };

    static String[][] trainRoutesAndPrices = {
            {"Istanbul", "Kocaeli", "50 TL"},
            {"Istanbul", "Bilecik", "150 TL"},
            {"Istanbul", "Ankara", "250 TL"},
            {"Istanbul", "Eskisehir", "200 TL"},
            {"Istanbul", "Konya", "300 TL"},
            {"Kocaeli", "Istanbul","50 TL"},
            {"Kocaeli", "Bilecik","50 TL"},
            {"Kocaeli", "Ankara", "200 TL"},
            {"Kocaeli", "Eskisehir", "100 TL"},
            {"Kocaeli", "Konya", "250 TL"},
            {"Bilecik", "Istanbul", "150 TL"},
            {"Bilecik", "Kocaeli","50 TL"},
            {"Bilecik", "Ankara", "150 TL"},
            {"Bilecik", "Eskisehir","50 TL"},
            {"Bilecik", "Konya", "200 TL"},
            {"Ankara", "Istanbul", "250 TL"},
            {"Ankara", "Kocaeli", "200 TL"},
            {"Ankara", "Bilecik", "150 TL"},
            {"Eskisehir", "Istanbul", "200 TL"},
            {"Eskisehir", "Kocaeli", "1OO TL"},
            {"Eskisehir", "Bilecik","50 TL"},
            {"Eskisehir", "Ankara", "100 TL"},
            {"Eskisehir", "Konya", "150 TL"},
            {"Konya", "Istanbul", "300 TL"},
            {"Konya", "Kocaeli", "250 TL"},
            {"Konya", "Bilecik", "200 TL"},
            {"Konya", "Eskisehir", "150 TL"}
    };

    static String[][] planeRoutesAndPrices = {
            {"Istanbul", "Ankra", "1000 TL"},
            {"Istanbul", "Konya", "1200 TL"},
            {"Ankara", "Istanbul", "1000 TL"},
            {"Konya", "Istanbul", "1200 TL"},
    };

    private String startingPoint;
    private String destination;
    private int distance;

    public Route(String startingPoint, String destination, int distance) {
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.distance = distance;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public String getDestination() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }



    public static void main(String[] args) {}
}