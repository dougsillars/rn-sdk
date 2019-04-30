package com.fidelreactlibrary.adapters;

import android.graphics.Bitmap;

import com.facebook.react.bridge.ReadableMap;
import com.fidel.sdk.Fidel;
import com.fidelreactlibrary.adapters.abstraction.DataOutput;
import com.fidelreactlibrary.adapters.abstraction.DataProcessor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class FidelOptionsAdapter implements DataProcessor<ReadableMap>, DataOutput<Bitmap> {

    public static final String BANNER_IMAGE_KEY = "bannerImage";
    public static final String AUTO_SCAN_KEY = "autoScan";
    public static final String COMPANY_NAME_KEY = "companyName";
    public static final String DELETE_INSTRUCTIONS_KEY = "deleteInstructions";
    public static final String PRIVACY_URL_KEY = "privacyUrl";
    public static final List<String> OPTION_KEYS = Collections.unmodifiableList(
            Arrays.asList(
                    BANNER_IMAGE_KEY,
                    AUTO_SCAN_KEY,
                    COMPANY_NAME_KEY,
                    DELETE_INSTRUCTIONS_KEY,
                    PRIVACY_URL_KEY
            ));

    private DataProcessor<ReadableMap> imageAdapter;
    public FidelOptionsAdapter(DataProcessor<ReadableMap> imageAdapter) {
        this.imageAdapter = imageAdapter;
    }

    @Override
    public void process(ReadableMap data) {
        if (valueIsValidFor(data, BANNER_IMAGE_KEY)) {
            imageAdapter.process(data.getMap(BANNER_IMAGE_KEY));
        }
        if (valueIsValidFor(data, AUTO_SCAN_KEY)) {
            Fidel.autoScan = data.getBoolean(AUTO_SCAN_KEY);
        }
        if (valueIsValidFor(data, COMPANY_NAME_KEY)) {
            Fidel.companyName = data.getString(COMPANY_NAME_KEY);
        }
        if (valueIsValidFor(data, DELETE_INSTRUCTIONS_KEY)) {
            Fidel.deleteInstructions = data.getString(DELETE_INSTRUCTIONS_KEY);
        }
        if (valueIsValidFor(data, PRIVACY_URL_KEY)) {
            Fidel.privacyURL = data.getString(PRIVACY_URL_KEY);
        }
    }

    private boolean valueIsValidFor(ReadableMap map, String key) {
        return (map.hasKey(key) && !map.isNull(key));
    }

    @Override
    public void output(Bitmap data) {
        Fidel.bannerImage = data;
    }
}
