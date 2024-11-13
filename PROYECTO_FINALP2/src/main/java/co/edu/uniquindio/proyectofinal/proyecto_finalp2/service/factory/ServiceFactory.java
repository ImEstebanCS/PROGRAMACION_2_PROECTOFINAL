package co.edu.uniquindio.proyectofinal.proyecto_finalp2.service.factory;


import co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.Vendedor;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.service.interfaces.*;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.service.impl.*;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.service.interfaces.IVendedorCrud;

public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    public IAdministradorCrud createAdministradorCrud() {
        return new AdministradorCrudImpl();
    }

    public IVendedorCrud createVendedorCrud(Vendedor vendedor) {
        return (IVendedorCrud) new VendedorCrudImpl(vendedor);
    }

    public IProductoCrud createProductoCrud() {
        return (IProductoCrud) new ProductoCrudImpl();
    }
}
