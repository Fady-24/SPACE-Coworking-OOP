package sample.sceneswitch;

public class Meeting_Room extends Room {
    public Meeting_Room(String room_name, int room_Id) {
        super(room_name, room_Id);
    }

    @Override
    public boolean fully_booked() {
        return this.numofvisitors==10;
    }


}


