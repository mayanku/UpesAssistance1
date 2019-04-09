package com.intern.mayank.upesassistance1;

public class projec {

    private String Title;
    private String skill_1;
    private String skill_2;
    private String skill_3;
    private String skill_4;

    public projec() {

    }

    public String getSkill_3() {
        return skill_3;
    }

    public void setSkill_3(String skill_3) {
        this.skill_3 = skill_3;
    }

    public String getSkill_4() {
        return skill_4;
    }

    public void setSkill_4(String skill_4) {
        this.skill_4 = skill_4;
    }

    public projec(String Title, String skill_1, String skill_2, String skill_3, String skill_4) {
        this.Title = Title;
        this.skill_1 = skill_1;
        this.skill_2 = skill_2;
        this.skill_3=skill_3;
        this.skill_4=skill_4;

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getSkill_1() {
        return skill_1;
    }

    public void setSkill_1(String skill_1) {
        this.skill_1 = skill_1;
    }

    public String getSkill_2() {
        return skill_2;
    }

    public void setSkill_2(String skill_2) {
        this.skill_2 = skill_2;
    }
}
