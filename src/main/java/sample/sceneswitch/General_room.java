package sample.sceneswitch;

public class General_room extends ROOM {
 public General_room(String room_name, int room_Id, int numofvisitors, int num_of_rooms, Visitor[] List_of_Visitors, Slots[] List_of_Slots) {
     super(room_name, room_Id, numofvisitors, num_of_rooms, List_of_Visitors, List_of_Slots);
 }
    public boolean check_visitores_num() {
        return numofvisitors <= num_of_rooms;
    }
}