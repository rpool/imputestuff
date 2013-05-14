package nl.vu.psy.rite.ui;

import nl.vu.psy.rite.exceptions.RiteException;
import nl.vu.psy.rite.operations.Recipe;
import nl.vu.psy.rite.operations.Step;
import nl.vu.psy.rite.operations.implementations.fileresolution.CopyInMongoFile;
import nl.vu.psy.rite.operations.implementations.fileresolution.CopyOutMongoFile;
import nl.vu.psy.rite.operations.implementations.resources.ExpandResources;
import nl.vu.psy.rite.operations.implementations.shell.RunBashScriptOperation;
import nl.vu.psy.rite.persistence.mongo.MongoRecipeStore;

public class SetupPayloads {

	public static void main(String args[]) throws RiteException {
		MongoRecipeStore msr = new MongoRecipeStore("fluke.psy.vu.nl", 989, "merge", "recipes");

		int[] numChunks = { 45, 47, 38, 35, 36, 39, 31, 31, 27, 30, 28, 28, 22, 18, 16, 17, 13, 17, 9, 14, 8, 8 };

		// loop over chr
		// loop over chunks

		for (int i = 1; i <= 22; i++) {
			if (i == 19) {
				// skip
			} else {
				int currentChunkSize = numChunks[(i - 1)];
				for (int k = 1; k <= currentChunkSize; k++) {

					StringBuffer gens = new StringBuffer();
					StringBuffer samples = new StringBuffer();

					Recipe recipe = new Recipe("MERGE_" + i + "_" + k);

					// Expand resource
					Step s = new Step("expand");
					ExpandResources er = new ExpandResources();
					s.add(er);
					recipe.add(s);

					// Unzip and make executable gtool
					s = new Step("setup_script");
					RunBashScriptOperation bco = new RunBashScriptOperation();
					StringBuffer script = new StringBuffer();
					script.append("#!/bin/bash\n");
					script.append("unzip -o gtool.zip\n");
					script.append("chmod +x gtool\n");
					bco.setScript(script.toString());

					s.add(bco);
					recipe.add(s);

					// Get gen gzs and samples
					for (int j = 1; j <= 10; j++) {
						s = new Step("copyin_gen_" + j);
						CopyInMongoFile cmo = new CopyInMongoFile();
						cmo.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".gen.gz");
						gens.append(" chr" + i + "_set" + j + "_chunk" + k + ".gen");

						s.add(cmo);
						recipe.add(s);

						s = new Step("unzip_gen_" + j);
						bco = new RunBashScriptOperation();
						script = new StringBuffer();
						script.append("#!/bin/bash\n");
						script.append("gunzip chr" + i + "_set" + j + "_chunk" + k + ".gen.gz\n");
						bco.setScript(script.toString());

						s.add(bco);
						recipe.add(s);

						s = new Step("copyin_sample_" + j);
						cmo = new CopyInMongoFile();
						cmo.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".sample");
						samples.append(" chr" + i + "_set" + j + "_chunk" + k + ".sample");

						s.add(cmo);
						recipe.add(s);
					}

					String outgen = "chr" + i + "_chunk" + k + ".gen";
					String outsample = "chr" + i + "_chunk" + k + ".sample";

					s = new Step("run_gtool");
					bco = new RunBashScriptOperation();
					script = new StringBuffer();
					script.append("#!/bin/bash\n");
					script.append("./gtool -M --g" + gens.toString() + " --s" + samples.toString() + " --og " + outgen + " --os " + outsample + "\n");
					bco.setScript(script.toString());

					s.add(bco);
					recipe.add(s);

					// Compress gen file
					s = new Step("compress_gen");
					bco = new RunBashScriptOperation();
					script = new StringBuffer();
					script.append("#!/bin/bash\n");

					script.append("gzip chr" + i + "_chunk" + k + ".gen\n");
					bco.setScript(script.toString());

					s.add(bco);
					recipe.add(s);

					// Copy out sample and gen.gz
					// Copy output to mongo
					s = new Step("copyout_gen");
					CopyOutMongoFile cmo = new CopyOutMongoFile();
					cmo.setFileName("chr" + i + "_chunk" + k + ".gen.gz");

					s.add(cmo);
					recipe.add(s);

					s = new Step("copyout_sample");
					cmo = new CopyOutMongoFile();
					cmo.setFileName("chr" + i + "_chunk" + k + ".sample");

					s.add(cmo);
					recipe.add(s);

					recipe.setTimeout(28800000);
					recipe.setResetOnFailure(true);
					recipe.setResetOnTimeout(true);
					msr.putRecipe(recipe);
				}
			}
		}
	}

}
