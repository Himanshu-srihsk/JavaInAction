package LowLevelDesign.MysqlDbExample;
public class Column {
    String name;
    DataType type;
    boolean isPrimaryKey;
    boolean isUnique;

    public Column(String name, DataType type, boolean isPrimaryKey, boolean isUnique) {
        this.name = name;
        this.type = type;
        this.isPrimaryKey = isPrimaryKey;
        this.isUnique = isUnique;
    }
}
