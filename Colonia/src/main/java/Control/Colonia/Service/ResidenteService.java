package Control.Colonia.Service;

import Control.Colonia.Entity.Residente;

public interface ResidenteService {
    Residente login(String nombreResidente, String dpiResidente);
}