package sample.sceneswitch;

public class General_room extends Room {
 public General_room(String room_name, int room_Id) {
     super(room_name, room_Id);
 }

    @Override
    public boolean fully_booked() {
        return this.numofvisitors == 20;
    }


}