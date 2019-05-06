package tf.www.echecklisttfamd.Technician;

public class JobAvailableClass {
    private String jr;
    private String area;
    private String checklist;
    private String equipment;
    private String requestor;
    private String time;
    private String device, daily;

    public  JobAvailableClass(String jr, String area, String checklist, String equipment, String requestor, String time, String device){
        this.jr = jr;
        this.area = area;
        this.checklist = checklist;
        this.equipment = equipment;
        this.requestor = requestor;
        this.time = time;
        this.device = device;
        this.daily = daily;
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

    /*  private String jrNumber;
    private String requestor;
    private String jrDateTime;
    private String jobType;
    private String equipment;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    private String area;

    public String getJrNumber() {
        return jrNumber;
    }

    public void setJrNumber(String jrNumber) {
        this.jrNumber = jrNumber;
    }

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getJrDateTime() {
        return jrDateTime;
    }

    public void setJrDateTime(String jrDateTime) {
        this.jrDateTime = jrDateTime;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }*/
}

