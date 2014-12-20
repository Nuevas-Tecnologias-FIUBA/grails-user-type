package user.type

abstract class EstadoUsuario {
	abstract void chequearPuedeLoguearse(Usuario usuario)
}

class HabilitadoEstadoUsuario extends EstadoUsuario {
	void chequearPuedeLoguearse(Usuario usuario) {
	}
}

class InhabilitadoEstadoUsuario extends EstadoUsuario {
	void chequearPuedeLoguearse(Usuario usuario) {
		throw new IllegalStateException("el usuario=${usuario} no puede loguearse xq está inhabilitado")
	}
}
