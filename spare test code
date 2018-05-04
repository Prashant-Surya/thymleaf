public class DemoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DemoService demoService;

	Demo mockCourse = new Demo( "Maven", "Java Build Tool","3.4.9");

	//String exampleCourseJson = "{\"name\":\"[A-Z][a-zA-Z]*\s*\",\"description\":\"[A-Z][a-zA-Z]*\s*\",\"version\":\"^(10|\d)(\.\d{1,2})?$\"}";

	/*@Test
	public void retrieveDetailsForPerson() throws Exception {

		Mockito.when(
				DemoService.retrieveperson(Mockito.anyString(),
						Mockito.anyString())).thenReturn(mockCourse);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/students/Student1/courses/Course1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:Course1,name:Spring,description:10 Steps}";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
*/

	@Test
	public void createPerson() throws Exception {
		Demo mockDemo = new Demo("Maven", "Java Build Tool","3.4.9");

		// studentService.addCourse to respond back with mockCourse
		//Mockito.when(
				//DemoService.addCourse(Mockito.anyString(),
					//	Mockito.any(Course.class))).thenReturn(mockCourse);

		// Send course as body to /students/Student1/courses
		//RequestBuilder requestBuilder = MockMvcRequestBuilders
				//.post("/students/Student1/courses")
				//.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
				//.contentType(MediaType.APPLICATION_JSON);

		//MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		//MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		//assertEquals("http://localhost/students/Student1/courses/1",
				//response.getHeader(HttpHeaders.LOCATION));

	}

}
