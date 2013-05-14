package nl.vu.psy.rite.ui;

import nl.vu.psy.relic.Relic;
import nl.vu.psy.relic.exceptions.RelicException;
import nl.vu.psy.relic.persistence.mongo.MongoStore;
import nl.vu.psy.relic.resolvers.ResolverDescriptor;
import nl.vu.psy.relic.resolvers.implementations.GridSRMResolver;

public class SetupRelics {
	public static void main(String args[]) throws RelicException {
		MongoStore ms = new MongoStore("fluke.psy.vu.nl", 989, "impute", "relics");
		
		int[] numChunks = {45, 47, 38, 35, 36, 39, 31, 31, 27, 30, 28, 28, 22, 18, 16, 17, 13, 17, 9, 14, 8, 8};
		// loop over chr
		// loop over sets
		// loop over chunks
		for (int i = 1; i <= 1; i++) {
			for (int j = 3; j <= 3; j++) {
				// Peds per subset
				Relic r = new Relic("chr" + i + "_set" + j + ".ped");
				r.setFileName("MDATA_MRG5_2_CHR"+i+"_NOPARENTS_"+j+".ped");
				ResolverDescriptor rd = new ResolverDescriptor(r.getIdentifier(), GridSRMResolver.ENVIRONMENT);
				rd.setProperty(GridSRMResolver.DescriptorKeys.SRMURL.getKey(), "srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/in/" + r.getFileName());
				ms.putRelic(r);
				ms.putResolverDescriptor(rd);
				
				int currentChunkSize = numChunks[(i-1)];
				for (int k = 1; k <= currentChunkSize ; k++) {
				
					// Dats per chunk
					r = new Relic("chr" + i + "_set" + j + "_chunk" + k + ".dat");
					r.setFileName("chunk" + k + "-MDATA_MRG5_2_CHR" + i + "_NOPARENTS_" + j + ".dat");
					rd = new ResolverDescriptor(r.getIdentifier(), GridSRMResolver.ENVIRONMENT);
					rd.setProperty(GridSRMResolver.DescriptorKeys.SRMURL.getKey(), "srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/in/" + r.getFileName());
					ms.putRelic(r);
					ms.putResolverDescriptor(rd);
	
					// output per chunk
//					r = new Relic("chr" + i + "_set" + j + "_chunk" + k + ".haps");
//					r.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".haps.gz");
//					rd = new ResolverDescriptor(r.getIdentifier(), GridSRMResolver.ENVIRONMENT);
//					rd.setProperty(GridSRMResolver.DescriptorKeys.SRMURL.getKey(), "srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out/" + r.getFileName());
//					ms.putRelic(r);
//					ms.putResolverDescriptor(rd);
//					
//					r = new Relic("chr" + i + "_set" + j + "_chunk" + k + ".log");
//					r.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".log");
//					rd = new ResolverDescriptor(r.getIdentifier(), GridSRMResolver.ENVIRONMENT);
//					rd.setProperty(GridSRMResolver.DescriptorKeys.SRMURL.getKey(), "srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out/" + r.getFileName());
//					ms.putRelic(r);
//					ms.putResolverDescriptor(rd);
					
				}
			}
		}
	}
}