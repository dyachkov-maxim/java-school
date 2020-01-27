public class StudentVisits {
    private int student_id;
    private int lesson_id;
    private boolean isDelete = false;

    public StudentVisits(int student_id, int lesson_id) {
        this.student_id = student_id;
        this.lesson_id = lesson_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
