package com.quran.android.quranandroidlearning.dao;

import java.util.Collections;
import java.util.List;

/**
 * Created by HP_Spectre on 7/28/2016.
 */
public class Bookmark {
    public final List<Long>tags;

    public final long id;
    public final Integer sura;
    public final Integer ayah;
    public final int page;
    public final long timestamp;

    public Bookmark(List<Long> tags, long id, Integer sura, Integer ayah, int page, long timestamp) {
        this.tags = Collections.unmodifiableList(tags);
        this.id = id;
        this.sura = sura;
        this.ayah = ayah;
        this.page = page;
        this.timestamp = timestamp;
    }

    public boolean isPageBookmark() {
        return sura==null && ayah==null;
    }
}
