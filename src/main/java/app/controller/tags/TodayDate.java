package app.controller.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class TodayDate extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print(Date.valueOf(LocalDate.now().toString()));
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
