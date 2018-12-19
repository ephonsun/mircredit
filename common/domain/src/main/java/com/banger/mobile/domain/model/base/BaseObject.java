package com.banger.mobile.domain.model.base;

import java.io.Serializable;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Base class for Model objects. Child objects should implement toString(),
 * equals() and hashCode().
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public abstract class BaseObject implements Serializable {

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1621418163, 1780544121).appendSuper(super.hashCode())
            .toHashCode();
    }

    /**
     * Returns a multi-line String with key=value pairs.
     * @return a String representation of this class.
     */
//     public abstract String toString();
    /**
     * Compares object equality. When using Hibernate, the primary key should
     * not be a part of this comparison.
     * @param o object to compare to
     * @return true/false based on equality tests
     */
//      public abstract boolean equals(Object o);
    /**
     * When you override equals, you should override hashCode. See "Why are
     * equals() and hashCode() importation" for more information:
     * http://www.hibernate.org/109.html
     * @return hashCode
     */
//      public abstract int hashCode();
    private int startRow;
    private int endRow;

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

}
