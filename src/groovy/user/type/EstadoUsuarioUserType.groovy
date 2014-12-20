package user.type

import org.hibernate.usertype.UserType

import java.io.Serializable
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

import org.hibernate.HibernateException
import org.hibernate.engine.spi.SessionImplementor

import org.hibernate.type.*

/**
 * Ver referencia de los métodos acá:
 * https://github.com/hibernate/hibernate-orm/blob/master/hibernate-core/src/main/java/org/hibernate/usertype/UserType.java
 **/
class EstadoUsuarioUserType implements UserType {

	Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) {
		String estadoUsuario = StringType.INSTANCE.get(rs, names[0])

		if (estadoUsuario) {
			switch (estadoUsuario) {
				case 'inhabilitado': return new InhabilitadoEstadoUsuario()
				case 'habilitado': return new HabilitadoEstadoUsuario()
				default: throw new IllegalArgumentException("no se reconoce estado usuario con valor=${estadoUsuario}")
			}
		} else {
			null
		}
	}

	void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) {
		if (value != null) {
			StringType.INSTANCE.set(st, convertirCodigo(value), index)
		} else {
			StringType.INSTANCE.set(st, null, index)
		}
	}

	int[] sqlTypes() {
		[StringType.INSTANCE.sqlType()] as int[]
	}

	Class returnedClass() {
		EstadoUsuario
	}

	boolean equals(Object x, Object y) {
		if (x == y) true
		else if (x == null || y == null) false
		else x == y
	}

	int hashCode(Object x) {
		x?.hashCode() ?: 0
	}

	Object deepCopy(Object value) {
		// al ser inmutable no tiene sentido hacer una copia profunda
		value
	}

	boolean isMutable() {
		// nuestro estado usuario es inmutable, no cambia su estado interno
		false
	}

	private String convertirCodigo(EstadoUsuario estadoUsuario) {
		switch (estadoUsuario.class) {
			case HabilitadoEstadoUsuario.class: return 'habilitado'
			case InhabilitadoEstadoUsuario.class: return 'inhabilitado'
			default: throw new IllegalArgumentException("no se reconoce estado usuario=${estadoUsuario} ni su clase=${estadoUsuario.class}")
		}
	}

	Serializable disassemble(Object value) {
		throw new UnsupportedOperationException("no soportado value=${value}")
	}

	Object assemble(Serializable cached, Object owner) {
		throw new UnsupportedOperationException("no soportado ${cached} ${owner}")
	}

	Object replace(Object original, Object target, Object owner) {
		throw new UnsupportedOperationException("no soportado ${original} ${target} ${owner}")
	}
}
