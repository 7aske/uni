package rs.ac.metropolitan.cs230.controller;

import rs.ac.metropolitan.cs230.model.Student;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.json.*;

@Named(value = "studentController")
@RequestScoped
public class StudentController {

	@Resource(mappedName = "jms/messageQueue")
	private Topic messageQueue;
	@Inject
	@JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
	private JMSContext context;

	@Inject
	private Student student;

	private String json;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String generateJson() {
		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();

		JsonArrayBuilder phoneNumbersBuilder = Json.createArrayBuilder();
		student.getPhoneNumbers().forEach(phoneNumbersBuilder::add);

		JsonObject jsonObject = jsonObjectBuilder
				.add("index", student.getIndex())
				.add("name", student.getName())
				.add("status", student.getStatus())
				.add("traditional", student.isTraditional())
				.add("phone_numbers", phoneNumbersBuilder.build())
				.add("role", student.getRole())
				.add("address", Json.createObjectBuilder()
						.add("street", student.getStreet())
						.add("city", student.getCity())
						.add("zipcode", student.getZipcode())
						.build()
				)
				.build();

		StringWriter stringWriter = new StringWriter();

		try (JsonWriter jsonWriter = Json.createWriter(stringWriter)) {
			jsonWriter.writeObject(jsonObject);
		}
		setJson(stringWriter.toString());
		sendJMSMessageToMessageQueue(json);
		return "created_json";
	}

	public String parseJson() {
		JsonObject jsonObject;

		try (JsonReader jsonReader = Json.createReader(new StringReader(json))) {
			jsonObject = jsonReader.readObject();
		}

		student.setIndex(jsonObject.getInt("index"));
		student.setName(jsonObject.getString("name"));
		student.setStatus(jsonObject.getString("status"));
		student.setTraditional(jsonObject.getBoolean("traditional"));
		student.setRole(jsonObject.getString("role"));
		student.setCity(jsonObject.getJsonObject("address").getString("city"));
		student.setStreet(jsonObject.getJsonObject("address").getString("street"));
		student.setZipcode(jsonObject.getJsonObject("address").getInt("zipcode"));
		student.setPhoneNumbers(jsonObject.getJsonArray("phone_numbers")
				.getValuesAs(JsonString.class)
				.stream()
				.map(JsonString::getString)
				.collect(Collectors.toList()));

		return "parsed_json";
	}

	private void sendJMSMessageToMessageQueue(String messageData) {
		context.createProducer().send(messageQueue, messageData);
	}

}
