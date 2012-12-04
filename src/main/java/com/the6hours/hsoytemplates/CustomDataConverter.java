package com.the6hours.hsoytemplates;

/**
 * @author Igor Artamonov (http://igorartamonov.com)
 * @since 04.12.12
 */
public interface CustomDataConverter {

    /**
     * Convert data to data-type supported by Soy, or return null
     *
     * @param input a data to convert
     * @param current current data converter instance
     * @return supported type or null if doesn't support this data type
     */
    public Object tryConvert(Object input, DataConverter current);

}
