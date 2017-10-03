package com.seguridad;

import java.util.Collection;

import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.application.exceptions.BusinessException;
import com.seguridad.dto.ActualizarUsuarioDTO;
import com.seguridad.dto.RegistrarUsuarioDTO;
import com.seguridad.dto.UsuarioDTO;

@Remote
public interface UsuariosServiceRemote {
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public abstract UsuarioDTO findByLogin(String login);

	public abstract ActualizarUsuarioDTO findForEditByLogin(String login);


	/**
	 * Ontiene una lista completa de todos los usuarios del sistema.
	 * 
	 * @return
	 */
	public abstract Collection<UsuarioDTO> listAll();

	/**
	 * Permite agregar un usuario al repositorio.
	 * 
	 * @param usuario Usuario a agregar.
	 * @return Usuario agregado.
	 * @throws BusinessException En caso de errores de negocio.
	 * @throws BusinessConstraintViolationException En caso de errores de validacion.
	 */
	void registrarUsuario(RegistrarUsuarioDTO usuario) throws BusinessException;

	/**
	 * Permite agregar un usuario al repositorio.
	 * 
	 * @param usuario Usuario a agregar.
	 * @throws BusinessException En caso de errores de negocio.
	 * @throws BusinessConstraintViolationException En caso de errores de validacion.
	 */
	void actualizarUsuario(ActualizarUsuarioDTO usuario) throws BusinessException;
	
	/**
	 * Permite modificar la contrase�a del usuario.
	 * 
	 * @param login Login de usuario a actualizar
	 * @param passwAnterior Password Anterior
	 * @param passwNuevo Password nuevo
	 * @return
	 * @throws BusinessException
	 * @throws BusinessConstraintViolationException
	 */
	void actualizarPassword(String login, String passwAnterior, String passwNuevo) throws BusinessException;

	/**
	 * Activa el usuario indicado.
	 * 
	 * @param login
	 */
	void activarUsuario(String login);

	/**
	 * Desactiva el usuario indicado.
	 * 
	 * @param login
	 */
	void desactivarUsuario(String login);
}