package com.upm.newsclient.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

	public static void main(String[] args) {
		
		Options options = new Options();
		
		options.addOption("getArticle", true, "Get an article by its name");
		
		CommandLineParser parser = new DefaultParser();
		
		try {
			CommandLine cmd = parser.parse(options, args);
			
			if (cmd.hasOption("getArticle")) {
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
