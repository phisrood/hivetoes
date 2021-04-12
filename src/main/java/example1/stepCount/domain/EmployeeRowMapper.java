/*
 * Copyright 2006-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package example1.stepCount.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper<Object> {

	// "id"를 나타내는 상수
	public static final String ID_COLUMN = "id";

	// "name"를 나타내는 상수
	public static final String NAME_COLUMN = "name";

	// "credit"를 나타내는 상수
	public static final String CREDIT_COLUMN = "credit";
	

	/**
	 * CustomerCredit VO를 set
	 */
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();

		employee.setId(rs.getString(ID_COLUMN));
		employee.setName(rs.getString(NAME_COLUMN));
		employee.setCredit(rs.getInt(CREDIT_COLUMN));

		return employee;
	}

}
