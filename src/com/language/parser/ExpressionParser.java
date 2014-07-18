package com.language.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java_cup.runtime.Symbol;

import com.language.model.exceptions.ParsingException;
import com.language.model.expression.EvaluatableExpression;

public class ExpressionParser {

	public static EvaluatableExpression parse(String expText) throws Exception {
		byte[] expbytes = expText.getBytes();
		return parseBytes(expbytes);
	}
	
	public static EvaluatableExpression parseFile(String filePath) throws IOException {
		 byte [] b = Files.readAllBytes(Paths.get(filePath));
		 return parseBytes(b);
	}
	
	private static EvaluatableExpression parseBytes(byte[] b) {
		ByteArrayInputStream bais = new ByteArrayInputStream(b);
		ParserCup parser = new ParserCup(new Scanner(bais));
		int line = -1;
		int column = -1;
		try {
			Symbol topsym = parser.parse();
			line = topsym.left;
			column = topsym.right;
			Object obj = topsym.value;
			 if (!(obj instanceof EvaluatableExpression)) {
					throw new IllegalArgumentException("The object parsed is not instance of EvaluatableExpression");
			 }
			return (EvaluatableExpression)obj;
		} catch (Throwable ex) {
			if (line!=-1 && column!=-1) {
				throw new ParsingException("Error at line " + line + " column " + column, ex);
			} else {
				throw new ParsingException(ex);
			}
		}
	}
}
