package LowLevelDesign.GoogleSheet;


import java.sql.Timestamp;
enum UserRole{
    ADMIN,
    NORMAL,
}
class User{
    String name;
    String email;
    UserRole role;

}
class Sheet{
    String sheetId;
    int rowId;
    int prevRowId;
    String rowJson;
}
class RowJsonData{
   int prevColId;
   int colId;
   String colData;
   String dataType;
   Timestamp lastEditedTime;
   User lastEditedBy;
}

public class Main {
    public static void main(String[] args) {

    }
}
