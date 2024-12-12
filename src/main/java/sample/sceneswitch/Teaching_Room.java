package sample.sceneswitch;

public class Teaching_Room extends Room{
    private String Board_Type;
    private String Projector_Type;
    public String Instructor_Name;
    public Teaching_Room(String room_name, int room_Id ,
                        String Board_Type, String Projector_Type, String Instructor_Name) {
        super(room_name, room_Id);
        this.Board_Type = Board_Type;
        this.Projector_Type = Projector_Type;
        this.Instructor_Name = Instructor_Name;
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

    @Override
    public boolean fully_booked() {
        return this.numofvisitors==10;
    }
}
