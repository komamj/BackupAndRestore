package com.koma.vcalendarlibrary.database;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.koma.vcalendarlibrary.SingleComponentContentValues;
import com.koma.vcalendarlibrary.component.Component;
import com.koma.vcalendarlibrary.utils.LogUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * It is used to insert parsed components from vcs or ics file. Normally
 * iCalendar components are stored in different databases, so every component
 * has its own implementation.
 */
public abstract class ComponentInsertHelper {
    private static final String TAG = "ComponentInsertHelper";

    protected ComponentInsertHelper() {

    }

    /**
     * Create a concrete insert helper for one iCalendar component.
     *
     * @param context the Context
     * @param sccv    content values will be inserted into database
     * @return a helper if succeed, null otherwise
     */
    static ComponentInsertHelper buildInsertHelper(Context context,
                                                   SingleComponentContentValues sccv) {
        LogUtil.i(TAG, "buildInsertHelper(): component type: "
                + sccv.componentType);

        if (sccv.componentType.equals(Component.VEVENT)) {
            return new VEventInsertHelper(context);
        }

        return null;
    }

    /**
     * Insert one component into database.
     *
     * @param sccv information of the component
     * @return a URI if succeed, null otherwise
     */
    abstract Uri insertContentValues(SingleComponentContentValues sccv);

    /**
     * Insert more than one content values into database
     *
     * @param multiContentValues is a List content the information of the component
     * @return a URI if succeed, null otherwise
     */
    abstract Uri insertMultiComponentContentValues(
            List<SingleComponentContentValues> multiContentValues);

    protected void buildMemberCVList(LinkedList<ContentValues> list,
                                     String eventId, String columnName) {
        for (ContentValues values : list) {
            values.put(columnName, eventId);
        }
    }
}
