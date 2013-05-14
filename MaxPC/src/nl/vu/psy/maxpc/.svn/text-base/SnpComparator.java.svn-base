package nl.vu.psy.maxpc;

import java.util.Comparator;

public class SnpComparator implements Comparator<SnpScore> {

	@Override
	public int compare(SnpScore o1, SnpScore o2) {
		if (o1.getPos() < o2.getPos()) {
			return -1;
		} else if (o1.getPos() == o2.getPos()) {
			return 0;
		} else if (o1.getPos() > o2.getPos()) {
			return 1;
		}
		return 0;
	}

}
