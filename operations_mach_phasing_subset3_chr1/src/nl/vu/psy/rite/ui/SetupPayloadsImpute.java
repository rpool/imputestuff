// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SetupFslPayloads.java

package nl.vu.psy.rite.ui;

import nl.vu.psy.rite.exceptions.RiteException;
import nl.vu.psy.rite.operations.Recipe;
import nl.vu.psy.rite.operations.Step;
import nl.vu.psy.rite.operations.implementations.fileresolution.CopyInMongoFile;
import nl.vu.psy.rite.operations.implementations.fileresolution.CopyInOperation;
import nl.vu.psy.rite.operations.implementations.fileresolution.CopyOutMongoFile;
import nl.vu.psy.rite.operations.implementations.resources.ExpandResources;
import nl.vu.psy.rite.operations.implementations.shell.RunBashScriptOperation;
import nl.vu.psy.rite.persistence.mongo.MongoRecipeStore;

public class SetupPayloadsImpute {

	public static void main(String args[]) throws RiteException {
		MongoRecipeStore msr = new MongoRecipeStore("fluke.psy.vu.nl", 989, "impute", "recipes");

		int[] numChunks = { 45, 47, 38, 35, 36, 39, 31, 31, 27, 30, 28, 28, 22, 18, 16, 17, 13, 17, 9, 14, 8, 8 };

		// loop over chr
		// loop over sets
		// loop over chunks
		for (int i = 1; i <= 1; i++) {
			for (int j = 3; j <= 3; j++) {
				int currentChunkSize = numChunks[(i - 1)];
				for (int k = 1; k <= currentChunkSize; k++) {

					Recipe recipe = new Recipe("IMPUTE_" + i + "_" + j + "_" + k);

					// Expand resource
					Step s = new Step("expand");
					ExpandResources er = new ExpandResources();

					s.add(er);
					recipe.add(s);

					// Unzip and make executable mach
					s = new Step("setup_minimac");
					RunBashScriptOperation bco = new RunBashScriptOperation();
					StringBuffer script = new StringBuffer();
					script.append("#!/bin/bash\n");
					script.append("unzip -o minimac.zip\n");
					script.append("chmod +x minimac\n");
					bco.setScript(script.toString());

					s.add(bco);
					recipe.add(s);

					// Copy in reference haps
					s = new Step("copyin_refhaps");
					CopyInOperation co = new CopyInOperation();
					co.setRelicId("chr" + i + ".vcf");
					co.setNumTries(3);

					s.add(co);
					recipe.add(s);

					// Zip refhaps
					s = new Step("zip_refhaps");
					bco = new RunBashScriptOperation();
					script = new StringBuffer();
					script.append("#!/bin/bash\n");
					script.append("gzip chr" + i + ".vcf\n");
					bco.setScript(script.toString());

					s.add(bco);
					recipe.add(s);

					// Copyin haps
					s = new Step("copyin_haps");
					CopyInMongoFile cmo = new CopyInMongoFile();
					cmo.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".haps.gz");
					
					s.add(cmo);
					recipe.add(s);

					// Copyin snps
					s = new Step("copyin_snps");
					co = new CopyInOperation();
					co.setRelicId("chr" + i + "_set" + j + "_chunk" + k + ".snps");
					co.setNumTries(3);

					s.add(co);
					recipe.add(s);

					// Copyin autochunk
					s = new Step("copyin_autochunk");
					co = new CopyInOperation();
					co.setRelicId("chr" + i + "_set" + j + "autochunk");
					co.setNumTries(3);

					s.add(co);
					recipe.add(s);

					// Run minimac
					s = new Step("run_minimac");
					bco = new RunBashScriptOperation();
					script = new StringBuffer();
					script.append("#!/bin/bash\n");
					script.append("./minimac  --phased --probs --gzip --refhaps chr" + i + ".vcf.gz --vcfReference --haps chr" + i + "_set" + j + "_chunk" + k + ".haps.gz --snps chunk" + k + "-MDATA_MRG5_2_CHR" + i + "_NOPARENTS_" + j + ".dat.snps --autoclip autoChunk-MDATA_MRG5_2_CHR" + i + "_NOPARENTS_" + j + ".dat --prefix chr" + i + "_set" + j + "_chunk" + k + ".minimac &> chr" + i + "_set" + j + "_chunk" + k + ".minimac.log\n");
					bco.setScript(script.toString());

					s.add(bco);
					recipe.add(s);

					// Copy out all output files 
					s = new Step("copyout_dose");
					CopyOutMongoFile coo = new CopyOutMongoFile();
					coo.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".minimac.dose.gz");

					s.add(coo);
					recipe.add(s);

					s = new Step("copyout_erate");
					coo = new CopyOutMongoFile();
					coo.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".minimac.erate.gz");

					s.add(coo);
					recipe.add(s);
					
					s = new Step("copyout_hapdose");
					coo = new CopyOutMongoFile();
					coo.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".minimac.hapDose.gz");				

					s.add(coo);
					recipe.add(s);

					s = new Step("copyout_haps");
					coo = new CopyOutMongoFile();
					coo.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".minimac.haps.gz");
					
					s.add(coo);
					recipe.add(s);

					s = new Step("copyout_info_draft");
					coo = new CopyOutMongoFile();
					coo.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".minimac.info.draft");
					
					s.add(coo);
					recipe.add(s);

					s = new Step("copyout_info");
					coo = new CopyOutMongoFile();
					coo.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".minimac.info.gz");
					
					s.add(coo);
					recipe.add(s);

					s = new Step("copyout_prob");
					coo = new CopyOutMongoFile();
					coo.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".minimac.prob.gz");
					
					s.add(coo);
					recipe.add(s);
					
					s = new Step("copyout_rec");
					coo = new CopyOutMongoFile();
					coo.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".minimac.rec.gz");
					
					s.add(coo);
					recipe.add(s);

					s = new Step("copyout_log");
					coo = new CopyOutMongoFile();
					coo.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".minimac.log");
					
					s.add(coo);
					recipe.add(s);

					recipe.setTimeout(36000000);
					recipe.setResetOnFailure(true);
					recipe.setResetOnTimeout(true);
					msr.putRecipe(recipe);
				}
			}
		}
	}
}
