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

package example1.sometrend.domain;

public class Sometrend extends Object{
	private Object date;
	private Object documentdate;
	private Object source;
	private Object keyword;
	private Object title;
	private Object content;
	private Object url;

	public Object getDate() {
		return date;
	}

	public void setDate(Object date) {
		this.date = date;
	}

	public Object getDocumentdate() {
		return documentdate;
	}

	public void setDocumentdate(Object documentdate) {
		this.documentdate = documentdate;
	}

	public Object getSource() {
		return source;
	}

	public void setSource(Object source) {
		this.source = source;
	}

	public Object getKeyword() {
		return keyword;
	}

	public void setKeyword(Object keyword) {
		this.keyword = keyword;
	}

	public Object getTitle() {
		return title;
	}

	public void setTitle(Object title) {
		this.title = title;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public Object getUrl() {
		return url;
	}

	public void setUrl(Object url) {
		this.url = url;
	}

	@Override
	public String toString(){
		return "["+this.date+"/"+this.keyword+"/"+this.title+"]";
	}
}
