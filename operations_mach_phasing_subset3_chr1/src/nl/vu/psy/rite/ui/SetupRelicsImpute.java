package nl.vu.psy.rite.ui;

import nl.vu.psy.relic.Relic;
import nl.vu.psy.relic.exceptions.RelicException;
import nl.vu.psy.relic.persistence.mongo.MongoStore;
import nl.vu.psy.relic.resolvers.ResolverDescriptor;
import nl.vu.psy.relic.resolvers.implementations.GridSRMResolver;

public class SetupRelicsImpute {
	public static void main(String args[]) throws RelicException {
		MongoStore ms = new MongoStore("fluke.psy.vu.nl", 989, "impute", "relics");

		int[] numChunks = { 45, 47, 38, 35, 36, 39, 31, 31, 27, 30, 28, 28, 22, 18, 16, 17, 13, 17, 9, 14, 8, 8 };
		// loop over chr
		// loop over sets
		// loop over chunks
		for (int i = 1; i <= 1; i++) {
			Relic r = new Relic("chr" + i + ".vcf");
			r.setFileName("chr" + i + ".vcf");
			ResolverDescriptor rd = new ResolverDescriptor(r.getIdentifier(), GridSRMResolver.ENVIRONMENT);
			rd.setProperty(GridSRMResolver.DescriptorKeys.SRMURL.getKey(), "srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl4/" + r.getFileName());
			ms.putRelic(r);
			ms.putResolverDescriptor(rd);

			for (int j = 3; j <= 3; j++) {
				// input autochunk
				// autoChunk-MDATA_MRG5_2_CHR9_NOPARENTS_6.dat
				r = new Relic("chr" + i + "_set" + j + "autochunk");
				r.setFileName("autoChunk-MDATA_MRG5_2_CHR" + i + "_NOPARENTS_" + j + ".dat");
				rd = new ResolverDescriptor(r.getIdentifier(), GridSRMResolver.ENVIRONMENT);
				rd.setProperty(GridSRMResolver.DescriptorKeys.SRMURL.getKey(), "srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/in2/" + r.getFileName());
				ms.putRelic(r);
				ms.putResolverDescriptor(rd);

				int currentChunkSize = numChunks[(i - 1)];
				for (int k = 1; k <= currentChunkSize; k++) {

					// input snps per chunk
					// chunk1-MDATA_MRG5_2_CHR1_NOPARENTS_1.dat.snps
					r = new Relic("chr" + i + "_set" + j + "_chunk" + k + ".snps");
					r.setFileName("chunk" + k + "-MDATA_MRG5_2_CHR" + i + "_NOPARENTS_" + j + ".dat.snps");
					rd = new ResolverDescriptor(r.getIdentifier(), GridSRMResolver.ENVIRONMENT);
					rd.setProperty(GridSRMResolver.DescriptorKeys.SRMURL.getKey(), "srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/in2/" + r.getFileName());
					ms.putRelic(r);
					ms.putResolverDescriptor(rd);

					// input hap per chunk
//					r = new Relic("chr" + i + "_set" + j + "_chunk" + k + ".haps");
//					r.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".haps.gz");
//					rd = new ResolverDescriptor(r.getIdentifier(), GridSRMResolver.ENVIRONMENT);
//					rd.setProperty(GridSRMResolver.DescriptorKeys.SRMURL.getKey(), "srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out/" + r.getFileName());
//					ms.putRelic(r);
//					ms.putResolverDescriptor(rd);
//					
					// output files
//					r = new Relic("chr" + i + "_set" + j + "_chunk" + k + ".minimac.dose.gz");
//					r.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".minimac.dose.gz");
//					rd = new ResolverDescriptor(r.getIdentifier(), GridSRMResolver.ENVIRONMENT);
//					rd.setProperty(GridSRMResolver.DescriptorKeys.SRMURL.getKey(), "srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out2/" + r.getFileName());
//					ms.putRelic(r);
//					ms.putResolverDescriptor(rd);
//					
//					r = new Relic("chr" + i + "_set" + j + "_chunk" + k + ".minimac.erate.gz");
//					r.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".minimac.erate.gz");
//					rd = new ResolverDescriptor(r.getIdentifier(), GridSRMResolver.ENVIRONMENT);
//					rd.setProperty(GridSRMResolver.DescriptorKeys.SRMURL.getKey(), "srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out2/" + r.getFileName());
//					ms.putRelic(r);
//					ms.putResolverDescriptor(rd);
//				
//					r = new Relic("chr" + i + "_set" + j + "_chunk" + k + ".minimac.hapDose.gz");
//					r.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".minimac.hapDose.gz");
//					rd = new ResolverDescriptor(r.getIdentifier(), GridSRMResolver.ENVIRONMENT);
//					rd.setProperty(GridSRMResolver.DescriptorKeys.SRMURL.getKey(), "srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out2/" + r.getFileName());
//					ms.putRelic(r);
//					ms.putResolverDescriptor(rd);
//					
//					r = new Relic("chr" + i + "_set" + j + "_chunk" + k + ".minimac.haps.gz");
//					r.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".minimac.haps.gz");
//					rd = new ResolverDescriptor(r.getIdentifier(), GridSRMResolver.ENVIRONMENT);
//					rd.setProperty(GridSRMResolver.DescriptorKeys.SRMURL.getKey(), "srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out2/" + r.getFileName());
//					ms.putRelic(r);
//					ms.putResolverDescriptor(rd);
//					
//					r = new Relic("chr" + i + "_set" + j + "_chunk" + k + ".minimac.info.draft");
//					r.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".minimac.info.draft");
//					rd = new ResolverDescriptor(r.getIdentifier(), GridSRMResolver.ENVIRONMENT);
//					rd.setProperty(GridSRMResolver.DescriptorKeys.SRMURL.getKey(), "srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out2/" + r.getFileName());
//					ms.putRelic(r);
//					ms.putResolverDescriptor(rd);
//
//					r = new Relic("chr" + i + "_set" + j + "_chunk" + k + ".minimac.info.gz");
//					r.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".minimac.info.gz");
//					rd = new ResolverDescriptor(r.getIdentifier(), GridSRMResolver.ENVIRONMENT);
//					rd.setProperty(GridSRMResolver.DescriptorKeys.SRMURL.getKey(), "srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out2/" + r.getFileName());
//					ms.putRelic(r);
//					ms.putResolverDescriptor(rd);
//					
//					r = new Relic("chr" + i + "_set" + j + "_chunk" + k + ".minimac.prob.gz");
//					r.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".minimac.prob.gz");
//					rd = new ResolverDescriptor(r.getIdentifier(), GridSRMResolver.ENVIRONMENT);
//					rd.setProperty(GridSRMResolver.DescriptorKeys.SRMURL.getKey(), "srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out2/" + r.getFileName());
//					ms.putRelic(r);
//					ms.putResolverDescriptor(rd);
//					
//					r = new Relic("chr" + i + "_set" + j + "_chunk" + k + ".minimac.rec.gz");
//					r.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".minimac.rec.gz");
//					rd = new ResolverDescriptor(r.getIdentifier(), GridSRMResolver.ENVIRONMENT);
//					rd.setProperty(GridSRMResolver.DescriptorKeys.SRMURL.getKey(), "srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out2/" + r.getFileName());
//					ms.putRelic(r);
//					ms.putResolverDescriptor(rd);
//					
//					r = new Relic("chr" + i + "_set" + j + "_chunk" + k + ".minimac.log");
//					r.setFileName("chr" + i + "_set" + j + "_chunk" + k + ".minimac.log");
//					rd = new ResolverDescriptor(r.getIdentifier(), GridSRMResolver.ENVIRONMENT);
//					rd.setProperty(GridSRMResolver.DescriptorKeys.SRMURL.getKey(), "srm.grid.sara.nl/pnfs/grid.sara.nl/data/lsgrid/VU_psy/vmk/gonl/out2/" + r.getFileName());
//					ms.putRelic(r);
//					ms.putResolverDescriptor(rd);
				}
			}
		}
	}
}