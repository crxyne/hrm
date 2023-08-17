package org.crayne.gdboard.level.data.object.type.decoration;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.savefile.decrypt.LevelDataDecryption;
import org.crayne.gdboard.savefile.encrypt.LevelDataEncryption;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectData;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;

@SuppressWarnings("unused")
public class TextObject extends ColorableObject {

    @NotNull
    private String base64EncodedText;

    @NotNull
    private String clearText;

    public TextObject(final int objectID, final float positionX, final float positionY, @NotNull final String text) {
        super(objectID, positionX, positionY);
        this.clearText = text;
        this.base64EncodedText = new String(LevelDataEncryption.encodeBase64(text), StandardCharsets.UTF_8);
    }

    public TextObject(final int objectID, final float positionX, final float positionY, @NotNull final String text,
                      final boolean encoded) {
        super(objectID, positionX, positionY);
        if (!encoded) {
            this.clearText = text;
            this.base64EncodedText = new String(LevelDataEncryption.encodeBase64(text), StandardCharsets.UTF_8);
            return;
        }
        this.clearText = new String(LevelDataDecryption.decodeBase64(text), StandardCharsets.UTF_8);
        this.base64EncodedText = text;
    }

    public TextObject(@NotNull final LevelObject levelObject, @NotNull final String text) {
        super(levelObject);
        this.clearText = text;
        this.base64EncodedText = new String(LevelDataEncryption.encodeBase64(text), StandardCharsets.UTF_8);
    }

    public TextObject(@NotNull final LevelObject levelObject, @NotNull final String text, final boolean encoded) {
        super(levelObject);
        if (!encoded) {
            this.clearText = text;
            this.base64EncodedText = new String(LevelDataEncryption.encodeBase64(text), StandardCharsets.UTF_8);
            return;
        }
        this.clearText = new String(LevelDataDecryption.decodeBase64(text), StandardCharsets.UTF_8);
        this.base64EncodedText = text;
    }

    public TextObject(final int objectID, final float positionX, final float positionY) {
        this(objectID, positionX, positionY, "A", false);
    }

    public TextObject(@NotNull final LevelObject levelObject) {
        this(levelObject, "A", false);
    }

    public TextObject(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.base64EncodedText = objectProperties.stringBase64Property(LevelObjectData.TEXT_BASE64);
        this.clearText = new String(LevelDataDecryption.decodeBase64(base64EncodedText), StandardCharsets.UTF_8);
    }

    @NotNull
    public String base64EncodedText() {
        return base64EncodedText;
    }

    public void clearText(@NotNull final String text) {
        this.clearText = text;
        this.base64EncodedText = new String(LevelDataEncryption.encodeBase64(text), StandardCharsets.UTF_8);
    }

    @NotNull
    public String clearText() {
        return clearText;
    }

}
