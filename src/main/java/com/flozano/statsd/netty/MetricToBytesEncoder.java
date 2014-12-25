package com.flozano.statsd.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flozano.statsd.metrics.Metric;

public class MetricToBytesEncoder extends MessageToByteEncoder<Metric> {

	private static Logger LOGGER = LoggerFactory
			.getLogger(MetricToBytesEncoder.class);

	@Override
	public boolean acceptOutboundMessage(Object msg) throws Exception {
		return msg instanceof Metric;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, Metric msg, ByteBuf out)
			throws Exception {
		LOGGER.warn("Writing {} ", msg);
		msg.toStringParts((part) -> out.writeBytes(part
				.getBytes(StandardCharsets.UTF_8)));
		LOGGER.warn("Wrote {} ", msg);
	}

}
