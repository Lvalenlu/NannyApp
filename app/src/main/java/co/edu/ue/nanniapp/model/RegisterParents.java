package co.edu.ue.nanniapp.model;


public class RegisterParents {
    private String pers_name;
    private String pers_numdoc;
    private String pers_description;
    private String phon_number;

    private String addr_name;

    public String getPhon_number() {
        return phon_number;
    }

    public void setPhon_number(String phon_number) {
        this.phon_number = phon_number;
    }

    public String getAddr_name() {
        return addr_name;
    }

    public void setAddr_name(String addr_name) {
        this.addr_name = addr_name;
    }

    public String getPers_name() {
        return pers_name;
    }
    public void setPers_name(String pers_name) {
        this.pers_name = pers_name;
    }

    public String getPers_numdoc() {
        return pers_numdoc;
    }
    public void setPers_numdoc(String pers_numdoc) {
        this.pers_numdoc = pers_numdoc;
    }


    public String getPers_description() {
        return pers_description;
    }
    public void setPers_description(String pers_description) {
        this.pers_description = pers_description;
    }


}
