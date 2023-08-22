package telran.time;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.UnsupportedTemporalTypeException;

public class NextFriday13 implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		if(!temporal.isSupported(ChronoField.DAY_OF_WEEK) || !temporal.isSupported(ChronoField.DAY_OF_MONTH)) {
			throw new UnsupportedTemporalTypeException("must support DAY_OF_WEEK and DAY_OF_MONTH");
		}
		if(!temporal.isSupported(ChronoUnit.MONTHS)) {
			throw new UnsupportedTemporalTypeException("must support ChronoUnit.MONTHS");
		}
		temporal = adjustTemporal(temporal);
		
		while(temporal.get(ChronoField.DAY_OF_WEEK) != DayOfWeek.FRIDAY.getValue()) {
			temporal = temporal.plus(1, ChronoUnit.MONTHS);
		}
		return temporal;
	}

	private Temporal adjustTemporal(Temporal temporal) {
		
		if (temporal.get(ChronoField.DAY_OF_MONTH) >= 13) {
			temporal = temporal.plus(1, ChronoUnit.MONTHS);
		}
		return temporal.with(ChronoField.DAY_OF_MONTH, 13);
	}

}
