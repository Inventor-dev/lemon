package xyz.lemone.lemon.system.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordRealm extends AuthenticatingRealm {

//    @Autowired
//    private EmployeeService employeeService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authToken;
//        Employee employee = employeeService.loadByUsername(token.getUsername())
//                .orElseThrow(() -> new AuthenticationException(LoginErrors.NOT_FOUND.msgDesc()));
//        String[] split = ShiroUtils.getSaltPassword(employee.getPassword());
//        return new SimpleAuthenticationInfo(employeeToPrincipal(employee)
//                , split[1], ByteSource.Util.bytes(split[0]), getName());
    return null;
    }




}
