package user.type

class UsuarioController {

	def index() {
		render "hola!"
	}

	def crearUsuario() {
		Usuario nuevo = Usuario.withTransaction {
			new Usuario(params.nombreDeUsuario, params.apellido, params.nombre).save(failOnError: true)
		}

		render "Usuario nuevo creado=${nuevo}"
	}

	def buscarUsuario() {
		Usuario usuario = Usuario.get(params.id)
		render "Usuario encontrado=${usuario} estado=${usuario?.estado}"
	}
}
