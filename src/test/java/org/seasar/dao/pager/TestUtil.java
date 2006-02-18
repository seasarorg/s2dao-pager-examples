package org.seasar.dao.pager;

import org.seasar.dao.pager.PagerResultSetFactoryWrapper;

/**
 * @author agata
 */
public class TestUtil {

    public static void setUseAbsolute(boolean useAbsolute) {
        PagerResultSetFactoryWrapper.useScrollCursor = useAbsolute;
    }

}
