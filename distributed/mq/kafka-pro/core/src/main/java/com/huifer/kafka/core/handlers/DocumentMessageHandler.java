package com.huifer.kafka.core.handlers;

import com.huifer.kafka.core.excephandler.ExceptionHandler;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public abstract class DocumentMessageHandler extends SafelyMessageHandler {

	public DocumentMessageHandler() {
		super();
	}

	public DocumentMessageHandler(ExceptionHandler exceptionHandler) {
		super(exceptionHandler);
	}

	public DocumentMessageHandler(List<ExceptionHandler> exceptionHandlers) {
		super(exceptionHandlers);
	}

	protected void doExecute(String message) {
		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
			Document document = docBuilder.parse(new ByteArrayInputStream(message.getBytes()));
			doExecuteDocument(document);
		} catch (IOException | ParserConfigurationException | SAXException e) {
		}
	}

	protected abstract void doExecuteDocument(Document document);
}
