package nl.vu.psy.rite.ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class CreateDownloadScript {
	public static void main(String args[]) throws IOException {
		int[] numChunks = { 45, 47, 38, 35, 36, 39, 31, 31, 27, 30, 28, 28, 22, 18, 16, 17, 13, 17, 9, 14, 8, 8 };
		// loop over chr
		// loop over sets
		// loop over chunks
		for (int i = 1; i <= 22; i++) {
			// File per chromosome
			FileOutputStream fos = new FileOutputStream(new File("dl_chr" + i + ".sh"));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write("#!/bin/bash\n");
			bw.write("echo downloading chromosome: " + i + "\n");
			bw.write("wd=`pwd`\n");
			for (int j = 1; j <= 10; j++) {
				bw.write("echo downloading subset: " + j + "\n");
				int currentChunkSize = numChunks[(i - 1)];
				for (int k = 1; k <= currentChunkSize; k++) {
					bw.write("echo downloading chunk: " + k + " for subset" + j + "\n");
					bw.write("srmcp -server_mode=passive srm://srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out/chr" + i + "_set" + j + "_chunk" + k + ".haps.gz file:///\"$wd\"/chr" + i + "_set" + j + "_chunk" + k + ".haps.gz\n");
					bw.write("srmcp -server_mode=passive srm://srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out/chr" + i + "_set" + j + "_chunk" + k + ".log file:///\"$wd\"/chr" + i + "_set" + j + "_chunk" + k + ".log\n");
					bw.write("srmcp -server_mode=passive srm://srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out2/chr" + i + "_set" + j + "_chunk" + k + ".minimac.dose.gz file:///\"$wd\"/chr" + i + "_set" + j + "_chunk" + k + ".minimac.dose.gz\n");
					bw.write("srmcp -server_mode=passive srm://srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out2/chr" + i + "_set" + j + "_chunk" + k + ".minimac.erate.gz file:///\"$wd\"/chr" + i + "_set" + j + "_chunk" + k + ".minimac.erate.gz\n");
					bw.write("srmcp -server_mode=passive srm://srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out2/chr" + i + "_set" + j + "_chunk" + k + ".minimac.hapDose.gz file:///\"$wd\"/chr" + i + "_set" + j + "_chunk" + k + ".minimac.hapDose.gz\n");
					bw.write("srmcp -server_mode=passive srm://srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out2/chr" + i + "_set" + j + "_chunk" + k + ".minimac.haps.gz file:///\"$wd\"/chr" + i + "_set" + j + "_chunk" + k + ".minimac.haps.gz\n");
					bw.write("srmcp -server_mode=passive srm://srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out2/chr" + i + "_set" + j + "_chunk" + k + ".minimac.info.draft file:///\"$wd\"/chr" + i + "_set" + j + "_chunk" + k + ".minimac.info.draft\n");
					bw.write("srmcp -server_mode=passive srm://srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out2/chr" + i + "_set" + j + "_chunk" + k + ".minimac.info.gz file:///\"$wd\"/chr" + i + "_set" + j + "_chunk" + k + ".minimac.info.gz\n");
					bw.write("srmcp -server_mode=passive srm://srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out2/chr" + i + "_set" + j + "_chunk" + k + ".minimac.prob.gz file:///\"$wd\"/chr" + i + "_set" + j + "_chunk" + k + ".minimac.prob.gz\n");
					bw.write("srmcp -server_mode=passive srm://srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out2/chr" + i + "_set" + j + "_chunk" + k + ".minimac.rec.gz file:///\"$wd\"/chr" + i + "_set" + j + "_chunk" + k + ".minimac.rec.gz\n");
					bw.write("srmcp -server_mode=passive srm://srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out2/chr" + i + "_set" + j + "_chunk" + k + ".minimac.log file:///\"$wd\"/chr" + i + "_set" + j + "_chunk" + k + ".minimac.log\n");

					bw.flush();
				}
			}
			bw.flush();
			bw.close();

		}
	}

}