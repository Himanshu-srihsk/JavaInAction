package Reflection_and_Annotations.AnnotationbasedSQLQueryBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

enum RelationshipType {
    COLUMN, ONE_TO_ONE, ONE_TO_MANY, MANY_TO_MANY, NONE
}

public class SqlQueryBinderUtil {

    public static <T> void SelectQueryBuilder(Class<T> clazz) {
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("@Table Annotation must be present in the class");
        }
        Table table = clazz.getAnnotation(Table.class);
        String tableName = table.name();
        String tableAlias = "u";  // Main table alias

        List<String> columnNames = new ArrayList<>();
        List<String> joinColumnNames = new ArrayList<>();
        processFields(clazz, tableAlias, columnNames, joinColumnNames);

        System.out.println("SELECT " + String.join(", ", columnNames) + " FROM " + tableName +
                " " + tableAlias + " " + String.join(" ", joinColumnNames) + ";");
    }

    private static void processFields(Class<?> clazz, String tableAlias, List<String> columnNames, List<String> joinColumnNames) {
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            RelationshipType relationshipType = getRelationshipType(field);
            handleField(relationshipType, field, columnNames, joinColumnNames, tableAlias);
        }
    }

    public static RelationshipType getRelationshipType(Field field) {
        if (field.isAnnotationPresent(Column.class)) {
            return RelationshipType.COLUMN;
        } else if (field.isAnnotationPresent(OneToOne.class)) {
            return RelationshipType.ONE_TO_ONE;
        } else if (field.isAnnotationPresent(OneToMany.class)) {
            return RelationshipType.ONE_TO_MANY;
        } else if (field.isAnnotationPresent(ManyToMany.class)) {
            return RelationshipType.MANY_TO_MANY;
        }
        return RelationshipType.NONE;
    }

    public static void handleField(RelationshipType relationshipType, Field field, List<String> columnNames, List<String> joinColumnNames, String tableAlias) {
        switch (relationshipType) {
            case COLUMN:
                Column column = field.getAnnotation(Column.class);
                columnNames.add(tableAlias + "." + column.name() + " AS " + tableAlias + "_" + column.name());
                break;
            case ONE_TO_ONE:
                handleOneToOne(field, tableAlias, columnNames, joinColumnNames);
                break;
            case ONE_TO_MANY:
                handleOneToMany(field, tableAlias, columnNames, joinColumnNames);
                break;
            case MANY_TO_MANY:
                handleManyToMany(field, tableAlias, columnNames, joinColumnNames);
                break;
            default:
                break;
        }
    }

    private static void handleOneToOne(Field field, String mainTableAlias, List<String> columns, List<String> joins) {
        OneToOne oneToOne = field.getAnnotation(OneToOne.class);
        String joinTable = field.getType().getAnnotation(Table.class).name();
        String joinAlias = field.getName();  // Alias for the joined table

        if (joinTable.equals(mainTableAlias)) {
            throw new IllegalArgumentException("Join table cannot be the same as the main table");
        }

        processFields(field.getType(), joinAlias, columns, joins);

        joins.add("LEFT JOIN " + joinTable + " " + joinAlias + " ON " + mainTableAlias + "." + oneToOne.joinColumn()
                + " = " + joinAlias + "." + oneToOne.referencedColumn());

        processFields(field.getType(), joinAlias, columns, joins);
    }

    private static void handleOneToMany(Field field, String mainTableAlias, List<String> columns, List<String> joins) {
        OneToMany oneToMany = field.getAnnotation(OneToMany.class);

        Class<?> targetEntity = field.getType().getComponentType() == null ? (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0] : field.getType().getComponentType();

        String joinTable = targetEntity.getAnnotation(Table.class).name();
        String joinAlias = field.getName();

        processFields(targetEntity, joinAlias, columns, joins);


        joins.add("LEFT JOIN " + joinTable + " " + joinAlias + " ON " + mainTableAlias + ".id = " + joinAlias + "." + oneToMany.mappedBy());

        processFields(targetEntity, joinAlias, columns, joins);
    }

    private static void handleManyToMany(Field field, String mainTableAlias, List<String> columns, List<String> joins) {
        ManyToMany manyToMany = field.getAnnotation(ManyToMany.class);
        String joinTable = manyToMany.joinTable();
        String joinAlias = field.getName();  // Alias for the joined table

        // get the target entity
        Class<?> targetEntity = null;
        if (field.getGenericType() instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
            targetEntity = (Class<?>) parameterizedType.getActualTypeArguments()[0];
        }

        if (targetEntity == null) {
            throw new IllegalArgumentException("Could not determine the target entity for ManyToMany relationship.");
        }

        String targetTable = targetEntity.getAnnotation(Table.class).name();
        String inverseJoinAlias = targetEntity.getSimpleName().toLowerCase();


        for (Field joinField : targetEntity.getDeclaredFields()) {
            if (joinField.isAnnotationPresent(Column.class)) {
                Column joinColumn = joinField.getAnnotation(Column.class);
                columns.add(inverseJoinAlias + "." + joinColumn.name() + " AS " + inverseJoinAlias + "_" + joinColumn.name());
            }
        }

        // Adding the JOIN clauses for ManyToMany relationship
        joins.add("JOIN " + joinTable + " " + joinAlias + " ON " + mainTableAlias + ".id = " + joinAlias + "." + manyToMany.joinColumn());
        joins.add("JOIN " + targetTable + " " + inverseJoinAlias + " ON " + joinAlias + "." + manyToMany.inverseJoinColumn() + " = " + inverseJoinAlias + ".id");


        processFields(targetEntity, inverseJoinAlias, columns, joins);
    }

    public static <T> void InsertQueryBuilder(Object obj) {
       // toDo
    }

    public static <T> void DeleteQueryBuilder(Object obj) {
        /// toDo
    }

    public static <T> void UpdateQueryBuilder(Object obj) {
        // toDo
    }
    public static <T> void CreateQueryBuilder(Object obj) {
        // toDo
    }
}
