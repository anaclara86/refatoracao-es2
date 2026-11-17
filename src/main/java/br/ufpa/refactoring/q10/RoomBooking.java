package br.ufpa.refactoring.q10;

public class RoomBooking {

    public double quote(BookingRequest request) {
        double base = request.schedule().durationHours() * 50.0;
        if (request.projector()) base += 30.0;
        if (request.sound()) base += 20.0;
        if (request.attendees() > 40) base *= 1.15;

        if ("A".equals(request.room().building()) && request.room().floor() >= 3) base += 10.0;
        if ("LAB".equals(request.room().room())) base += 40.0;

        return Math.round(base * 100.0) / 100.0;
    }

    public double quote(String building, int floor, String room,
                        int startHour, int endHour,
                        boolean projector, boolean sound,
                        int attendees) {

        double base = (endHour - startHour) * 50.0;
        if (projector) base += 30.0;
        if (sound) base += 20.0;
        if (attendees > 40) base *= 1.15;

        if ("A".equals(building) && floor >= 3) base += 10.0;
        if ("LAB".equals(room)) base += 40.0;

        return Math.round(base * 100.0) / 100.0;
    }

    public record Room(String building, int floor, String room) {}

    public record Schedule(int startHour, int endHour) {
        public int durationHours() { return endHour - startHour; }
    }

    public record BookingRequest(Room room, Schedule schedule,
                                boolean projector, boolean sound, int attendees) {}
}
