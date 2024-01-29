import java.util.ArrayList;
import java.util.List;

public class Customer
{
   List<Reservation> madeReservationsList;
   List<Integer> chosenSeats;

   Customer()
   {
      madeReservationsList = new ArrayList<>();
      chosenSeats = new ArrayList<>();
   }

   public static void main(String[] args) {}
}