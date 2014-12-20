package user.type

import org.codehaus.groovy.util.HashCodeHelper

abstract class EstadoUsuario {
	abstract void chequearPuedeLoguearse(Usuario usuario)

	boolean equals(Object o) {
		if (o == null) false
		else if (o.is(this)) true
		else if (!(o instanceof EstadoUsuario)) false
		// la comparación se hace exclusivamente por la clase ya que no hay atributos ni estado
		else this.class == o.class
	}

	int hashCode() {
		int hc = HashCodeHelper.initHash()
		hc = HashCodeHelper.updateHash(hc, this.class)
		hc
	}
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
