package com.seguridad;

import javax.ejb.Remote;

@Remote
public interface CambioClaveServiceRemote {

	void recuperarClave(String login);

	void comprobarCodigo(String login, String codR);

	void cambiarContrasenia(String login, String plainTextPasswordNuevo);

}
