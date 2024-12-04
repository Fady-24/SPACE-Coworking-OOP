package sample.sceneswitch;

public class Teaching_Room extends ROOM{
    private String Board_Type;
    private String Projector_Type;
    public String Instructor_Name;
    public Teaching_Room(String room_name, int room_Id, int numofvisitors, int num_of_rooms, Visitor[] List_of_Visitors, Slots[] List_of_Slots,
                        String Board_Type, String Projector_Type, String Instructor_Name) {
        super(room_name, room_Id, numofvisitors, num_of_rooms, List_of_Visitors, List_of_Slots);
        this.Board_Type = Board_Type;
        this.Projector_Type = Projector_Type;
        this.Instructor_Name = Instructor_Name;
    }
    public boolean check_for_visitors_num(){
        return numofvisitors <= num_of_rooms;
    }
    public String getBoard_Type() {
        return Board_Type;
    }

    public void setBoard_Type(String Board_Type) {
        this.Board_Type = Board_Type;
    }

    public String getProjector_Type() {
        return Projector_Type;
    }

    public void setProjector_Type(String Projector_Type) {
        this.Projector_Type = Projector_Type;
    }

    public String getInstructor_Name() {
        return Instructor_Name;
    }

    public void setInstructor_Name(String Instructor_Name) {
        this.Instructor_Name = Instructor_Name;
    }

}
