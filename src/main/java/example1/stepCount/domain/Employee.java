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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class Employee extends Object{

	// field "id"
	@Id
	private String id;

	// field "name"
	private String name;

	// field "credit"
	private int credit;
	
	/**
	 * 생성자
	 */
	public Employee() {
	}

	public Employee(String id, String name, int credit) {
		this.id = id;
		this.name = name;
		this.credit = credit;
	}

	/**
	 * field 내용을 String으로 return
	 */
	@Override
	public String toString() {
		return "Employee [id=" + id + ",name=" + name + ", credit=" + credit + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}
}
