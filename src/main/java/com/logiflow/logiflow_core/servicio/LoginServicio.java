package com.logiflow.logiflow_core.servicio;

import com.logiflow.logiflow_core.dto.request.*;
import com.logiflow.logiflow_core.dto.response.*;

public interface LoginServicio {
	
	LoginResponseDTO autenticarUsuario(LoginRequestDTO loginRequest);
	void cerrarSesion(String token);

}
