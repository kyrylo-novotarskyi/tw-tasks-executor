package com.transferwise.tasks.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;

@UtilityClass
public class UUIDUtils {
    @SuppressWarnings("checkstyle:magicnumber")
    public static UUID toUUID(byte[] bytes) {
        if (bytes == null) {
            throw new IllegalArgumentException("bytes must not be null.");
        } else if (bytes.length != 16) {
            throw new IllegalArgumentException("bytes has to contain exactly 16 bytes.");
        }

        long msb = 0;
        long lsb = 0;
        for (int i = 0; i < 8; i++)
            msb = (msb << 8) | (bytes[i] & 0xff);
        for (int i = 8; i < 16; i++)
            lsb = (lsb << 8) | (bytes[i] & 0xff);
        long mostSigBits = msb;
        long leastSigBits = lsb;

        return new UUID(mostSigBits, leastSigBits);
    }

    @SuppressWarnings("checkstyle:magicnumber")
    public static byte[] toBytes(UUID uuid) {
        byte[] bytes = new byte[16];
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        bb.order(ByteOrder.BIG_ENDIAN); //  or ByteOrder.BIG_ENDIAN
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());

        return bytes;
    }

    @SuppressWarnings("checkstyle:magicnumber")
    public static UUID toUUID(String st) {
        String hyphenlessUuid = StringUtils.remove(st, '-');
        BigInteger bigInteger = new BigInteger(hyphenlessUuid, 16);
        return new UUID(bigInteger.shiftRight(64).longValue(), bigInteger.longValue());
    }

    public static UUID toUUID(Object arg) {
        if (arg == null) {
            return null;
        } else if (arg instanceof UUID) {
            return (UUID) arg;
        } else if (arg instanceof byte[]) {
            return UUIDUtils.toUUID((byte[]) arg);
        }

        throw new NotImplementedException("" + arg.getClass().getName() + " is not supported.");
    }
}
