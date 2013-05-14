package nl.vu.psy.maxpc;

import java.io.File;
import java.util.ArrayList;

import joptsimple.OptionParser;
import joptsimple.OptionSpec;

@SuppressWarnings("unchecked")
public class MaxAPCOptionParser extends OptionParser {
	public OptionSpec<File> genFile;
	public OptionSpec<File> sampleFile;
	public OptionSpec<String> outPrefix; 
	public OptionSpec help;

	public MaxAPCOptionParser() {
		super();

		ArrayList<String> genFileOption = new ArrayList<String>();
		genFileOption.add("g");
		genFileOption.add("gen");
		
		ArrayList<String> sampleFileOption = new ArrayList<String>();
		sampleFileOption.add("s");
		sampleFileOption.add("sample");
		
		ArrayList<String> prefixOption = new ArrayList<String>();
		prefixOption.add("p");
		prefixOption.add("o");
		prefixOption.add("prefix");
				
		ArrayList<String> helpCommand = new ArrayList<String>();
		helpCommand.add("h");
		helpCommand.add("?");
		helpCommand.add("help");

		genFile = acceptsAll(genFileOption, "Required - IMPUTE/SNPTEST .gen file with the posterior genotype probabilities.").withRequiredArg().ofType(File.class).describedAs("file");
		sampleFile = acceptsAll(sampleFileOption, "Required - IMPUTE/SNPTEST .sample file containing the individual identifiers").withRequiredArg().ofType(File.class).describedAs("file");
		outPrefix = acceptsAll(prefixOption, "Prefix for the output files.").withOptionalArg().ofType(String.class).describedAs("string");
		help = acceptsAll(helpCommand, "Prints this help and exits.");
	}

}
