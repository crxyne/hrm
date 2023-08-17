package org.crayne.gdboard.savefile.encrypt;

import org.apache.commons.codec.binary.Base64;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;

public class LevelDataEncryption {

    private LevelDataEncryption() {}

    public static byte @NotNull [] encodeBase64(@NotNull final String cleartext) {
        return Base64.encodeBase64(cleartext.getBytes(StandardCharsets.UTF_8));
    }

}
