package ui;
/**
 * Column interface serves as a blueprint for creating columns in TableUI and InfoUI.
 * @param <T> The type of Data Transfer Object (DTO) that this column will handle.
 */
public interface Column<T> {

    /**
     * Gets the name of the column.
     * @return The name of the column as a string.
     */
    String getColumnName();
    
    /**
     * Retrieves the value from a given DTO that this column will display.
     * @param dataTransferObject The DTO to extract the value from.
     * @return The value to display in this column.
     */
    String getValue(T dataTransferObject);
}
