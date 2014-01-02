package androidstudio.pl.serwisinformacyjny;

import androidstudio.pl.serwisinformacyjny.tasks.Cache;

public class RetainCache {
    private static RetainCache sSingleton;
    public Cache mRetainedCache;

    public static RetainCache getOrCreateRetainableCache() {
        if (sSingleton == null) {
            sSingleton = new RetainCache();
        }
        return sSingleton;
    }

}