package com.flozano.metrics;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

interface BackgroundReporter extends AutoCloseable {

	void addGauge(Gauge gauge, Supplier<Long> producer, long time, TimeUnit unit);

	@Override
	void close();

}