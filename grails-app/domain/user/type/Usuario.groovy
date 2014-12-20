package user.type

import org.codehaus.groovy.util.HashCodeHelper

class Usuario {

	String nombre
	String apellido

	String nombreDeUsuario

	EstadoUsuario estado = new HabilitadoEstadoUsuario()

	static constraints = {
		nombre blank: false, nullable: false
		apellido  blank: false, nullable: false
		nombreDeUsuario blank: false, nullable: false, unique: true
		estado nullable: false
	}

	static mapping = {
		estado type: EstadoUsuarioUserType
	}

	boolean equals(Object o) {
		if (o == null) false
		else if (o.is(this)) true
		// usamos instanceof y no comparación de clases ya que hibernate en algunos casos crea clases
		// on the fly para hacer proxys y en ese caso no matchearían las clases
		else if (!(o instanceof Usuario)) false
		else this.nombreDeUsuario == o.nombreDeUsuario
	}

	int hashCode() {
		int hc = HashCodeHelper.initHash()
		hc = HashCodeHelper.updateHash(hc, nombreDeUsuario)
		hc
	}
}
