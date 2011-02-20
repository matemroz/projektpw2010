//$Id: $
/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2009, Red Hat Middleware LLC or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Middleware LLC.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 *
 */
package org.hibernate.test.annotations.lob;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.java.CharacterArrayTypeDescriptor;
import org.hibernate.type.descriptor.sql.LongVarcharTypeDescriptor;

/**
 * A type that maps JDBC {@link java.sql.Types#LONGVARCHAR LONGVARCHAR} and {@code Character[]}.
 * 
 * @author Strong Liu
 */
public class CharacterArrayTextType extends AbstractSingleColumnStandardBasicType<Character[]> {
	public static final CharacterArrayTextType INSTANCE = new CharacterArrayTextType();

	public CharacterArrayTextType() {
		super( LongVarcharTypeDescriptor.INSTANCE, CharacterArrayTypeDescriptor.INSTANCE );
	}

	public String getName() {
		// todo name these annotation types for addition to the registry
		return null;
	}
}
