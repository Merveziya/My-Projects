public class Trip
{
    static String[][] busTripRoutes = {
            {"Istanbul", "Kocaeli", "Ankara", "Kocaeli", "Istanbul"},
            {"Istanbul", "Kocaeli", "Eskisehir", "Konya", "Eskisehir", "Kocaeli", "Istanbul"}
    };

    static String[][] trainTripRoutes =
            {
                    {"Istanbul", "Kocaeli", "Bilecik", "Eskisehir", "Ankara", "Eskisehir", "Bilecik", "Kocaeli", "Istanbul"},
                    {"Istanbul", "Kocaeli", "Bilecik", "Eskisehir", "Konya", "Eskisehir", "Bilecik", "Kocaeli", "Istanbul"}
            };
    static String[][] planeTripRoutes =
            {
                    {"Istanbul", "Konya", "Istanbul"},
                    {"Istanbul", "Ankara", "Istanbul"}
            };

    private Vehicle vehicle;
    private Route route;
    private String date;
    private String hour;
    private String price;
    private String tripNumber;
    private String ownCompanyName;

    public Trip(Route route, String date, String hour, String price, Vehicle vehicle, String tripNumber, String ownCompanyName)
    {
        this.vehicle = vehicle;
        this.route = route;
        this.date = date;
        this.hour = hour;
        this.price = price;
        this.tripNumber = tripNumber;
        this.ownCompanyName = ownCompanyName;
    }

    public String getVehicleName() {
        return vehicle.getVehicleId();
    }
    public Vehicle getVehicle() { return vehicle; }
    public String getTripNumber() {
        return tripNumber;
    }
    public String getHour() { return hour; }
    public String getDate() { return date; }
    public String getPrice() {
        return price;
    }
    public String getOwnCompany() { return ownCompanyName; }
}