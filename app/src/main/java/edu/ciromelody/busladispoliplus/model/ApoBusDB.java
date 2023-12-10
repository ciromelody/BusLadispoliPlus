package edu.ciromelody.busladispoliplus.model;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Final class for ApoBusDB metadata
 */
public final class ApoBusDB {

  /**
   * The name of the ApoBusDB
   */
  public static final String DB_NAME = "ApoBusDB";

  /**
   * The current version of the DB
   */
  public static final int DB_VERSION = 1;

  /**
   * The Authority for the ContentProvider
   */
  public static final String AUTHORITY = " edu.ciromelody.busladispoliplus";

  /**
   * The class with the metadata for the BusStop table
   */
  public static final class BusStop implements BaseColumns {

    /**
     * The Path for this kind of resource
     */
    public static final String PATH = "bus_stop";

    /**
     * The Uri for this resources
     */
    public static final Uri CONTENT_URI = Uri.parse(ContentResolver.SCHEME_CONTENT + "://"
            + AUTHORITY + "/" + PATH);

    /**
     * The mime type for the dir
     */
    public static final String MIME_TYPE_DIR = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd." + PATH;

    /**
     * The mime type for the single item
     */
    public static final String MIME_TYPE_ITEM = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd." + PATH;

    /**
     * The name of the table for the data
     */
    public static final String TABLE_NAME = "BUS_STOP";

    /**
     * The Id for the BusStop (String)
     */
    public static final String STOP_ID = "stopId";

    /**
     * The name of the BusStop (String)
     */
    public static final String NAME = "name";

    /**
     * The direction of the BusStop (String)
     */
    public static final String DIRECTION = "direction";

    /**
     * The indicator of the BusStop (String)
     */
    public static final String INDICATOR = "indicator";

    /**
     * The latitude of the BusStop (float)
     */
    public static final String LATITUDE = "latitude";

    /**
     * The longitude of the BusStop (float)
     */
    public static final String LONGITUDE = "longitude";

  }

}
