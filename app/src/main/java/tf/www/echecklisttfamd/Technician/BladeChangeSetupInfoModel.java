package tf.www.echecklisttfamd.Technician;

import java.util.Date;

public class BladeChangeSetupInfoModel {

    private String EmpId;
    private String EmpName;
    private String DateTime;
    private String BladeChangeZ1;
    private String BladeChangeZ2;
    private String BladeGroup;
    private String UsedBladeConditionZ1;
    private String UsedBladeConditionZ2;
    private String NewBladeConditionZ1;
    private String NewBladeConditionZ2;
    private String BladeLifeZ1;
    private String BladeLifeZ2;

    public BladeChangeSetupInfoModel(String empId, String empName, String dateTime, String bladeChangeZ1, String bladeChangeZ2, String bladeGroup, String usedBladeConditionZ1, String usedBladeConditionZ2, String newBladeConditionZ1, String newBladeConditionZ2, String bladeLifeZ1, String bladeLifeZ2) {
        EmpId = empId;
        EmpName = empName;
        DateTime = dateTime;
        BladeChangeZ1 = bladeChangeZ1;
        BladeChangeZ2 = bladeChangeZ2;
        BladeGroup = bladeGroup;
        UsedBladeConditionZ1 = usedBladeConditionZ1;
        UsedBladeConditionZ2 = usedBladeConditionZ2;
        NewBladeConditionZ1 = newBladeConditionZ1;
        NewBladeConditionZ2 = newBladeConditionZ2;
        BladeLifeZ1 = bladeLifeZ1;
        BladeLifeZ2 = bladeLifeZ2;
    }


    public BladeChangeSetupInfoModel(String empId, String dateTime, String bladeChangeZ1, String bladeChangeZ2, String bladeGroup, String usedBladeConditionZ1, String usedBladeConditionZ2, String newBladeConditionZ1, String newBladeConditionZ2, String bladeLifeZ1, String bladeLifeZ2) {
    }

    public BladeChangeSetupInfoModel() {

    }


    public String getEmpId() {
        return EmpId;
    }

    public void setEmpId(String empId) {
        EmpId = empId;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public String getBladeChangeZ1() {
        return BladeChangeZ1;
    }

    public void setBladeChangeZ1(String bladeChangeZ1) {
        BladeChangeZ1 = bladeChangeZ1;
    }

    public String getBladeChangeZ2() {
        return BladeChangeZ2;
    }

    public void setBladeChangeZ2(String bladeChangeZ2) {
        BladeChangeZ2 = bladeChangeZ2;
    }

    public String getBladeGroup() {
        return BladeGroup;
    }

    public void setBladeGroup(String bladeGroup) {
        BladeGroup = bladeGroup;
    }

    public String getUsedBladeConditionZ1() {
        return UsedBladeConditionZ1;
    }

    public void setUsedBladeConditionZ1(String usedBladeConditionZ1) {
        UsedBladeConditionZ1 = usedBladeConditionZ1;
    }

    public String getUsedBladeConditionZ2() {
        return UsedBladeConditionZ2;
    }

    public void setUsedBladeConditionZ2(String usedBladeConditionZ2) {
        UsedBladeConditionZ2 = usedBladeConditionZ2;
    }

    public String getNewBladeConditionZ1() {
        return NewBladeConditionZ1;
    }

    public void setNewBladeConditionZ1(String newBladeConditionZ1) {
        NewBladeConditionZ1 = newBladeConditionZ1;
    }

    public String getNewBladeConditionZ2() {
        return NewBladeConditionZ2;
    }

    public void setNewBladeConditionZ2(String newBladeConditionZ2) {
        NewBladeConditionZ2 = newBladeConditionZ2;
    }

    public String getBladeLifeZ1() {
        return BladeLifeZ1;
    }

    public void setBladeLifeZ1(String bladeLifeZ1) {
        BladeLifeZ1 = bladeLifeZ1;
    }

    public String getBladeLifeZ2() {
        return BladeLifeZ2;
    }

    public void setBladeLifeZ2(String bladeLifeZ2) {
        BladeLifeZ2 = bladeLifeZ2;
    }

}
