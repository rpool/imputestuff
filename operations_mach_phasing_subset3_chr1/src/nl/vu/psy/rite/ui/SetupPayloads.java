// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SetupFslPayloads.java

package nl.vu.psy.rite.ui;

import nl.vu.psy.rite.exceptions.RiteException;
import nl.vu.psy.rite.operations.Recipe;
import nl.vu.psy.rite.operations.Step;
import nl.vu.psy.rite.operations.implementations.fileresolution.CopyInOperation;
import nl.vu.psy.rite.operations.implementations.fileresolution.CopyOutMongoFile;
import nl.vu.psy.rite.operations.implementations.resources.ExpandResources;
import nl.vu.psy.rite.operations.implementations.shell.RunBashScriptOperation;
import nl.vu.psy.rite.persistence.mongo.MongoRecipeStore;

public class SetupPayloads {

	public static void main(String args[]) throws RiteException {
		MongoRecipeStore msr = new MongoRecipeStore("fluke.psy.vu.nl", 989, "impute", "recipes");
		
		int[] numChunks = {45, 47, 38, 35, 36, 39, 31, 31, 27, 30, 28, 28, 22, 18, 16, 17, 13, 17, 9, 14, 8, 8};

		// loop over chr
		// loop over sets
		// loop over chunks
		for (int i = 1; i <= 1; i++) {
			for (int j = 3; j <= 3; j++) {
				int currentChunkSize = numChunks[(i-1)];
				for (int k = 1; k <= currentChunkSize ; k++) {
				
					Recipe recipe = new Recipe("PHASE_" + i + "_" + j + "_" + k);

					// Expand resource
					Step s = new Step("expand");
					ExpandResources er = new ExpandResources();
					s.add(er);
					recipe.add(s);

					// Unzip and make executable mach
					s = new Step("setup_mach");
					RunBashScriptOperation bco = new RunBashScriptOperation();
					StringBuffer script = new StringBuffer();
					script.append("#!/bin/bash\n");
					script.append("unzip -o mach.zip\n");
					script.append("chmod +x mach\n");
					bco.setScript(script.toString());

					s.add(bco);
					recipe.add(s);

					// Copy in chunk: dat and ped
					s = new Step("copyin_dat");
					CopyInOperation co = new CopyInOperation();
					co.setRelicId("chr" + i + "_set" + j + "_chunk" + k + ".dat");
					co.setNumTries(3);

					s.add(co);
					recipe.add(s);

					s = new Step("copyin_ped");
					co = new CopyInOperation();
					co.setRelicId("chr" + i + "_set" + j + ".ped");
					co.setNumTries(3);

					s.add(co);
					recipe.add(s);

					// Run mach
					s = new Step("run_mach");
					bco = new RunBashScriptOperation();
					script = new StringBuffer();
					script.append("#!/bin/bash\n");
					script.append("./mach -d chunk" + k + "-MDATA_MRG5_2_CHR" + i + "_NOPARENTS_" + j + ".dat -p MDATA_MRG5_2_CHR" + i + "_NOPARENTS_" + j + ".ped --rounds 20 --states 200 --phase --prefix chr" + i + "_set" + j + "_chunk" + k + ".haps &> chr" + i + "_set" + j + "_chunk" + k + ".log\n");
					bco.setScript(script.toString());

					s.add(bco);
					recipe.add(s);
					
					// Copy out haploptypes and log
					s = new Step("copyout_haps");
					CopyOutMongoFile cmo = new CopyOutMongoFile();
					cmo.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".haps.gz");
					
					s.add(cmo);
					recipe.add(s);

					s = new Step("copyout_log");
					cmo = new CopyOutMongoFile();
					cmo.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".log");
					
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
