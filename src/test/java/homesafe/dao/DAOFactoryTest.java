package homesafe.dao;

import homesafe.UnitTest;
import org.junit.jupiter.api.Assertions;

/**
 * created by:
 * author: MichaelMillar
 */
public class DAOFactoryTest {

    @UnitTest
    public void testCreatNotNull() {
        Class<UserDAO> daoClass = UserDAO.class;
        UserDAO dao1 = DAOFactory.create(daoClass);
        UserDAO dao2 = DAOFactory.create(daoClass, DAOUtils.getConnection());
        UserDAO dao3 = DAOFactory.create(daoClass, DAOUtils::getConnection);

        Assertions.assertNotNull(dao1);
        Assertions.assertNotNull(dao2);
        Assertions.assertNotNull(dao3);
    }

    @UnitTest
    public void testCreateEquals() {
        Class<UserDAO> daoClass = UserDAO.class;
        UserDAO dao1 = DAOFactory.create(daoClass);
        UserDAO dao2 = DAOFactory.create(daoClass, DAOUtils.getConnection());
        UserDAO dao3 = DAOFactory.create(daoClass, DAOUtils::getConnection);

        Assertions.assertTrue(dao1 instanceof UserSQLiteDAO);
        Assertions.assertTrue(dao2 instanceof UserSQLiteDAO);
        Assertions.assertTrue(dao3 instanceof UserSQLiteDAO);
    }

}
