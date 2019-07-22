package APIs;

import centralObject.CentralObject;
import circularOrbit.CircularOrbit;
import track.Track;

public interface StrategySS {
	
	/**
	 * Referring to the IO strategy "readByBufferedReader".
	 * @param fileName
	 * @param orbit
	 */
	public void StellarSystemI(String fileName, CircularOrbit<CentralObject, Track> orbit);
	
	public void StellarSystemO(String fileName, CircularOrbit<CentralObject, Track> orbit);
	
}
