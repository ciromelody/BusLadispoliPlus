package edu.ciromelody.busladispoliplus.model;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;


import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;



/**
 * This is the class that contains the information related to the BusStop
 */
public final class BusStop implements Parcelable /* Serializable */ {

  /**
   * Constant that identify an existing data
   */
  private static final byte PRESENT = 1;

  /**
   * Constant that identify a NON existing data
   */
  private static final byte NOT_PRESENT = 0;

  /**
   * Implementation of a CREATOR for the creation of the instance
   */
  public static final Creator<BusStop> CREATOR = new Creator<BusStop>() {
    public BusStop createFromParcel(Parcel in) {
      return new BusStop(in);
    }

    public BusStop[] newArray(int size) {
      return new BusStop[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(name);
    if (direction != null) {
      dest.writeByte(PRESENT);
      dest.writeString(direction);
    } else {
      dest.writeByte(NOT_PRESENT);
    }
    if (latitude != 0.0f) {
      dest.writeByte(PRESENT);
      dest.writeFloat(latitude);
      dest.writeFloat(longitude);
    } else {
      dest.writeByte(NOT_PRESENT);
    }
  }

  /**
   * Keys for the properties
   */
  public interface Keys {
    String ID = "id";
    String NAME = "name";
    String DIRECTION = "direction";
    String LATITUDE = "latitude";
    String LONGITUDE = "longitude";
    String INDICATOR = "indicator";
    String LOCATION = "location";
  }

  public final String id;

  public final String name;

  public final String direction;

  public final float latitude;

  public final float longitude;

  public final String indicator;


  private BusStop(final String id, final String name, final String direction,
                  final float latitude, final float longitude, final String indicator) {
    this.id = id;
    this.name = name;
    this.direction = direction;
    this.latitude = latitude;
    this.longitude = longitude;
    this.indicator = indicator;
  }

  private BusStop(Parcel in) {
    id = in.readString();
    name = in.readString();
    boolean present = in.readByte() == PRESENT;
    if (present) {
      direction = in.readString();
    } else {
      direction = null;
    }
    present = in.readByte() == PRESENT;
    if (present) {
      latitude = in.readFloat();
      longitude = in.readFloat();
    } else {
      latitude = 0.0f;
      longitude = 0.0f;
    }
    present = in.readByte() == PRESENT;
    if (present) {
      indicator = in.readString();
    } else {
      indicator = null;
    }
  }

  /**
   * Copy BusStop data into the given Intent
   *
   * @param intent The Intent for the BusStop data
   */
  public void toIntent(final Intent intent) {
    intent.putExtra(Keys.ID, id);
    intent.putExtra(Keys.NAME, name);
    intent.putExtra(Keys.DIRECTION, direction);
    intent.putExtra(Keys.LATITUDE, latitude);
    intent.putExtra(Keys.LONGITUDE, longitude);
    intent.putExtra(Keys.INDICATOR, indicator);
  }

  @Override
  public String toString() {
    final StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("id:" + id)
            .append(" name:" + name)
            .append(" direction:" + direction)
            .append(" indicator:" + indicator)
            .append(" @[").append(latitude).append(",").append(longitude).append("]");
    return stringBuilder.toString();
  }

  /**
   * Builder Pattern implementation for the BuStop class
   */
  public static class Builder {

    private String mId;

    private String mName;

    private String mDirection;

    private float mLatitude;

    private float mLongitude;

    private String mIndicator;

    /**
     * Creates a Builder for the BusStop class
     *
     * @param id   The id for the BusStop
     * @param name The stopId of the BusStop
     */
    private Builder(final String id, final String name) {
      this.mId = id;
      this.mName = name;
    }

    /**
     * Static Factory method for the Builder of the BusStop object
     *
     * @param id   The id for the BusStop
     * @param name The stopId of the BusStop
     * @return The Builder for the BusStop class
     */
    public static Builder create(final String id, final String name) {
      return new Builder(id, name);
    }

    /**
     * Set the optional direction for the BusStop
     *
     * @param direction The direction for the BusStop
     * @return The BusStop itself
     */
    public Builder withDirection(final String direction) {
      this.mDirection = direction;
      return this;
    }

    /**
     * Set the optional indicator for the BusStop
     *
     * @param indicator The indicator for the BusStop
     * @return The BusStop itself
     */
    public Builder withIndicator(final String indicator) {
      this.mIndicator = indicator;
      return this;
    }

    /**
     * Set the optional location for the BusStop
     *
     * @param latitude  The route for the BusStop
     * @param longitude The longitude for the BusStop
     * @return The BusStop itself
     */
    public Builder withLocation(final float latitude, final float longitude) {
      this.mLatitude = latitude;
      this.mLongitude = longitude;
      return this;
    }

    /**
     * @return The BusStop with the given data
     */
    public BusStop build() {
      return new BusStop(mId, mName, mDirection, mLatitude, mLongitude, mIndicator);
    }

  }

  /**
   * @return The JSON representation of the current BusStop
   * @throws JSONException In case of creation error
   */
  public JSONObject toJson() throws JSONException {
    final JSONObject jsonObject = new JSONObject();
    jsonObject.put(Keys.ID, id);
    jsonObject.put(Keys.NAME, name);
    jsonObject.put(Keys.DIRECTION, direction);
    jsonObject.put(Keys.INDICATOR, indicator);
    if (latitude != 0.0f && longitude != 0.0f) {
      JSONObject location = new JSONObject();
      location.put(Keys.LATITUDE, latitude);
      location.put(Keys.LONGITUDE, longitude);
      jsonObject.put(Keys.LOCATION, location);
    }
    return jsonObject;
  }

  /**
   * @return The data for this BusStop into a ContentValue object
   */
  public ContentValues toContentValues() {
    final ContentValues values = new ContentValues();
    values.put(ApoBusDB.BusStop.STOP_ID, id);
    values.put(ApoBusDB.BusStop.NAME, name);
    values.put(ApoBusDB.BusStop.DIRECTION, direction);
    values.put(ApoBusDB.BusStop.INDICATOR, indicator);
    values.put(ApoBusDB.BusStop.LATITUDE, latitude);
    values.put(ApoBusDB.BusStop.LONGITUDE, longitude);
    return values;
  }

  /**
   * Utility method that creates a BusStop from data into an Intent
   *
   * @param inputIntent The Intent with the data
   * @return The created BusStop
   */
  public static BusStop fromIntent(final Intent inputIntent) {
    final String id = inputIntent.getStringExtra(Keys.ID);
    final String name = inputIntent.getStringExtra(Keys.NAME);
    final String direction = inputIntent.getStringExtra(Keys.DIRECTION);
    final float latitude = inputIntent.getFloatExtra(Keys.LATITUDE, 0.0f);
    final float longitude = inputIntent.getFloatExtra(Keys.LONGITUDE, 0.0f);
    final String indicator = inputIntent.getStringExtra(Keys.INDICATOR);
    final BusStop busStop = Builder.create(id, name)
                                    .withDirection(direction)
                                    .withLocation(latitude, longitude)
                                    .withIndicator(indicator)
                                    .build();
    return busStop;
  }

  /**
   * We use this method to create the BusStop from the JSON representation
   *
   * @param jsonObject The JSONObject with the BusStop data
   * @return The BusStop with the data from the JSONObject
   * @throws JSONException In case of parsing error
   */
  public static BusStop fromJson(final JSONObject jsonObject) throws JSONException {
    final String id = jsonObject.getString(Keys.ID);
    final String name = jsonObject.getString(Keys.NAME);
    final String direction = jsonObject.optString(Keys.DIRECTION);
    final String indicator = jsonObject.optString(Keys.INDICATOR);
    final JSONObject location = jsonObject.optJSONObject(Keys.LOCATION);
    final float latitude;
    final float longitude;
    if (location != null) {
      latitude = (float) location.getDouble(Keys.LATITUDE);
      longitude = (float) location.getDouble(Keys.LONGITUDE);
    } else {
      latitude = 0.0f;
      longitude = 0.0f;
    }
    final BusStop busStop = Builder.create(id, name)
                                    .withDirection(direction)
                                    .withLocation(latitude, longitude)
                                    .withIndicator(indicator)
                                    .build();
    return busStop;
  }



  /**
   * Utility method that returns a BusStop from a given cursor
   *
   * @param cursor The Cursor for the data
   * @return The BusStop if any
   */
  public static BusStop fromCursor(@NonNull final Cursor cursor) {

    String stopId = cursor.getString(cursor.getColumnIndex(ApoBusDB.BusStop.STOP_ID));
    String name = cursor.getString(cursor.getColumnIndex(ApoBusDB.BusStop.NAME));
    String direction = cursor.getString(cursor.getColumnIndex(ApoBusDB.BusStop.DIRECTION));
    String indicator = cursor.getString(cursor.getColumnIndex(ApoBusDB.BusStop.INDICATOR));
    float latitude = cursor.getFloat(cursor.getColumnIndex(ApoBusDB.BusStop.LATITUDE));
    float longitude = cursor.getFloat(cursor.getColumnIndex(ApoBusDB.BusStop.LONGITUDE));
    return Builder.create(stopId, name)
                   .withDirection(direction)
                   .withIndicator(indicator)
                   .withLocation(latitude, longitude)
                   .build();
  }

}
