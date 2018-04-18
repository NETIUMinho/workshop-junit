package todo.task;


public class Task {

    private long    _id;
    private String _description;
    private boolean _completed;

    private String  _user;




    public Task(long id, String description, boolean completed) {

        super();
        this._id = id;
        this._description = description;
        this._completed = completed;
    }





    public Task(String description) {

        this(0, description, false);
    }





    public long getId() {

        return _id;
    }





    public String getDescription() {

        return _description;
    }





    public void setDescription(String description) {

        this._description = description;
    }





    public boolean isCompleted() {

        return _completed;
    }





    public void setCompleted(boolean completed) {

        this._completed = completed;
    }





    @Override
    public String toString() {

        return this._id + this._description + this._completed;
    }





    protected void setId(long id) {

        this._id = id;
    }





    public String getUser() {

        return _user;
    }





    public void setUser(String user) {

        this._user = user;
    }

}
