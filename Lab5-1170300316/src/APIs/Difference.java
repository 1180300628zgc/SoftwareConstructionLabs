package APIs;

public class Difference {
	private int trackNumDiff;
	private int[] sameTrackObjNumDiff = new int[100000];
	
	public Difference(int trackNumDiff, int[] sameTrackObjNumDiff) {
		this.trackNumDiff = trackNumDiff;
		this.sameTrackObjNumDiff = sameTrackObjNumDiff;
	}
	
	public int GetTrackNumDiff() {
		return this.trackNumDiff;
	}
	
	public int[] GetSameTrackObjNumDiff() {
		return this.sameTrackObjNumDiff;
	}
}
