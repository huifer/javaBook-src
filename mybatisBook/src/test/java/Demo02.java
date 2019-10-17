import com.huifer.mybatis.dao.DeptMapper;
import com.huifer.mybatis.pojo.Dept;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * 描述:
 * mybatis02
 *
 * @author huifer
 * @date 2019-02-24
 */
public class Demo02 {

    private SqlSession session;


    @Before
    public void init() {
        try {
            InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            session = factory.openSession();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    @Test
    public void test01() {
        DeptMapper mapper = session.getMapper(DeptMapper.class);
        Dept dept = mapper.deptFindById(1L);
        System.out.println(dept);
    }


    @Test
    public void test02() {
        DeptMapper mapper = session.getMapper(DeptMapper.class);
        Dept depts = mapper.deptFindByIdWithEmp(1L);
        System.out.println();
    }


    @After
    public void end() {
        if (session != null) {
            session.close();

        }
    }


}
