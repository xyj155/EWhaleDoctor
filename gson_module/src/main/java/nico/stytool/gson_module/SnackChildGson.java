package nico.stytool.gson_module;

public class SnackChildGson {

    /**
     * id : 12
     * snackName : 推荐产品
     * snackPicture : null
     * pid : 1
     */

    private int id;
    private String snackName;
    private Object snackPicture;
    private int pid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSnackName() {
        return snackName;
    }

    public void setSnackName(String snackName) {
        this.snackName = snackName;
    }

    public Object getSnackPicture() {
        return snackPicture;
    }

    public void setSnackPicture(Object snackPicture) {
        this.snackPicture = snackPicture;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
