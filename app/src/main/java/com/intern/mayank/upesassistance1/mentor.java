package com.intern.mayank.upesassistance1;

public class mentor {
    private String names;
    private String department;
    private String email;
    private String skill1;
    private String skill2;
    private String skill3;
    private String skill4;

    public mentor(){

    }
    public mentor(String names, String department, String email, String skill1,String skill2,String skill3,String skill4 ){
        this.names=names;
        this.department=department;
        this.email=email;
        this.skill1=skill1;
        this.skill2=skill2;
        this.skill3=skill3;
        this.skill4=skill4;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkill1() {
        return skill1;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getSkill2() {
        return skill2;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getSkill3() {
        return skill3;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public String getSkill4() {
        return skill4;
    }

    public void setSkill4(String skill4) {
        this.skill4 = skill4;
    }
}
