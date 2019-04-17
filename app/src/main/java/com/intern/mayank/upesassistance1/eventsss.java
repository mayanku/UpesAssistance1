package com.intern.mayank.upesassistance1;

public class eventsss {

    private String eventname;
    private String url;
    private String chaptername;
    private String contactperson;
    private String contactemail;

    public eventsss(){

    }

    public eventsss(String eventname, String url, String chaptername, String contactperson, String contactemail) {
        this.eventname = eventname;
        this.url = url;
        this.chaptername = chaptername;
        this.contactperson = contactperson;
        this.contactemail = contactemail;
    }


    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getChaptername() {
        return chaptername;
    }

    public void setChaptername(String chaptername) {
        this.chaptername = chaptername;
    }

    public String getContactperson() {
        return contactperson;
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson;
    }

    public String getContactemail() {
        return contactemail;
    }

    public void setContactemail(String contactemail) {
        this.contactemail = contactemail;
    }






}
