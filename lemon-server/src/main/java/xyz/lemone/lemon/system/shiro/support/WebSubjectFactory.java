package xyz.lemone.lemon.system.shiro.support;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;


/**
 * @author lemon
 */
public class WebSubjectFactory extends DefaultWebSubjectFactory {


    public WebSubjectFactory() {
        super();
    }


    @Override
    public Subject createSubject(SubjectContext context) {
        // 禁用 session
        context.setSessionCreationEnabled(Boolean.FALSE);
        return super.createSubject(context);
    }

}
