package bo;

import bo.impl.CustomerBoImpl;

public class BoFactory {
    private static BoFactory boFactory;

    public BoFactory() {
    }

    public static BoFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BoFactory() : boFactory;
    }
    public enum BoType{
        Customer
    }
    public SuperBo getBO(BoType type){
        switch (type){
            case Customer:
                return new CustomerBoImpl();
            default:
                return null;
        }
    }
}
