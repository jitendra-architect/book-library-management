package com.example.book_library_management.service;

import com.example.book_library_management.entity.Book;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BookServiceTests {

	@Autowired
	private BookService bookService;

	@BeforeAll
	static void setUp(){
		System.out.println("setUp");
	}

	@BeforeEach
	void eachTest(){
		System.out.println("eachTest");
	}

	@AfterAll
	static void eachTearDown(){
		System.out.println("eachTearDown");
	}

	@AfterEach
	void eachTearDown2(){
		System.out.println("eachTearDown2");
	}

	//@Disabled
	@Test
	void findAll() {
		List<Book> allBooks = bookService.getAllBooks();
		Assertions.assertEquals( 1, allBooks.size(), "Books not found");
	}

	@ParameterizedTest
	@ValueSource(strings = {
			"ram",
			"shyam",
			"vipul"
	})
	void findBookByTitle(String title) {
		Assertions.assertNotNull(title, "Title should not be null");
	}
	//@Disabled
	@ParameterizedTest
	@CsvSource({
			"1,1,2",
			"2,10,12",
			"3,3,9"
	})
	void test(int a, int b, int expected)
	{
		Assertions.assertEquals( expected, a+b, "Incorrect result");
	}
}
