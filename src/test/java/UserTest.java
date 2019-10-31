import com.rj.dao.PermissionDao;
import com.rj.dao.RoleDao;
import com.rj.dao.UserDao;
import com.rj.pojo.User;
import com.rj.service.PermissionService;
import com.rj.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Set;

public class UserTest {
    @Test
    public void testFindUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");
        User user = userDao.findByUsername("李四");
        System.out.println(user.toString());
    }

    @Test
    public void testFindRoleName() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        RoleDao roleDao = (RoleDao) context.getBean("roleDao");
        Set<String> roleNames = roleDao.findByUsername("admin");
        for (String roleName : roleNames) {
            System.out.println(roleName);
        }
    }

    @Test
    public void testFindPerName() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        PermissionDao permissionDao = (PermissionDao) context.getBean("permissionDao");
        Set<String> pernames = permissionDao.findByUsername("admin");
        for (String pername : pernames) {
            System.out.println(pername);
        }
    }

    @Test
    public void testAddUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");
        User user = new User(null, "子子", "123", "123","xx");
        userDao.add(user);
    }

    @Test
    public void testAddUserService() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserService userService = (UserService) context.getBean("userService");
        User user = new User(null, "子子", "123", null,"xxx");
        userService.add(user);
    }
    @Test
    public void testFindPermissionService() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        PermissionService permissionService = (PermissionService) context.getBean("permissionService");
        Set<String> admin = permissionService.findByUsername("admin");
        for (String s : admin) {
            System.out.println(s);
        }
    }
}
