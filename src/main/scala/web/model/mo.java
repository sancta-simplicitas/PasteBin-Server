package web.model;

import java.util.Calendar;
import java.util.Date;

public class mo {
    String foo = "";
    Date ca = Calendar.getInstance().getTime();

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public Date getCa() {
        return ca;
    }

    public void setCa(Date ca) {
        this.ca = ca;
    }
}
