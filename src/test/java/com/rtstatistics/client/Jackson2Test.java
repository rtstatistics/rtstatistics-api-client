/**
 * 
 */
package com.rtstatistics.client;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.rtstatistics.client.model.CalculatedField;
import com.rtstatistics.client.model.Field;
import com.rtstatistics.client.model.NativeField;

/**
 * @author James Hu (Zhengmao Hu)
 *
 */
public class Jackson2Test {

	static public class Pojo{
		public String name;
		public Pojo2 pojo2;
		
		public Pojo(){
			
		}
		
		public Pojo(String name){
			this.name = name;
		}
	}
	
	static public class Pojo2{
		public Integer id;
	}
	
	static public class Pojo3{
		private String name;
		private Integer age;
		
		public Pojo3 setName(String name){
			this.name = name;
			return this;
		}
		
		public Pojo3 setAge(Integer age){
			this.age = age;
			return this;
		}

		public String getName() {
			return name;
		}

		public Integer getAge() {
			return age;
		}
	}
	
	@Test
	public void shouldSerializeArray() throws JsonProcessingException{
		Pojo[] objs = new Pojo[]{new Pojo("name1"), new Pojo("name2"), new Pojo("name3")};
		String json = Jackson2.objectMapper.writeValueAsString(objs);
		assertEquals("[{\"name\":\"name1\"},{\"name\":\"name2\"},{\"name\":\"name3\"}]", StringUtils.remove(json, ' '));
	}
	
	@Test
	public void shouldSerializeCollection() throws JsonProcessingException{
		List<Pojo> objs = Arrays.asList(new Pojo("name1"), new Pojo("name2"), new Pojo("name3"));
		String json = Jackson2.objectMapper.writeValueAsString(objs);
		assertEquals("[{\"name\":\"name1\"},{\"name\":\"name2\"},{\"name\":\"name3\"}]", StringUtils.remove(json, ' '));
	}
	
	@Test
	public void shouldDeserializeWithFluentSetter() throws JsonParseException, JsonMappingException, IOException{
		Pojo3 o = Jackson2.objectMapper.readValue("{\"name\":\"Me\", \"age\": 33}", Pojo3.class);
		assertEquals("Me", o.getName());
		assertEquals(Integer.valueOf(33), o.getAge());
	}
	
	@Test
	public void shouldRoundTripWithField() throws IOException{
		NativeField nf = new NativeField("nativeName", "native.path");
		CalculatedField cf = new CalculatedField("calcName", "a+b");
		
		NativeField nf2 = (NativeField) Jackson2.objectMapper.readValue(Jackson2.objectMapper.writeValueAsString(nf), Field.class);
		assertEquals(nf.getName(), nf2.getName());
		assertEquals(nf.getPath(), nf2.getPath());

		CalculatedField cf2 = (CalculatedField) Jackson2.objectMapper.readValue(Jackson2.objectMapper.writeValueAsString(cf), Field.class);
		assertEquals(cf.getName(), cf2.getName());
		assertEquals(cf.getFormula(), cf2.getFormula());
}
}
