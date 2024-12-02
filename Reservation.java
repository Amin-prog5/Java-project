import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation {
    // Attributes
    private String reservationID;  
    private Owner owner;          
    private Vehicle vehicle;      
    private Spot spot;            
    private List<Slot> slots;     
    private Date reservationDate;  

    // Constructor
    public Reservation(String reservationID, Owner owner, Vehicle vehicle, Spot spot, List<Slot> slots, Date reservationDate) {
        this.reservationID = reservationID;
        this.owner = owner;
        this.vehicle = vehicle;
        this.spot = spot;
        this.slots = slots;
        this.reservationDate = reservationDate;
    }

    // Getters
    public String getReservationID() {
        return reservationID;
    }

    public Owner getOwner() {
        return owner;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Spot getSpot() {
        return spot;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    
  private static boolean isSpotCompatible(String vehicleType, String spotType) {
        switch (vehicleType) {
            case "bike":
                return spotType.equals("bike spot");
            case "car":
                return spotType.equals("normal spot");
            case "4x4":
                return spotType.equals("large spot");
            default:
                return false;
        }
    }

    // Make Reservation
    public static Reservation makeReservation(Owner owner, Vehicle vehicle, Spot spot, List<Slot> slots, Date reservationDate) {
        if (!SpotCompatible(vehicle.getType(), spot.getType())) {
            System.out.println(" Vehicle type does not match spot type");
            return null;
        }
        if (slots.isEmpty()) {
            System.out.println("No slots selected for reservation");
            return null;
        }

        String reservationID = System.currentTimeMillis();
        return new Reservation(reservationID, owner, vehicle, spot, slots, reservationDate);
    }

    // Update Reservation
    public void updateReservation(List<Slot> newSlots) {
        if (newSlots.isEmpty()) {
            System.out.println("No new slots selected");
            return;
        }

        // Free old slots
        for (Slot slot : slots) {
            slot.free();
        }

        // Reserve new slots
        for (Slot slot : newSlots) {
            slot.reserve();
        }

        this.slots = newSlots;
        System.out.println("Reservation updated");
    }

     public void cancelReservation(double penalty) {
        for (Slot slot : slots) {
            slot.free(); // Free  the slots
        }
        System.out.println("Reservation " + reservationID + " has been canceled with a penalty of " +penalty+" $$");
    }
  
   // Display Available Slots
public static void displayAvailableSlots(List<Spot> allSpots, String vehicleType) {
    System.out.println("Available slots for " + vehicleType + ":");
    for (Spot spot : allSpots) {
         
        if (spot.getType().equals(vehicleType)) {
            
            for (Slot slot : spot.getSlots()) {
                
                if (!slot.isReserved()) {
                    System.out.println("Spot ID: " + spot.getSpotID() + " | Slot ID: " + slot.getSlotID());
                }
            }
        }
    }
}

   
    // Display Reservation Details
    public void displayReservation() {
        System.out.println("Reservation ID: " + reservationID);
        System.out.println("Owner: " + owner.getName());
        System.out.println("Vehicle: " + vehicle.getLicenseNumber());
        System.out.println("Spot Type: " + spot.getType());
        System.out.println("Slots Reserved: " + slots.size());
        System.out.println("Date: " + reservationDate);
    }
}
