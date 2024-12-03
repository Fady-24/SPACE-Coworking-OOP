package sample.sceneswitch;

public abstract class ROOM {
    protected String room_name;
    protected int room_Id;
    protected int numofvisitors;
    protected int num_of_rooms;
    protected Visitor[] List_of_Visitors;
    protected Slots[] List_of_Slots;
    public ROOM(String room_name, int room_Id, int numofvisitors, int num_of_rooms, Visitor[] List_of_Visitors, Slots[] List_of_Slots) {
        this.room_name = room_name;
        this.room_Id = room_Id;
        this.numofvisitors = numofvisitors;
        this.num_of_rooms = num_of_rooms;
        this.List_of_Visitors = List_of_Visitors;
        this.List_of_Slots = List_of_Slots;
    }


}
