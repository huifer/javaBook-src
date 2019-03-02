import com.huifer.mybatis.pojo.Dept;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-02-21
 */
public class Demo {

    public static void main(String[] args) throws Exception {

        Dept dept = new Dept();
        dept.setDname("技术部");
        dept.setLoc("oc");
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = factory.openSession();

        session.insert("insertDept", dept);
        session.commit();

        session.close();
    }

}
