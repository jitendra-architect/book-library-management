package com.example.book_library_management.service;

import com.example.book_library_management.entity.Book;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class BookArgumentsProvider implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
		return Stream.of(
				Arguments.of(Book.builder().title("Java").build()),
				Arguments.of(Book.builder().title("C#").build()));
	}
}