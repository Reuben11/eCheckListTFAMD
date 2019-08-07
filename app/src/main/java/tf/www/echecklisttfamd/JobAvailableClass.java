package tf.www.echecklisttfamd;

public class JobAvailableClass {
    private String jr;
    private String process;
    private String area;
    private String checklist;
    private String equipment;
    private String requestor;
    private String time;
    private String device;
    private String daily;
    private String techid;
    private String scode;


    public  JobAvailableClass(String jr, String process, String area, String checklist, String equipment, String requestor, String time, String device, String techid, String daily, String scode){
        this.jr = jr;
        this.process = process;
        this.area = area;
        this.checklist = checklist;
        this.equipment = equipment;
        this.requestor = requestor;
        this.time = time;
        this.device = device;
        this.daily = daily;
        this.techid = techid;
        this.scode = scode;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getScode() {
        return scode;
    }

    public void setScode(String scode) {
        this.scode = scode;
    }

    public String getTechid() {
        return techid;
    }

    public void setTechid(String techid) {
        this.techid = techid;
    }

    public String getJr() {
        return jr;
    }

    public void setJr(String jrNumber) {
        this.jr = jrNumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getChecklist() {
        return checklist;
    }

    public void setChecklist(String checklistname) {
        this.checklist = checklistname;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipmentName) {
        this.equipment = equipmentName;
    }

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDaily() {
        return daily;
    }

    public void setDaily(String daily) {
        this.daily = daily;
    }


}

