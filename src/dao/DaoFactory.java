package dao;

import dao.impl.CustomerDaoImpl;

public class DaoFactory {
    public static  DaoFactory  daoFactory;

    private DaoFactory() {}

    public static DaoFactory getDaoFactory() {
        return (daoFactory == null) ? new DaoFactory() : daoFactory;
    }

    public enum DAOTYPE{
   Customer
    }
    public SuperDAO getDAOType(DAOTYPE daotype) {
        switch (daotype){
            case Customer:
                return new CustomerDaoImpl();

            default:
                return null;
        }

    }
}
